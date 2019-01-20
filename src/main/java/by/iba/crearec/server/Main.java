package by.iba.crearec.server;

import by.iba.crearec.server.servlet.HelloServlet;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.io.IOException;
import java.rmi.AlreadyBoundException;

public class Main {

	public static void main(String[] args) throws AlreadyBoundException, IOException {
		System.out.println("Starting...");

		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		Initialize initialize = container.select(Initialize.class).get();
		initialize.run();

		System.out.println("666 Running...");
	}
}
