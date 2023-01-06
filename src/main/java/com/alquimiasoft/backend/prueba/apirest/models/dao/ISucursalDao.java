package com.alquimiasoft.backend.prueba.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.alquimiasoft.backend.prueba.apirest.models.entity.Sucursal;

public interface ISucursalDao  extends CrudRepository<Sucursal, Long> {

}
