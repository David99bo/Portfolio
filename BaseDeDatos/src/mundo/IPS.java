package mundo;

public class IPS {
	
	private String nomDept;
	private String codDept;
	private String nombre;
	private String nitsNit;
	private String direccion;
	private String email;
	
	public IPS(String nomDept, String codDept, String nombre, String nitsNit, String direccion, String email) {
		super();
		this.nomDept = nomDept;
		this.codDept = codDept;
		this.nombre = nombre;
		this.nitsNit = nitsNit;
		this.direccion = direccion;
		this.email = email;
	}

	public String getNomDept() {
		return nomDept;
	}

	public void setNomDept(String nomDept) {
		this.nomDept = nomDept;
	}

	public String getCodDept() {
		return codDept;
	}

	public void setCodDept(String codDept) {
		this.codDept = codDept;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNitsNit() {
		return nitsNit;
	}

	public void setNitsNit(String nitsNit) {
		this.nitsNit = nitsNit;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	

}
