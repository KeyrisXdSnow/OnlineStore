package model.beans;

import model.entities.Specification;

import java.io.Serializable;

public class Product implements Serializable {

    private double cost;
    private String name;
    private Specification specification;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }
}
