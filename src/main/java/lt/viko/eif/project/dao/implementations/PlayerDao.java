package lt.viko.eif.project.dao.implementations;

import lt.viko.eif.project.dao.Dao;
import lt.viko.eif.project.entities.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao implements Dao<Player> {


    private Connection myConn;

    public PlayerDao() {
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
    public List<Player> getAll() {
        List<Player> players = new ArrayList<>();
        Statement myStmt = null;
        ResultSet result = null;

        try {
            myStmt = myConn.createStatement();
            String query = "select * from player";
            result = myStmt.executeQuery(query);

            while (result.next()) {
                Player player = new Player(result.getInt("id"), result.getString("name"), result.getString("player_class"));
                players.add(player);
            }

            return players;
        } catch (Exception e) {

            System.out.print(e);
            return null;
        }
    }

    @Override
    public Player get(int id) {
        ResultSet result = null;
        String query = "select * from player where id = " + id;
        try {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            result = pstmt.executeQuery(query);
            result.next();
            Player player = new Player(result.getInt("id"), result.getString("name"), result.getString("player_class"));
            return player;

        } catch (Exception e) {
            System.out.print(e);
            return null;
        }

    }

    @Override
    public Boolean post(Player player) {
        String query = "insert into player(id,name,player_class) VALUES (?,?,?)";
        try {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            {
                pstmt.setInt(1, 0);
                pstmt.setString(2, player.getName());
                pstmt.setString(3, player.getPlayer_class());
            }
            pstmt.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        }
    }

    @Override
    public Boolean put(Player player) {
        String query = "UPDATE player SET name = ? ,player_class = ? where id = ?";
        try {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            {

                pstmt.setString(1, player.getName());
                pstmt.setString(2, player.getPlayer_class());
                pstmt.setInt(3, player.getId());
            }
            pstmt.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        }
    }

    @Override
    public Boolean delete(int id) {
        String query = "DELETE FROM player where id = ?";
        try {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            {
                pstmt.setInt(1, id);
            }
            pstmt.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        }
    }

    public List<Player> getByName(String name) {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player WHERE player.name = ?";
        try {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            {
                pstmt.setString(1, name);
            }
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                Player player = new Player(result.getInt("id"), result.getString("name"), result.getString("player_class"));
                players.add(player);
            }

            return players;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Player> getByClass(String player_class) {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player WHERE player.player_class = ?";
        try {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            {
                pstmt.setString(1, player_class);
            }
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                Player player = new Player(result.getInt("id"), result.getString("name"), result.getString("player_class"));
                players.add(player);
            }

            return players;
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean postPlayerDeck(String playerName, String deckName) {
        String getQuery = "SELECT player.id FROM player WHERE player.name = ?";
        int player_id = 0;
        try {
            PreparedStatement pstmt = myConn.prepareStatement(getQuery);
            {
                pstmt.setString(1, playerName);
            }
            ResultSet result = pstmt.executeQuery();
            result.next();
            player_id = result.getInt("id");

        } catch (Exception e) {
            System.out.print(e);
            return null;
        }

        String query = "INSERT INTO deck (deck.player_id, deck.name) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            {
                pstmt.setInt(1, player_id);
                pstmt.setString(2, deckName);
            }
            pstmt.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        }
    }

    public boolean postCardToDeck(String playerName, int deckId, int cardId) {
        String getQuery = "SELECT deck.id FROM deck INNER JOIN player ON deck.player_id = player.id WHERE player.name = ?";
        List<Integer> deck_ids = new ArrayList<>();
        try {
            PreparedStatement pstmt = myConn.prepareStatement(getQuery);
            {
                pstmt.setString(1, playerName);
            }
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                deck_ids.add(result.getInt("id"));
            }
            if (!deck_ids.contains(deckId)) {
                return false;
            }
        } catch (Exception e) {
            System.out.print(e);
            return false;
        }

        String query = "INSERT INTO deck_cards (deck_cards.deck_id, deck_cards.card_id) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = myConn.prepareStatement(query);
            {
                pstmt.setInt(1, deckId);
                pstmt.setInt(2, cardId);
            }
            pstmt.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        }
    }
}
