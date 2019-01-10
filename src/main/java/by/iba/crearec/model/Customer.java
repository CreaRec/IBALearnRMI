package by.iba.crearec.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements CustomerTO {
	private String ssn;
	private String customerName;
	private String address;
}
