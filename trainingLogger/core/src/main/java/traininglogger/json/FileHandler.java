package traininglogger.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import traininglogger.core.Exercise;
import traininglogger.core.Session;

public class FileHandler {

  /*
   * Denne klassen skal håndtere lagring av objekter ved filskriving og
   * fillesning. Metodene er statiske.
   */

  // Statisk metode som lager en ObjectMapper med riktig modul registrert.
  private static ObjectMapper makeMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new TrainingLoggerModule());
    return mapper;
  }

  // Statisk metode som skriver en liste med sessions til fil med bruk av json.
  public static void writeToFile(OutputStream out, List<Session> sessions) throws IOException {
    Writer writer = new OutputStreamWriter(out, "UTF-8");
    ObjectMapper mapper = FileHandler.makeMapper();
    mapper.writeValue(writer, sessions);

  }

  // Statisk metode som leser en json fil, og returnerer en liste med sessions.
  public static List<Session> readFromFile(InputStream in) throws IOException {
    ObjectMapper mapper = FileHandler.makeMapper();
    List<Session> sessions = mapper.readValue(in, new TypeReference<List<Session>>() {
    });
    return sessions;
  }

  public static void writeExerciseToFile(String fileName, Exercise exercise) {
    try {
      ObjectMapper mapper = FileHandler.makeMapper();
      File file = new File(fileName);
      OutputStream out = new FileOutputStream(file);
      Writer writer = new OutputStreamWriter(out, "UTF-8");
      mapper.writeValue(writer, exercise);

    }
    catch(IOException e) {
      System.out.println("writeExerciseToFile klarte ikke å skrive til: " + fileName);
    }
  }
  @SuppressFBWarnings
  public static Exercise readExerciseFromFile(String fileName) {
    Exercise exercise = null;
    try {
      ObjectMapper mapper = FileHandler.makeMapper();
      InputStream in = new FileInputStream(fileName);
      exercise = mapper.readValue(in, new TypeReference<Exercise>() {
      });
      in.close();
    }
    catch(IOException e) {
      System.out.println("readExerciseFromFile klarte ikke å lese: " + fileName);
    }
    return exercise;
  }
  public static void writeSessionToFile(String fileName, Session session) {
    try {
      ObjectMapper mapper = FileHandler.makeMapper();
      File file = new File(fileName);
      OutputStream out = new FileOutputStream(file);
      Writer writer = new OutputStreamWriter(out, "UTF-8");
      mapper.writeValue(writer, session);

    }
    catch(IOException e) {
      System.out.println("writeSessionToFile klarte ikke å skrive til: " + fileName);
    }
  }

  @SuppressFBWarnings
  public static Session readSessionFromFile(String fileName) {
    Session session = null;
    try {
      ObjectMapper mapper = FileHandler.makeMapper();
      InputStream in = new FileInputStream(fileName);
      session = mapper.readValue(in, new TypeReference<Session>() {
      });
      in.close();
    }
    catch(IOException e) {
      System.out.println("readSessionFromFile klarte ikke å lese: " + fileName);
    }
    return session;
  }


}