package br.com.lazaro.api.config.exception.handler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.lazaro.api.config.exception.handler.dto.ErroDeEntradaDTO;
import br.com.lazaro.api.config.exception.handler.dto.ErroPadraoExceptionDTO;
import br.com.lazaro.api.service.exception.EntityNotFoundServiceException;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@Autowired
	private MessageSource menssageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeEntradaDTO> handler(MethodArgumentNotValidException exception) {
		List<ErroDeEntradaDTO> erroDTO = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = menssageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeEntradaDTO erro = new ErroDeEntradaDTO(e.getField(), mensagem);
			erroDTO.add(erro);
		});

		return erroDTO;
	}
	
	

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundServiceException.class)
	public ResponseEntity<ErroPadraoExceptionDTO> handlerId(EntityNotFoundServiceException exception,
			HttpServletRequest request) {

		ErroPadraoExceptionDTO error = new ErroPadraoExceptionDTO();
		error.setInstant(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Resource not found");
		error.setMensagem(exception.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ErroPadraoExceptionDTO> handlerDeleteId(EmptyResultDataAccessException exception,
			HttpServletRequest request) {

		ErroPadraoExceptionDTO error = new ErroPadraoExceptionDTO();
		error.setInstant(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Id não encontrado");
		error.setMensagem(exception.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErroPadraoExceptionDTO> handlerIntegrity(DataIntegrityViolationException exception,
			HttpServletRequest request) {

		ErroPadraoExceptionDTO error = new ErroPadraoExceptionDTO();
		error.setInstant(Instant.now());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setError("Existi um ou mais sócios integrados a este partido, não é possível deletar");
		error.setMensagem(exception.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}


}
