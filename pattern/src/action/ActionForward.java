package action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ActionForward {
	private String path; //어디로 갈 것인가?
	private boolean redirect; // 어떤 방식(forward / sendRedirect)
}
