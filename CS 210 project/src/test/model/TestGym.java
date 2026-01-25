package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestGym {
    private Gym gym;
    private Excercise excercise;
    private ArrayList<Excercise> exercises;
    private Excercise excercise1;
    //private ArrayList<Excercise> suggestedExcercises;
    

@BeforeEach
void runBefore(){
    this.exercises = new ArrayList<>();
    this.exercises.add(excercise);
    this.excercise1 = new Excercise("squats","legs" , 2);
    this.exercises.add(excercise1);
    this.gym = new Gym("User1",excercise, exercises);
    //this.suggestedExcercises = new ArrayList<>();
    //gym.initialiseSuggestedExcercises();
    
}


@Test
    public void testGetName() {
        assertEquals("User1", gym.getName());
    }

@Test
    public void testGetCurrent() {
        assertEquals(excercise, gym.getCurrent());
    }
@Test 
    public void testgetRemainingExcercises  (){
        assertEquals(2, gym.getRemainingExcercises().size());
        exercises.remove(excercise1);
        assertEquals(1, gym.getRemainingExcercises().size());
        exercises.add(excercise1);
        assertEquals(2, gym.getRemainingExcercises().size());

    }  
@Test
    public void testLogWorkoutSession() {
        assertTrue(gym.getRemainingExcercises().contains(excercise));
        assertFalse(gym.getWorkoutHistory().contains(excercise));
        gym.logWorkoutSession(excercise);
        assertFalse(gym.getRemainingExcercises().contains(excercise));
        assertTrue(gym.getWorkoutHistory().contains(excercise));



    }
@Test
    public void testAddExcercise() {
        assertEquals(2, gym.getRemainingExcercises().size());
        gym.addExcercise(excercise);
        assertEquals(3, gym.getRemainingExcercises().size());
        assertTrue(gym.getRemainingExcercises().contains(excercise));
    }
@Test
    public void testgetWorkoutHistory(){
        assertEquals (0, gym.getWorkoutHistory().size());
        gym.logWorkoutSession(excercise);
        assertTrue (gym.getWorkoutHistory().contains(excercise));
        assertEquals (1, gym.getWorkoutHistory().size());
        gym.logWorkoutSession(excercise1);
        assertEquals (2, gym.getWorkoutHistory().size());
    }
@Test
    public void testInitializeSuggestedExcercises() {
        assertEquals(10, gym.suggestExcercise().size());
        gym.suggestExcercise().clear();
        assertEquals(0, gym.suggestExcercise().size());
        gym.initialiseSuggestedExcercises();
        assertEquals(10, gym.suggestExcercise().size());



     }
@Test
      public void testGetSuggestedExercises() {
        gym.suggestExcercise().clear();
        assertEquals(0, gym.suggestExcercise().size());
        gym.suggestExcercise();
        assertEquals(0, gym.suggestExcercise().size());
        gym.initialiseSuggestedExcercises();
        gym.suggestExcercise();
        assertEquals(10, gym.suggestExcercise().size());     
      }  

    }

