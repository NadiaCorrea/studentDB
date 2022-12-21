package com.jacaranda.studentDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.studentDB.model.Student;
import com.jacaranda.studentDB.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	StudentRepository repositorio;
	
	public Student getStudent(long id) {
		return repositorio.findById(id).orElse(null);//optional or else por si no está el id consigue el nulo
	}
	
	public List<Student> getStudents(){
		return repositorio.findAll();
	}
	
	public Student add(Student s) {
		return repositorio.save(s);
	}

	public void delete(Student s) {
		repositorio.delete(s);
	}
	
	public Student update(Student s) {
		if(getStudent(s.getId()) != null) {
			return repositorio.save(s);
		}else {
			return null;
		}
	}
	
	
	
	
}
