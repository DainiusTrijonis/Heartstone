package lt.viko.eif.project.services.resources;

import lt.viko.eif.project.dao.implementations.CardDao;
import lt.viko.eif.project.entities.Card;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cards")
public class CardResource {

    private CardDao dao = new CardDao();

    /**
     * GET All Cards
     */
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

    /**
     * GET Card by ID
     */
    @Path("/card/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCard(@PathParam("id") int id) {
        try {
            if (dao.get(id) != null) {
                return Response.ok(dao.get(id), MediaType.APPLICATION_JSON).build();
            }
            else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * GET Cards by Class
     */
    @Path("/classes/{class}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCardsByClass(@PathParam("class") String playerClass) {
        return Response.ok(dao.getByClass(playerClass), MediaType.APPLICATION_JSON).build();
    }

    /**
     * GET Cards by Type
     */
    @Path("/types/{type}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCardsByType(@PathParam("type") String type) {
        return Response.ok(dao.getByType(type), MediaType.APPLICATION_JSON).build();
    }

    /**
     * POST Card
     */
    @Path("/card")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCard(Card card) {
        try {
            return Response.ok(dao.post(card), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * PUT Card
     */
    @Path("/card/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putCard(Card card,@PathParam("id") int id) {
        try {
            return Response.ok(dao.put(card,id), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * DELETE Card
     */
    @Path("/card/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCard(@PathParam("id") int id) {
        try {
            return Response.ok(dao.delete(id), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
