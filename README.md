


# crudSpringboot
Se generan métodos para realizar CRUD en Spring Boot

## IclienteService

En IClienteService que es nuestr interface creamos los metodos Listar por id, gurdar y delete


`public Cliente findByid(Long id);
	
public Cliente save(Cliente cliente);
	
public void delete(Long id);`


![1](https://user-images.githubusercontent.com/68626555/171968735-286f0fdb-415d-483d-ba2f-284b6a991cb3.png)


Despues en ClienteServiceImpl

implentaremos los metodos que hicimos en IclienteService decoraremos el litar con @Transactional(readOnly = true) 
sabe y delete lo decoraremos  @Transactional

`
package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.models.dao.IclienteDao;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IclienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> finAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findByid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}`

![2](https://user-images.githubusercontent.com/68626555/171968891-1f3f53ac-8063-4140-bb6a-f1f4e53ca1ef.png)

En nuestros metodos buscar por id, guardar y delte traeremos la entidad de clienteDao y traeremos los metodos estos los implementas de el CrudRepository

findById(id)
save(cliente)
deleteById(id)


![3](https://user-images.githubusercontent.com/68626555/171969406-072fd19b-55b6-4fee-aa42-84ff56dc0c3e.png)


##Crear Metodo Show y create en controlador

En ClienteRestController crearemos un metodo que traera un  Cliente show y se decorara con @PathVariable ya que se pasa por Id y se pondra el tipo Long de la entidad y el id y retornaremos el Cliente con  clienteService.findByid(id); que es la interface que ya creamos en IClienteService

`@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable Long id) {
		return clienteService.findByid(id);
	}`
  
  Despues tendremos nuestro create que traera la misma ruta que listar solo que cambiara la anotacion por @PostMapping 
  igual lo retornaremos de nuestra implementación clienteService.save(cliente); y persistira el cliente y como vien en formato JASON se pondra @RequestBody
  
 ` @PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}`
  
  
  
  ![4](https://user-images.githubusercontent.com/68626555/171970103-13ae3a55-1077-477f-af24-60208cc53a3f.png)
  
  Por ultimo vamos a traer la fecha esto lo aremos en la entidad Cliente creando un metodo decorado con @PrePersist y asignando la fecha aun new date
  
  `
  @PrePersist
	public void prePersist() {
		createAt = new Date();
	}
`

por ultimo decoramos con @ResponseStatus(HttpStatus.CREATED) que te dara el codigo a 201 que indica el contenido


![5](https://user-images.githubusercontent.com/68626555/171970341-75f03f8a-3ca1-4920-8ff4-19263ca0fba9.png)

##Escribir update y delete 

Para escribir el update lo aremos en el controlador ya que ya los teniamos declarados en nuestro controlador #ClienteRestController 
para guardar ocupamos el @PostMapping y crearemos un metodo de Tipo cliente que guardara y lo anotaremos con @RequestBody de tipo Cliente cliente
y retornara del clienteService.save(cliente) recordemos que sabe ya fue declarado en nuestra interface

        @Override
	@Transactional
	public Cliente save(Cliente cliente) {
		// El sabe va a retornar la identidad guardada en base de datos que contiene el id y le pasamos el cliente
		return clienteDao.save(cliente);
	}



luego crearemos nuestro delete que lo anotaremos con @DeleteMapping y la url es igual a la de PutMapping, @GetMapping y crearemos un metodo de tipo void que borrara lo que traiga nuestro cleinte por id 

	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteService.delete(id);
	}

![6](https://user-images.githubusercontent.com/68626555/172074694-594f211f-46e6-4965-aac5-bb977313579b.png)

# Cors para tepartir recursos API REST

 `NOTA al momento de generar nuetro ClienteRestController y mandadar llamar ya se POST, PUT, DELETE, GET devemos decorar nuestra clase con @CrossOrigin`
 Ya que tenemos nuestra clase REST configurada con nuestras ligar debemos decorar de la sigueinte anotación @CrossOrigin(origins= {"http://localhost:4200"}) que manda a llamar la ruta de angular por donde entrar nuestros servicos rest
 
 ![2022-07-11 18_21_42-Window](https://user-images.githubusercontent.com/68626555/178374883-71d2e080-55df-4684-9409-b70c1ce2957a.png)

#Implementando Servicio Angular con HttpClient

Papoder comunicar al servidor devemos de importar el import {HttpClientModule} from '@angular/common/http';

 ![0](https://user-images.githubusercontent.com/68626555/178375856-6edfd984-6ada-453a-8b25-cf550a32b4e9.png)
 
 y mandarlo llamar a en el import

 ![2022-07-11 18_33_58-Window](https://user-images.githubusercontent.com/68626555/178375952-29294a3e-c8af-4a54-a00d-a974d22ad1b5.png)

