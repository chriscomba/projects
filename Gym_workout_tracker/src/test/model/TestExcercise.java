package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestExcercise {
    private Excercise excercise;
    

    @BeforeEach
    void runBefore(){
        excercise = new Excercise("push ups","chest" , 1);
    }
    @Test 
    public void testgetName(){
        assertEquals("push ups", excercise.getName());
    }

    @Test
     public void testCategory(){
        assertEquals("chest", excercise.getCategory());
     }
    @Test
    public void testgetDifficulty(){
        assertEquals(1, excercise.getDifficulty());
    }


}
