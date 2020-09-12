package trainingLogger.core;

public class Session{

/* Et objekt av klassen "Session" tar vare på all informasjon rundt en økt. I førate omgang inneholder objektet:  
- En beskrivelse av økten. 
- Datoen økten foregikk, lagret som en streng. */

    private String description; 
    private final String date;

    public Session(String date){
        this.date = date;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

}