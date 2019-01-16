package by.iba.crearec.dao;

import by.iba.crearec.model.Customer;

import java.util.List;

public interface CustomerDao extends CrudDao<Long, Customer> {

	List<Customer> findAll();
}
