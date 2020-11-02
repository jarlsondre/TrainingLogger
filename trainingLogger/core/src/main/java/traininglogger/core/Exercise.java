package traininglogger.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * En Exercise er en navngitt treningsøvelse. En Exercise består av flere Set.
 */
public class Exercise implements Iterable<Set> {

  private List<Set> sets = new ArrayList<>();
  private String name;

  /**
   * Tom konstruktør som kun instansierer et objekt uten noe informasjon.
   */
  public Exercise() {

  }
  
  /**
   * Konstruktør som instansierer objektet med navn på øvelsen og en array med sett.
   *
   * @param name Et navn på øvelsen.
   * @param sets En array med set som objektet skal bestå av.
   */
  public Exercise(String name, Set... sets) {
      this.setName(name);
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
   * Returnerer øvelsens navn.
   *
   * @return øvelsens navn
   */
  public String getName() {
    return this.name;
  }

  /**
   * Endrer øvelsens navn.
   *
   * @param name øvelsens nye navn
   */
  public void setName(String name) {
      Pattern p = Pattern.compile("[^a-zA-Z]");
      Matcher m = p.matcher(name);
      if ((name.length() > 20) || m.find()) {
          throw new IllegalArgumentException();
      }
      this.name = name;
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