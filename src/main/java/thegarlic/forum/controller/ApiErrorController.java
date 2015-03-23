package thegarlic.forum.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import thegarlic.forum.exception.DefaultException;

@RestController
public class ApiErrorController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	@RequestMapping("/error")
	public void error(HttpServletRequest req) {
		Object code = req.getAttribute("javax.servlet.error.status_code");
		HttpStatus status = code == null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.valueOf(Integer.parseInt(code.toString()));
		throw new DefaultException(status.name(), status);
	}
}
