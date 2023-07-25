package rest.dao;

import rest.entity.Project;
import rest.entity.Worker;

import java.util.List;

public interface WorkerDAO {
    public List<Worker> getAllWorkers();

    public void saveWorker(String workerName, int departmentId);

    public Worker getWorker(String workerName);

    public void deleteWorker(String workerName);

    public void updateWorker(int workerId, String workerName, int departmentId);

    public List<Project> getAllProjectsByWorkerName(String workerName);

    public Worker getWorkerWithProjects(String workerName);
}
