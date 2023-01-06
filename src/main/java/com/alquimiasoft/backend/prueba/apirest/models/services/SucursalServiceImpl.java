package com.alquimiasoft.backend.prueba.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alquimiasoft.backend.prueba.apirest.models.dao.ISucursalDao;
import com.alquimiasoft.backend.prueba.apirest.models.entity.Sucursal;

public class SucursalServiceImpl implements ISucursalService {
	@Autowired
	private ISucursalDao SucursalDao;

	@Override
	@Transactional(readOnly = true)
	public List<Sucursal> findAll() {
		return (List<Sucursal>) SucursalDao.findAll();
	}

	@Override
	@Transactional()
	public Sucursal save(Sucursal Sucursal) {
		return SucursalDao.save(Sucursal);

	}

	@Override
	@Transactional(readOnly = true)
	public Sucursal findById(Long id) {
		return SucursalDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public void delete(Long id) {
		SucursalDao.deleteById(id);

	}
}
