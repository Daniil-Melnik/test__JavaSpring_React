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
}