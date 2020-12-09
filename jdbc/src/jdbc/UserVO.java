package jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class UserVO {
	private int no;
	private String username;
	private int birthyear;
	private String addr;
	private String mobile;
	
	//생성자, getter, setter, toString => 
}
