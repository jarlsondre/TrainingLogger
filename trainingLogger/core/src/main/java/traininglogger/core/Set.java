package traininglogger.core; 


/**
 * Et set-objekt tar vare på informasjonen om et set, henholdsvis antall repetisjoner og vekt.
 */
public class Set {

  private int repetitions;
  private double weight;

  // Denne er jeg usikker på om er nødvendig. Lar den stå midlertidig.
  public Set() {
  }

  /**
   * Konstruktør som instansierer objektet med antall repetisjoner og vekt.
   * @param repetitions En integer som representerer antall reps
   * @param weight En double som representerer vekt.
   */
  public Set(int repetitions, double weight){
    this.repetitions = repetitions;
    this.weight = weight; 
  }

  /**
   * @return reps En integer som representerer antall reps
   */
  public int getRepetitions() {
    return this.repetitions;
  }

  /**
   * 
   * @return weight En double som representerer vekt.
   */
  public double getWeight() {
    return this.weight; 
  }

  /**
   * @param object Sjekker om object er lik dette objektet
   */
  @Override
  public boolean equals(Object object) {
    if(!(object instanceof Set)) {
      return false;
    }
    Set set = (Set) object;
    return set.getWeight() == this.getWeight() && set.getRepetitions() == this.getRepetitions();
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