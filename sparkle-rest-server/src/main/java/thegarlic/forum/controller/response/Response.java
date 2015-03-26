package thegarlic.forum.controller.response;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import thegarlic.forum.exception.DefaultException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Response {
    
    @Getter
    Metadata meta;
    @Getter
    @JsonInclude(Include.NON_EMPTY)
    Object data;
    
    private Response(HttpStatus status) {
        this.meta = new Metadata();
        meta.setOk(!(status.is4xxClientError() | status.is5xxServerError()));
    }
    
    private Response(HttpStatus status, String message) {
        this(status);
        meta.setMessage(message);
    }
    
    public static ResponseEntity<?> of() {
        return of(null, HttpStatus.OK);
    }
    
    public static ResponseEntity<?> of(Object data) {
        return of(data, HttpStatus.OK);
    }
    
    public static ResponseEntity<?> of(Object data, HttpStatus status) {
        Response r = new Response(status);
        r.data = data;
        
        return new ResponseEntity<>(r, status);
    }
    
    public static ResponseEntity<?> of(DefaultException e) {
        Response r = new Response(e.getStatus(), e.getMessage());
        return new ResponseEntity<>(r, e.getStatus());
    }
}
