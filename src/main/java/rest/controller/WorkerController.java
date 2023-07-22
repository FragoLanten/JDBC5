package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.dao.WorkerDAO;
import rest.dao.WorkerDAOImpl;
import rest.entity.Worker;

import java.util.List;

@RestController
@RequestMapping("/workers")

public class WorkerController {

    @Autowired
    private WorkerDAO workerDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Worker>> getAll() {

        List<Worker> workers = workerDAO.getAllWorkers();

        if (workers == null || workers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping(value="/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Worker> getWorker(@PathVariable String name) {
        Worker worker = workerDAO.getWorker(name);
        return new ResponseEntity<>(worker, HttpStatus.OK);
    }
}
