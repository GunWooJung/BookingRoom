package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private long id;
	
	private String name;
	
	private String userId;
	
	private String password;
	
	private String phone;

	private String major;
}
