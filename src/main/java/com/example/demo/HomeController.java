package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    ToDoRepository todoRepository;

    @RequestMapping("/")
    public String listCourses(Model model){
        model.addAttribute("todos", todoRepository.findAll());
        return "todolist";
    }

    @GetMapping("/add")
    public String courseForm(Model model){
        model.addAttribute("todo", new Todo());
        return "todoform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Todo todo, BindingResult result){
        if (result.hasErrors()){
            return "todoform";
        }
        todoRepository.save(todo);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "todoshow";
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "todoform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        todoRepository.deleteById(id);
        return "redirect:/";
    }
}