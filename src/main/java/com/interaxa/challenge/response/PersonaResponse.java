package com.interaxa.challenge.response;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.interaxa.challenge.entities.Persona;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonaResponse {
	
	private Boolean ok;
	private String mensaje;
	private HttpStatus status;
	private List<Persona> usuarios;
	

}
