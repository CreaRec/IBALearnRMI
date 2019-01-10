package by.iba.crearec.command;

import by.iba.crearec.annotation.CrearecBeanState;
import by.iba.crearec.annotation.CrearecStateType;
import by.iba.crearec.model.CustomerTO;
import by.iba.crearec.util.DataBaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;

@CrearecBeanState(CrearecStateType.STATEFUL)
public class AddCustomerCommandImpl extends UnicastRemoteObject implements AddCustomerCommand, Remote {

	private static final long serialVersionUID = -5947057176466325544L;
	private static final Logger LOGGER = LoggerFactory.getLogger(AddCustomerCommandImpl.class);

	public AddCustomerCommandImpl() throws RemoteException {
	}

	public CustomerTO execute(CustomerTO obj) throws RemoteException {
		Connection connection = null;
		try {
			String insertTableSQL = "INSERT INTO customer (ssn, cust_name, address) VALUES (?,?,?)";
			connection = DataBaseUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, obj.getSsn());
			preparedStatement.setString(2, obj.getCustomerName());
			preparedStatement.setString(3, obj.getAddress());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("", e);
		} finally {
			DataBaseUtils.closeConnection(connection);
		}
		return obj;
	}
}
