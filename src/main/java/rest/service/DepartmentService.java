package rest.service;

import rest.entity.Department;
import rest.entity.Worker;

import java.util.List;

public interface DepartmentService {

    public List<Department> getAllDepartments();

    public void saveDepartment(String departmentName);

    public Department getDepartment(String departmentName);

    public void deleteDepartment(String departmentName);

    public void updateDepartment(String departmentName, int departmentId);

    public List<Worker> getAllWorkersByDepartmentName(String departmentName);
}
