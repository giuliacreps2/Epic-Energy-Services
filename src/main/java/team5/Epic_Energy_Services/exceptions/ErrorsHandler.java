package team5.Epic_Energy_Services.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team5.Epic_Energy_Services.dto.ErrorsDTO;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorsHandler {

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorsDTO handleBadRequest(BadRequestException ex) {
		return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
	}


	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorsDTO handleGenericEx(Exception ex) {
		ex.printStackTrace();
		return new ErrorsDTO("C'è stato un errore lato server, giuro che lo risolveremo presto!", LocalDateTime.now());
	}


}
