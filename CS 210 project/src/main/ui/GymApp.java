package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.EventLog;
import model.Excercise;
import model.Gym;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class GymApp {
    private static final String JSON_STORE = "./data/gym.json";
    private Gym gym;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    

    // EFFECTS: runs the gym application
    public GymApp() {
        input = new Scanner(System.in);
        Excercise excercise1 = new Excercise("Squats", "Legs", 2);
        ArrayList<Excercise> excercises = new ArrayList<>();
        excercises.add(excercise1);
        gym = new Gym("Chris", excercise1, excercises);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGym();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGym() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        EventLog.getInstance().printLog();
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("v")) {
            viewExercises();
        } else if (command.equals("a")) {
            addExcercise();
        } else if (command.equals("l")) {
            logWorkoutSession();
        } else if (command.equals("s")) {
            viewSuggestedExercises();
        } else if (command.equals("h")) {
            viewWorkoutHistory();
        } else if (command.equals("save")) {
            saveGym();
        } else if (command.equals("load")) {
            loadGym();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes Gym and Scanner with squat excercise as default
    // excercise
    private void init() {
        ArrayList<Excercise> exercises = new ArrayList<>();
        Excercise excercise1 = new Excercise("Squats", "Legs", 2);
        exercises.add(excercise1);

        gym = new Gym("User1", excercise1, exercises);
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view exercises");
        System.out.println("\ta -> add an exercise");
        System.out.println("\tl -> log an excercise");
        System.out.println("\ts -> view suggested exercises");
        System.out.println("\th -> view workout history");
        System.out.println("\tsave -> save gymtracker to file");
        System.out.println("\tload -> load gymtracker from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays the list of current exercises
    private void viewExercises() {
        ArrayList<Excercise> exercises = gym.getRemainingExcercises();
        if (exercises.isEmpty()) {
            System.out.println("No exercises remaining.");
        } else {
            for (Excercise e : exercises) {
                System.out.println(e.getName() + " - " + e.getCategory() + " - Difficulty: " + e.getDifficulty());
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new exercise to the gym
    private void addExcercise() {
        System.out.println("Enter exercise name:");
        String name = input.next();
        ArrayList<Excercise> suggestedExcercises = gym.suggestExcercise();
        for (Excercise e : suggestedExcercises) {
            if (e.getName().equals(name)) {
                gym.addExcercise(e);
                System.out.println("Excercise added successfully!");

            }
        }

    }

    // MODIFIES: this
    // EFFECTS: logs a workout session
    private void logWorkoutSession() {
        System.out.println("Enter exercise to log:");
        String name = input.next();

        ArrayList<Excercise> exercises = gym.getRemainingExcercises();
        for (Excercise e : exercises) {
            if (e.getName().equals(name)) {
                gym.logWorkoutSession(e);
                System.out.println("Workout session logged!");
                return;
            }
        }
        System.out.println("Exercise not found.");
    }

    // EFFECTS: displays suggested exercises
    private void viewSuggestedExercises() {
        ArrayList<Excercise> suggested = gym.suggestExcercise();
        System.out.println("Suggested Exercises:");
        for (Excercise e : suggested) {
            System.out.println(e.getName() + " - " + e.getCategory() + " - Difficulty: " + e.getDifficulty());
        }
    }

    // EFFECTS: displays workout history
    private void viewWorkoutHistory() {
        ArrayList<Excercise> history = gym.getWorkoutHistory();
        if (history.size() == 0) {
            System.out.println("No workouts logged.");
        } else {
            System.out.println("Workout History:");
            for (Excercise e : history) {
                System.out.println(e.getName() + " - " + e.getCategory() + " - Difficulty: " + e.getDifficulty());
            }
        }
    }

    // EFFECts: saves Gym to the file
    private void saveGym() {
        try {
            jsonWriter.open();
            jsonWriter.write(gym);
            jsonWriter.close();
            System.out.println("Saved " + gym.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Gym from file
    private void loadGym() {
        try {
            gym = jsonReader.read();
            System.out.println("Loaded " + gym.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
    public static void main(String[] args) {
        new GymApp();}

}
