package br.com.alinson.testeunimed.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alinson.testeunimed.model.Plano;
import br.com.alinson.testeunimed.service.PlanoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/planos")
@RequiredArgsConstructor
public class PlanoController {
	
	private final PlanoService planoService;
	
	@GetMapping
	public List<Plano> getPlanos() {
		return planoService.getAllPlanos();
	}

}
