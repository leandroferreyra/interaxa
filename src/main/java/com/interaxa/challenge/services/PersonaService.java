package com.interaxa.challenge.services;

import java.util.List;
import java.util.Optional;

import com.interaxa.challenge.dto.PersonaDTO;
import com.interaxa.challenge.entities.Persona;


public interface PersonaService {
	
	public List<Persona> getAllPersonas();
	
	public Optional<Persona> getPersona(Long id);
	
	public Persona savePersona(PersonaDTO persona);
	
	public void deletePersonaByID(Long id);
	
	public Persona updatePersonaByID(PersonaDTO p, Long id);
	
	public Persona findByNombre(String nombre);
	
	public Persona findByDni(Integer dni);
	
}
