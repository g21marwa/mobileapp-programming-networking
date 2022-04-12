package com.example.networking;

import java.util.Map;

public class Mountain {
    public String id;
    public String name;
    public String type;
    public String company;
    public String location;
    public String category;
    public int size;
    public int cost;
    public Map<String, String> auxdata;

    public Mountain(String id, String name, String type, String company, String location, String category, int size, int cost, Map<String, String> auxdata) {
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
}