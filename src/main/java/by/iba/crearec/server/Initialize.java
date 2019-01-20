package by.iba.crearec.server;

import by.iba.crearec.annotation.CrearecWebServlet;
import by.iba.crearec.command.AddCustomerCommand;
import by.iba.crearec.command.AddCustomerCommandImpl;
import by.iba.crearec.command.Command;
import by.iba.crearec.command.ServerCommandManager;
import by.iba.crearec.server.servlet.HelloServlet;
import by.iba.crearec.server.servlet.wrapper.HttpHandlerWithServletSupport;
import com.sun.net.httpserver.HttpServer;
import org.reflections.Reflections;

import javax.inject.Inject;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Initialize {

	@Inject
	private HelloServlet helloServlet;

	public void run() throws AlreadyBoundException, IOException {
		HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
		Reflections reflections = new Reflections("by.iba.crearec.server.servlet");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(CrearecWebServlet.class);
		annotated.stream().findFirst().ifPresent(item -> {
//			try {
//				String path = item.getAnnotation(CrearecWebServlet.class).value();
//				System.out.println(path);
//				server.createContext(path, new HttpHandlerWithServletSupport((HttpServlet) item.newInstance()));
				server.createContext("/test", new HttpHandlerWithServletSupport(helloServlet));
//			} catch (InstantiationException | IllegalAccessException e) {
//				e.printStackTrace();
//			}
		});

		server.setExecutor(null);
		server.start();

		Registry registry = LocateRegistry.createRegistry(2005);
		ServerCommandManagerImpl scm = new ServerCommandManagerImpl();

		Map<Class, Command> commands = new HashMap<>();
		commands.put(AddCustomerCommand.class, new AddCustomerCommandImpl());
		scm.setCommands(commands);

		Remote remoteServerCommandManager = UnicastRemoteObject.exportObject(scm, 2005);
		registry.bind(ServerCommandManager.class.getSimpleName(), remoteServerCommandManager);
	}
}
