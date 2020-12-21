package domain;

import lombok.Data;


//get/set, equals, defaul constructor, hashcode, tostring
@Data
public class MemberVO {	
	private String userid;
	private String password;
	private String name;
	private String gender;
	private String email;
	
}
