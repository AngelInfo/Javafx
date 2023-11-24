package com.example.javafx;

import java.util.Objects;

//Creo la clase padre persona.
public class Persona {
	
	//Añado los atributos.
	private String nombre;
	private String DNI;
	private int tlf;
	private int edad;
	
	//Creo el constructor.
	public Persona(String nombre, String dNI, int tlf, int edad) {
		super();
		this.nombre = nombre;
		this.DNI = dNI;
		this.tlf = tlf;
		this.edad = edad;
	}
	
	
	//Getters y setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public int getTlf() {
		return tlf;
	}
	public void setTlf(int tlf) {
		this.tlf = tlf;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
	//Genero un equals que servirá para comparar alumnos.
	@Override
	public int hashCode() {
		return Objects.hash(DNI, edad, nombre, tlf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(DNI, other.DNI) && edad == other.edad && Objects.equals(nombre, other.nombre)
				&& tlf == other.tlf;
	}


	//To string para mostrar la información  de la persona.
	public String toString() {
		return "Persona [nombre=" + nombre + ", DNI=" + DNI + ", tlf=" + tlf + ", edad=" + edad + "]";
	}

	
	
	
}
