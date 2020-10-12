package traininglogger.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Et exercise-objekt skal ta vare på informasjonen rundt en øvelse.
 * Objektet inneholder informanjon om:
 * - Øvelsens navn
 * - Antall sett
 * - Hvor mye vekt og hvor mange reps hvert sett består av.
 */
public class Exercise {

  private List<Integer[]> sets = new ArrayList<Integer[]>();
  private String exerciseName;

  /**
   * Tom konstruktør som kun instansierer et objekt uten noe informasjon
   */
  public Exercise() {

  }
  
  /**
   * Konstruktør som tar initsialiserer objektet med navn på øvelsen og en array med sett.
   * @param String name
   * @param Integer[] integers
   */
  public Exercise(String name, Integer... integers) {
    this.exerciseName = name;
    this.addSets(integers);
  }

  /**
   * Metode for å legge til sett til et exercise-objekt. Denne krever at antall argumenter som tas inn
   * er delelig på 2, slik at man kan koble en vekt mot et antall reps.
   *
   * @param integers: Tar inn et array ov integeres. Må være delelig på 2.
   */
  public void addSets(Integer... integers) {
    if (integers.length % 2 != 0) {
      throw new IllegalArgumentException("The number of integers most be even.");
    }
    for (int i = 0; i < integers.length; i = i + 2) {
      Integer[] t = { integers[i], integers[i + 1] };
      this.sets.add(t);
    }
  }

  /**
   * Fjerner sett nr. i fra liten med sett.
   * Fjerner et sett basert på indexen som tas inn.
   *
   * @param i: indexen til settet som skal fjernes
   */
  public void removeSet(int i) {
    this.sets.remove(i);
  }

  /**
   * @param i
   * @return Sett nr. i
   */
  public Integer[] getSet(int i) {
    return this.sets.get(i);
  }

  public String getName() {
    return this.exerciseName;
  }

  /**
   * @return En liste bestående av alle settene som er utført.
   */
  public void setName(String name) {
    this.exerciseName = name;
  }

  public Collection<Integer[]> getSets() {
    return this.sets.stream().collect(Collectors.toList());
  }

  public boolean equals(Object object) {
    if(!(object instanceof Exercise)){
      return false;
    }
    Exercise obj = (Exercise) object;
    if(Exercise.isEqual(obj.getSets(), this.getSets()) &&
    this.getName().equals(obj.getName())){
      return true;
    }
    return false;
  }

  /**
   * Statisk metode som sammenlikner to lister av heltall.
   * @param col1 
   * @param col2
   * @return true eller false, avhengig av om listene består av de samme elementene.
   */
  public static boolean isEqual(Collection<Integer[]> col1, Collection<Integer[]> col2) {
    if (col1.size() != col2.size()) {
      return false;
    }
    Iterator<Integer[]> iterator1 = col1.iterator();
    Iterator<Integer[]> iterator2 = col2.iterator();
    while (iterator1.hasNext() && iterator2.hasNext()) {
      Integer[] t1 = iterator1.next();
      Integer[] t2 = iterator2.next();
      for (int j = 0; j < 2; j++) {
        if (!t1[j].equals(t2[j])) {
          return false;
        }
      }
    }
    return true;
  }

  // Denne implementasjonen er bare anbefalt dersom man aldri ser for seg å
  // plassere exercise-objekter
  // i et HashMap eller en HashTable.
  @Override
  public int hashCode() {
    assert false : "hashCode not designed";
    return 1;
  }

}