package br.com.alinson.testeunimed.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.alinson.testeunimed.model.Plano;
import br.com.alinson.testeunimed.repository.PlanoRepository;
import br.com.alinson.testeunimed.service.PlanoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanoServiceImpl implements PlanoService {
	
	private final PlanoRepository planoRepository;
	
	public List<Plano> getAllPlanos() {
		return planoRepository.findAll();
	}

	
}
