package by.iba.crearec.model;

import by.iba.crearec.annotation.CrearecNotSql;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.rmi.Remote;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Entity implements Remote, Serializable {
	@CrearecNotSql
	private static final long serialVersionUID = 5865358553269068913L;

	@CrearecNotSql
	private Long id;

	@CrearecNotSql
	private String errorMessage;
}
