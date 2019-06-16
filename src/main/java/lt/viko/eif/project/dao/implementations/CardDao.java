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
    public List<Card> getAll(){
        List<Card> cards = new ArrayList<>();
        Statement myStmt = null;
        ResultSet result = null;

        try
        {
            myStmt = myConn.createStatement();
            String query ="select * from card";
            result = myStmt.executeQuery(query);

            while (result.next())
            {
                Card card = new Card(result.getInt("id"), result.getString("name"), result.getString("type"), result.getInt("attack"),result.getInt("health"),result.getString("text"), result.getString("player_class"));
                cards.add(card);
            }

            return cards;
        }
        catch(Exception e)
        {

            System.out.print(e);
            return null;
        }

    }

    @Override
    public Card get(int id){

        ResultSet result = null;
        String query ="select * from card where id = "+id;
        try
        {
            PreparedStatement pstmt = myConn.prepareStatement(query);

            result = pstmt.executeQuery(query);
            result.next();
            Card card = new Card(result.getInt("id"), result.getString("name"), result.getString("type"), result.getInt("attack"),result.getInt("health"),result.getString("text"), result.getString("player_class"));
            return card;

        }
        catch(Exception e)
        {
            System.out.print(e);
            return null;
        }

    }
    @Override
    public Boolean post(Card card) {



        String query ="insert into card(id,name,type,attack,health,text,player_class) VALUES (?,?,?,?,?,?,?)";
        try
        {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            {
                pstmt.setInt(1, 0);
                pstmt.setString(2, card.getName());
                pstmt.setString(3, card.getType());
                pstmt.setInt(4, card.getAttack());
                pstmt.setInt(5, card.getHealth());
                pstmt.setString(6, card.getText());
                pstmt.setString(7, card.getPlayer_Class());
            }
            pstmt.executeUpdate();

            return true;

        }
        catch(Exception e)
        {
            System.out.print(e);
            return false;
        }
    }

    @Override
    public Boolean put(Card card) {

        String query ="UPDATE card SET name = ? , type = ? , attack = ? , health = ? , text = ? , player_class = ? where id = ?";
        try
        {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            {

                pstmt.setString(1, card.getName());
                pstmt.setString(2, card.getType());
                pstmt.setInt(3, card.getAttack());
                pstmt.setInt(4, card.getHealth());
                pstmt.setString(5, card.getText());
                pstmt.setString(6, card.getPlayer_Class());
                pstmt.setInt(7, card.getId());
            }
            pstmt.executeUpdate();

            return true;

        }
        catch(Exception e)
        {
            System.out.print(e);
            return false;
        }
    }

    @Override
    public Boolean delete(int id) {

        String query ="DELETE FROM card where id = ?";
        try
        {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            {
                pstmt.setInt(1, id);
            }
            pstmt.executeUpdate();

            return true;

        }
        catch(Exception e)
        {
            System.out.print(e);
            return false;
        }
    }

    public List<Card> getByClass(String playerClass) {
        List<Card> cards = new ArrayList<>();
        String query = "SELECT * FROM card WHERE card.player_class = ?";
        try {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            {
                pstmt.setString(1, playerClass);
            }
            ResultSet result = pstmt.executeQuery();
            while (result.next())
            {
                Card card = new Card(result.getInt("id"), result.getString("name"), result.getString("type"), result.getInt("attack"),result.getInt("health"),result.getString("text"), result.getString("player_class"));
                cards.add(card);
            }

            return cards;
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<Card> getByType(String type) {
        List<Card> cards = new ArrayList<>();
        String query = "SELECT * FROM card WHERE card.type = ?";
        try {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            {
                pstmt.setString(1, type);
            }
            ResultSet result = pstmt.executeQuery();
            while (result.next())
            {
                Card card = new Card(result.getInt("id"), result.getString("name"), result.getString("type"), result.getInt("attack"),result.getInt("health"),result.getString("text"), result.getString("player_class"));
                cards.add(card);
            }

            return cards;
        }
        catch (Exception e) {
            return null;
        }
    }
}
