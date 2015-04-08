package thegarlic.forum.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import thegarlic.forum.dto.Response;
import thegarlic.forum.exception.DefaultException;

@Slf4j
@RestController
public class ApiErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public void error(HttpServletRequest req) {
        Object code = req.getAttribute("javax.servlet.error.status_code");

        HttpStatus status = code == null ? HttpStatus.INTERNAL_SERVER_ERROR
                : HttpStatus.valueOf(Integer.parseInt(code.toString()));
        log.debug("error controller handle error {}", status.value());
        throw new DefaultException(status.name(), status);
    }

    @RequestMapping(value = "/status404")
    public ResponseEntity<?> status404() {

        log.debug("404 에러");
        return Response.of(null, HttpStatus.NOT_FOUND);
    }
}

@Slf4j
@ControllerAdvice
class ApiErrorControllerAdvice {
    @ResponseBody
    @ExceptionHandler(value = DefaultException.class)
    public ResponseEntity<?> error(DefaultException e) {
        log.debug("exception 처리. 메시지 : {}", e.getMessage());
        return Response.of(e);
    }
}