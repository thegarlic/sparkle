package thegarlic.forum.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class Metadata {
    private Boolean ok;
    @JsonInclude(Include.NON_EMPTY)
    private String message;
}
