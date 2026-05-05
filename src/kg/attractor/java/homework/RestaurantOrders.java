package kg.attractor.java.homework;

import com.google.gson.Gson;
import kg.attractor.java.homework.domain.Item;
import kg.attractor.java.homework.domain.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class RestaurantOrders {
    // Этот блок кода менять нельзя! НАЧАЛО!
    private List<Order> orders;

    private RestaurantOrders(String fileName) {
        var filePath = Path.of("data", fileName);
        Gson gson = new Gson();
        try {
            orders = List.of(gson.fromJson(Files.readString(filePath), Order[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RestaurantOrders read(String fileName) {
        var ro = new RestaurantOrders(fileName);
        ro.getOrders().forEach(Order::calculateTotal);
        return ro;
    }

    public List<Order> getOrders() {
        return orders;
    }
    // Этот блок кода менять нельзя! КОНЕЦ!

    //----------------------------------------------------------------------
    //------   Реализация ваших методов должна быть ниже этой линии   ------
    //----------------------------------------------------------------------

    public void printOrders(List<Order> ordersToPrint) {
        ordersToPrint.forEach(order -> System.out.println("Customer: " + order.getCustomer().getFullName() +
                ", Total: " + order.getTotal() +
                ", Home Delivery: " + order.isHomeDelivery()));
    }

    public List<Order> getTopOrders(int n) {
        return orders.stream()
                .sorted((o1, o2) -> Double.compare(o2.getTotal(), o1.getTotal()))
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<Order> getLowestOrders(int n) {
        return orders.stream()
                .sorted((o1, o2) -> Double.compare(o1.getTotal(), o2.getTotal()))
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<Order> getHomeDeliveryOrders() {
        return orders.stream()
                .filter(Order::isHomeDelivery)
                .collect(Collectors.toList());
    }

    public Order getMostProfitableHomeDelivery() {
        return getHomeDeliveryOrders().stream()
                .max((o1, o2) -> Double.compare(o1.getTotal(), o2.getTotal()))
                .orElse(null);
    }

    public Order getLeastProfitableHomeDelivery() {
        return getHomeDeliveryOrders().stream()
                .min((o1, o2) -> Double.compare(o1.getTotal(), o2.getTotal()))
                .orElse(null);
    }

    public List<Order> getOrdersBetween(double minTotal, double maxTotal) {
        return orders.stream()
                .filter(order -> order.getTotal() > minTotal && order.getTotal() < maxTotal)
                .collect(Collectors.toList());
    }

    public double getTotalCostOfAllOrders() {
        return orders.stream()
                .mapToDouble(Order::getTotal)
                .sum();
    }

    public List<String> getUniqueSortedEmails() {
        return orders.stream()
                .map(order -> order.getCustomer().getEmail())
                .collect(Collectors.toCollection(TreeSet::new))
                .stream()
                .collect(Collectors.toList());
    }

    public Map<String, List<Order>> groupOrdersByCustomerName() {
        return orders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer().getFullName()));
    }

    public Map<String, Double> getTotalSumByCustomerName() {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getCustomer().getFullName(),
                        Collectors.summingDouble(Order::getTotal)
                ));
    }

    public String getCustomerWithMaxTotalSum() {
        return getTotalSumByCustomerName().entrySet().stream()
                .max((e1, e2) -> Double.compare(e1.getValue(), e2.getValue()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public String getCustomerWithMinTotalSum() {
        return getTotalSumByCustomerName().entrySet().stream()
                .min((e1, e2) -> Double.compare(e1.getValue(), e2.getValue()))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Map<String, Integer> getItemsGroupedByAmount() {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        Item::getName,
                        Collectors.summingInt(Item::getAmount)
                ));
    }
}