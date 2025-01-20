package br.com.exadel.controller;

import br.com.exadel.entity.Order;
import br.com.exadel.service.OrderService;
import br.com.exadel.util.OrderStatus;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

    @Inject
    OrderService service;

    @POST
    public Order createOrder(Order order) {
        return service.createOrder(order);
    }

    @GET
    public List<Order> listOrders() {
        return service.listOrders();
    }

    @GET
    @Path("/{id}")
    public Optional<Order> getOrder(@PathParam("id") UUID id) {
        return service.getOrder(id);
    }

    @PATCH
    @Path("/{id}/status")
    public Order updateOrderStatus(@PathParam("id") UUID id, @QueryParam("newStatus") OrderStatus newStatus) {
        return service.updateOrderStatus(id, newStatus);
    }
}
