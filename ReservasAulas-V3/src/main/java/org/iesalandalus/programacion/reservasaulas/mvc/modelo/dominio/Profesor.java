package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;
import java.io.Serializable;


public class Profesor implements Serializable {
    private static final String ER_TELEFONO = "[6-9][0-9]{8}";
    private static final String ER_CORREO = "([a-zA-z0-9.-_]{1,})(\\@[a-zA-z]{1,})(\\.[a-z]{1,3})";
    private String nombre;
    private String correo;
    private String telefono;
    
    public Profesor(String nombre, String correo) {
        setNombre (nombre);
        setCorreo (correo);
    }
    
    public Profesor(String nombre, String correo, String telefono) {
        setNombre(nombre);
        setCorreo(correo);
        setTelefono(telefono);
    }
    //Constructor Copia
    public Profesor (Profesor profesor) {
        if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		} else {
		setNombre(profesor.getNombre());
		setTelefono(profesor.getTelefono());
		setCorreo(profesor.getCorreo());
		}
    }
    
    //Set y Get de los atributos. El set usa el formateaNombre
    private void setNombre(String nombre) {        
        if(nombre==null){       
            throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
        }
        else if(nombre.trim() .equals("")){        
            throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");       
        } else {
            this.nombre = formateaNombre(nombre);
        }
    }
    
 
  //Método formateaNombre
	private String formateaNombre(String nombre) {
		nombre = nombre.replaceAll("\\s{2,}", " ").trim();
		String [] palabras = nombre.split(" ");
		String nombre2 = "";
		for (int i=0; i<=palabras.length-1; i++) {
			palabras[i] = palabras[i].substring(0,1).toUpperCase() + palabras[i].substring(1).toLowerCase();
			nombre2 = nombre2 + palabras[i] + " ";
		}
		nombre = nombre2.trim();
		return nombre;
	}


    
    //Set de Correo
    public void setCorreo(String correo) {       
        if(correo==null){
            throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");      
        }
        else if(correo.trim().equals("") || !correo.matches(ER_CORREO)){        
            throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");         
        } else {
            this.correo=correo; 
        }
    }
    
    //Set de Telefono
	public void setTelefono(String telefono){
		if (telefono == null) {
			this.telefono = telefono;
		} 
		else if (telefono.trim().equals("") || !telefono.matches(ER_TELEFONO)) {
		      throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
		    } else {
		    this.telefono = telefono;
		    }
		  }

    public String getNombre() {
        return nombre;

    }

    
    public String getCorreo() {
        return correo;
    }



    public String getTelefono() {
        return telefono;
    }
    
    //Método getProfesorFicticio
	public static Profesor getProfesorFicticio(String correo) {
		Profesor profesor = new Profesor("Profesor", correo);
		return new Profesor(profesor);
	}
	@Override
	public int hashCode() {
		return Objects.hash(correo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(correo, other.correo);
	}

	// Método toString que muestra el teléfono, el nombre y el correo del profesor.
	@Override
	public String toString() {
		String ponerTelefono = (telefono == null) ? "" : ", teléfono=" + telefono; 
		return "nombre=" + nombre + ", correo=" + correo + ponerTelefono;
	}
}