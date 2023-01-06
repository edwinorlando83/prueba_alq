package com.alquimiasoft.backend.prueba.apirest.cotrollers;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alquimiasoft.backend.prueba.apirest.models.entity.Cliente;
import com.alquimiasoft.backend.prueba.apirest.models.entity.Respuesta;
import com.alquimiasoft.backend.prueba.apirest.models.entity.Sucursal;
import com.alquimiasoft.backend.prueba.apirest.models.entity.TipoIdentificacion;
import com.alquimiasoft.backend.prueba.apirest.models.services.IClienteService;
import com.alquimiasoft.backend.prueba.apirest.utils.Validaciones;

@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
 
	

	/**
	 * Funcionalidad para buscar y obtener un listado de clientes.
	 */
	@GetMapping("/clientes/{numero_identificacion}")
	public Respuesta<Cliente> buscarCliente(@PathVariable String numero_identificacion) {

		Respuesta<Cliente> res = new Respuesta<Cliente>();

		try {
			List<Cliente> clientes = clienteService.findByCli_numero_identificacion(numero_identificacion);		 
			res.setEstado("OK");
			Cliente cli = clientes.get(0);
			res.setDato(cli);
			res.setMensajeError("");
		} catch (Exception ex) {
			res.setEstado("ERROR");
			res.setDatoList(null);
			res.setMensajeError(ex.getMessage());
		}

		return res;

	}

	@GetMapping("/cliente/{id}")
	public Cliente buscarClientePorID(@PathVariable Long id) {
		return clienteService.findById(id);
	}

	/** Funcionalidad para crear un nuevo cliente con la dirección matriz
	 * @param cliente
	 * @return
	 */
	@PostMapping("/cliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Respuesta<Cliente> crearCliente(@RequestBody Cliente cliente) {

		Respuesta<Cliente> res = new Respuesta<Cliente>();

		try {

			if (!Validaciones.getInstance().validarTipo(cliente.getCli_tipo_identificacion())) {
				res.setEstado("ERROR");
				res.setMensajeError("El tipo de identificación debe ser :" + TipoIdentificacion.CEDULA.toString()
						+ " o " + TipoIdentificacion.RUC.toString());
				return res;

			}

			if (!Validaciones.getInstance().validarCedulaRuc(cliente.getCli_tipo_identificacion(),
					cliente.getCli_numero_identificacion())) {
				res.setEstado("ERROR");
				res.setMensajeError("Número de identificación incorrecto: " + cliente.getCli_numero_identificacion());
				return res;
			}

			Cliente cli = clienteService.save(cliente);

			res.setEstado("OK");
			res.setDato(cli);

		} catch (ConstraintViolationException verr) {
			res.setEstado("ERROR");

		} catch (Exception ex) {
			res.setEstado("ERROR");
			res.setMensajeError(
					"El número de identificación: " + cliente.getCli_numero_identificacion() + ", ya existe");
			res.setMensajeError(ex.getMessage());
		}

		return res;

	}

	/** Funcionalidad para editar los datos del client
	 * @param cliente
	 * @param id
	 * @return
	 */
	@PutMapping("/cliente/{id}")
	public Respuesta<Cliente> editarCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
		Respuesta<Cliente> res = new Respuesta<Cliente>();

		try {

			Cliente clienteActual = clienteService.findById(id);
			if (clienteActual == null) {
				res.setEstado("ERROR");
				res.setMensajeError("Error: no se pudo editar, el cliente ID: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				return res;
			}
			if (!Validaciones.getInstance().validarTipo(cliente.getCli_tipo_identificacion())) {
				res.setEstado("ERROR");
				res.setMensajeError("El tipo de identificación debe ser :" + TipoIdentificacion.CEDULA.toString()
						+ " o " + TipoIdentificacion.RUC.toString());
				return res;

			}

			if (!Validaciones.getInstance().validarCedulaRuc(cliente.getCli_tipo_identificacion(),
					cliente.getCli_numero_identificacion())) {
				res.setEstado("ERROR");
				res.setMensajeError("Número de identificación incorrecto: " + cliente.getCli_numero_identificacion());
				return res;
			}

			clienteActual.setCli_nombres(cliente.getCli_nombres());
			clienteActual.setCli_numero_identificacion(cliente.getCli_numero_identificacion());
			clienteActual.setCli_telefono(cliente.getCli_telefono());

			clienteActual.setCli_email(cliente.getCli_email());
			clienteActual.setSucursales(cliente.getSucursales());

			Cliente clienteUpdated = clienteService.save(clienteActual);

			res.setEstado("OK");
			res.setDato(clienteUpdated);

		} catch (ConstraintViolationException verr) {
			res.setEstado("ERROR");

		} catch (Exception ex) {
			res.setEstado("ERROR");
			res.setMensajeError(
					"El número de identificación: " + cliente.getCli_numero_identificacion() + ", ya existe");
			res.setMensajeError(ex.getMessage());
		}

		return res;
	}

	/** Funcionalidad para eliminar un cliente
	 * @param id
	 * @return
	 */
	@DeleteMapping("/cliente/{id}")
	public Respuesta<Cliente> eliminarCliente(@PathVariable Long id) {
		Respuesta<Cliente> res = new Respuesta<Cliente>();
		Cliente clienteActual = clienteService.findById(id);
		if (clienteActual == null) {
			res.setEstado("ERROR");
			res.setMensajeError("El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return res;
		}

		clienteService.delete(id);
		res.setEstado("OK");
		// res.setDato("Se ha eliminado correctamente");
		return res;
	}

	/** Funcionalidad para registrar una nueva dirección por cliente
	 * @param sucursal
	 * @param idCliente
	 * @return
	 */
	@PostMapping("/sucursal/{idCliente}")
	@ResponseStatus(HttpStatus.CREATED)
	public Respuesta<Cliente> agregarSucursal(@RequestBody Sucursal sucursal, @PathVariable Long idCliente) {
		Respuesta<Cliente> res = new Respuesta<Cliente>();
		Cliente clienteActual = clienteService.findById(idCliente);
		if (clienteActual == null) {
			res.setEstado("ERROR");
			res.setMensajeError(
					"El cliente ID: ".concat(idCliente.toString().concat(" no existe en la base de datos!")));
			return res;
		}
		sucursal.setCliente(clienteActual);
		clienteService.addSucursal(idCliente, sucursal);
		res.setEstado("OK");

		res.setDato("Regsitro insertado correctamente");

		return res;
	}
	
	
	 
	
	/** Funcionalidad para listar las direcciones adicionales del cliente
	 * @param cli_id
	 * @return
	 */
	@GetMapping("/listarsucursales/{cli_id}")
	public Respuesta<Sucursal> listarSucursales(@PathVariable Long cli_id) {		
		Respuesta<Sucursal> res = new Respuesta<Sucursal>();
		Cliente cliente = clienteService.findById(cli_id);
		List<Sucursal> sucursales = clienteService.findAllSucursales(cliente);
		res.setEstado("OK");

		res.setDato(sucursales);
		return res;
		 
	}
	
}
