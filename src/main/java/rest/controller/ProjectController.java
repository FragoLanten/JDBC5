package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.dao.ProjectDAO;
import rest.entity.Project;
import rest.entity.Worker;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectDAO projectDAO;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Project>> getAll() {

        List<Project> projects = projectDAO.getAllProjects();

        if (projects == null || projects.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping(value="/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getWorker(@PathVariable String title) {
        Project project = projectDAO.getProject(title);

        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(project, HttpStatus.OK);
    }
}
