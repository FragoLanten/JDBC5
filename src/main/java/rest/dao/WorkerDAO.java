package rest.dao;

import rest.entity.Project;
import rest.entity.Worker;

import java.util.List;

public interface WorkerDAO {
    public List<Worker> getAllWorkers();

    public void saveWorker(String workerName, int departmentId);

    public Worker getWorker(String workerName);

    public void deleteWorker(String name);

    public List<Project> getAllProjectsByWorkerName(String workerName);
}
