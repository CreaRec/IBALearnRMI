package by.iba.crearec.server;

import by.iba.crearec.command.Command;
import by.iba.crearec.command.ServerCommandManager;
import by.iba.crearec.model.TransferObject;
import lombok.Setter;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCommandManagerImpl implements ServerCommandManager {

	private static final long serialVersionUID = -379232848482999023L;

	private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

	@Setter
	private Map<Class, Command> commands;

	@Override
	public <T extends Command, D extends TransferObject> D execute(final Class<T> clazz, D obj) throws RemoteException, ExecutionException, InterruptedException {
		System.out.println(commands.get(clazz));
		return EXECUTOR_SERVICE.submit(new Worker<D>(commands.get(clazz), obj)).get();
	}
}
