package com.themathcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.themathcode.entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer>{

}
