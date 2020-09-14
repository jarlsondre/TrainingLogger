package trainingLogger.core;

import java.time.LocalDateTime;

public class Session{

/* Et objekt av klassen "Session" tar vare på all informasjon rundt en økt. I førate omgang inneholder objektet:  
- En beskrivelse av økten. 
- Datoen økten foregikk, lagret som en streng. */

    private String description; 
    private LocalDateTime date;
    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy"): for å formatere

    public Session(){
        date = LocalDateTime.now();
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

    public static void main(String[] args){
        Session s = new Session();
        System.out.println(s.getDate());
    }
}