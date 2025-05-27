package com.uninassau.periodo3.backend.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uninassau.periodo3.backend.projeto.domain.Pessoa;
import com.uninassau.periodo3.backend.projeto.service.pessoa.DeletePessoaByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.pessoa.FindPessoaByIdUseCase;
import com.uninassau.periodo3.backend.projeto.service.pessoa.FindPessoaByNameAndAgeUseCase;
import com.uninassau.periodo3.backend.projeto.service.pessoa.SavePessoaUseCase;
import com.uninassau.periodo3.backend.projeto.service.pessoa.UpdatePessoaUseCase;
import com.uninassau.periodo3.backend.projeto.service.pessoa.dto.PessoaDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pessoa")
@CrossOrigin(origins = "*")
public class PessoaController {
	
	@Autowired
	private FindPessoaByIdUseCase findPessoaByIdUseCase;
	
	@Autowired
	private FindPessoaByNameAndAgeUseCase findPessoaByNameAndAgeUseCase;
	
	@Autowired
	private SavePessoaUseCase savePessoaUseCase;
	
	@Autowired
	private UpdatePessoaUseCase updatePessoaUseCase;
	
	@Autowired
	private DeletePessoaByIdUseCase deletePessoaByIdUseCase;
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPessoa(@PathVariable Long id) {
		return ResponseEntity.ok(this.findPessoaByIdUseCase.execute(id));
	}
	
	@GetMapping("/listByNameAndAge")
	public ResponseEntity<List<Pessoa>> listPessoasByNameAndAge(@RequestParam String nome, @RequestParam Integer idade) {
		return ResponseEntity.ok(this.findPessoaByNameAndAgeUseCase.execute(nome, idade));
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> savePessoa(@RequestBody @Valid PessoaDto pessoaDto) {
		return ResponseEntity.status(HttpStatus.CREATED)
							.body(this.savePessoaUseCase.execute(pessoaDto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody @Valid PessoaDto pessoaDto) {
		return ResponseEntity.ok(this.updatePessoaUseCase.execute(id, pessoaDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePessoa(@PathVariable Long id) {
		this.deletePessoaByIdUseCase.execute(id);
		return ResponseEntity.noContent().build();
	}

}
