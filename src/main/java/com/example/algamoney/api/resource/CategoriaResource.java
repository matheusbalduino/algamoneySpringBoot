package com.example.algamoney.api.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	private CategoriaRepository _repository;
	
	@GetMapping("/todos")
	public List<Categoria> ObterTodos(){
		
		var todos = _repository.findAll();
		
		return todos;
		
	}
	
}