package by.iba.crearec.server;

import by.iba.crearec.annotation.CrearecBeanState;
import by.iba.crearec.command.Command;
import by.iba.crearec.command.ServerCommandManager;
import by.iba.crearec.executor.RejectedExecutionHandlerImpl;
import by.iba.crearec.model.TransferObject;
import lombok.Setter;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerCommandManagerImpl implements ServerCommandManager {

	private static final long serialVersionUID = -379232848482999023L;

	private static final ExecutorService EXECUTOR_SERVICE;

	@Setter
	private Map<Class, Command> commands;

	static  {
		RejectedExecutionHandler rejectionHandler = new RejectedExecutionHandlerImpl();
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		EXECUTOR_SERVICE = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), threadFactory, rejectionHandler);

	}

	@Override
	public <T extends Command, D extends TransferObject> D execute(final Class<T> clazz, D obj) throws RemoteException, ExecutionException, InterruptedException {
		Command command = commands.get(clazz);
		Class<? extends Command> implClass = command.getClass();
		switch (implClass.getAnnotation(CrearecBeanState.class).value()) {
			case STATEFUL:
				return (D) command.execute(obj);
			case STATELESS:
			default:
				try {
					return EXECUTOR_SERVICE.submit(new Worker<D>(implClass.newInstance(), obj)).get();
				} catch (InstantiationException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
		}
	}
}
