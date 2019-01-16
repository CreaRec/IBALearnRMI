package by.iba.crearec.model;

import by.iba.crearec.annotation.CrearecAliasSql;
import by.iba.crearec.annotation.CrearecNotSql;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Entity implements CustomerTO {
	@CrearecNotSql
	private static final long serialVersionUID = 1270898336029025561L;

	private String ssn;
	@CrearecAliasSql("cust_name")
	private String customerName;
	private String address;
}
