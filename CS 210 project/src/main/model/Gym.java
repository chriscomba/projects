package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
// The Gym class represents a workout management system for gym users.
// It keeps track of the current exercise, the exercises in the workout session,
// the workout history, and suggested exercises for the user.

public class Gym implements Writable {
    private String name; // name of the user
    private Excercise current; // current excercise the user is doing
    private ArrayList<Excercise> excercises; // list of excercises left in workout
    private ArrayList<Excercise> workoutHistory; // list of excercises in the workout history
    private ArrayList<Excercise> suggestedExcercises; // list of suggested excercises to the user

    // REQUIRES: Gym name to not be empty
    // EFFECTS: name on account is set as the Name.
    // Displays remaining excercises

    public Gym(String name, Excercise excercise, ArrayList<Excercise> excercises) {
        this.name = name;
        this.current = excercise;
        this.excercises = excercises;
        this.workoutHistory = new ArrayList<>();
        this.suggestedExcercises = new ArrayList<>();
        initialiseSuggestedExcercises();

    }

    public void initialiseSuggestedExcercises() {
        suggestedExcercises.add(new Excercise("Push-Ups", "Chest", 1));
        suggestedExcercises.add(new Excercise("Squats", "Legs", 2));
        suggestedExcercises.add(new Excercise("Pull-Ups", "Back", 3));
        suggestedExcercises.add(new Excercise("Lunges", "Legs", 2));
        suggestedExcercises.add(new Excercise("Bench-Press", "Chest", 3));
        suggestedExcercises.add(new Excercise("Deadlift", "Back", 3));
        suggestedExcercises.add(new Excercise("Bicep-Curls", "Arms", 1));
        suggestedExcercises.add(new Excercise("Tricep-Dips", "Arms", 2));
        suggestedExcercises.add(new Excercise("Plank", "Core", 1));
        suggestedExcercises.add(new Excercise("Russian-Twists", "Core", 2));
    }

    public String getName() {
        return this.name;
    }

    public Excercise getCurrent() {
        return this.current;
    }

    public ArrayList<Excercise> getRemainingExcercises() {
        return this.excercises;
    }

    // REQUIRES: workoutSession to not be null
    // MODIFIES: this
    // EFFECTS: adds a new excercise to workout history and removes excercise from
    // excercises
    public void logWorkoutSession(Excercise excercise) {
        workoutHistory.add(excercise);
        excercises.remove(excercise);
        EventLog.getInstance().logEvent(new Event(excercise.getName() +" has been logged"));
        EventLog.getInstance().logEvent(new Event(excercise.getName() +" has been added to workout history"));
    }

    // REQUIRES: workout history to exist
    // EFFECTS: returns list of logged excercises
    public ArrayList<Excercise> getWorkoutHistory() {
        return this.workoutHistory;
    }

    // REQUIRES: this
    // EFFECTS: suggests Excercises for user to add to workout
    public ArrayList<Excercise> suggestExcercise() {
        return this.suggestedExcercises;
    }

    // MODIFIES: this
    // EFFECTS adds suggested excercise to remaining excercises
    public void addExcercise(Excercise excercise) {
        this.excercises.add(excercise);
        EventLog.getInstance().logEvent(new Event(excercise.getName() +" has been added to exercises"));
    }
    @Override
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("excercise", current.toJson());
        json.put("excercises", excercisestoJson());
        json.put("workoutHistory", workoutHistoryToJson());
        return json;
    }
    private JSONArray excercisestoJson(){
        JSONArray jsonArray = new JSONArray();
        for (Excercise e :excercises){
            jsonArray.put(e.toJson());
        }
        return jsonArray;

    }
    private JSONArray workoutHistoryToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Excercise e : workoutHistory) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }

}
