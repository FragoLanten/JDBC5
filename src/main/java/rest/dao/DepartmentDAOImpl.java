package rest.dao;

import org.springframework.stereotype.Component;
import rest.entity.Department;
import rest.entity.Worker;
import rest.util.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentDAOImpl implements DepartmentDAO {

    @Override
    public List<Department> getAllDepartments() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);

            String SQL = "select id,name from department";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Department> departmentList = new ArrayList<>();
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setName(resultSet.getString("name"));
                departmentList.add(department);
            }
            connection.close();
            return departmentList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveDepartment(String departmentName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "insert into department values (default, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, departmentName);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department getDepartment(String departmentName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "select id,name from department where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, departmentName);

            ResultSet resultSet = preparedStatement.executeQuery();
            Department department = new Department();
            resultSet.next();
            department.setId(resultSet.getInt("id"));
            String depart = resultSet.getString("name");
            department.setName(depart);

            connection.close();
            return department;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteDepartment(String departmentName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "delete from department where name =?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, departmentName);

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateDepartment(String departmentName, int departmentId) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "update department set name=?\n" +
                    "where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, departmentName);
            preparedStatement.setInt(2, departmentId);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Worker> getAllWorkersByDepartmentName(String departmentName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);

            String SQL = "select worker.id, worker.name from worker\n" +
                    "join department on worker.department_id = department.id\n" +
                    "where department.name =?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, departmentName);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Worker> workerList = new ArrayList<>();
            while (resultSet.next()) {
                Worker worker = new Worker();
                worker.setId(resultSet.getInt("id"));
                worker.setName(resultSet.getString("name"));
                workerList.add(worker);
            }
            connection.close();
            return workerList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department getDepartmentWithWorkers(String departmentName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "select id,name from department where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, departmentName);

            ResultSet resultSet = preparedStatement.executeQuery();
            Department department = new Department();
            resultSet.next();
            department.setId(resultSet.getInt("id"));
            String depart = resultSet.getString("name");
            department.setName(depart);
            department.setListOfWorkers(getAllWorkersByDepartmentName(depart));

            connection.close();
            return department;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
