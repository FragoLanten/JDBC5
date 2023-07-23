package rest.service;

import rest.entity.Project;
import rest.entity.Worker;

import java.util.List;

public interface ProjectService {

    public List<Project> getAllProjects();

    public void saveProject(String projectName);

    public Project getProject(String projectName);

    public void deleteProject(String projectName);

    public void updateProject(String projectName, int projectId);

    public List<Worker> getAllWorkersByProjectTitle(String projectTitle);

}
