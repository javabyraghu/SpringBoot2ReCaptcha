package in.nareshit.raghu.model;

import lombok.Data;

@Data
public class ReCaptchResponseType {

	private boolean success;
	private String challenge_ts;
	private String hostname;
}
