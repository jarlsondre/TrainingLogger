package trainingLogger.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import trainingLogger.core.Session;
import trainingLogger.core.SessionLogger;

public class ModuleTest {

    private static ObjectMapper mapper;

    @BeforeAll
    public static void setUp() {
        ModuleTest.mapper = new ObjectMapper();
        mapper.registerModule(new TrainingLoggerModule());
    }

    @Test
    public void testSessionSerializer() {
        Session session = new Session("det var en fin økt");
        session.setDate("15/09/2020 10:02");
        try {
            assertEquals("{\"stringDescription\":\"det var en fin økt\",\"date\":\"15/09/2020 10:02\"}",
                    mapper.writeValueAsString(session));
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    @Test
    public void testSessionDeserializer() {
        Session session = null;
        try {
            session = ModuleTest.mapper.readValue(
                    "{\"stringDescription\":\"det var en fin økt\",\"date\":\"15/09/2020 10:02\"}", Session.class);
        } catch (JsonMappingException e) {
            fail();
        } catch (JsonProcessingException e) {
            fail();
        }
        Session test_Session = new Session("det var en fin økt");
        test_Session.setDate("15/09/2020 10:02");
        assertEquals(test_Session, session);
    }

    @Test
    public void testSessionLoggerSerializer(){
        SessionLogger logger = new SessionLogger();
        Session session1 = new Session("fin og bra");
        session1.setDate("16/09/2020 15:42");
        Session session2 = new Session("braa");
        session2.setDate("16/09/2020 15:42");
        logger.addSession(session1);
        logger.addSession(session2);
        try {
            assertEquals("{\"sessions\":[{\"stringDescription\":\"fin og bra\",\"date\":\"16/09/2020 15:42\"},{\"stringDescription\":\"braa\",\"date\":\"16/09/2020 15:42\"}]}", 
            mapper.writeValueAsString(logger));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testSessionLoggerDeserializer(){
        SessionLogger logger = null;
        try {
            logger = ModuleTest.mapper.readValue(
                    "{\"sessions\":[{\"stringDescription\":\"fin og bra\",\"date\":\"16/09/2020 15:42\"},{\"stringDescription\":\"braa\",\"date\":\"16/09/2020 15:42\"}]}", SessionLogger.class);
        } catch (JsonMappingException e) {
            fail();
        } catch (JsonProcessingException e) {
            fail();
        }
        SessionLogger test_logger = new SessionLogger();
        Session session1 = new Session("fin og bra");
        session1.setDate("16/09/2020 15:42");
        Session session2 = new Session("braa");
        session2.setDate("16/09/2020 15:42");
        test_logger.addSession(session1);
        test_logger.addSession(session2);
        //assertEquals(test_logger, logger);
    }




}