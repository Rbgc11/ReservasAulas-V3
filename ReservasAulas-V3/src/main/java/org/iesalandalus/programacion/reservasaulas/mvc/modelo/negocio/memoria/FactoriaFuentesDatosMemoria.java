package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IAulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IProfesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IReservas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IFuenteDatos;


public class FactoriaFuentesDatosMemoria implements IFuenteDatos {
	public FactoriaFuentesDatosMemoria() {
	}
	@Override
	public IAulas crearAulas() {
		return new Aulas();
	}

	@Override
	public IProfesores crearProfesores() {
		return new Profesores();
	}

	@Override
	public IReservas crearReservas() {
		return new Reservas();
	}
}