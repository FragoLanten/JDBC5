package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.dao.DepartmentDAO;
import rest.entity.Department;
import rest.entity.Worker;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDAO departmentDAO;

    @Override
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    @Override
    public void saveDepartment(String departmentName) {
        departmentDAO.saveDepartment(departmentName);
    }

    @Override
    public Department getDepartment(String departmentName) {
        return departmentDAO.getDepartment(departmentName);
    }

    @Override
    public void deleteDepartment(String departmentName) {
        departmentDAO.deleteDepartment(departmentName);
    }

    @Override
    public void updateDepartment(String departmentName, int departmentId) {
        departmentDAO.updateDepartment(departmentName, departmentId);
    }

    @Override
    public List<Worker> getAllWorkersByDepartmentName(String departmentName) {
        return departmentDAO.getAllWorkersByDepartmentName(departmentName);
    }

    @Override
    public Department getDepartmentWithWorkers(String departmentName) {
        return departmentDAO.getDepartmentWithWorkers(departmentName);
    }
}
