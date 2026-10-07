package ui;

import model.EventLog;
import model.Excercise;
import model.Gym;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Gym_gui extends JPanel {
    private static final String JSON_STORE = "./data/gym.json";
    private Gym gym;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private DefaultListModel<String> listModel;
    private JList<String> displayList;
    private JTextField excerciseNameField;
    private JTextField categoryField;
    private JTextField difficultyField;

    public Gym_gui() {
        // EFFECTS: Initialize Gym and JSON persistence
        Excercise defaultExcercise = new Excercise("Squats", "Legs", 2);
        ArrayList<Excercise> excercises = new ArrayList<>();
        excercises.add(defaultExcercise);
        gym = new Gym("User1", defaultExcercise, excercises);

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        setLayout(new BorderLayout());

        // EFFECTS: Initialize UI components
        listModel = new DefaultListModel<>();
        displayList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(displayList);

        excerciseNameField = new JTextField(10);
        categoryField = new JTextField(10);
        difficultyField = new JTextField(5);

        // EFFECTS: Create Buttons
        JButton viewExcercisesButton = new JButton("View Exercises");
        JButton addExcerciseButton = new JButton("Add Exercise");
        JButton logWorkoutButton = new JButton("Log Workout");
        JButton viewSuggestedButton = new JButton("View Suggested");
        JButton viewHistoryButton = new JButton("View History");
        JButton saveButton = new JButton("Save Gym");
        JButton loadButton = new JButton("Load Gym");
        JButton showGraphButton = new JButton("Show Graph");

        // EFFECTS: Add Action Listeners
        viewExcercisesButton.addActionListener(e -> viewExcercises());
        addExcerciseButton.addActionListener(e -> addExercise());
        logWorkoutButton.addActionListener(e -> logWorkout());
        viewSuggestedButton.addActionListener(e -> viewSuggestedExcercises());
        viewHistoryButton.addActionListener(e -> viewWorkoutHistory());
        saveButton.addActionListener(e -> saveGym());
        loadButton.addActionListener(e -> loadGym());
        showGraphButton.addActionListener(e -> showGraph());

        // EFFECTS: Create Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Exercise Name:"));
        inputPanel.add(excerciseNameField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Difficulty:"));
        inputPanel.add(difficultyField);
        inputPanel.add(addExcerciseButton);
        inputPanel.add(logWorkoutButton);

        // EFFECTS: Create Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
        buttonPanel.add(viewExcercisesButton);
        buttonPanel.add(viewSuggestedButton);
        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(showGraphButton);

        // EFFECTS: Adds components to the main panel
        add(listScrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        // EFFECTS: Initialize with current exercises
        viewExcercises();
        JFrame frame = new JFrame("Gym Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.pack();
        frame.setSize(600, 400);
        frame.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            EventLog.getInstance().printLog();
        } ));
    }

    // EFFECTS: Displays current exercises
    private void viewExcercises() {
        listModel.clear();
        List<Excercise> excercises = gym.getRemainingExcercises();
        if (excercises.isEmpty()) {
            listModel.addElement("No exercises available.");
        } else {
            for (Excercise e : excercises) {
                listModel.addElement(e.getName() + " - " + e.getCategory() + " - Difficulty: " + e.getDifficulty());
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: Adds a new exercise
    private void addExercise() {
        String name = excerciseNameField.getText();
        String category = categoryField.getText();
        int difficulty;

        try {
            difficulty = Integer.parseInt(difficultyField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Difficulty must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Excercise newExcercise = new Excercise(name, category, difficulty);
        gym.addExcercise(newExcercise);
        JOptionPane.showMessageDialog(this, "Excercise added successfully!", "Success",
                JOptionPane.INFORMATION_MESSAGE);
        viewExcercises();

        excerciseNameField.setText("");
        categoryField.setText("");
        difficultyField.setText("");
    }

    // MODIFIES: Exercise removed from exercise and added to workout history
    // EFFECTS: Logs a workout session
    private void logWorkout() {
        int selectedIndex = displayList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select an exercise to log!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Excercise> excercises = gym.getRemainingExcercises();
        if (selectedIndex < excercises.size()) {
            Excercise selectedExcercise = excercises.get(selectedIndex);
            gym.logWorkoutSession(selectedExcercise);
            JOptionPane.showMessageDialog(this, "Workout session logged for: " + selectedExcercise.getName(),
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            viewExcercises(); // Refresh the list after logging
        }
    }

    // EFFECTS: Displays suggested excercises
    private void viewSuggestedExcercises() {
        listModel.clear();
        List<Excercise> suggested = gym.suggestExcercise();
        if (suggested.isEmpty()) {
            listModel.addElement("No suggested excercises available.");
        } else {
            for (Excercise e : suggested) {
                listModel.addElement(e.getName() + " - " + e.getCategory() + " - Difficulty: " + e.getDifficulty());
            }
        }
    }

    // EFFECTS: Displays workout history
    private void viewWorkoutHistory() {
        listModel.clear();
        List<Excercise> history = gym.getWorkoutHistory();
        if (history.isEmpty()) {
            listModel.addElement("No workouts logged.");
        } else {
            for (Excercise e : history) {
                listModel.addElement(e.getName() + " - " + e.getCategory() + " - Difficulty: " + e.getDifficulty());
            }
        }
    }

    // EFFECTS: Saves the gym data
    private void saveGym() {
        try {
            jsonWriter.open();
            jsonWriter.write(gym);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Gym data saved successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving gym data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: Loads the gym data
    private void loadGym() {
        try {
            gym = jsonReader.read();
            viewExcercises();
            JOptionPane.showMessageDialog(this, "Gym data loaded successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading gym data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // REQUIRES: workoutHistory == null
    // MODIFIES: this
    // EFFECTS: Creates a new Pie chart of workouts in history
    private void showGraph() {
        Map<String, Integer> categoryCount = new HashMap<>();
        for (Excercise e : gym.getWorkoutHistory()) {
            categoryCount.put(e.getCategory(), categoryCount.getOrDefault(e.getCategory(), 0) + 1);
        }

        JFrame chartFrame = new JFrame("Exercise Category Pie Chart");
        chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chartFrame.setSize(600, 600);

        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int width = getWidth();
                int height = getHeight();

                int total = categoryCount.values().stream().mapToInt(Integer::intValue).sum();
                int startAngle = 0;

                int x = width / 4; // Center X for pie chart
                int y = height / 4; // Center Y for pie chart
                int diameter = Math.min(width, height) / 2; // Size of pie chart

                for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
                    int arcAngle = (int) ((entry.getValue() / (double) total) * 360);

                    g.setColor(getRandomColor());
                    g.fillArc(x, y, diameter, diameter, startAngle, arcAngle);

                    // Draw category labels
                    int midAngle = startAngle + arcAngle / 2;
                    int labelX = (int) (x + diameter / 2 + Math.cos(Math.toRadians(midAngle)) * diameter / 2.5);
                    int labelY = (int) (y + diameter / 2 - Math.sin(Math.toRadians(midAngle)) * diameter / 2.5);
                    g.setColor(Color.BLACK);
                    g.drawString(entry.getKey(), labelX, labelY);

                    startAngle += arcAngle;
                }
            }
        };

        chartFrame.add(chartPanel);
        chartFrame.setVisible(true);
    }

    private Color getRandomColor() {
        Random rand = new Random();
        return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }
}