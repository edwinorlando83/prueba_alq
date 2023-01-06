package com.alquimiasoft.backend.prueba.apirest.models.services;

import java.util.List;

import com.alquimiasoft.backend.prueba.apirest.models.entity.Cliente;
import com.alquimiasoft.backend.prueba.apirest.models.entity.Sucursal;

public interface IClienteService {

 

	public Cliente save(Cliente cliente);

	public Cliente findById(Long id);
	public Cliente findByText(Long id);

	public void delete(Long id);

	List<Cliente> findAll(); 
	
	public List<Cliente>  findByCli_numero_identificacion(String cli_numero_identificacion);
	
	public  Sucursal  addSucursal(Long idCliente,  Sucursal  sucursales );
	
 

	List<Sucursal> findAllSucursales(Cliente cliente);
}
