	package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;



public class Consola {

	  private static final DateTimeFormatter FORMATO_DIA= DateTimeFormatter.ofPattern("dd/MM/yyyy");


	    private Consola() {   
	    }
	    
	    //Método mostrarMenu
	    public static void mostrarMenu() {
	            mostrarCabecera("Reservas de aulas");
	            for (Opcion opcion: Opcion.values()) {
	                    System.out.println(opcion);
	            }
	    }

	    //Método mostrarCabecera
	    public static void mostrarCabecera(String mensaje) {
	            System.out.printf("%n%s%n", mensaje);
	            String cadena = "%0" + mensaje.length() + "d%n";
	            System.out.println(String.format(cadena, 0).replace("0", "-"));
	    }

	    //Método elegirOpcion
	    public static int elegirOpcion() {
	            int ordinalOpcion = 0;
	                
	            do {
	                    System.out.print("Escoge una opción: ");
	                    ordinalOpcion = Entrada.entero() - 1;
	            } while (!Opcion.esOrdinalValido(ordinalOpcion));
	         return ordinalOpcion;
	    }

	    //Método leerAula
	    public static Aula leerAula(){
			System.out.println("Introduce el nombre del aula y número puestos de ese aula: ");
	   		Aula aula = new Aula(leerNombreAula(), leerNumeroPuestos());
			return new Aula(aula);
		}
	    
	 // Método leerNumeroPuestos
		public static int leerNumeroPuestos() {
			System.out.println("Introduzca el número de puestos del aula");
			int puestos = Entrada.entero();
			return puestos;
		}

		// Método leerAulaFicticia
		public static Aula leerAulaFicticia() {
			Aula aula = Aula.getAulaFicticia(leerNombreAula());
			return aula;
		}
	    
	    //Método leerNombreAula
		public static String leerNombreAula() {
			System.out.print("Introduzca el nombre del aula: ");
			return Entrada.cadena();
		}
		
	    //Método leerProfesor
	    public static Profesor leerProfesor(){

	        System.out.println("Introduce el correo: ");
	        String correo = Entrada.cadena();
	        
	        System.out.println("Introduce el teléfono: ");
	        String telefono = Entrada.cadena();
	        
	        
			if(telefono == null || telefono == "") {
	        	return new Profesor(leerNombreProfesor(), correo);
	        }else{
	        	return new Profesor(leerNombreProfesor(), correo, telefono);
	        }
	     
	     
	    }
	    
	    //Método leerNombreProfesor
	    public static String leerNombreProfesor(){
	            System.out.println("Introduce nombre del profesor: ");
	            String nombre=Entrada.cadena();       
	        return nombre;
	    }
	    

		public static Profesor leerProfesorFicticio() {
			System.out.println("Introduzca el correo del profesor");
			String correo = Entrada.cadena();
			Profesor profesor = Profesor.getProfesorFicticio(correo);
			return profesor;
		}

	    
	    //Método leerTramo
		public static Tramo leerTramo() {
			System.out.println("Eliga un tramo insertando 1 (Mañana) o 2 (Tarde): ");
			int indice = Entrada.entero();
			switch (indice) {
			case 1:
				return Tramo.MANANA;

			case 2:
				return Tramo.TARDE;

			default:
				return null;
			}
		}
	   
	    //Método leerDia
		public static LocalDate leerDia() {
			boolean fechaCorrecta = false;
			LocalDate fecha = null;
			
			do {
				try {
					System.out.print("Introduzca la fecha (dd/mm/aaaa): ");
					fecha = LocalDate.parse(Entrada.cadena(), FORMATO_DIA);
					fechaCorrecta = true;
				} catch(DateTimeParseException e) {
					System.out.println(e.getMessage());
				}
			} while(!fechaCorrecta);
			
			return fecha;
		}
	    
	 // Método elegirPermanencia
		public static int elegirPermanencia() {
			int permanenciaElegida = 0;
			do {
				System.out.println("Seleccione una permanencia:");
				System.out.println("1- Por tramo (mañana o tarde)");
				System.out.println("2- Por horas");
				permanenciaElegida = Entrada.entero();
			} while (permanenciaElegida < 1 || permanenciaElegida > 2);
			return permanenciaElegida;
		}

		//Método leerPermanencia
		public static Permanencia leerPermanencia() {
			Permanencia permanenciaFinal=null;
			int permanenciaElegida = elegirPermanencia();
			if(permanenciaElegida==1) {
				permanenciaFinal=new PermanenciaPorTramo(leerDia(),leerTramo());
			}
			else {
				permanenciaFinal=new PermanenciaPorHora(leerDia(),leerHora());
			}
			if (permanenciaFinal instanceof PermanenciaPorTramo)
				return new PermanenciaPorTramo((PermanenciaPorTramo) permanenciaFinal);
			else {
				return new PermanenciaPorHora((PermanenciaPorHora) permanenciaFinal);
			}
		}
		
		//Método leerHora
		private static LocalTime leerHora() {
			boolean horaCorrecta = false;
			LocalTime hora = null;
			
			do {
				try {
					System.out.print("Introduzca la hora (8 - 22): ");
					hora = LocalTime.of(Entrada.entero(), 0);
					horaCorrecta = true;
				} catch(DateTimeException e) {
					System.out.println(e.getMessage());
				}
			} while(!horaCorrecta);
			
			return hora;
		}


		//Método leerReserva
		public static Reserva leerReserva() {
			Profesor profesor = leerProfesorFicticio();
			Aula aula = leerAulaFicticia();
			Permanencia permanencia = leerPermanencia();
			return new Reserva(profesor, aula, permanencia);
		}
		
		//Método leerReservaFicticia
		public static Reserva leerReservaFicticia() {
			return Reserva.getReservaFicticia(leerAulaFicticia(), leerPermanencia());

		}
		
	}

