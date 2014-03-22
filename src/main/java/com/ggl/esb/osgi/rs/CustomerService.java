package com.ggl.esb.osgi.rs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/customerservice/")
public class CustomerService {
	private static Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
    long currentId = 123;
    Map<Long, Customer> customers = new HashMap<Long, Customer>();
    Map<Long, Order> orders = new HashMap<Long, Order>();

    public CustomerService() {
        init();
    }

//    @GET
//    @Path("/customers/{id}/")
//    @Produces("application/xml")
//    public Customer getCustomer(@PathParam("id") String id) {
//    	
//        logger.info("----invoking getCustomer, Customer id is: " + id + "----");
//        long idNumber = Long.parseLong(id);
//        Customer c = customers.get(idNumber);
//        return c;
//    }

    @GET
    @Path("/customers/")
    @Produces("application/xml")
    public List<Customer> getCustomers() {
        logger.info("----Retrieve All Customers----");
        Collection<Customer> cc = customers.values();
        List<Customer> customerList = new ArrayList<Customer>();
        for(Iterator<Customer> iter=cc.iterator(); iter.hasNext();){
        	customerList.add(iter.next());
        }
        return customerList;
    }
    
    @PUT
    @Path("/customers/")
    public Response updateCustomer(Customer customer) {
        logger.info("----invoking updateCustomer, Customer name is: " + customer.getName());
        Customer c = customers.get(customer.getId());
        Response r;
        if (c != null) {
            customers.put(customer.getId(), customer);
            r = Response.ok().build();
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @POST
    @Path("/customers/")
    public Response addCustomer(Customer customer) {
        logger.info("----invoking addCustomer, Customer name is: " + customer.getName());
        customer.setId(++currentId);

        customers.put(customer.getId(), customer);

        return Response.ok().type("application/xml").entity(customer).build();
    }

    @DELETE
    @Path("/customers/{id}/")
    public Response deleteCustomer(@PathParam("id") String id) {
        logger.info("----invoking deleteCustomer, Customer id is: " + id);
        long idNumber = Long.parseLong(id);
        Customer c = customers.get(idNumber);

        Response r;
        if (c != null) {
            r = Response.ok().build();
            customers.remove(idNumber);
        } else {
            r = Response.notModified().build();
        }

        return r;
    }

    @GET
    @Path("/orders/{orderId}/")
    public Response getOrder(@PathParam("orderId") String orderId) {
        logger.info("----invoking getOrder, Order id is: " + orderId);
        long idNumber = Long.parseLong(orderId);
        Order order = orders.get(idNumber);
        logger.info("order id: " + order.getId());
        
        return Response.ok().type("application/xml").entity(order).build();
    }

    final void init() {
        Customer c = new Customer();
        c.setName("John");
        c.setId(123);
        customers.put(c.getId(), c);

        Order o = new Order();
        o.setDescription("order 223");
        o.setId(223);
        orders.put(o.getId(), o);
    }

}
