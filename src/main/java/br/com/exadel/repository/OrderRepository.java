package br.com.exadel.repository;

import br.com.exadel.entity.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {

    public Order findById(UUID id){
        return find("id", id).firstResult();
    }
}
