package com.alquimiasoft.backend.prueba.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alquimiasoft.backend.prueba.apirest.models.entity.Cliente;
import com.alquimiasoft.backend.prueba.apirest.models.entity.Sucursal;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

	
	@Query("select c from Cliente c where c.cli_numero_identificacion =?1")
	public List<Cliente> findByCli_numero_identificacion(String cli_numero_identificacion);
	
	@Query("select c from Cliente c where c.cli_nombres like %?1%")
	public List<Cliente> findByCli_nombres(String cli_nombres);
	
	
	@Query("from Sucursal s where s.cliente = ?1 ")
	public List<Sucursal> findAllSucursales(Cliente cliente);
	
}
