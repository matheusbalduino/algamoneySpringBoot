package com.example.algamoney.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class algamoneyExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	protected MessageSource messageSource;
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		// TODO Auto-generated method stub
		String messageUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String messageDev = ex.getCause().toString();
		return handleExceptionInternal(ex, new Erro(messageUser, messageDev), headers, HttpStatus.BAD_REQUEST, request);
	}
	
	public static class Erro {
		private String messageUser;
		private String messageDev;
		
		public Erro(String messageUser, String messageDev) {
			super();
			this.messageUser = messageUser;
			this.messageDev = messageDev;
		}
		
		public String getMessageUser() {
			return messageUser;
		}
		public void setMessageUser(String messageUser) {
			this.messageUser = messageUser;
		}
		public String getMessageDev() {
			return messageDev;
		}
		public void setMessageDev(String messageDev) {
			this.messageDev = messageDev;
		}
		
		
	}
}
