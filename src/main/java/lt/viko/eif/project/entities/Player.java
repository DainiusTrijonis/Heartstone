package lt.viko.eif.project.entities;

public class Player {
    int id;
    String name;
    String player_class;


    public Player() {
    }

    public Player(int id, String name, String player_class) {
        this.id = id;
        this.name = name;
        this.player_class = player_class;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", player_class='" + player_class + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayer_class() {
        return player_class;
    }

    public void setPlayer_class(String player_class) {
        this.player_class = player_class;
    }
}
