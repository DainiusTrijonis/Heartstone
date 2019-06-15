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

    /**
     * Default constructor (creates an empty Card object).
     */
    public Card() {
    }

    /**
     * Creates an Card object with the given parameters.
     *
     * @param id    - a unique number to identify a card.
     * @param name  - a name for this item.
     */
    public Card(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}