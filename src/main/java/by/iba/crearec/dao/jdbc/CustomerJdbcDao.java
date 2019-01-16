package by.iba.crearec.dao.jdbc;

import by.iba.crearec.dao.CustomerDao;
import by.iba.crearec.dao.helper.JdbcDaoSupport;
import by.iba.crearec.dao.helper.RowMapper;
import by.iba.crearec.dao.helper.RowMapperImpl;
import by.iba.crearec.dao.helper.SqlBuilder;
import by.iba.crearec.model.Customer;

import java.util.List;

public class CustomerJdbcDao extends JdbcDaoSupport implements CustomerDao {

	private RowMapper<Customer> rowMap = new RowMapperImpl<>();
	private SqlBuilder builder = new SqlBuilder();

	@Override
	public List<Customer> findAll() {
		return selectList(builder.getSelectSQL(Customer.class), rowMap, null);
	}

	@Override
	public void create(final Customer obj) {
		create(builder.getInsertSQL(obj), obj);
	}

	@Override
	public Customer read(final Long id) {
		return selectOne("", rowMap, id);
	}

	@Override
	public void update(final Customer obj) {
	}

	@Override
	public void delete(final Long id) {
		delete(builder.getDeleteSQL(Customer.class), id);
	}
}
