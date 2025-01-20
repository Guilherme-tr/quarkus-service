package br.com.exadel;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import br.com.exadel.entity.Order;
import br.com.exadel.repository.OrderRepository;
import br.com.exadel.service.OrderService;
import br.com.exadel.util.OrderStatus;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@QuarkusTest
class OrderServiceTest {

    @Inject
    OrderService service;

    @Mock
    OrderRepository repository;

    private UUID orderId;
    private Order order;

    @BeforeEach
    public void setUp() {
        this.repository = mock(OrderRepository.class);
    }

    @Test
    void shouldCreateOrder() {
        Order order = new Order();
        order.customerName = "Teste";
        order.product = "Celular";
        order.quantity = 1;

        Order created = service.createOrder(order);
        assertNotNull(created);
    }

    @Test
    void shouldListOrders() {
        List<Order> orders = service.listOrders();
        assertNotNull(orders);
    }

    @Test
    void testGetOrder_whenOrderExists() {

        Order order = new Order();
        order.customerName = "Teste2";
        order.product = "Celular";
        order.quantity = 1;

        service.createOrder(order);

        when(repository.findById(order.id)).thenReturn(order);

        Optional<Order> result = service.getOrder(order.id);

        assertTrue(result.isPresent());
        assertEquals(order.id, result.get().id);
    }

    @Test
    void testGetOrder_whenOrderDoesNotExist() {
        when(repository.findById(orderId)).thenReturn(null);

        Optional<Order> result = service.getOrder(orderId);

        assertFalse(result.isPresent(), "This order doesn't exist");
    }

    @Test
    void testUpdateOrderStatus_whenOrderExists() {

        Order order = new Order();
        order.customerName = "Teste3";
        order.product = "Celular";
        order.quantity = 1;

        service.createOrder(order);

        when(repository.findById(order.id)).thenReturn(order);

        Order updatedOrder = service.updateOrderStatus(order.id, OrderStatus.SHIPPED);

        assertNotNull(updatedOrder, "This order should be update");
        assertEquals(OrderStatus.SHIPPED, updatedOrder.status, "This status should be SHIPPED");
    }

}