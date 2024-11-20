package br.com.ecosafe.resource;

import br.com.ecosafe.model.SolicitacaoDescarte;
import br.com.ecosafe.service.SolicitacaoDescarteService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/solicitacoes")
public class SolicitacaoDescarteResource {

    private SolicitacaoDescarteService service = new SolicitacaoDescarteService();
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSolicitacoes() {
        try {
            List<SolicitacaoDescarte> solicitacoes = service.getAllSolicitacoes();
            String json = gson.toJson(solicitacoes);
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
    public Response getSolicitacaoById(@PathParam("id") int id) {
        try {
            SolicitacaoDescarte solicitacao = service.getSolicitacaoById(id);
            if (solicitacao != null) {
                String json = gson.toJson(solicitacao);
                return Response.ok(json).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Solicitação de descarte não encontrada.")
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
    public Response createSolicitacao(String jsonBody) {
        try {
            SolicitacaoDescarte solicitacao = gson.fromJson(jsonBody, SolicitacaoDescarte.class);
            service.createSolicitacao(solicitacao);
            String jsonResponse = gson.toJson(solicitacao);
            return Response.status(Response.Status.CREATED)
                    .entity(jsonResponse)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar solicitação de descarte: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSolicitacao(@PathParam("id") int id, String jsonBody) {
        try {
            SolicitacaoDescarte solicitacao = gson.fromJson(jsonBody, SolicitacaoDescarte.class);
            solicitacao.setIdSolicitacao(id);
            service.updateSolicitacao(solicitacao);
            return Response.ok("Solicitação de descarte atualizada com sucesso.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar solicitação de descarte: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSolicitacao(@PathParam("id") int id) {
        try {
            service.deleteSolicitacao(id);
            return Response.noContent().build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir solicitação de descarte.")
                    .build();
        }
    }
}
