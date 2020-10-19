package traininglogger.core; 


/**
 * Et set-objekt tar vare på informasjonen om et set, henholdsvis antall repetisjoner og vekt.
 */
public class Set {

  private int reps; 
  private double weight;

  // Denne er jeg usikker på om er nødvendig. Lar den stå midlertidig.
  public Set() {
  }

  /**
   * Konstruktør som instansierer objektet med antall repetisjoner og vekt.
   * @param reps En integer som representerer antall reps
   * @param weight En double som representerer vekt.
   */
  public Set(int reps, double weight){
    this.reps = reps; 
    this.weight = weight; 
  }

  /**
   * @return reps En integer som representerer antall reps
   */
  public int getReps() {
    return this.reps; 
  }

  /**
   * 
   * @return weight En double som representerer vekt.
   */
  public double getWeight() {
    return this.weight; 
  }

  /**
   * 
   * @param reps En integer som representerer antall reps
   */
  public void setReps(int reps) {
    this.reps = reps;
  }

  /**
   * 
   * @param weight En double som representerer vekt.
   */
  public void setWeight(double weight) {
    this.weight = weight;
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
    return set.getWeight() == this.getWeight() && set.getReps() == this.getReps();
  }

  // Denne implementasjonen er bare anbefalt dersom man aldri ser for seg å
  // plassere set-objekter
  // i et HashMap eller en HashTable.
  @Override
  public int hashCode() {
    assert false : "hashCode not designed";
    return 1;
  }

}