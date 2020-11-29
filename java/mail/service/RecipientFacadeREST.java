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
import mail.Recipient;
import mail.User;

/**
 *
 * @author Cattc
 */
@Stateless
@Path("recipient")
public class RecipientFacadeREST extends AbstractFacade<Recipient> {

    @PersistenceContext(unitName = "RESTMailPU")
    private EntityManager em;

    public RecipientFacadeREST() {
        super(Recipient.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public String create(@QueryParam("Receipient") String Recipient,Recipient entity)  {
        super.create(entity);
        return (entity.toString());
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Recipient entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id,@QueryParam("key") String key) {
        Recipient  recip = find(key);
        if(recip != null){
            super.remove(super.find(id));
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Recipient find(@PathParam("id") Integer id) {
        Recipient recipient = null;
        try {
            recipient = em.createNamedQuery("Recipient.findByIdrecipient",Recipient.class).setParameter("id",id).getSingleResult();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return recipient;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Recipient> findAll() {
        List<Recipient> recipients = new ArrayList<>();
        try {
            recipients = em.createNamedQuery("Recipients.findAll", Recipient.class).setParameter("recipient",recipients).getResultList();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return recipients;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Recipient> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
