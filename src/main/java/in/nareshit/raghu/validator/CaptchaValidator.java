package in.nareshit.raghu.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import in.nareshit.raghu.model.ReCaptchResponseType;

@Component
public class CaptchaValidator {

	@Autowired
	private RestTemplate rt;

	public boolean isValid(String captcha) {
		String url="https://www.google.com/recaptcha/api/siteverify";
		String params="?secret=6Leso9AZAAAAAJGJsHCEGGNgkPVR4_r8l9N6iSUq"
				+ "&response="+captcha;
		ReCaptchResponseType resp = rt.postForObject(
				url+params, null, 
				ReCaptchResponseType.class);
		return resp.isSuccess();
	}
}
