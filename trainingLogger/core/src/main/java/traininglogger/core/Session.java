package traininglogger.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * En Session representerer en treningsøkt. Et Session-objekt spesifiseres ved:
 * - En beskrivelse av treningsøkten. - Datoen treningsøkten foregikk, lagret
 * som et LocalDateTime objekt. - En liste med Exercise-objekter. Disse
 * representerer øvelsene treningsøkta besto av.
 * 
 * Datoen er på formatet dd/MM/yyyy HH:mm.
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

  // Konstruktør som tar inn beskrivelse.
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

  public void addExercises(Exercise... exercises) {
    for (Exercise e : exercises) {
      this.exercises.add(e);
    }
  }

  public LocalDateTime getDate() {
    return this.date;
  }

  /**
   * @return Datoen økten foregikk som en formatert streng
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
   * Sammenlikner dette Session-objektet med object.
   * 
   * @param object Objektet som instansen skal sammenliknes med
   * @return true dersom objektene har samme formatterte dato (String) og de to
   *         exercises-attributtene evalueres til å være like.
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
}
