package thegarlic.forum.exception;

import lombok.Getter;

import org.springframework.http.HttpStatus;

public class DefaultException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2642770126956262188L;
	
	@Getter
	private HttpStatus status;
	
	public DefaultException(String msg) {
		this(msg, HttpStatus.BAD_REQUEST);
	}
	
	public DefaultException(String msg, HttpStatus status) {
		super(msg);
		this.status = status;
	}
	
}
