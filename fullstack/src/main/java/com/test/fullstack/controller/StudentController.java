package com.test.fullstack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.fullstack.Auto;
import com.test.fullstack.DataBase;


@Controller
public class StudentController {

	@GetMapping("/blog")
	public String blog(Model model) {
		model.addAttribute("title", "Блог");
		Iterable<Auto> auto_list = DataBase.getAutos();
		model.addAttribute("autos", auto_list);
		return "blog-main";
	}

	@GetMapping("/blog/add")
	public String blogAdd(Model model) {
		return "blog-add";
	}

	@PostMapping("/blog/add")
	public String blogPostAdd(@RequestParam String name, @RequestParam String comand, @RequestParam String discr, @RequestParam String url, Model model){
		Auto auto = new Auto();
		auto.setAuto(0, name, comand, discr, url);
		DataBase.addAuto(auto);
		auto = null;
		Iterable<Auto> auto_list = DataBase.getAutos();
		model.addAttribute("autos", auto_list);
		return "redirect:/blog";
	}

	@GetMapping("/blog/{id}")
	public String blogDetails(@PathVariable(value = "id") long auto_id, Model model) {
		Auto auto = DataBase.getAuto(auto_id);
		if (auto != null){
			model.addAttribute("auto", auto);
			return "blog-details";
		}
		else {
			return "redirect:/";
		}
	}

	@GetMapping("/blog/{id}/edit")
	public String blogDetailsEdit(@PathVariable(value = "id") long auto_id, Model model) {
		Auto auto = DataBase.getAuto(auto_id);
		if (auto != null){
			model.addAttribute("auto", auto);
			return "blog-details-edit";
		}
		else {
			return "redirect:/";
		}
	}

	@PostMapping("/blog/{id}/edit")
	public String blogPostUpdate(@PathVariable(value = "id") int auto_id, @RequestParam String name, @RequestParam String comand, @RequestParam String discr, @RequestParam String url, Model model){
		Auto auto = new Auto();
		auto.setAuto(auto_id, name, comand, discr, url);
		DataBase.updAuto(auto);
		auto = null;
		Iterable<Auto> auto_list = DataBase.getAutos();
		model.addAttribute("autos", auto_list);
		return "redirect:/blog";
	}

	@PostMapping("/blog/{id}/remove")
	public String blogPostRemove(@PathVariable(value = "id") int auto_id, Model model){
		Auto auto = DataBase.getAuto(auto_id);
		DataBase.delAuto(auto);
		auto = null;
		Iterable<Auto> auto_list = DataBase.getAutos();
		model.addAttribute("autos", auto_list);
		return "redirect:/blog";
	}
}