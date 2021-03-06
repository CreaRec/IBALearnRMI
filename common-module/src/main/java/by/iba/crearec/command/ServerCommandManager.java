package by.iba.crearec.command;

import by.iba.crearec.command.Command;
import by.iba.crearec.model.TransferObject;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;

public interface ServerCommandManager extends Remote, Serializable {

	<T extends Command, D extends TransferObject> D execute(final Class<T> clazz, D obj) throws RemoteException, ExecutionException, InterruptedException;

}
