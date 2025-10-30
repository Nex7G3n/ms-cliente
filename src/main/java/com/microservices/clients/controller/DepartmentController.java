package com.microservices.clients.controller;

import com.microservices.clients.dto.DepartmentDTO;
import com.microservices.clients.mapper.ClientMapper;
import com.microservices.clients.model.Department;
import com.microservices.clients.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private ClientMapper clientMapper; // Reutilizamos ClientMapper para mapear Department

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<Department> departments = departmentService.findAllDepartments();
        List<DepartmentDTO> departmentDTOs = departments.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(departmentDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        return departmentService.findDepartmentById(id)
                .map(clientMapper::toDto)
                .map(departmentDTO -> new ResponseEntity<>(departmentDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        Department department = clientMapper.toEntity(departmentDTO);
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(clientMapper.toDto(savedDepartment), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        try {
            Department departmentDetails = clientMapper.toEntity(departmentDTO);
            Department updatedDepartment = departmentService.updateDepartment(id, departmentDetails);
            return new ResponseEntity<>(clientMapper.toDto(updatedDepartment), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.deleteDepartment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
