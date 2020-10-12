package traininglogger.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Session {

  /*
   * Et objekt av klassen "Session" tar vare på all informasjon rundt en økt. I
   * førate omgang inneholder objektet: - En beskrivelse av økten. - Datoen økten
   * foregikk, lagret som et LocalDateTime objekt. Datoen skal være formet som
   * dette: dd/MM/yyyy HH:mm. Datoen blir generert automatisk ved opprettelse av
   * objektet.
   */

  private String description;
  private LocalDateTime date;
  private final DateTimeFormatter dtf; // kan endre hvis klokkeslett trengs
  private List<Exercise> exercises = new ArrayList<>();

  // Konstruktor som ikke tar inn beskrivelse.
  public Session() {
    date = LocalDateTime.now();
    dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
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


//Metoder på listen med exercise-objekter. 

  public void addExercises(Exercise... exercises) {
    for (Exercise e : exercises) {
      this.exercises.add(e);
    }
  }

  //Henter øvelse nr. i
  public Exercise getExercise(int i){
    return this.exercises.get(i);
  }

  public Collection<Exercise> getListOfExercises(){
    return this.exercises.stream().collect(Collectors.toList());
  }

  public void removeExercise(int i){
    this.exercises.remove(i);
  }

  public LocalDateTime getDate() {
    return this.date;
  }

  public String getDateString() {
    return dtf.format(date).toString();
  }

  // Tar inn en streng på formen "dd/MM/yyyy HH:mm", og setter atributten "date"
  // lik datoen strengen beskriver.
  public void setDate(String date) {
    LocalDateTime d = LocalDateTime.parse(date, this.dtf);
    this.date = d;
  }

  public void setDate(LocalDateTime ldt) {
      this.date = ldt; 
  }

  /*
   * Sammenlikner dette objektet med objektet som blir tatt inn som argument.
   * Objektene er like om de har samme beskivelse og dato.
   */
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Session)) {
      return false;
    }
    Session session = (Session) object;
    if (session.getDate().equals(this.getDate()) && session.getDescription().equals(this.getDescription())
    && this.exercises.equals(session.getListOfExercises())) {
      return true;
    }
    return false;
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
