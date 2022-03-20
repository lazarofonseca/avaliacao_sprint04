package br.com.lazaro.api.service.exception;

public class EntityNotFoundServiceException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public EntityNotFoundServiceException(String msg) {
		super(msg);
	}

}
