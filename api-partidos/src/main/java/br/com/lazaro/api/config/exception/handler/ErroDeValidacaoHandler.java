package br.com.lazaro.api.config.exception.handler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.lazaro.api.config.exception.handler.dto.ErroDeEntradaDTO;
import br.com.lazaro.api.config.exception.handler.dto.ErroPadraoExceptionDTO;
import br.com.lazaro.api.service.exception.EntityNotFoundException;

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
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErroPadraoExceptionDTO> handlerId(EntityNotFoundException exception,
			HttpServletRequest request) {

		ErroPadraoExceptionDTO error = new ErroPadraoExceptionDTO();
		error.setInstant(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Resource not found");
		error.setMensagem(exception.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
