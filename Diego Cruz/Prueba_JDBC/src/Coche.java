import java.sql.Timestamp;

import lombok.Data;

@Data
public class Coche {

	private String matricula;
	private int serie;
	private double precio;
	private Timestamp fabricacion;
	private boolean enStock;

}
