package com.interaxa.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorDTO {
	
	private int codigoError;
	private String detalleError;
	private String descripcion;

}
