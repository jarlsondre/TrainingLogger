package traininglogger.core;

/**
 * Et Set er den "atomiske enheten" i en treningsøkt (Session). Et Set
 * representerer at en utøver løfter et visst antall kg (weight), et visst
 * antall ganger (repetitions). En treningsøkt (Session) består av flere øvelser
 * (Exercise) som i sin tur består av flere Set.
 */

public class Set {

  private final int repetitions;
  private final double weight;

  /**
   * Konstruerer et Set med et gitt antall repetisjoner og en gitt vekt i kg. Maksimal
   * vekt som kan legges inn er 1000kg og maksimalt antall repetisjoner som kan legges
   * inn er 1000
   *
   * @param repetitions antallet repetisjoner
   * @param weight      antallet kilo
   */
  public Set(int repetitions, double weight) {
    if (repetitions > 1000) {
      throw new IllegalArgumentException("Antall repetisjoner kan ikke være høyere enn 1000");
    }
    if (weight > 1000) {
      throw new IllegalArgumentException("Vekt kan ikke være over 1000kg");
    }
    this.repetitions = repetitions;
    this.weight = weight;
  }

  /**
   * Returnerer antallet repetisjoner som dette settet besto av.
   *
   * @return repetitions antallet repetisjoner
   */
  public int getRepetitions() {
    return this.repetitions;
  }

  /**
   * Returnerer antallet kg som ble løftet i dette settet.
   *
   * @return weight antallet kilo
   */
  public double getWeight() {
    return this.weight;
  }

  @Override
  public String toString() {
    return "repetitions: " + repetitions + ", weight: " + weight;
  }
}