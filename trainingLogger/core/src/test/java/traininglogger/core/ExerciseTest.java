package traininglogger.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class ExerciseTest {


  @Test
  public void contructorTest(){
    Collection<Integer[]> col = new ArrayList<>();
    Integer[] set1 = {5,5}; 
    Integer[] set2 = {6,6};
    Integer[] set3 = {7,7};
    col.add(set1); 
    col.add(set2);
    col.add(set3);
    Exercise exercise = new Exercise("Knebøy", 5,5,6,6,7,7); 
    assertTrue(Exercise.isEqual(col, exercise.getSets()));
    try {
      new Exercise("Knebøy");
    } catch (Exception e) {
      fail();
    }
  } 

  @Test
  public void ConstructorIllegalArgumentTest(){
    try {
      new Exercise("Knebøy", 5,5,5);
      fail(); 
    } catch (Exception e) {
    }
    try {
      new Exercise("Knebøy", 5,5,5,5,5,4,4,4,4);
      fail();
    } catch (Exception e) {
    }
    try {
      new Exercise("Knebøy", 5);
      fail();
    } catch (Exception e) {
    }
  }
  
  @Test
  public void addSetsTest(){
    Exercise exercise = new Exercise("Knebøy", 5,5);
    exercise.addSets(2,2,3,3);
    Collection<Integer[]> col = new ArrayList<>();
    Integer[] set1 = {5,5}; 
    Integer[] set2 = {2,2};
    Integer[] set3 = {3,3};
    col.add(set1); 
    col.add(set2);
    col.add(set3);
    assertTrue(Exercise.isEqual(col, exercise.getSets()));
    assertTrue(Exercise.isEqual(new ArrayList<>(), new Exercise("Knebøy").getSets()));
    try {
      exercise.addSets(2,2,2);
      fail();
    } catch (Exception e) {
    }
  }

  @Test
  public void setsListTest(){
   Exercise exercise = new Exercise("Knebøy", 5,5);
   Collection<Integer[]> col = exercise.getSets(); 
   Integer[] i = {1,1};
   col.add(i); 
   assertFalse(Exercise.isEqual(exercise.getSets(), col));
  }



}