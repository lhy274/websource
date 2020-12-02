package exam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class loginDTO {
	//~DTO(Date Transfer Object)
	private String userid;
	private String userpwd;
}
