package com.marcioleal.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcioleal.cursomc.domain.Categoria;
import com.marcioleal.cursomc.domain.Cidade;
import com.marcioleal.cursomc.domain.Cliente;
import com.marcioleal.cursomc.domain.Endereco;
import com.marcioleal.cursomc.domain.Estado;
import com.marcioleal.cursomc.domain.ItemPedido;
import com.marcioleal.cursomc.domain.Pagamento;
import com.marcioleal.cursomc.domain.PagamentoComBoleto;
import com.marcioleal.cursomc.domain.PagamentoComCartao;
import com.marcioleal.cursomc.domain.Pedido;
import com.marcioleal.cursomc.domain.Produto;
import com.marcioleal.cursomc.domain.enums.EstadoPagamento;
import com.marcioleal.cursomc.domain.enums.TipoCliente;
import com.marcioleal.cursomc.repositories.CategoriaRepository;
import com.marcioleal.cursomc.repositories.CidadeRepository;
import com.marcioleal.cursomc.repositories.ClienteRepository;
import com.marcioleal.cursomc.repositories.EnderecoRepository;
import com.marcioleal.cursomc.repositories.EstadoRepository;
import com.marcioleal.cursomc.repositories.ItemPedidoRepository;
import com.marcioleal.cursomc.repositories.PagamentoRepository;
import com.marcioleal.cursomc.repositories.PedidoRepository;
import com.marcioleal.cursomc.repositories.ProdutoRepository;


@Service
public class DBService {
	
	
	@Autowired
	private CategoriaRepository categoriaRepositiry;
	
	@Autowired
	private ProdutoRepository  produtoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateDatabase() throws ParseException {
		
		 Categoria cat1 = new Categoria(null,"Informática");
		  Categoria cat2 = new Categoria(null,"Escritorio");
		  Categoria cat3 = new Categoria(null,"Escritorio 1");
		  Categoria cat4 = new Categoria(null,"Escritorio 2");
		  Categoria cat5 = new Categoria(null,"Escritorio 3");
		  Categoria cat6 = new Categoria(null,"Escritorio 4");
		  Categoria cat7 = new Categoria(null,"Escritorio 5");
		  
		  
		  Produto p1 = new Produto(null,"Computador",2000.00);
		  Produto p2 = new Produto(null,"Impressora",800.00);
		  Produto p3 = new Produto(null,"Mouse",20.00);
		  Produto p4 = new Produto(null,"Sulfite",20.00);
		  cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		  cat2.getProdutos().addAll(Arrays.asList(p4));
		  
		  p1.getCategorias().addAll(Arrays.asList(cat1));
		  p2.getCategorias().addAll(Arrays.asList(cat1));
		  p3.getCategorias().addAll(Arrays.asList(cat1));
		  p4.getCategorias().addAll(Arrays.asList(cat2));
		  
		 
		  
		  categoriaRepositiry.save(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		  produtoRepository.save(Arrays.asList(p1,p2,p3,p4));
		  
		  Estado est1 = new Estado(null,"Minas Gerais");
		  Estado est2 = new Estado(null,"São Paulo");
		  
		  Cidade c1 = new Cidade(null,"Uberlandia",est1);
		  Cidade c2 = new Cidade(null,"Campinas",est2);
		  Cidade c3 = new Cidade(null,"São Paulo",est2);
		  
		  
		  est1.getCidades().add(c1);
		  est2.getCidades().addAll(Arrays.asList(c2,c3));
		  
		  estadoRepository.save(Arrays.asList(est1,est2));
		  cidadeRepository.save(Arrays.asList(c1,c2,c3));
		  
		  Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","3656345656",TipoCliente.PESSOAFISICA , pe.encode("123"));
		  cli1.getTelefones().addAll(Arrays.asList("29888242","995317678"));
		  
		  Endereco e1 = new Endereco(null,"Rua Flores","300","Apto 203","Jardim","38220834",cli1,c1);
		  Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","38777012",cli1,c2);
		  
		  cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		  
		  clienteRepository.save(Arrays.asList(cli1));
		  enderecoRepository.save(Arrays.asList(e1,e2));
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");
		  Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32"),cli1,e1);
		  Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35"),cli1,e2);
		  
		  Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1 , 6 );
		  ped1.setPagamento(pagto1);
		  
		  
		  Pagamento pagto2 = new PagamentoComBoleto (null, EstadoPagamento.PEDENTE, ped2 , sdf.parse("20/10/2017 00:00") , null);
		  ped2.setPagamento(pagto2);
		  
		  cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		  
		  pedidoRepository.save(Arrays.asList(ped1, ped2));
		  pagamentoRepository.save(Arrays.asList(pagto1,pagto2));
		  
		  ItemPedido ip1 = new ItemPedido(p1,ped1,0.00,1,2000.00);
		  ItemPedido ip2 = new ItemPedido(p3,ped1,0.00,2,80.00);
		  ItemPedido ip3 = new ItemPedido(p2,ped2,100.00,1,800.00);
		  
		  ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		  ped2.getItens().addAll(Arrays.asList(ip3));
		  
		  p1.getItens().addAll(Arrays.asList(ip1));
		  p2.getItens().addAll(Arrays.asList(ip3));
		  p3.getItens().addAll(Arrays.asList(ip2));
		  
		  itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
		
	}

}
