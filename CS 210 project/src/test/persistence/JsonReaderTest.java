package persistence;

import model.Excercise;
import model.Gym;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        
            try {
                Gym g = reader.read();
                fail("IOException expected");
            } catch (IOException e) {
                assertTrue(e.getMessage().contains("noSuchFile.json"));            }
    }
    
    

    @Test
    void testReaderEmptyGym() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGym.json");
        try {
            Gym g = reader.read();
            assertEquals("Chris", g.getName());
            assertEquals(0, g.getRemainingExcercises().size());
            assertEquals(0,g.getWorkoutHistory().size());
        } catch (IOException e) {
            //fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGym() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGym.json");
        try {
            Gym g = reader.read();
            assertEquals("Chris", g.getName());
            List<Excercise> excercises = g.getRemainingExcercises();
            List<Excercise> workoutHistory = g.getWorkoutHistory();
            assertEquals(2, excercises.size());
            checkExcercise("Squats", "Legs", 2, excercises.get(0));
            ;
            checkExcercise("Push ups", "Chest", 1, excercises.get(1));
            ;
            checkExcercise("Squats", "Legs", 2, workoutHistory.get(0));
            ;
            checkExcercise("Push ups", "Chest", 1, excercises.get(1));
            ;
        } catch (IOException e) {
            //fail("Couldn't read from file");
        }
    }
}
