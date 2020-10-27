package traininglogger.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * En Exercise er en navngitt treningsøvelse. En Exercise består av flere Set.
 */
public class Exercise implements Iterable<Set>{

  private List<Set> sets = new ArrayList<>();
  private String name;

  /**
   * Tom konstruktør som kun instansierer et objekt uten noe informasjon
   */
  public Exercise() {

  }
  
  /**
   * Konstruktør som instansierer objektet med navn på øvelsen og en array med sett.
   * @param name Et navn på øvelsen.
   * @param sets En array med set som objektet skal bestå av.
   */
  public Exercise(String name, Set... sets) {
    this.name = name;
    this.addSets(sets);
  }

  /**
   * Metode for å legge til sett til exercise-objektet. 
   *
   * @param sets Tar inn et array av sett.
   */
  public void addSets(Set...sets) {
    this.sets.addAll(Arrays.asList(sets));
  }

  /**
   * 
   * @return Henter navn på øvelsen.
   */
  public String getName() {
    return this.name;
  }

  /**
   * 
   * @param name Setter navn på øvelsen.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sammenlikner dette Exercise-objektet med object.
   * @param object Objektet som instansen skal sammenliknes med
   * @return true dersom objektene har samme navn og de to sets-attributtene evalueres til å være like
   */
  @Override
  public boolean equals(Object object) {
    if(!(object instanceof Exercise)){
      return false;
    }
    Exercise theOtherExercise = (Exercise) object;
    return this.name.equals(theOtherExercise.getName()) && this.sets.equals(theOtherExercise.sets);
  }

  // Denne implementasjonen er bare anbefalt dersom man aldri ser for seg å
  // plassere exercise-objekter
  // i et HashMap eller en HashTable.
  @Override
  public int hashCode() {
    assert false : "hashCode not designed";
    return 1;
  }

  @Override
  public String toString() {
    return this.name + sets.toString();
  }

  @Override
  public Iterator<Set> iterator() {
    return this.sets.iterator();
  }
}