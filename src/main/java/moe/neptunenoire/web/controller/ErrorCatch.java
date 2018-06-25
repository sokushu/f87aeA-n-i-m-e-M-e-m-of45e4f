package moe.neptunenoire.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import moe.neptunenoire.web.controller.error.BangumiException;
import moe.neptunenoire.web.controller.error.HomeException;
import moe.neptunenoire.web.controller.error.HomeNotFoundException;

@ControllerAdvice
public class ErrorCatch {

	@ExceptionHandler(value = BangumiException.class)
	public String bangumiError(BangumiException e) {
		return "";
	}

	@ExceptionHandler(value = HomeException.class)
	public String homeError(HomeException e) {
		return "";
	}

	@ExceptionHandler(value = HomeNotFoundException.class)
	public String homeNotFoundError(HomeNotFoundException e, Model model) {
		model.addAttribute("test", "HomeNotFoundException");
		return "test";
	}

}