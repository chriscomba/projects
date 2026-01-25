package persistence;

import model.Excercise;
import model.Gym;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWritertest extends JsonTest {
    private Excercise excercise1;
    private ArrayList<Excercise> excercises;

    @Test
    void testWriterInvalidFile() {
        try {
            Gym g = new Gym("Chris", excercise1, excercises);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            //fail("IOException was expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testWriterEmptyGym() {
        try {
            Excercise excercise1 = new Excercise("Squats", "Legs", 2);
            ArrayList<Excercise> excercises = new ArrayList<>();
            Gym g = new Gym("Chris", excercise1, excercises);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGym.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGym.json");
            g = reader.read();
            assertEquals("Chris", g.getName());
            assertEquals(0, g.getRemainingExcercises().size());
        } catch (IOException e) {
            //fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGym() {
        try {
            Excercise excercise1 = new Excercise("Squats", "Legs", 2);
            ArrayList<Excercise> excercises = new ArrayList<>();
            Gym g = new Gym("Chris", excercise1, excercises);
            g.addExcercise(excercise1);
            g.addExcercise(new Excercise("push ups", "chest", 1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGym.json");
            writer.open();
            writer.write(g);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGym.json");
            g = reader.read();
            assertEquals("Chris", g.getName());
            List<Excercise> excercises1 = g.getRemainingExcercises();
            List<Excercise> workoutHistory = g.getWorkoutHistory();
            assertEquals(2, excercises1.size());
            checkExcercise("Squats", "Legs", 2, excercises.get(0));
            ;
            checkExcercise("push ups", "chest", 1, excercises.get(1));
            ;
            ;

        } catch (IOException e) {
            //fail("Exception should not have been thrown");
        }
    }

}
