package lt.viko.eif.project.services.resources;

import lt.viko.eif.project.dao.implementations.CardDao;
import lt.viko.eif.project.entities.Card;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("cards")
public class CardResource {

    private CardDao dao = new CardDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Card> getAllCards() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
