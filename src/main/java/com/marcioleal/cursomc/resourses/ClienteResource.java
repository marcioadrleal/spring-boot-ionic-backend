package com.marcioleal.cursomc.resourses;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcioleal.cursomc.domain.Cliente;
import com.marcioleal.cursomc.dto.ClienteDTO;
import com.marcioleal.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
  @Autowired	
  private ClienteService clienteService;	

  @RequestMapping(value="/{id}" , method=RequestMethod.GET)	
  public ResponseEntity<Cliente> find(@PathVariable Integer id){
	Cliente obj = clienteService.find(id);
	return ResponseEntity.ok().body(obj);
			
  }
  
  @RequestMapping(method=RequestMethod.POST)
  public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDto ){
	Cliente obj = clienteService.fromDTO(objDto);
	obj = clienteService.insert(obj);  
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
	return ResponseEntity.created(uri).build();
  }
  
  @RequestMapping(value="/{id}" , method=RequestMethod.PUT)
  public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO , @PathVariable Integer id){
	Cliente obj = clienteService.fromDTO(objDTO);
	obj.setId(id);  
	obj = clienteService.update(obj);  
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(obj.getId()).toUri();
	return ResponseEntity.created(uri).build();		
  }
  
  @RequestMapping(value="/{id}" , method=RequestMethod.DELETE)
  public ResponseEntity<Void> delete( @PathVariable Integer id ){
	  clienteService.delete(id);  
	return ResponseEntity.noContent().build();
  }
  
  
  @RequestMapping(method=RequestMethod.GET)	
  public ResponseEntity<List<ClienteDTO>> findAll() {
	List<Cliente> list = clienteService.findAll();  
	List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); 
	return ResponseEntity.ok().body(listDto); 
  }	
  
  
  @RequestMapping(value="/page" , method=RequestMethod.GET)	
  public ResponseEntity<Page<ClienteDTO>> findPage(
	@RequestParam(value="page" , defaultValue="0") Integer page,
	@RequestParam(value="linePerPage" , defaultValue="24") Integer linePerPage ,
	@RequestParam(value="orderBy" , defaultValue="nome") String orderBy ,
	@RequestParam(value="direction" , defaultValue="DESC") String direction ){ 
	  
	Page<Cliente> list = clienteService.findPage(page, linePerPage, orderBy, direction);  
	Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj)); 
	return ResponseEntity.ok().body(listDto); 
  }	

}
