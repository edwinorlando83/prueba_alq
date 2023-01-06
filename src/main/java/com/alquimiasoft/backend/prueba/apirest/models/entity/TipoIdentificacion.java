package com.alquimiasoft.backend.prueba.apirest.models.entity;

public enum TipoIdentificacion {
	 CEDULA("CEDULA"), RUC("RUC") ;

    private String tipo  ;

    private TipoIdentificacion(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

 
}
