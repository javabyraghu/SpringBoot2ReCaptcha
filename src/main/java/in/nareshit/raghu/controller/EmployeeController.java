package in.nareshit.raghu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.nareshit.raghu.modal.Employee;
import in.nareshit.raghu.service.IEmployeeService;
import in.nareshit.raghu.validator.CaptchaValidator;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;
	@Autowired
	private CaptchaValidator validator;

	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("employee", new Employee());
		return "EmployeeRegister";
	}

	@PostMapping("/save")
	public String saveEmployee(
			@ModelAttribute("employee") Employee employee,
			@RequestParam(name="g-recaptcha-response")String captcha,
			Model model) 
	{
		if(validator.isValid(captcha)) {
			service.createEmployee(employee);
			model.addAttribute("employee", new Employee());
			model.addAttribute("message", "Employee added!!");
		} else {
			model.addAttribute("message", "Please Verify Captcha");
		}
		
		return "EmployeeRegister";
	}

	@GetMapping("/all")
	public String getAllEmployees(Model model) 
	{
		model.addAttribute("list", service.getAllEmployees());
		return "EmployeeData";
	}

}
