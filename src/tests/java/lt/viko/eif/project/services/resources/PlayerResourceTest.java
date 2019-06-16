package lt.viko.eif.project.services.resources;

import lt.viko.eif.project.dao.implementations.PlayerDao;
import lt.viko.eif.project.entities.Player;
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

public class PlayerResourceTest {

    private HttpServer server;
    private WebTarget target;
    private PlayerDao dao;


    @Before
    public void setUp() throws Exception {
        server = Publisher.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target(Publisher.BASE_URI);
        dao = new PlayerDao();
    }

    @After
    public void tearDown() throws Exception {
        List<Player> players = dao.getByClass("test class");
        for (Player player : players) {
            dao.delete(player.getId());
        }

        server.stop();
    }

    @Test
    public void getAllPlayers() {
        Response responseMsg = target.path("players").request().get();
        assertEquals(200, responseMsg.getStatus());
    }

    @Test
    public void getPlayerById() {
        Player player = new Player(0,"test name", "test class");
        dao.post(player);
        List<Player> players = dao.getAll();
        Response responseMsg = target.path("players/player/" + players.get(0).getId()).request().get();
        assertEquals(200, responseMsg.getStatus());
    }

    @Test
    public void getPlayerByName() {
        Response responseMsg = target.path("players/names/name").request().get();
        assertEquals(200, responseMsg.getStatus());
    }

    @Test
    public void getPlayerByClass() {
        Response responseMsg = target.path("players/classes/class").request().get();
        assertEquals(200, responseMsg.getStatus());
    }

    @Test
    public void postPlayer() {
        Player player = new Player(0, "test name", "test class");
        Response responseMsg = target.path("players/player").request().post(Entity.entity(player, MediaType.APPLICATION_JSON));
        assertEquals(200,responseMsg.getStatus());
    }

    @Test
    public void postPlayerDeck() {
        Player player = new Player(0, "test name", "test class");
        Response responseMsg = target.path("players/test name/decks/add").queryParam("name", "test name").request().post(Entity.entity(player, MediaType.APPLICATION_JSON));
        assertEquals(200,responseMsg.getStatus());
    }

    @Test
    public void postCardToDeck() {
        Player player = new Player(0, "test name", "test class");
        Response responseMsg = target.path("players/test name/decks/2").queryParam("cardId", 1).request().post(Entity.entity(player, MediaType.APPLICATION_JSON));
        assertEquals(200,responseMsg.getStatus());
    }

    @Test
    public void putPlayer() {
        Player player = new Player(0, "test name", "test class");
        Response responseMsg = target.path("players/player/0").request().put(Entity.entity(player, MediaType.APPLICATION_JSON));
        assertEquals(200,responseMsg.getStatus());
    }

    @Test
    public void deletePlayer() {
        Response responseMsg = target.path("players/player/0").request().delete();
        assertEquals(200,responseMsg.getStatus());
    }
}