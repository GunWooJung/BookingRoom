package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {

	private long id;
	
	private String title;
	
	private String building;
	
	private String room;
	
	private String reason;
	
	private long userId;
	
	private String logtime;
	
}
