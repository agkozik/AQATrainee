package jsonClasses;

public class Response{
	private int reviewScore;
	private String releaseDate;
	private String name;
	private String rating;
	private int id;
	private String category;



	@Override
 	public String toString(){
		return 
			"Response{" + 
			"reviewScore = '" + reviewScore + '\'' + 
			",releaseDate = '" + releaseDate + '\'' + 
			",name = '" + name + '\'' + 
			",rating = '" + rating + '\'' + 
			",id = '" + id + '\'' + 
			",category = '" + category + '\'' + 
			"}";
		}
}
