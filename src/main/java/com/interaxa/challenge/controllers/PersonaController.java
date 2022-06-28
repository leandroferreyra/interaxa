package com.interaxa.challenge.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interaxa.challenge.dto.PersonaDTO;
import com.interaxa.challenge.entities.Persona;
import com.interaxa.challenge.exceptions.BadRequestExc;
import com.interaxa.challenge.response.PersonaResponse;
import com.interaxa.challenge.services.PersonaService;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/personas")
public class PersonaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonaController.class);

	@Autowired
	private PersonaService personaService;
	
	@ApiOperation(value = "Retorna todas las personas")
	@GetMapping("/")
	public ResponseEntity<PersonaResponse> getPersonas() {
		LOGGER.info("Request received for GET [/personas/]");
		List<Persona> personas= this.personaService.getAllPersonas();
		if (personas.isEmpty()) {
			return new ResponseEntity<PersonaResponse>(new PersonaResponse(true, "No hay personas cargadas.", HttpStatus.OK, personas), HttpStatus.OK);	
		}
		return new ResponseEntity<PersonaResponse>(new PersonaResponse(true, "Personas devueltas", HttpStatus.OK, personas), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna una persona según su ID")
	@GetMapping("/{id}")
	public ResponseEntity<PersonaResponse> getPersonaById(@PathVariable Long id) throws BadRequestExc {
		LOGGER.info("Request received for GET [/personas/:id]");
		Optional<Persona> p= this.personaService.getPersona(id);
		if (p.isPresent()) {
			Persona persona = p.get();
			return new ResponseEntity<PersonaResponse>(new PersonaResponse(true,"Persona encontrada", HttpStatus.OK, new ArrayList<>(Arrays.asList(persona))), HttpStatus.OK);
		}
		return new ResponseEntity<PersonaResponse>(new PersonaResponse(false,"No se encontró persona con ID: "+id, HttpStatus.NOT_FOUND, new ArrayList<>()), HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Guarda una persona en la bbdd", notes="El dni no debe existir.")
	@PostMapping("/")
	public ResponseEntity<PersonaResponse> savePersona(@RequestBody PersonaDTO persona) throws BadRequestExc {
		LOGGER.info("Request received for POST [/personas/]");
		Persona personaByDni= this.personaService.findByDni(persona.getDni());
		if (personaByDni != null) {
			return new ResponseEntity<PersonaResponse>(new PersonaResponse(false, "La persona ya existe", HttpStatus.CONFLICT, new ArrayList<>()), HttpStatus.CONFLICT);	
		}
		Persona p= this.personaService.savePersona(persona);
		return new ResponseEntity<PersonaResponse>(new PersonaResponse(true, "Persona guardada", HttpStatus.OK, new ArrayList<>(Arrays.asList(p))), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Elimina una persona según su ID")
	@DeleteMapping("/deleteByID/{id}")
	public ResponseEntity<PersonaResponse> deleteByID(@PathVariable Long id) throws BadRequestExc {
		LOGGER.info("Request received for DELETE [/personas/deleteByID/:id]");
		Optional<Persona> p = this.personaService.getPersona(id);
		if (p.isPresent()) {
			this.personaService.deletePersonaByID(id);
			return new ResponseEntity<PersonaResponse>(new PersonaResponse(true, "Persona eliminada", HttpStatus.OK, new ArrayList<>(Arrays.asList(p.get()))),HttpStatus.OK);
		}
		return new ResponseEntity<PersonaResponse>(new PersonaResponse(false, "Persona no existe con id "+id, HttpStatus.NOT_FOUND, new ArrayList<>()),HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Actualiza una persona validando que el dni no exsita.")
	@PutMapping("/updatePersona/{id}")
	public ResponseEntity<PersonaResponse> updatePersonaByID(@RequestBody PersonaDTO persona, @PathVariable Long id) {
		Optional<Persona> p = this.personaService.getPersona(id);
		if (p.isPresent()) {
			Persona personaByDNI= this.personaService.findByDni(persona.getDni());
			if (personaByDNI != null) {
				return new ResponseEntity<PersonaResponse>(new PersonaResponse(false, "La persona con ese dni ya existe", HttpStatus.CONFLICT, new ArrayList<>()), HttpStatus.CONFLICT);
			}
			Persona personaActualizada = this.personaService.updateUsuarioByID(persona, id);
			return new ResponseEntity<PersonaResponse>(new PersonaResponse(true, "Persona actualizada", HttpStatus.OK, new ArrayList<>(Arrays.asList(personaActualizada))),HttpStatus.OK);
		}	
		return new ResponseEntity<PersonaResponse>(new PersonaResponse(false, "Persona no existe con id " + id, HttpStatus.NOT_FOUND, new ArrayList<>()),HttpStatus.NOT_FOUND);
	}
	
	

}
