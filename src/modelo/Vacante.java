package modelo;

import java.util.Date;

public class Vacante {

	private int id;
	private Date fechaPublicacion;
	private String nombre;
	private String descripcion;
	private String detalle;
	
	public Vacante(int id) {
		// Por default, la fecha de publicacion es la fecha del servidor
		this.fechaPublicacion = new Date();
		this.id = id;
	}
	
	public Vacante(int id, Date fechaPublicacion, String nombre, String descripcion, String detalle) {
		super();
		this.id = id;
		this.fechaPublicacion = fechaPublicacion;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.detalle = detalle;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	@Override
	public String toString() {
		return "Vacante{" + "id=" + id + ", fechaPublicacion=" + fechaPublicacion + ", nombre=" + nombre + ", descripcion=" + descripcion + ", detalle=" + detalle + '}';
	}
	

}
