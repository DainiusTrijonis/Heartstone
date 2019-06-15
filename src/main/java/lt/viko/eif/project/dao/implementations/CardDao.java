package lt.viko.eif.project.dao.implementations;

import lt.viko.eif.project.dao.Dao;
import lt.viko.eif.project.entities.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardDao implements Dao<Card> {

    private Connection myConn;

    public CardDao() {
        String url = "jdbc:mysql://localhost:3306/database";
        String user = "root";
        String password = "";

        try {
            myConn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Card> getAll() throws Exception {
        List<Card> cards = new ArrayList<>();
        Statement myStmt = null;
        ResultSet result = null;

        myStmt = myConn.createStatement();
        result = myStmt.executeQuery("select * from card");

        while (result.next()) {
            Card card = new Card(result.getInt("id"), result.getString("name"));
            cards.add(card);
        }

        return cards;
    }

    @Override
    public void post(Card card) {

    }
}
