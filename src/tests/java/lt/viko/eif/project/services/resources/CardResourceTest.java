package lt.viko.eif.project.services.resources;

import lt.viko.eif.project.dao.implementations.CardDao;
import lt.viko.eif.project.entities.Card;
import lt.viko.eif.project.services.Publisher;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static org.junit.Assert.*;

public class CardResourceTest {

    private HttpServer server;
    private WebTarget target;
    private CardDao dao;

    @Before
    public void setUp() throws Exception {
        server = Publisher.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target(Publisher.BASE_URI);
        dao = new CardDao();
    }

    @After
    public void tearDown() throws Exception {
        List<Card> cards = dao.getByClass("test class");
        for (Card card : cards) {
            dao.delete(card.getId());
        }
        server.stop();
    }

    @Test
    public void getAllCards() {
        Response responseMsg = target.path("cards").request().get();
        assertEquals(200, responseMsg.getStatus());
    }

    @Test
    public void getCard() {
        Card card = new Card(0, "test name","test type",0,0,"test text", "test class");
        dao.post(card);
        List<Card> cards = dao.getAll();
        Response responseMsg = target.path("cards/card/" + cards.get(0).getId()).request().get();
        assertEquals(200, responseMsg.getStatus());
    }

    @Test
    public void getCardsByClass() {
        Response responseMsg = target.path("cards/classes/class").request().get();
        assertEquals(200, responseMsg.getStatus());
    }

    @Test
    public void getCardsByType() {
        Response responseMsg = target.path("cards/types/type").request().get();
        assertEquals(200, responseMsg.getStatus());
    }

    @Test
    public void postCard() {
        Card card = new Card(0, "test name", "test type", 0,0,"test text","test class");
        Response responseMsg = target.path("cards/card").request().post(Entity.entity(card, MediaType.APPLICATION_JSON));
        assertEquals(200,responseMsg.getStatus());
    }

    @Test
    public void putCard() {
        Card card = new Card(0, "test name", "test type", 0,0,"test text","test class");
        Response responseMsg = target.path("cards/card/0").request().put(Entity.entity(card, MediaType.APPLICATION_JSON));
        assertEquals(200,responseMsg.getStatus());
    }

    @Test
    public void deleteCard() {
        Response responseMsg = target.path("cards/card/0").request().delete();
        assertEquals(200, responseMsg.getStatus());
    }
}