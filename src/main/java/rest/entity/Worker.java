package rest.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Worker {

    private int id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Project> projectList;

    public Worker() {
    }

    public Worker(String name, List<Project> projectList) {
        this.name = name;
        this.projectList = projectList;
    }

    public Worker(int id, String name, List<Project> projectList) {
        this.id = id;
        this.name = name;
        this.projectList = projectList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", projectList=" + projectList +
                '}';
    }
}
