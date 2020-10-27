package traininglogger.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Et objekt av klassen "Session" tar vare på all informasjon rundt en økt. I
 * Ojektet inneholder: 
 * - En beskrivelse av økten. 
 * - Datoen økten foregikk, lagret som et LocalDateTime objekt.
 * - En liste med session-objekter, som beskriver hvilke øvelser som ble gjort.
 * Datoen skal være formet som
 * dette: dd/MM/yyyy HH:mm. Datoen blir generert automatisk ved opprettelse av
 * objektet.
 */
public class Session {


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

  /**
   * Gir ut en liste av alle exercise-objektebe dette session-objektet inneholder.
   * @return En liste med alle øvelsene som er gjort.
   */
  public Collection<Exercise> getListOfExercises(){
    return this.exercises.stream().collect(Collectors.toList());
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
   *Tar inn en streng på formen "dd/MM/yyyy HH:mm", og setter atributten "date"
   *lik datoen strengen beskriver. 
   *@param date datoen som økten foregikk som en formatert streng.
   */
  public void setDate(String date) {
    LocalDateTime d = LocalDateTime.parse(date, this.dateTimeFormatter);
    this.date = d;
  }

  /**
   * Sammenlikner dette objektet med objektet som blir tatt inn som argument.
   * Objektene er like dersom de har samme dato (representert som String) og består av de samme øvelsene.
   */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Session)) {
      return false;
    }
    Session theOtherSession = (Session) object;
    return this.getDateAsString().equals(theOtherSession.getDateAsString()) && this.exercises.equals(theOtherSession.exercises);
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
