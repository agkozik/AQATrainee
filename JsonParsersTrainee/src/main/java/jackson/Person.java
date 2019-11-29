package jackson;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.annotation.Generated;
@Data
public class Person{

	@JsonProperty("address")
	private String address;

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("latitude")
	private double latitude;

	@JsonProperty("greeting")
	private String greeting;

	@JsonProperty("about")
	private String about;

	@JsonProperty("index")
	private int index;

	@JsonProperty("registered")
	private String registered;

	@JsonProperty("isActive")
	private boolean isActive;

	@JsonProperty("picture")
	private String picture;

	@JsonProperty("friends")
	private List<FriendsItem> friends;

	@JsonProperty("tags")
	private List<String> tags;

	@JsonProperty("favoriteFruit")
	private String favoriteFruit;

	@JsonProperty("balance")
	private String balance;

	@JsonProperty("eyeColor")
	private String eyeColor;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("name")
	private String name;

	@JsonProperty("guid")
	private String guid;

	@JsonProperty("company")
	private String company;

	@JsonProperty("_id")
	private String id;

	@JsonProperty("age")
	private int age;

	@JsonProperty("email")
	private String email;

	@JsonProperty("longitude")
	private double longitude;
}