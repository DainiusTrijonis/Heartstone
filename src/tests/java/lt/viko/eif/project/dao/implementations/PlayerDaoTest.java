package lt.viko.eif.project.dao.implementations;

import lt.viko.eif.project.entities.Card;
import lt.viko.eif.project.entities.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlayerDaoTest {

    private PlayerDao dao;
    private CardDao cardDao;

    @Before
    public void setUp() throws Exception {
        dao = new PlayerDao();
        cardDao = new CardDao();
    }

    @After
    public void tearDown() throws Exception {
        List<Player> players = dao.getByClass("test class");
        List<Card> cards = cardDao.getByClass("test class");

        for (Player player : players) {
            dao.delete(player.getId());
        }

        for (Card card : cards) {
            cardDao.delete(card.getId());
        }
    }

    @Test
    public void getAll() {
        assertEquals(ArrayList.class, dao.getAll().getClass());
    }

    @Test
    public void get() {
        Player player = new Player(0,"test name", "test class");
        dao.post(player);
        List<Player> players = dao.getAll();

        assertEquals(Player.class, dao.get(players.get(0).getId()).getClass());
    }

    @Test
    public void post() {
        Player player = new Player(0,"test name", "test class");
        assertEquals(true,dao.post(player));
    }

    @Test
    public void put() {
        Player player = new Player();
        assertEquals(true, dao.put(player,0));
    }

    @Test
    public void delete() {
        assertEquals(true, dao.delete(0));
    }

    @Test
    public void getByName() {
        assertEquals(ArrayList.class,dao.getByName("name").getClass());
    }

    @Test
    public void getByClass() {
        assertEquals(ArrayList.class,dao.getByClass("class").getClass());
    }

    @Test
    public void postPlayerDeck() {
        Player player = new Player(0,"test name", "test class");
        dao.post(player);
        assertEquals(true, dao.postPlayerDeck("test name", "test deck"));
    }

    @Test
    public void postCardToDeck() {
        Card card = new Card(0, "test name","test type",0,0,"test text", "test class");
        cardDao.post(card);
        List<Card> cards = cardDao.getAll();

        assertEquals(true, dao.postCardToDeck("test name", 1, cards.get(0).getId()));
    }
}