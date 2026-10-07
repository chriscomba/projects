package model;

import org.json.JSONObject;

import persistence.Writable;

public class Excercise implements Writable{
    private String name; // name of excercise
    private String category; // category of excercise
    private int difficulty;  // difficulty of excercise


    public Excercise(String name ,String category, Integer difficulty){
        this.name = name;
        this.category = category;
        this.difficulty = difficulty;
    }

    public String getName(){
        return this.name;
    }
    public String getCategory(){
        return this.category ;
    }
    public int getDifficulty(){
        return this.difficulty;
    }
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("category",category);
        json.put("difficulty",difficulty);
        return json;

    }
}

