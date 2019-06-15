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
    @Path("/card")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCard(@QueryParam("k") int k) {
        try
        {
            if(dao.get(k).getName() != null )
            {
                return Response.ok(dao.get(k), MediaType.APPLICATION_JSON).build();
            }
            else
            {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/card")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertCard(Card card) {
        try
        {
            return Response.ok(dao.post(card), MediaType.APPLICATION_JSON).build();
        }
        catch (Exception e)
            {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/card")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putCard(Card card) {
        try
        {
            return Response.ok(dao.put(card), MediaType.APPLICATION_JSON).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/card")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCard(@QueryParam("k") int k) {
        try
        {
            return Response.ok(dao.delete(k), MediaType.APPLICATION_JSON).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
