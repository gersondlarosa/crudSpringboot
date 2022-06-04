package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> finAll();
	
	public Cliente findByid(Long id);
	
	//Se crea un cliente que retornara un cliente con el metodo save y recibe el cliente 
	//que se almacena y retornamois el cliente guardado por id
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
	
}
