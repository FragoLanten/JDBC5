package rest.dao;

import rest.entity.Worker;

public interface WorkersProjectsDAO {

    public void saveWorkerProject(int workerId, int projectId);

    public void deleteWorkerProject(int workerId, int projectId);

    public void updateWorkerProject(int oldWorkerId, int oldProjectId,
                                    int newWorkerId, int newProjectId);
}
