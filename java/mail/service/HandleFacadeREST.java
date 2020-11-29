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
@Path("handle")
public class HandleFacadeREST extends AbstractFacade<Handle> {

    @PersistenceContext(unitName = "RESTMailPU")
    private EntityManager em;

    public HandleFacadeREST() {
        super(Handle.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public String create(@QueryParam("user") String User,Handle entity)  {
        super.create(entity);
        return (entity.toString());
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Handle entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Handle> findAll() {
        List<Handle> handles = new ArrayList<>();
        try {
            handles = em.createNamedQuery("Handle.findAll", Handle.class).setParameter("handles",handles).getResultList();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return handles;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Handle find(@PathParam("id") Integer id) {
        Handle handle = null;
        try {
            handle = em.createNamedQuery("Handle.findByIdr",Handle.class).setParameter("handle",id).getSingleResult();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return handle;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Handle> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
