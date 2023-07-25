package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.dao.ProjectDAO;
import rest.entity.Project;
import rest.entity.Worker;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectDAO projectDAO;

    @Override
    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    @Override
    public void saveProject(String projectName) {
        projectDAO.saveProject(projectName);
    }

    @Override
    public Project getProject(String projectName) {
        return projectDAO.getProject(projectName);
    }

    @Override
    public void deleteProject(String projectName) {
        projectDAO.deleteProject(projectName);
    }

    @Override
    public void updateProject(String projectName, int projectId) {
        projectDAO.updateProject(projectName, projectId);
    }

    @Override
    public List<Worker> getAllWorkersByProjectTitle(String projectTitle) {
        return projectDAO.getAllWorkersByProjectTitle(projectTitle);
    }

    @Override
    public Project getProjectWithWorkers(String projectName) {
        return projectDAO.getProjectWithWorkers(projectName);
    }
}
