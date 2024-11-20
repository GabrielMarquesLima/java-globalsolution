package br.com.ecosafe.resource;

import br.com.ecosafe.model.PontoDescarte;
import br.com.ecosafe.service.PontoDescarteService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/pontos")
public class PontoDescarteResource {

    private PontoDescarteService service = new PontoDescarteService();
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPontos(@QueryParam("uf") String uf, @QueryParam("cidade") String cidade) {
        try {
            List<PontoDescarte> pontos;
            if (uf != null && cidade != null) {
                pontos = service.getPontosByUfAndCidade(uf, cidade);
            } else {
                pontos = service.getAllPontos();
            }
            String json = gson.toJson(pontos);
            return Response.ok(json).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao acessar o banco de dados.")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPontoById(@PathParam("id") int id) {
        try {
            PontoDescarte ponto = service.getPontoById(id);
            if (ponto != null) {
                String json = gson.toJson(ponto);
                return Response.ok(json).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Ponto de descarte não encontrado.")
                        .build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao acessar o banco de dados.")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPonto(String jsonBody) {
        try {
            PontoDescarte ponto = gson.fromJson(jsonBody, PontoDescarte.class);
            service.createPonto(ponto);
            String jsonResponse = gson.toJson(ponto);
            return Response.status(Response.Status.CREATED)
                    .entity(jsonResponse)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar ponto de descarte: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePonto(@PathParam("id") int id, String jsonBody) {
        try {
            PontoDescarte ponto = gson.fromJson(jsonBody, PontoDescarte.class);
            ponto.setIdPonto(id);
            service.updatePonto(ponto);
            return Response.ok("Ponto de descarte atualizado com sucesso.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar ponto de descarte: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePonto(@PathParam("id") int id) {
        try {
            service.deletePonto(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir ponto de descarte.")
                    .build();
        }
    }
}
