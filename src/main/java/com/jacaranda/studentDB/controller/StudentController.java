package com.jacaranda.studentDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.studentDB.model.Student;
import com.jacaranda.studentDB.service.StudentService;



@Controller
public class StudentController {
	
	@Autowired
	private StudentService servicio;

	@GetMapping("/")
	public String listStudent(Model model) {

		model.addAttribute("lista", servicio.getStudents());

		// lo que devuelve es el cod html correspondiente a renderizar la plantilla
		return "listStudents";
	}

	@GetMapping("/addStudent")
	public String addStudent(Model model) {

		Student student = new Student();

		model.addAttribute("estudiante", student);

		// lo que devuelve es el cod html correspondiente a renderizar la plantilla
		return "addStudentForm";
	}

	@PostMapping("/addStudent/submit")
	public String addSubmitStudent(@Validated @ModelAttribute("estudiante") Student estudiante,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "addStudentForm";
		} else {
			servicio.add(estudiante);
			// para redireccionar
			return "redirect:/listStudent";
		}
	}

	@GetMapping("/deleteStudent")
	public String deleteStudent(Model model,
			@RequestParam(name = "id") Long id){

		Student estudiante = servicio.getStudent(id);

		if (estudiante != null) {
			model.addAttribute("estudiante", estudiante);
		}

		return "deleteStudent";
	}

	@PostMapping("/deleteStudent/submit")
	public String deleteSubmitStudent(@ModelAttribute("estudiante") Student estudiante) {

		servicio.delete(estudiante);

		// para redireccionar
		return "redirect:/listStudent";
	}

	@GetMapping("/updateStudent")
	public String updateStudent(Model model,
			@RequestParam(name = "id") Long id){

		Student estudiante = servicio.getStudent(id);

		if (estudiante != null) {
			model.addAttribute("estudiante", estudiante);
		}

		return "updateStudent";
	}

	@PostMapping("/updateStudent/submit")
	public String updateStudent(@Validated @ModelAttribute("estudiante") Student estudiante,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "updateStudent";
		} else {
			servicio.update(estudiante);
			return "redirect:/listStudent";
		}
	}

	
}
