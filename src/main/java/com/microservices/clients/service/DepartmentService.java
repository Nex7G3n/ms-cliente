package com.microservices.clients.service;

import com.microservices.clients.model.Department;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> findAllDepartments();
    Optional<Department> findDepartmentById(Long id);
    Department saveDepartment(Department department);
    Department updateDepartment(Long id, Department departmentDetails);
    void deleteDepartment(Long id);
}
