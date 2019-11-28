package jackson;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

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

	public Person(){};
	public Person(String address, String gender, double latitude, String greeting, String about, int index, String registered, boolean isActive, String picture, List<FriendsItem> friends, List<String> tags, String favoriteFruit, String balance, String eyeColor, String phone, String name, String guid, String company, String id, int age, String email, double longitude) {
		this.address = address;
		this.gender = gender;
		this.latitude = latitude;
		this.greeting = greeting;
		this.about = about;
		this.index = index;
		this.registered = registered;
		this.isActive = isActive;
		this.picture = picture;
		this.friends = friends;
		this.tags = tags;
		this.favoriteFruit = favoriteFruit;
		this.balance = balance;
		this.eyeColor = eyeColor;
		this.phone = phone;
		this.name = name;
		this.guid = guid;
		this.company = company;
		this.id = id;
		this.age = age;
		this.email = email;
		this.longitude = longitude;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setGreeting(String greeting){
		this.greeting = greeting;
	}

	public String getGreeting(){
		return greeting;
	}

	public void setAbout(String about){
		this.about = about;
	}

	public String getAbout(){
		return about;
	}

	public void setIndex(int index){
		this.index = index;
	}

	public int getIndex(){
		return index;
	}

	public void setRegistered(String registered){
		this.registered = registered;
	}

	public String getRegistered(){
		return registered;
	}

	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public void setPicture(String picture){
		this.picture = picture;
	}

	public String getPicture(){
		return picture;
	}

	public void setFriends(List<FriendsItem> friends){
		this.friends = friends;
	}

	public List<FriendsItem> getFriends(){
		return friends;
	}

	public void setTags(List<String> tags){
		this.tags = tags;
	}

	public List<String> getTags(){
		return tags;
	}

	public void setFavoriteFruit(String favoriteFruit){
		this.favoriteFruit = favoriteFruit;
	}

	public String getFavoriteFruit(){
		return favoriteFruit;
	}

	public void setBalance(String balance){
		this.balance = balance;
	}

	public String getBalance(){
		return balance;
	}

	public void setEyeColor(String eyeColor){
		this.eyeColor = eyeColor;
	}

	public String getEyeColor(){
		return eyeColor;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setGuid(String guid){
		this.guid = guid;
	}

	public String getGuid(){
		return guid;
	}

	public void setCompany(String company){
		this.company = company;
	}

	public String getCompany(){
		return company;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setAge(int age){
		this.age = age;
	}

	public int getAge(){
		return age;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	public double getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"Person{" + 
			"address = '" + address + '\'' + 
			",gender = '" + gender + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",greeting = '" + greeting + '\'' + 
			",about = '" + about + '\'' + 
			",index = '" + index + '\'' + 
			",registered = '" + registered + '\'' + 
			",isActive = '" + isActive + '\'' + 
			",picture = '" + picture + '\'' + 
			",friends = '" + friends + '\'' + 
			",tags = '" + tags + '\'' + 
			",favoriteFruit = '" + favoriteFruit + '\'' + 
			",balance = '" + balance + '\'' + 
			",eyeColor = '" + eyeColor + '\'' + 
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",guid = '" + guid + '\'' + 
			",company = '" + company + '\'' + 
			",_id = '" + id + '\'' + 
			",age = '" + age + '\'' + 
			",email = '" + email + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}