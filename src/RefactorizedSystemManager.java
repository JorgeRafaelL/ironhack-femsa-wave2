import java.util.*;

/*Creamos las clases que tienen una única responsabilidad (SRP)*/
// InventoryService
class InventoryService {

    public void verifyInventory(Order order) {
        if (getInventoryLevel() < order.getQuantity()) {
            throw new IllegalStateException("Out of stock");
        }
    }

    private int getInventoryLevel() {
        return 100;
    }

}

// PaymentService
class PaymentService {

    public void processPayment(double amount) {
        if (!process(amount)) {
            throw new IllegalStateException("Payment failed");
        }
    }

    private boolean process(double amount) {
        return amount > 100;
    }

}

// ExpressPaymentService
class ExpressPaymentService {

    public void processPayment(double amount, String priority) {
        if (!process(amount, priority)) {
            throw new IllegalStateException("Express payment failed");
        }
    }

    private boolean process(double amount, String priority) {
        return amount > 10 && priority.equals("yes");
    }

}

// OrderRepository
class OrderRepository {

    public void updateOrderStatus(int orderId, String status) {
        // Lógica para actualizar el status
    }

}

// NotificationService
class NotificationService {

    public void notifyCustomer(String email, String message) {
        // Logica para enviar la notificación
    }

}

// Creamos la interfaz con el método para procesar la orden
interface OrderProcessor {

    void processOrder(Order order);

}

/*Creamos las clases StandardOrderProcessor y ExpressOrderProcessor que implementan la interfaz OrderProcessor, así pueden extendenrse sin modificar la base (OCP)*/
// StandardOrderProcessor
class StandardOrderProcessor implements OrderProcessor {

    private final InventoryService inventoryService;

    private final PaymentService paymentService;

    private final OrderRepository orderRepository;

    private final NotificationService notificationService;

    public StandardOrderProcessor(InventoryService inventoryService, PaymentService paymentService,
                                  OrderRepository orderRepository, NotificationService notificationService) {
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void processOrder(Order order) {
        inventoryService.verifyInventory(order);
        paymentService.processPayment(order.getAmount());
        orderRepository.updateOrderStatus(order.getId(), "processed");
        notificationService.notifyCustomer(order.getCustomerEmail(), "Your standard order has been processed.");
        System.out.println("Orden procesada: " + order);
    }

}

/*las clases StandardOrderProcessor y ExpressOrderProcessor pueden usarse en lugar de OrderProcessor sin alterar la funcionalidad (LSP)*/
// ExpressOrderProcessor
class ExpressOrderProcessor implements OrderProcessor {

    private final InventoryService inventoryService;

    private final ExpressPaymentService expressPaymentService;

    private final OrderRepository orderRepository;

    private final NotificationService notificationService;

    public ExpressOrderProcessor(InventoryService inventoryService, ExpressPaymentService expressPaymentService,
                                 OrderRepository orderRepository, NotificationService notificationService) {
        this.inventoryService = inventoryService;
        this.expressPaymentService = expressPaymentService;
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void processOrder(Order order) {
        inventoryService.verifyInventory(order);
        expressPaymentService.processPayment(order.getAmount(), "highPriority");
        orderRepository.updateOrderStatus(order.getId(), "processed");
        notificationService.notifyCustomer(order.getCustomerEmail(), "Your express order has been processed.");
    }

}

// SystemManager depende de OrderProcessor, esto permite pasar diferentes procesadores de pedidos (standard o express) mediante inyección de dependencias (DIP)
class SystemManager {

    private final Map<String, OrderProcessor> orderProcessors;

    public SystemManager(Map<String, OrderProcessor> orderProcessors) {
        this.orderProcessors = orderProcessors;
    }

    public void processOrder(Order order) {
        OrderProcessor processor = orderProcessors.get(order.getType());
        processor.processOrder(order);
    }

}

// Order POJO
class Order {

    private String type;

    private int id;

    private double amount;

    private int quantity;

    private String customerEmail;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Order{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", amount=" + amount +
                ", quantity=" + quantity +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }

}

public class Main {

    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryService();
        PaymentService paymentService = new PaymentService();
        ExpressPaymentService expressPaymentService = new ExpressPaymentService();
        OrderRepository orderRepository = new OrderRepository();
        NotificationService notificationService = new NotificationService();

        Map<String, OrderProcessor> orderProcessors = new HashMap<>();
        orderProcessors.put("standard", new StandardOrderProcessor(inventoryService, paymentService, orderRepository, notificationService));
        orderProcessors.put("express", new ExpressOrderProcessor(inventoryService, expressPaymentService, orderRepository, notificationService));

        SystemManager systemManager = new SystemManager(orderProcessors);

        Order order = new Order();
        order.setId(1);
        order.setType("standard");
        order.setAmount(500);
        order.setQuantity(1);
        order.setCustomerEmail("prueba@gmail.com");

        systemManager.processOrder(order);
    }

}
