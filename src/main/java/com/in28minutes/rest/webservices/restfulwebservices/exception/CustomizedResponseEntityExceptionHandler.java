package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;
//BASICAMENTE ES UN MANEJADOR DE EXCEPCIONES QUE RESPONDERA DEPENDIENDO DEL TIPO DE ERROR
@ControllerAdvice // <---PARA COMPARTIR COSAS ENTRE CONTROLADORES USAMOS LA SIGUIENTE ETIQUETA
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		//CREAMOS UNA NUEVA INSTACIA DE NUESTRO BEAN EN ESPESIFICO
		// LE PASAMOS POR PARAMETRO LOS DATOS DE NUESTRA CLASE EXCEPTIONRESPONSE OBTENEMOS EL MENSAGE DE LA EXCEPCION ex.getMessage
		//Y POR ULTIMO ENVIAMOS LA DESCRIPCION CON REQUEST.GETDESCRIPTION(FALSE) LO QUE EVALUARA CASO POR CASO PARA VER SI REALMENTE SE INCLUYE LA DESCRIPCION
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		//CREAR UN NUEVO OBJETO DE ENTIDAD DE RESPUESTA
		//LO QUE RESIVE ES LA RESPUESTA DE LA ECEPCION Y EL ESTATUS(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	//DAR AL CONSUMIDOR UNA RESPUESTA DE QUE FUE LO QUE SALIO MAL DE SU SOLICITUD INCORRECTA
	//MENSAJE DE EXCEPCION PARA ARGUMENTOS DE METODO NO VALIDO POR EJEMPLO AL CREAR UN USUARIO POR POST Y ESTE INCORRECTA SU CADENA MINIMA DE CARACTERES
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		//SOLICITUD INCORRECTA
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}	
}