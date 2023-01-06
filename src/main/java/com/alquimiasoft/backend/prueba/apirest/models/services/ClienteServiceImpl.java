package com.alquimiasoft.backend.prueba.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alquimiasoft.backend.prueba.apirest.models.dao.IClienteDao;
import com.alquimiasoft.backend.prueba.apirest.models.dao.ISucursalDao;
import com.alquimiasoft.backend.prueba.apirest.models.entity.Cliente;
import com.alquimiasoft.backend.prueba.apirest.models.entity.Sucursal;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;

	@Autowired
	private ISucursalDao sucursalDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional()
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);

	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public void delete(Long id) {
		clienteDao.deleteById(id);

	}

	@Override
	public Cliente findByText(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> findByCli_numero_identificacion(String cli_numero_identificacion) {

		return clienteDao.findByCli_numero_identificacion(cli_numero_identificacion);
	}

	@Override
	public Sucursal addSucursal(Long idCliente, Sucursal sucursales) {

		return sucursalDao.save(sucursales);
	}

	@Override 
	public List<Sucursal> findAllSucursales(Cliente cliente) {

		return clienteDao.findAllSucursales(cliente);
	}
	
	 

}
