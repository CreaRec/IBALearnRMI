package by.iba.crearec.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer implements CustomerTO {
	private static final long serialVersionUID = 1270898336029025561L;

	private String ssn;
	private String customerName;
	private String address;

	private String errorMessage;

	public Customer(String ssn, String customerName, String address) {
		this.ssn = ssn;
		this.customerName = customerName;
		this.address = address;
	}
}
