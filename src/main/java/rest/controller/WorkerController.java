package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.entity.Worker;
import rest.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/workers")

public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Worker>> getAll() {

        List<Worker> workers = workerService.getAllWorkers();

        if (workers == null || workers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping(value="/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Worker> getWorker(@PathVariable String name) {
        Worker worker = workerService.getWorker(name);
        return new ResponseEntity<>(worker, HttpStatus.OK);
    }
}
