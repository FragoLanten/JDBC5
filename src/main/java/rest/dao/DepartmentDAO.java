package rest.dao;

import rest.entity.Department;
import rest.entity.Worker;

import java.util.List;

public interface DepartmentDAO {
    public List<Department> getAllDepartments();

    public void saveDepartment(String departmentName);

    public Department getDepartment(String departmentName);

    public void deleteDepartment(String departmentName);

    public List<Worker> getAllWorkersByDepartmentName(String departmentName);
}
