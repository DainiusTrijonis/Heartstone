package lt.viko.eif.project.dao.implementations;

import lt.viko.eif.project.entities.Card;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CardDaoTest {

    private CardDao dao;

    @Before
    public void setUp() throws Exception {
        dao = new CardDao();
    }

    @After
    public void tearDown() throws Exception {
        List<Card> testCards = dao.getByClass("test class");

        for (Card card : testCards) {
            dao.delete(card.getId());
        }
    }

    @Test
    public void getAll() {
        assertEquals(ArrayList.class, dao.getAll().getClass());
    }

    @Test
    public void get() {
        Card card = new Card(0, "test name","test type",0,0,"test text", "test class");
        dao.post(card);
        List<Card> cards = dao.getAll();
        assertEquals(Card.class, dao.get(cards.get(0).getId()).getClass());
    }

    @Test
    public void post() {
        Card card = new Card(0, "test name","test type",0,0,"test text", "test class");
        assertEquals(true, dao.post(card));
    }

    @Test
    public void put() {
        Card card = new Card(0, "test name","test type",0,0,"test text", "test class");
        assertEquals(true, dao.put(card,0));
    }

    @Test
    public void delete() {
        assertEquals(true,dao.delete(0));
    }

    @Test
    public void getByClass() {
        assertEquals(ArrayList.class, dao.getByClass("test class").getClass());
    }

    @Test
    public void getByType() {
        assertEquals(ArrayList.class, dao.getByType("test type").getClass());
    }
}