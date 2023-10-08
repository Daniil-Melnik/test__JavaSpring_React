package com.test.fullstack.controller;

import org.springframework.web.bind.annotation.*;

import com.test.fullstack.Auto;
import com.test.fullstack.DataBase;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    @GetMapping("/getAll")
    public List<Auto> list(){
        return DataBase.getAutos();
    }

	@PostMapping("/add")
    public String add(@RequestBody Auto auto){
		System.out.println("QQQQQQQQQQQQ");
		System.out.println(auto.getName() + " " + auto.getComand() + " " + auto.getDiscription());
		System.out.println("QQQQQQQQQQQQ");
		DataBase.addAuto(auto);
        return "New student is added";
    }
}