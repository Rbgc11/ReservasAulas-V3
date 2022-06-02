package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IAulas;


public class Aulas implements IAulas{
	
	private List<Aula> coleccionAulas;


		public Aulas() {
			coleccionAulas = new ArrayList<>();
		}

		// Constructor copia
		public Aulas(IAulas aulas) {
			if (aulas == null) {
				throw new NullPointerException("No se pueden copiar unas aulas nulas.");
			} else {
				setAulas(aulas);
			}
		}
		
		// Método setAulas
		private void setAulas(IAulas aulas) {
			if (aulas == null) {
				throw new NullPointerException("No se puede copiar un aula nula.");
			} else {
				this.coleccionAulas = aulas.getAulas();
			}
		}
		// Método getAulas		
		public List<Aula> getAulas() {
			List<Aula> lista = copiaProfundaAulas(coleccionAulas);
			lista.sort(Comparator.comparing(Aula::getNombre));
			return lista;
		}

	    
		// Método copiaProfundaAulas
		private List<Aula> copiaProfundaAulas(List<Aula> listaAulas) {
			List<Aula> copiaAulas = new ArrayList<Aula>();
			Iterator<Aula> iterador = listaAulas.iterator();
			while (iterador.hasNext()) {
				copiaAulas.add(new Aula(iterador.next()));
			}
			return copiaAulas;
		}
		
		// Método getNumAulas
		public int getNumAulas() {
			return coleccionAulas.size();

		}
	    

		// Método insertar
		public void insertar(Aula aula) throws OperationNotSupportedException {
			if (aula == null) {
				throw new NullPointerException(" No se puede insertar un aula nula.");
			}
			
			if(coleccionAulas.contains(aula)) {
				throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
			}
			
			coleccionAulas.add(new Aula(aula));
		}
	   
		// Método buscar
		public Aula buscar(Aula aula) {
			if (aula == null) {
				throw new NullPointerException("No se puede buscar un aula nula.");
			}
			Iterator<Aula> iterador = coleccionAulas.iterator();
			while (iterador.hasNext()) {
				Aula aulaBuscada = iterador.next();
				if (aula.equals(aulaBuscada)) {
					return new Aula(aulaBuscada);
				}
			}
			return null;
		}
		// Método borrar
		public void borrar(Aula aula) throws OperationNotSupportedException {
			if (aula == null) {
				throw new NullPointerException("No se puede borrar un aula nula.");
			} else if (!coleccionAulas.contains(aula))  {
				throw new OperationNotSupportedException("No existe ningún aula con ese nombre.");
			} else {
				coleccionAulas.remove(coleccionAulas.indexOf(aula));
			}
		}
	     
		// Metodo representar
		public List<String> representar() {
			List<String> representacion = new ArrayList<String>();
			Iterator<Aula> iterador = coleccionAulas.iterator();
			while (iterador.hasNext()) {
				representacion.add(iterador.next().toString());
			}
			return representacion;
		}

		@Override
		public void comenzar() {
			
		}

		@Override	
		public void terminar() {
			
		}
	}