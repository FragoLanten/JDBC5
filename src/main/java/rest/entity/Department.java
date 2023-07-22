package rest.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Department {
    private int id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Worker> listOfWorkers;

    public Department() {
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

    public List<Worker> getListOfWorkers() {
        return listOfWorkers;
    }

    public void setListOfWorkers(List<Worker> listOfWorkers) {
        this.listOfWorkers = listOfWorkers;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", listOfWorkers=" + listOfWorkers +
                '}';
    }
}
