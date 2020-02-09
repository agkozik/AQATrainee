package jsonClasses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class VideoGame {
	private int reviewScore;
	private String releaseDate;
	private String name;
	private String rating;
	private int id;
	private String category;
}