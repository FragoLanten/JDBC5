package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.dao.DepartmentDAO;
import rest.dao.ProjectDAO;
import rest.entity.Department;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentDAO departmentDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> getAll() {

        List<Department> departments = departmentDAO.getAllDepartments();

        if (departments == null || departments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping(value="/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> getWorker(@PathVariable String name) {
        Department department = departmentDAO.getDepartment(name);

        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}
