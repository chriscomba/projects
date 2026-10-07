package persistence;

import model.Excercise;
import model.Gym;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: Constructs reader to read from source
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: Reads Gym from files and returns it.
    // throws IOException if an error occurs reading data from file
    public Gym read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGym(jsonObject);
    }

    // EFFECTS: Reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses gym from JSON object and returns it

    private Gym parseGym(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Excercise current = new Excercise("Squats", "Legs", 2);
        ArrayList<Excercise> excercises = new ArrayList<>();

        Gym g = new Gym(name, current, excercises);
        addExcercises(g, jsonObject);
        addWorkoutHistorys(g, jsonObject);
        return g;
    }

    private void addExcercises(Gym g, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("excercises");
        for (Object json : jsonArray) {
            JSONObject nextExcercise = (JSONObject) json;
            addExcercise(g, nextExcercise);
        }
    }

    private void addWorkoutHistorys(Gym g, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("workoutHistory");
        for (Object json : jsonArray) {
            JSONObject nextExcercise = (JSONObject) json;
            addWorkoutHistory(g, nextExcercise);
        }

    }

    // MODIFIES: Gym
    // EFFECTS: parses excercise from JSON object and adds it to gym
    private void addExcercise(Gym g, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String category = jsonObject.getString("category");
        int difficulty = jsonObject.getInt("difficulty");
        Excercise excercise = new Excercise(name, category, difficulty);
        g.addExcercise(excercise);
    }

    private void addWorkoutHistory(Gym g, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String category = jsonObject.getString("category");
        int difficulty = jsonObject.getInt("difficulty");
        Excercise excercise = new Excercise(name, category, difficulty);
        g.logWorkoutSession(excercise);
    }
}
