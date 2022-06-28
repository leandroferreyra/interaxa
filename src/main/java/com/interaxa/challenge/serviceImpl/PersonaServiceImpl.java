package com.interaxa.challenge.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interaxa.challenge.dto.PersonaDTO;
import com.interaxa.challenge.entities.Persona;
import com.interaxa.challenge.mapper.PersonaDTOToPersona;
import com.interaxa.challenge.repositories.PersonaRepository;
import com.interaxa.challenge.services.PersonaService;


@Service
public class PersonaServiceImpl implements PersonaService {
	
	@Autowired
	private PersonaRepository repository;
	
	@Autowired
	private PersonaDTOToPersona mapper;
	
	@Override
	public List<Persona> getAllPersonas() {
		return (List<Persona>) this.repository.findAll();
	}

	@Override
	public Optional<Persona> getPersona(Long id ) {
		return this.repository.findById(id);
	}

	@Override
	public Persona savePersona(PersonaDTO persona) {
		return this.repository.save(this.mapper.map(persona));
	}

	@Override
	public void deletePersonaByID(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public Persona findByNombre(String nombre) {
		return this.repository.findByNombre(nombre);
	}

	@Override
	public Persona findByDni(Integer dni) {
		return this.repository.findByDni(dni);
	}

	@Override
	public Persona updatePersonaByID(PersonaDTO p, Long id) {
		return this.repository.save(this.mapper.mapWithID(p, id));
	}

	



}
