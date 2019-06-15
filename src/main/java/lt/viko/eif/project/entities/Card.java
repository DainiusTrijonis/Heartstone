package lt.viko.eif.project.entities;


/**
 * Card class
 * used to create a card object.
 *
 * @author Astijus Jenkus
 * @version 1.0.0
 */

public class Card {

    private int id;
    private String name;
    private String type;
    private int attack;
    private int health;
    private String text;
    private String player_Class;

    /**
     * Default constructor (creates an empty Card object).
     */
    public Card() {
    }

    /**
     * Constructor with all variables Card Object
     * @param id id
     * @param name name
     * @param type type
     * @param attack attack
     * @param health health
     * @param text text
     * @param player_Class player_Class
     */
    public Card(int id, String name, String type, int attack, int health, String text, String player_Class) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.health = health;
        this.text = text;
        this.player_Class = player_Class;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", attack=" + attack +
                ", health=" + health +
                ", text='" + text + '\'' +
                ", player_Class='" + player_Class + '\'' +
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPlayer_Class() {
        return player_Class;
    }

    public void setPlayer_Class(String player_Class) {
        this.player_Class = player_Class;
    }
}