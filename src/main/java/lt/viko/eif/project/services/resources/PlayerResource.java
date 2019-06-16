package lt.viko.eif.project.services.resources;

import lt.viko.eif.project.dao.implementations.PlayerDao;
import lt.viko.eif.project.entities.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("players")
public class PlayerResource {

    private PlayerDao dao = new PlayerDao();

    /**
     * GET All Players
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getAllPlayers() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * GET Player by ID
     */
    @Path("/player/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayerById(@PathParam("id") int id) {
        try {
            if (dao.get(id) != null) {
                return Response.ok(dao.get(id), MediaType.APPLICATION_JSON).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * GET Player by Name
     */
    @Path("/names/{name}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayerByName(@QueryParam("name") String name) {
        return Response.ok(dao.getByName(name), MediaType.APPLICATION_JSON).build();
    }

    /**
     * GET Players by Class
     */
    @Path("/classes/{class}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlyerById(@PathParam("class") String player_class) {
        return Response.ok(dao.getByClass(player_class), MediaType.APPLICATION_JSON).build();
    }

    /**
     * POST Player
     */
    @Path("/player")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPlayer(Player player) {
        try {
            return Response.ok(dao.post(player), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * POST Player Deck
     */
    @POST
    @Path("/{playerName}/decks/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPlayerDeck(@PathParam("playerName") String playerName, @QueryParam("name") String deckName) {
        try {
            return Response.ok(dao.postPlayerDeck(playerName, deckName), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * POST Card to Deck
     */
    @POST
    @Path("/{playerName}/decks/{deckId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCardToDeck(@PathParam("playerName") String playerName, @PathParam("deckId") int deckId, @QueryParam("cardId") int cardId) {
        try {
            return Response.ok(dao.postCardToDeck(playerName, deckId, cardId), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * PUT Player
     */
    @Path("/player/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPlayer(@PathParam("id") int id,Player player) {
        try {
            return Response.ok(dao.put(player,id), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * DELETE Player
     */
    @Path("/player/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlayer(@PathParam("id") int id) {
        try {
            return Response.ok(dao.delete(id), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
