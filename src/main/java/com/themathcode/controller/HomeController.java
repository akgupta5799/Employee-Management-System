package com.themathcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.themathcode.entity.Employee;
import com.themathcode.service.EmpService;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	EmpService empService;
	
	@GetMapping("/")
	public String index(Model m) {
		List<Employee> list = empService.getAllEmp();
		m.addAttribute("empList", list);
		return "index";
	}
	
	@GetMapping("/loadEmpSave")
	public String loadEmpSave() {
		return "emp_save";
	}
	
	@GetMapping("/EditEmp/{id}")
	public String editEmp(@PathVariable int id, Model m) {
		//System.out.print(id);
		Employee emp = empService.getEmpById(id);
		m.addAttribute("emp",emp);
		return "edit_emp";
	}
	
	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp, HttpSession session) {
		//System.out.println(emp);
		Employee  newEmp = empService.saveEmp(emp);
		if(newEmp != null) {
			//System.out.print("Save successfully");
			session.setAttribute("msg", "Register Successfully");
		}else {
			//System.out.println("Something wrong on Server");
			session.setAttribute("msg", "Something wrong on Server");
		}
		return "redirect:/loadEmpSave";
	}
	
	@PostMapping("/updateEmpDtls")
	public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
		//System.out.println(emp);
		Employee  updateEmp = empService.saveEmp(emp);
		if(updateEmp != null) {
			//System.out.print("Save successfully");
			session.setAttribute("msg", "Update Successfully");
		}else {
			//System.out.println("Something wrong on Server");
			session.setAttribute("msg", "Something wrong on Server");
		}
		return "redirect:/";
	}
	
	@GetMapping("/deleteEmp/{id}")
	public String loadEmpSave(@PathVariable int id, HttpSession session) {
		boolean f = empService.deleteEmp(id);
		if (f) {
			session.setAttribute("msg", "Delete sucessfully");
		} else {
			session.setAttribute("msg", "something wrong on server");
		}
		return "redirect:/";
	}

}
