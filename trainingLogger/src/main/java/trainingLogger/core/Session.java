package trainingLogger.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

public class Session {

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

    //Tar inn en streng på formen "dd/MM/yyyy HH:mm", og setter atributten "date" lik datoen strengen beskriver.
    public void setDate(String date){
        LocalDateTimeStringConverter converter = new LocalDateTimeStringConverter();
        LocalDateTime d = converter.fromString(date);
        this.date = d;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof Session)){
            return false;
        }
        Session session = (Session) object;
        if(session.getDate().equals(this.getDate()) &&
         session.getDescription().equals(this.getDescription())){
             return true;
        }
        return false;
    }
}