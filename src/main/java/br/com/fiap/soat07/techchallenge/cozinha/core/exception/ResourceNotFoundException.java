package br.com.fiap.soat07.techchallenge.cozinha.core.exception;

public class ResourceNotFoundException extends RuntimeException {
	static final long serialVersionUID = 7299475808171844773L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}