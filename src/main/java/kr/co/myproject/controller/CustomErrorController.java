package kr.co.myproject.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController{
	//404에러가 뜰때 특정 액션을 취할수 있게 해주는 인터페이스
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest request)
	{
		int statusCode = (int)request.getAttribute("jakarta.servlet.error.status_code");
		
		//404번이라는 에러가 발생하면 404번을 statusCode에 저장
		if(statusCode == 404)
		{
			return "redirect:/login-page";
		}
		return "redirect:/login-page";
	}
}
