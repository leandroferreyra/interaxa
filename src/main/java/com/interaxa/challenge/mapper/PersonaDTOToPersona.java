package com.interaxa.challenge.mapper;


import org.springframework.stereotype.Component;

import com.interaxa.challenge.dto.PersonaDTO;
import com.interaxa.challenge.entities.Persona;


@Component
public class PersonaDTOToPersona implements IMapper<PersonaDTO, Persona>{

	@Override
	public Persona map(PersonaDTO in) {
		Persona persona= new Persona();
		persona.setNombre(in.getNombre());
		persona.setApellido(in.getApellido());
		persona.setDni(in.getDni());
		persona.setEmpleado(in.getEmpleado());
		return persona;
	}
	
	public Persona mapWithID(PersonaDTO in, Long id) {
		Persona persona= new Persona();
		persona.setId(id);
		persona.setNombre(in.getNombre());
		persona.setApellido(in.getApellido());
		persona.setDni(in.getDni());
		persona.setEmpleado(in.getEmpleado());
		return persona;
	}

}
