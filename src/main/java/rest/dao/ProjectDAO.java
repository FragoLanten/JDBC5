package rest.dao;

import rest.entity.Project;
import rest.entity.Worker;

import java.util.List;

public interface ProjectDAO {
    public List<Project> getAllProjects();

    public void saveProject(String projectName);

    public Project getProject(String projectName);

    public void deleteProject(String projectName);

    public void updateProject(String projectName, int projectId);

    public List<Worker> getAllWorkersByProjectTitle(String projectTitle);

    public Project getProjectWithWorkers(String projectName);
}
