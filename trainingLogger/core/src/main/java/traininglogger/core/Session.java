package traininglogger.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;


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
    this.setDescription(description);
    this.addExercises(exercises);
  }

  public void setDescription(String description) {
    if(description.length() > 60) {
      throw new IllegalArgumentException("The description can only contain 60 letters.");
    }
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
    if(this.exercises.size() + exercises.length > 20) {
      throw new IllegalArgumentException("You have added to many exercises.");
    }
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
    LocalDateTime today = LocalDateTime.now();
    if(d.isAfter(today)) {
      throw new IllegalStateException("The date cannot be in the future.");
    }
    this.date = d;
  }

  @Override
  public Iterator<Exercise> iterator() {
    return this.exercises.iterator();
  }

  public String toString() {
    return this.getDateAsString() + this.exercises.toString();
  }
}
