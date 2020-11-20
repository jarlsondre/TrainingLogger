package traininglogger.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {

  @Test
  public void dateTest() {
    Session session = new Session();
    try {
      session.setDate("08/10/2020 17:29");
    } catch (Exception e) {
      fail("Det skal være mulig å sette datoen i fortiden.");
    }
    assertEquals(LocalDateTime.parse("08/10/2020 17:29", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), session.getDate());
    try {
      String future = LocalDateTime.now().plusMinutes(5).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
      session.setDate(future);
      fail("Det skal ikke være mulig å sette datoen i fremtiden.");
    } catch (Exception e) {
      //Do nothing
    }
  }

  @Test
  public void iteratorTest() {
    Exercise exercise1 = new Exercise("Benkpress", new Set(2, 2), new Set(4, 4));
    Exercise exercise2 = new Exercise("Benkpress", new Set(2, 2), new Set(4, 4));
    Exercise exercise3 = new Exercise("Knebøy", new Set(2, 2), new Set(4, 4));
    Session session1 = new Session("braa!", exercise1, exercise2, exercise3);
    Iterator<Exercise> it = session1.iterator();
    assertTrue(it.hasNext());
    assertTrue(ComparisonHelper.equalExercise(it.next(), exercise1));
    assertTrue(it.hasNext());
    assertTrue(ComparisonHelper.equalExercise(it.next(), exercise2));
    assertTrue(it.hasNext());
    assertTrue(ComparisonHelper.equalExercise(it.next(), exercise3));
    assertFalse(it.hasNext());
  }

  @Test
  public void setDescription() {
    Session session1 = new Session();
    try {
      session1.setDescription("Bra!");
    } catch (Exception e) {
      fail("Ordet inneholdt ikke for mange bokastaver, men unntak ble kasta!");
    }
    try {
      session1.setDescription("Her skal det ikke være mulig å skrive inn en beskrivelse på mer enn 60 bokstaver" +
          "Her skal det ikke være mulig å skrive inn en beskrivelse på mer enn 60 bokstaver." +
          "Her skal det ikke være mulig å skrive inn en beskrivelse på mer enn 60 bokstaver.");
      fail("For mange bokstaver er brukt i beskrivelsen av økta.");
    } catch (Exception e) {
      //Do nothing
    }
  }

  @Test
  public void addSetsTest() {
    Session session = new Session();
    try {
      Exercise exercise1 = new Exercise("Benkpress", new Set(2, 2), new Set(4, 4));
      Exercise exercise2 = new Exercise("Benkpress", new Set(2, 2), new Set(4, 4));
      Exercise exercise3 = new Exercise("Knebøy", new Set(2, 2), new Set(4, 4));
      session.addExercises(exercise1, exercise2, exercise3);
    } catch (Exception e) {
      fail("Det skal være mulig å legge til tre sett uten problemer.");
    }
    Exercise exercise = new Exercise("Benkpress", new Set(2, 2), new Set(4, 4));
    Exercise[] exercises = new Exercise[18];
    for (int i = 0; i < 18; i++) {
      exercises[i] = exercise;
    }
    try {
      session.addExercises(exercises);
      fail("Det skal ikke være mulig å legge til så mange øvelser");
    } catch (Exception e) {
      //Do nothing
    }
  }


}
