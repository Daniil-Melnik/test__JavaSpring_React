package com.test.fullstack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.fullstack.Auto;
import com.test.fullstack.DataBase;

@Controller
public class MainController {

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Главная страница");
		Iterable<Auto> auto_list = DataBase.getAutos();
		model.addAttribute("autos", auto_list);
		return "home";
	}
    @GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "О нас");
		return "about";
	}
}
