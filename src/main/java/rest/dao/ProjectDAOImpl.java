package rest.dao;

import org.springframework.stereotype.Controller;
import rest.entity.Project;
import rest.entity.Worker;
import rest.util.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectDAOImpl implements ProjectDAO {

    @Override
    public List<Project> getAllProjects() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);

            String SQL = "select id,title from project";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Project> list = new ArrayList<>();
            while (resultSet.next()) {
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setTitle(resultSet.getString("title"));
                list.add(project);
            }
            connection.close();
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveProject(String projectName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "insert into project values (default, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, projectName);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Project getProject(String projectName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "select id,title from project where title=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, projectName);

            ResultSet resultSet = preparedStatement.executeQuery();
            Project project = new Project();
            resultSet.next();
            project.setId(resultSet.getInt("id"));
            String projectTitle = resultSet.getString("title");
            project.setTitle(projectTitle);

            connection.close();
            return project;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProject(String projectName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "delete from project where title =?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, projectName);

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProject(String projectName, int projectId) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "update project set title=?\n" +
                    "where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, projectName);
            preparedStatement.setInt(2, projectId);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Worker> getAllWorkersByProjectTitle(String projectTitle) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);

            String SQL = "select worker.id,name from worker\n" +
                    "join workersprojects wp on worker.id = wp.workerid\n" +
                    "join project p on wp.projectid = p.id\n" +
                    "where p.title = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, projectTitle);
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
    public Project getProjectWithWorkers(String projectName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "select id,title from project where title=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, projectName);

            ResultSet resultSet = preparedStatement.executeQuery();
            Project project = new Project();
            resultSet.next();
            project.setId(resultSet.getInt("id"));
            String projectTitle = resultSet.getString("title");
            project.setTitle(projectTitle);
            project.setWorkerList(getAllWorkersByProjectTitle(projectTitle));

            connection.close();
            return project;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
