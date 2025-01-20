package br.com.exadel.service;

import br.com.exadel.entity.Order;
import br.com.exadel.repository.OrderRepository;
import br.com.exadel.util.OrderStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository repository;

    @Inject
    @Channel("order-events")
    Emitter<Order> orderEmitter;

    private static final Logger log = Logger.getLogger(OrderService.class);

    @Transactional
    public Order createOrder(Order order) {
        log.infov("Creating new order: {0}", order.id);
        order.status = OrderStatus.PENDING;
        repository.persist(order);

        log.info("Sending event to kafka");
        orderEmitter.send(order).toCompletableFuture().join();
        log.info("Event sent successfully");
        return order;
    }

    public List<Order> listOrders() {
        return repository.listAll();
    }

    public Optional<Order> getOrder(UUID id) {
        return Optional.ofNullable(repository.findById(id));
    }

    @Transactional
    public Order updateOrderStatus(UUID id, OrderStatus newStatus) {
        Order order = repository.findById(id);
        if (order != null) {
            order.status = newStatus;
            return order;
        }
        return null;
    }
}
