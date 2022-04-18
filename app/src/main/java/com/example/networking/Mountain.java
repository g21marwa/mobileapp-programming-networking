package com.example.networking;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Mountain implements Serializable {
    public String id;
    public String name;
    public String type;
    public String company;
    public String location;
    public String category;
    public int size;
    public int cost;
    public HashMap<String, String> auxdata;

    @Override
    public String toString() {
        return "Mountain{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", category='" + category + '\'' +
                ", size=" + size +
                ", cost=" + cost +
                ", auxdata=" + auxdata +
                '}';
    }

    public Mountain(String id, String name, String type, String company, String location, String category, int size, int cost, HashMap<String, String> auxdata) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.company = company;
        this.location = location;
        this.category = category;
        this.size = size;
        this.cost = cost;
        this.auxdata = auxdata;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public HashMap<String, String> getAuxdata() {
        return auxdata;
    }

    public void setAuxdata(HashMap<String, String> auxdata) {
        this.auxdata = auxdata;
    }
}