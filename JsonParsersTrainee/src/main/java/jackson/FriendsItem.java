package jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.Generated;
@Data
public class FriendsItem{
	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;
}