package by.iba.crearec.command;

import by.iba.crearec.annotation.CrearecBeanState;
import by.iba.crearec.annotation.CrearecStateType;
import by.iba.crearec.dao.CustomerDao;
import by.iba.crearec.dao.jdbc.CustomerJdbcDao;
import by.iba.crearec.model.Customer;
import by.iba.crearec.model.CustomerTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@CrearecBeanState(CrearecStateType.STATELESS)
public class AddCustomerCommandImpl extends UnicastRemoteObject implements AddCustomerCommand, Remote {

	private static final long serialVersionUID = -5947057176466325544L;
	private static final Logger LOGGER = LoggerFactory.getLogger(AddCustomerCommandImpl.class);

	private CustomerDao customerDao = new CustomerJdbcDao();

	public AddCustomerCommandImpl() throws RemoteException {
	}

	public CustomerTO execute(CustomerTO obj) throws RemoteException {
		customerDao.create(new Customer(obj.getSsn(), obj.getCustomerName(), obj.getAddress()));
		return obj;
	}
}
