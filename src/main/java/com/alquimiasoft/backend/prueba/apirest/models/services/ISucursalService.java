package com.alquimiasoft.backend.prueba.apirest.models.services;

import java.util.List;

import com.alquimiasoft.backend.prueba.apirest.models.entity.Sucursal;

public interface ISucursalService {

	public List<Sucursal> findAll();

	public Sucursal save(Sucursal Sucursal);

	public Sucursal findById(Long id);

	public void delete(Long id);
}
