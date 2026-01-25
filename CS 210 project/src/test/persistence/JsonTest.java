package persistence;

import static org.junit.Assert.assertEquals;

import model.Excercise;

public class JsonTest {
    protected void checkExcercise(String name, String category, int difficulty, Excercise excercise) {
        assertEquals(name, excercise.getName());
        assertEquals(category, excercise.getCategory());

    }

}
