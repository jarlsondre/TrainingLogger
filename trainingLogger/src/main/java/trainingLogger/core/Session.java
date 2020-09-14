package trainingLogger.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Session{

/* Et objekt av klassen "Session" tar vare på all informasjon rundt en økt. I førate omgang inneholder objektet:  
- En beskrivelse av økten. 
- Datoen økten foregikk, lagret som en streng. */

    private String description; 
    private LocalDateTime date;
    private final DateTimeFormatter dtf; //kan endre hvis klokkeslett trengs

    public Session(){
        date = LocalDateTime.now();
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    }
    public Session(String description){
        date = LocalDateTime.now();
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.description = description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public String getDateString(){
        return dtf.format(date).toString();
    }

}