package com.example.algamoney.api.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.flywaydb.core.internal.jdbc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository _repository;
	
	@GetMapping("/todos")
	public ResponseEntity<?> ObterTodos()
	{
		List<Categoria> categorias = _repository.findAll();		
		return !categorias.isEmpty()? ResponseEntity.ok(categorias): ResponseEntity.noContent().build(); 
		//204 deu certo, mas não mostra nada;
		//200 retorna ok;
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> SalvarCategoria(@RequestBody Categoria cat, HttpServletResponse response)
	{
		var categoria = _repository.save(cat);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		.buildAndExpand(categoria.getCodigo()).toUri();
		
		response.setHeader("location", uri.toASCIIString());
		// retorna uma url com o endereço do novo item adicionado 
		// retorna a hora de criação.
		
		return ResponseEntity.created(uri).body(categoria);
	}
	
	@GetMapping("/{codigo}")
	public Categoria buscarPeloCodigo(@PathVariable Long codigo)
	{
		return _repository.findById(codigo).orElse(null);
	}
	
	
	
}
