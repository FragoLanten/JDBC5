package rest.dao;

import rest.util.DataBaseUtil;

import java.sql.*;

public class WorkersProjectsDAOImpl implements WorkersProjectsDAO {
    @Override
    public void saveWorkerProject(int workerId, int projectId) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "insert into workersprojects values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, workerId);
            preparedStatement.setInt(2, projectId);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteWorkerProject(int workerId, int projectId) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "delete from workersprojects where \n" +
                    " workerid=? and projectid=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, workerId);
            preparedStatement.setInt(2, projectId);

            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateWorkerProject(int oldWorkerId, int oldProjectId,
                                    int newWorkerId, int newProjectId) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DataBaseUtil.URL, DataBaseUtil.USER, DataBaseUtil.PASSWORD);
            String SQL = "update workersprojects set workerid=?, projectid=? \n" +
                    "where workerid=? and projectid=?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, newWorkerId);
            preparedStatement.setInt(2, newProjectId);
            preparedStatement.setInt(3, oldWorkerId);
            preparedStatement.setInt(4, oldProjectId);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
