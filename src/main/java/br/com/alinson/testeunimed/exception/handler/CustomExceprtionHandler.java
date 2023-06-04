package br.com.alinson.testeunimed.exception.handler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alinson.testeunimed.exception.ArgumentoInvalidoException;
import br.com.alinson.testeunimed.exception.ErroDetalhes;
import br.com.alinson.testeunimed.exception.ValidacaoDetalhes;

//import br.gov.prf.api.exception.ArgumentNotValidException;
//import br.gov.prf.api.exception.CustomValidationDetails;
//import br.gov.prf.api.exception.CustomValidationException;
//import br.gov.prf.api.exception.ExceptionDetails;
//import br.gov.prf.api.exception.IndisponibilidadeTemporariaException;
//import br.gov.prf.api.exception.OperacaoNaoPermitidaException;
//import br.gov.prf.api.exception.ResourceAlreadyExistsDetails;
//import br.gov.prf.api.exception.ResourceAlreadyExistsException;
//import br.gov.prf.api.exception.ResourceNotFoundDetails;
//import br.gov.prf.api.exception.ResourceNotFoundException;
//import br.gov.prf.api.exception.SenhaInvalidaException;
//import br.gov.prf.api.exception.SenhaNaoConfereException;

@RestControllerAdvice
public class CustomExceprtionHandler {
	
	@ExceptionHandler(ArgumentoInvalidoException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDetalhes handleArgumentNotValidException(
    		ArgumentoInvalidoException exception) {
    	ErroDetalhes erroDetalhes = ErroDetalhes.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Argumento inválido")
                .detail(exception.getMessage())
                .developerMessage(exception.getClass().getName())
                .build();
        return erroDetalhes;
    }
    
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErroDetalhes handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        List<String> fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.toList());
        List<String> fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        return ValidacaoDetalhes.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field Validation Error")
                .detail("Check the field(s) below")
                .developerMessage(exception.getClass().getName())
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build();
    }
	
 // TESTAR MANDANDO FIND ID INEXISTENTE
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ResourceNotFoundDetails> handleResourceNotFoundException(
//        ResourceNotFoundException exception) {
//        return new ResponseEntity<>(
//            ResourceNotFoundDetails.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.NOT_FOUND.value())
//                .title("Recurso não encotrado")
//                .detail(exception.getMessage())
//                .developerMessage(exception.getClass().getName())
//                .build(), HttpStatus.NOT_FOUND);
//    }
    
    // TESTAR MANDANDO FIND ID EXISTENTE
//    @ExceptionHandler(ResourceAlreadyExistsException.class)
//    public ResponseEntity<ResourceAlreadyExistsDetails> handleResourceAlreadyExistsException(
//		ResourceAlreadyExistsException exception) {
//        return new ResponseEntity<>(
//    		ResourceAlreadyExistsDetails.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .title("Recurso já existe")
//                .detail(exception.getMessage())
//                .developerMessage(exception.getClass().getName())
//                .build(), HttpStatus.BAD_REQUEST);
//    }
    
//    @ExceptionHandler(CustomValidationException.class)
//    public ResponseEntity<CustomValidationDetails> handleValidationExceptionException(
//    		CustomValidationException exception) {
//        return new ResponseEntity<>(
//        		CustomValidationDetails.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .title("Field Validation Error")
//                .detail("Check the field(s) below")
//                .developerMessage(exception.getClass().getName())
//                .fields(exception.getFields())
//                .fieldsMessage(exception.getFieldsMessage() == null || exception.getFieldsMessage().isEmpty() ?
//                		exception.getMessage() : exception.getFieldsMessage())
//                .build(), HttpStatus.BAD_REQUEST);
//    }
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErroDetalhes handleMethodNotValidException( MethodArgumentNotValidException exception ){
//		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
//		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
//		String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
//
//        ErroDetalhes erroDetalhes = ErroDetalhes.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .title("Erro de Validação")
//                .detail(fields)
//                .developerMessage(fieldsMessage)
//                .build();
//        
//        return erroDetalhes;
//    }

//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ValidacaoDetalhes handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
//
//        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
//
//        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
//        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage)
//            .collect(Collectors.joining(", "));
//
//        return ValidacaoDetalhes.builder()
//                .timestamp(LocalDateTime.now())
//                .status(HttpStatus.BAD_REQUEST.value())
//                .title("Erro de Validação")
//                .detail("Verifique os campos abaixo")
//                .developerMessage(exception.getClass().getName())
//                .fields(fields)
//                .fieldsMessage(fieldsMessage)
//                .build();
//    }

    // TESTAR MANDANDO JSON MAL FORMADO (sem uma aspas p ex) NO BODY
//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(
//        Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        ErroDetalhes exceptionDetails = ErroDetalhes.builder()
//            .timestamp(LocalDateTime.now())
//            .status(status.value())
//            .title(ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage())
//            .detail(ex.getMessage())
//            .developerMessage(ex.getClass().getName())
//            .build();
//        return new ResponseEntity<>(exceptionDetails, headers, status);
//    }

}
