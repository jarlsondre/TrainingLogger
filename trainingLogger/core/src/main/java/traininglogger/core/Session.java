package traininglogger.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * En Session representerer en treningsøkt. Et Session-objekt spesifiseres ved:
 * <ul>
 * <li>En beskrivelse av treningsøkten</li>
 * <li>Datoen treningsøkten foregikk</li>
 * <li>En liste med Exercise-objekter</li>
 * </ul>
 * 
 * <p>
 * Datoen er på formatet dd/MM/yyyy HH:mm
 * </p>
 */
public class Session implements Iterable<Exercise> {

  private String description;
  private LocalDateTime date;
  private final DateTimeFormatter dateTimeFormatter; // kan endre hvis klokkeslett trengs
  private List<Exercise> exercises = new ArrayList<>();

  // Konstruktor som ikke tar inn beskrivelse.
  public Session() {
    this.date = LocalDateTime.now();
    this.dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
  }

  /**
   * Konstruerer en treningsøkt bestående av gitte øvelser og som har en gitt
   * beskrivelse.
   *
   * @param description en beskrivelse av treningsøkta
   * @param exercises   øvelsene som treningsøkta består av
   */
  public Session(String description, Exercise... exercises) {
    this();
    this.description = description;
    this.addExercises(exercises);
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }

  /**
   * Utivder denne treningsøkta med flere øvelser.
   *
   * @param exercises øvelsene som økta skal utvides med
   */
  public void addExercises(Exercise... exercises) {
    for (Exercise e : exercises) {
      this.exercises.add(e);
    }
  }

  public LocalDateTime getDate() {
    return this.date;
  }

  /**
   * Returnerer datoen en økt foregikk, representert som et String-objekt.
   *
   * @return datoen som økten foregikk
   */
  public String getDateAsString() {
    return this.date.format(this.dateTimeFormatter);
  }

  /**
   * Tar inn en streng på formen "dd/MM/yyyy HH:mm", og setter atributten "date"
   * lik datoen strengen beskriver.
   *
   * @param date datoen som økten foregikk som en formatert streng.
   */
  public void setDate(String date) {
    LocalDateTime d = LocalDateTime.parse(date, this.dateTimeFormatter);
    this.date = d;
  }

  @Override
  public Iterator<Exercise> iterator() {
    return this.exercises.iterator();
  }

  /**
   * Sammenlikner dette Session-objektet med et annet objekt. Resultatet av
   * sammenlikningen er sann (true) hvis og bare hvis argumentet også er et
   * Session-objekt, de to objektenes dato (representert som en String) er like og
   * hvis øvelsene (Exercise) som øktene består av vurderes som like.
   *
   * @param object Objektet som instansen skal sammenliknes med
   * @return true dersom det andre objektet er et ekvivalent Session-objekt, false
   *         ellers
   */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Session)) {
      return false;
    }
    Session theOtherSession = (Session) object;
    return this.getDateAsString().equals(theOtherSession.getDateAsString())
        && this.exercises.equals(theOtherSession.exercises);
  }

  // Denne implementasjonen er bare anbefalt dersom man aldri ser for seg å
  // plassere Session-objekter
  // i et HashMap eller en HashTable.
  @Override
  public int hashCode() {
    assert false : "hashCode not designed";
    return 1;
  }

  public String toString() {
    return this.getDateAsString() + this.exercises.toString();
  }
}
