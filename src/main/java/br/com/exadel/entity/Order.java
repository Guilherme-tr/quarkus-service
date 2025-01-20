package br.com.exadel.entity;

import br.com.exadel.util.OrderStatus;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    public String customerName;

    public String product;

    public int quantity;

    @Enumerated(EnumType.STRING)
    public OrderStatus status;

    public Order() {
    }

    public Order(UUID id, String customerName, String product, int quantity, OrderStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
    }
}
