package com.interaxa.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonaDTO {
	
	private String nombre;
	
	private String apellido;
	
	private Integer dni;
	
	private String empleado; // S o N
	
	
}