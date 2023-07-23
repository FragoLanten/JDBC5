package rest.dao;

import org.springframework.stereotype.Component;
import rest.entity.Project;
import rest.entity.Worker;
import rest.util.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class WorkerDAOImpl implements WorkerDAO {

    @Override
    public List<Worker> getAllWorkers() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);

            String SQL = "select id,name from worker";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Worker> list = new ArrayList<>();
            while (resultSet.next()) {
                Worker worker = new Worker();
                worker.setId(resultSet.getInt("id"));
                String workerName = resultSet.getString("name");
                worker.setName(workerName);
                list.add(worker);
            }
            connection.close();
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveWorker(String workerName, int departmentId) {

        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "insert into worker values (default, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, workerName);
            preparedStatement.setInt(2, departmentId);

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Worker getWorker(String workerName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "select id,name from worker where name=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, workerName);

            ResultSet resultSet = preparedStatement.executeQuery();
            Worker worker = new Worker();
            resultSet.next();
            worker.setId(resultSet.getInt("id"));
            worker.setName(resultSet.getString("name"));
            worker.setProjectList(getAllProjectsByWorkerName(workerName));

            connection.close();
            return worker;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteWorker(String workerName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "delete from worker where name =?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, workerName);

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateWorker(int workerId, String workerName, int departmentId) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "update worker set name=?, department_id=? where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1,workerName);
            preparedStatement.setInt(2,departmentId);
            preparedStatement.setInt(3,workerId);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Project> getAllProjectsByWorkerName (String workerName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);

            String SQL = "select project.id,title from project\n" +
                    "join workersprojects wp on project.id = wp.projectid\n" +
                    "join worker on wp.workerid = worker.id\n" +
                    "where worker.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, workerName);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Project> projectList = new ArrayList<>();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setTitle(resultSet.getString("title"));
                projectList.add(project);
            }
            connection.close();
            return projectList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
