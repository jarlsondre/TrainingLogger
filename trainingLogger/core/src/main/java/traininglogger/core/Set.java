package traininglogger.core; 

/**
 * Et Set er den "atomiske enheten" i en treningsøkt (Session). 
 * Et Set representerer at en utøver løfter et visst antall kg (weight) et visst antall ganger (repetitions).
 * En treningsøkt (Session) består av flere øvelser (Exercise) som i sin tur består av flere Set. 
 */

public class Set {

  private final int repetitions;
  private final double weight;

  /**
   * Konstruerer et Set med et gitt antall repetisjoner og en gitt vekt i kg
   * @param repetitions antallet repetisjoner
   * @param weight antallet kilo
   */
  public Set(int repetitions, double weight){
    this.repetitions = repetitions;
    this.weight = weight; 
  }

  /**
   * @return repetitions antallet repetisjoner
   */
  public int getRepetitions() {
    return this.repetitions;
  }

  /**
   * 
   * @return weight antallet kilo
   */
  public double getWeight() {
    return this.weight; 
  }

  /**
   * @param object objektet man sammenlikner med
   */
  @Override
  public boolean equals(Object object) {
    if(!(object instanceof Set)) {
      return false;
    }
    Set set = (Set) object;
    return set.getRepetitions() == this.getRepetitions() && set.getWeight() == this.getWeight();
  }

  // Denne implementasjonen er bare anbefalt dersom man aldri ser for seg å
  // plassere set-objekter
  // i et HashMap eller en HashTable.
  @Override
  public int hashCode() {
    assert false : "hashCode not designed";
    return 1;
  }

  @Override
  public String toString() {
    return "repetitions: " + repetitions + ", weight: " + weight;
  }
}