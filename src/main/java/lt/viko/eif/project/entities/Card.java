package lt.viko.eif.project.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Card class
 * used to create a card object.
 *
 * @author Astijus Jenkus
 * @version 1.0.0
 */
@XmlRootElement(name = "Card")
@XmlType(propOrder = {"id", "name"})
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

    @XmlElement(name = "ID")
    public int getId() {
        return id;
    }

    @XmlElement(name = "Name")
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