package by.iba.crearec.server;

import by.iba.crearec.command.AddCustomerCommand;
import by.iba.crearec.command.AddCustomerCommandImpl;
import by.iba.crearec.command.Command;
import by.iba.crearec.command.ServerCommandManager;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		System.out.println("Starting...");
		Registry registry = LocateRegistry.createRegistry(2005);
		ServerCommandManagerImpl scm = new ServerCommandManagerImpl();

		Map<Class, Command> commands = new HashMap<>();
		commands.put(AddCustomerCommand.class, new AddCustomerCommandImpl());
		scm.setCommands(commands);

		Remote remoteServerCommandManager = UnicastRemoteObject.exportObject(scm, 2005);
		registry.bind(ServerCommandManager.class.getSimpleName(), remoteServerCommandManager);
		System.out.println("Running...");
	}
}
