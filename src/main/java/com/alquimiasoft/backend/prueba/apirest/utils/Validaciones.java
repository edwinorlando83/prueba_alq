package com.alquimiasoft.backend.prueba.apirest.utils;

import com.alquimiasoft.backend.prueba.apirest.models.entity.TipoIdentificacion;

/**
 * @author Tecnologia
 *
 */
public class Validaciones {
	static Validaciones instance;

	public static Validaciones getInstance() {
		if (instance == null) {
			instance = new Validaciones();
		}
		return instance;
	}

	
	/**
	 * @param tipo
	 * @return TRUE O FALSE
	 */
	public boolean validarCedulaRuc(String tipo, String numero_identificacion) {

		boolean retorno = false;
		if (tipo.equals(TipoIdentificacion.CEDULA.toString())) {
			retorno = numero_identificacion.trim().length() == 10;

		} else {
			retorno = numero_identificacion.trim().length() == 13;
		}

		return retorno;

	}


	public boolean validarTipo(String tipo) {

		boolean retorno = false;

		if (tipo.equals(TipoIdentificacion.CEDULA.toString()) || tipo.equals(TipoIdentificacion.RUC.toString())) {
			retorno = true;
		}

		return retorno;

	}

}
