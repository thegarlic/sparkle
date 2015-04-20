package thegarlic.forum.dto.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@Data
@NoArgsConstructor
public class Article {
	private long id;
	@JsonIgnore
	private DateTime writeDate;
	@JsonIgnore
	private DateTime modifyDate;
	private String author;
	private String title;
	private Integer readCount;

	@JsonProperty
	public String getWriteDate() {
		return writeDate.withZone(DateTimeZone.UTC).toString();
	}

	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getModifyDate() {
		return modifyDate == null ? null : writeDate.withZone(DateTimeZone.UTC).toString();
	}
}