package lt.viko.eif.project.services.resources;

import lt.viko.eif.project.dao.Dao;
import lt.viko.eif.project.dao.implementations.PlayerDao;
import lt.viko.eif.project.entities.Player;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 */
@Path("players")
public class PlayerResource {

    private Dao dao = new PlayerDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getAllCards() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Path("/player")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayer(@QueryParam("k") int k) {
        try
        {
            if(dao.get(k) != null )
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

    @Path("/player")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertPlayer(Player player) {
        try
        {
            return Response.ok(dao.post(player), MediaType.APPLICATION_JSON).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/player")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPlayer(Player player) {
        try
        {
            return Response.ok(dao.put(player), MediaType.APPLICATION_JSON).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/player")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlayer(@QueryParam("k") int k) {
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
