package com.marcioleal.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcioleal.cursomc.domain.Cidade;
import com.marcioleal.cursomc.domain.Cliente;
import com.marcioleal.cursomc.domain.Endereco;
import com.marcioleal.cursomc.domain.enums.TipoCliente;
import com.marcioleal.cursomc.dto.ClienteDTO;
import com.marcioleal.cursomc.dto.ClienteNewDTO;
import com.marcioleal.cursomc.repositories.CidadeRepository;
import com.marcioleal.cursomc.repositories.ClienteRepository;
import com.marcioleal.cursomc.repositories.EnderecoRepository;

@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private CidadeRepository  cidaderepository;
	
	public Cliente find(Integer id) {
     Cliente obj = clienteRepository.findOne(id);	
	 if ( obj == null ) {
	   throw new ObjectNotFoundException("Objeto não encontrado ID: " + id + ", tipo:" + Cliente.class.getName() ); 
	  }
	  return obj;
	}
	
	public Cliente insert(Cliente obj) {
	  obj.setId(null);	
	  Cliente cli = clienteRepository.save(obj);	
	  enderecoRepository.save(cli.getEnderecos());
	  return cli;
	}

	public List<Cliente> findAll(){
	  return clienteRepository.findAll();	
	}
	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage , String orderBy , String direction   ){
	  PageRequest pageRequest = new PageRequest(page,linesPerPage, Direction.valueOf(direction) , orderBy);	
	  return clienteRepository.findAll(pageRequest);	
	}
	
	
	public Cliente update(Cliente obj) {
	  Cliente newObj = find(obj.getId());
	  updateData(newObj,obj);
	  return clienteRepository.save(newObj);	
	}
	
	private void updateData(Cliente newObj , Cliente obj ) {
	  newObj.setNome(obj.getNome());
	  newObj.setEmail(obj.getEmail());
	}
	
	public void delete(Integer id) {
	  find(id);
	  try {
	    clienteRepository.delete(id);
	  }catch(DataIntegrityViolationException e) {
		  throw new DataIntegrityException("Não é possível excluir uma Cliente há entidades relacionadas");
	  }
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
	  return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(),null, null, null);	
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
	  	
	  Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(),objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()),pe.encode(objDTO.getSenha()));
	  Cidade cid = cidaderepository.findOne(objDTO.getCidadeId());
	  Endereco end = new Endereco(null,objDTO.getLogradouro(),objDTO.getNumero(),objDTO.getComplemento(),objDTO.getBairro(),objDTO.getCep(),cli,cid);
	  cli.getEnderecos().add(end);
	  cli.getTelefones().add(objDTO.getTelefone1());
	  return cli;
	}


}
