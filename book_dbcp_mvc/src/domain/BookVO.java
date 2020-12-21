package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// VO : Value Object
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookVO {
	private int code;
	private String title;
	private String writer;
	private int price;
}
