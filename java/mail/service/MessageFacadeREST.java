/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import mail.Handle;
import mail.Message;
import mail.Recipient;

/**
 *
 * @author Cattc
 */
@Stateless
@Path("message")
public class MessageFacadeREST extends AbstractFacade<Message> {

    @PersistenceContext(unitName = "RESTMailPU")
    private EntityManager em;

    public MessageFacadeREST() {
        super(Message.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public String create(@QueryParam("Message") String Message ,Message entity)  {
        super.create(entity);
        return (entity.toString());
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Message entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Message find(@PathParam("id") Integer id) {
        Message message = null;
        try {
            message = em.createNamedQuery("Message.findByIdmessage",Message.class).setParameter("id",id).getSingleResult();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return message;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Message> findAll() {
        List<Message> messages = new ArrayList<>();
        try {
            messages = em.createNamedQuery("Messages.findAll", Message.class).setParameter("messages",messages).getResultList();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return messages;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Message> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
