package com.interaxa.challenge.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.interaxa.challenge.entities.Persona;



@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long>{
	
	public Persona findByNombre(String nombre);
	
	public Persona findByDni(Integer dni);
	

}
