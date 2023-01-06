package com.alquimiasoft.backend.prueba.apirest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alquimiasoft.backend.prueba.apirest.models.entity.Cliente;
import com.alquimiasoft.backend.prueba.apirest.models.entity.Sucursal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BackendPruebaApirest1Application.class)
@WebAppConfiguration
public class ClienteTest {

	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;
	private String URLAPI = "http://localhost:8080/api/";

 
	@Test
	@Order(1)  
	public void testCrearCliente() throws Exception {

		Cliente cliente = new Cliente();
		cliente.setCli_nombres("ADRIANA AGUILAR");
		cliente.setCli_numero_identificacion("1803751578");
		cliente.setCli_telefono("0983133761");
		cliente.setCli_tipo_identificacion("CEDULA");
		Sucursal sucursal = new Sucursal();

		sucursal.setSuc_ciudad("QUITO");
		sucursal.setSuc_direccion("AV LOS SHYRYS");
		sucursal.setSuc_esprincipal("1");
		sucursal.setSuc_provincia("PICHINCHA");
		ArrayList<Sucursal> lstSUcursales = new ArrayList<>();
		lstSUcursales.add(sucursal);
		cliente.setSucursales(lstSUcursales);

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		String inputJson = mapToJson(cliente);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(URLAPI + "cliente")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		//boolean estadoOk = content.contains("\"estado\":\"OK\"");

		//assertEquals(true, estadoOk);
	}

	@Test
	@Order(4) 
	public void modificarCliente() throws Exception {
		String uri = URLAPI + "/cliente/1";
		Cliente cliente = new Cliente();
		cliente.setCli_nombres("ADRIANA AGUILAR");
		cliente.setCli_numero_identificacion("1803751578");
		cliente.setCli_telefono("0983133761");
		cliente.setCli_tipo_identificacion("CEDULA");
		Sucursal sucursal = new Sucursal();

		sucursal.setSuc_ciudad("QUITO");
		sucursal.setSuc_direccion("AV LOS SHYRYS");
		sucursal.setSuc_esprincipal("1");
		sucursal.setSuc_provincia("PICHINCHA");
		ArrayList<Sucursal> lstSUcursales = new ArrayList<>();
		lstSUcursales.add(sucursal);
		cliente.setSucursales(lstSUcursales);

		String inputJson = mapToJson(cliente);

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		boolean estadoOk = content.contains("\"estado\":\"OK\"");

		//assertEquals(true, estadoOk);
	}

	@Test
	@Order(3) 
	public void eliminarCliente() throws Exception {
		String uri = URLAPI + "/cliente/1000";
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		boolean estadoOk = content.contains("no existe en la base de datos");
		assertEquals(true, estadoOk);
	}
	
	@Test
	@Order(5) 
	public void listadireccionesAdicionales() throws Exception {
		String uri = URLAPI + "/listarsucursales/1";
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		boolean estadoOk = content.contains("\"estado\":\"OK\"");
		assertEquals(true, estadoOk);
	}
	

	@Test
	@Order(2)  
	public void getClienteList() throws Exception {
		String uri = URLAPI+"clientes/1803751578";
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);		 
		String content = mvcResult.getResponse().getContentAsString();
		boolean estadoOk = content.contains("\"estado\":\"OK\"");
		assertEquals(true, estadoOk);
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
}
