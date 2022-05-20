/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import DAO.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Felipe
 */
@Path("rest")
public class FuncionarioWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FuncionarioWS
     */
    public FuncionarioWS() {
    }

    /**
     * Retrieves representation of an instance of ES.FuncionarioWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of FuncionarioWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("funcionarios")// http://localhost:8080/empresa/sistema/rest/funcionarios
    public String getFuncionario() {
        FuncionarioDao dao = new FuncionarioDao();
        ArrayList<Funcionario> listaFuncionario = dao.getLista();
        
        Gson gson = new Gson();
        return gson.toJson(listaFuncionario);      
        }   
    
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("funcionarios/{NUMAT}")// http://localhost:8080/empresa/sistema/rest/funcionarios/123
    public String getFuncionario(@PathParam("NUMAT") int numat) {
        FuncionarioDao dao = new FuncionarioDao();
        Funcionario funcionario = dao.consulta(numat);
        if(funcionario != null){
           Gson gson = new Gson();
        return gson.toJson(funcionario); 
        }else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
              
    }
    
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @Path("supervisores")// http://localhost:8080/empresa/sistema/rest/supervisores
    public String getSupervisores() {
        FuncionarioDao dao = new FuncionarioDao();
        ArrayList<Funcionario> listaFuncionario = dao.getSupervisor();
        
        Gson gson = new Gson();
        return gson.toJson(listaFuncionario);      
        }   
    
    //teste
     
        @DELETE
        @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        @Path("funcionarios/{NUMAT}")// http://localhost:8080/empresa/sistema/rest/funcionarios/123
        public Response delFuncionario(@PathParam("NUMAT") int id) {
        FuncionarioDao dao = new FuncionarioDao();
        
        if(dao.remove(id)){
        return Response.status(Response.Status.OK).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
        @POST
        @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        @Path("funcionarios") // http://localhost:8080/empresa/sistema/rest/funcionarios
        public Response addFuncionario(String content){
            Gson gson = new Gson();
            Funcionario funcionario = (Funcionario) gson.fromJson(content, Funcionario.class);
            FuncionarioDao dao = new FuncionarioDao();
            // tenta inserir um novo aluno
            try{
                dao.adiciona(funcionario);
            }
            catch(RuntimeException e){
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            return Response.status(Response.Status.OK).build(); 
        }
        
        @PUT
        @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
        @Path("funcionarios") // http://localhost:8080/empresa/sistema/rest/funcionarios
        public Response setFuncionario(String content){
            Gson gson = new Gson();
            Funcionario funcionario = (Funcionario) gson.fromJson(content, Funcionario.class);
            FuncionarioDao dao = new FuncionarioDao();
            try{
                //testa se conseguiu atualizar o funcionario
                if(dao.atualiza(funcionario))
                    return Response.status(Response.Status.OK).build();
                else 
                    return Response.status(Response.Status.NOT_FOUND).build();
            }
                catch(RuntimeException e){
                        return Response.status(Response.Status.BAD_REQUEST).build();
                        }
            }
        
        
    //fimteste
   
}
