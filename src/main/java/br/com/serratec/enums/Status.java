package br.com.serratec.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.exception.EnumException;

public enum Status {
	AGUARDANDO,
	EM_ATENDIMENTO,
	ATENDIDO;
	
	@JsonCreator
	public static Status verificar(String valor) {
		for (Status s : Status.values()) {
			if (s.name().equals(valor)) {
				return s;
			}
		}
		throw new EnumException("Status inválido");
	}
}
