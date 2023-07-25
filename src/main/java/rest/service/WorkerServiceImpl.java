package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.dao.WorkerDAO;
import rest.entity.Project;
import rest.entity.Worker;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService{

    @Autowired
    private WorkerDAO workerDAO;

    @Override
    public List<Worker> getAllWorkers() {
        return workerDAO.getAllWorkers();
    }

    @Override
    public void saveWorker(String workerName, int departmentId) {
        workerDAO.saveWorker(workerName,departmentId);
    }

    @Override
    public Worker getWorker(String workerName) {
        return workerDAO.getWorker(workerName);
    }

    @Override
    public void deleteWorker(String workerName) {
        workerDAO.deleteWorker(workerName);
    }

    @Override
    public void updateWorker(int workerId, String workerName, int departmentId) {
        workerDAO.updateWorker(workerId, workerName, departmentId);
    }

    @Override
    public List<Project> getAllProjectsByWorkerName(String workerName) {
        return workerDAO.getAllProjectsByWorkerName(workerName);
    }

    @Override
    public Worker getWorkerWithProjects(String workerName) {
        return workerDAO.getWorkerWithProjects(workerName);
    }
}
