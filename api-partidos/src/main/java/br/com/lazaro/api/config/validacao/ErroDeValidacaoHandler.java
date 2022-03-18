package br.com.lazaro.api.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.lazaro.api.config.validacao.dto.ErroDeEntradaDTO;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource menssageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeEntradaDTO> handler(MethodArgumentNotValidException exception){
		List<ErroDeEntradaDTO> erroDTO = new ArrayList<>();
		
		List<FieldError> fieldErrors =  exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach( e -> {
			String mensagem = menssageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeEntradaDTO erro = new ErroDeEntradaDTO(e.getField(), mensagem);
			erroDTO.add(erro);
		});
		
		return erroDTO;
	}

}
