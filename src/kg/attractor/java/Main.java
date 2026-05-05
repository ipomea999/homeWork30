package kg.attractor.java;

import kg.attractor.java.homework.RestaurantOrders;

public class Main {
    public static void main(String[] args) {

        // это для домашки
        // выберите любое количество заказов, какое вам нравится.

        var orders = RestaurantOrders.read("orders_100.json");
        //var orders = RestaurantOrders.read("orders_1000.json").getOrders();
        //var orders = RestaurantOrders.read("orders_10_000.json").getOrders();

        // протестировать ваши методы вы можете как раз в этом файле (или в любом другом, в котором вам будет удобно)

        System.out.println("--- All Orders ---");
        orders.printOrders(orders.getOrders());

        System.out.println("\n--- Top 3 Orders ---");
        orders.printOrders(orders.getTopOrders(3));

        System.out.println("\n--- Lowest 3 Orders ---");
        orders.printOrders(orders.getLowestOrders(3));

        System.out.println("\n--- Most profitable home delivery ---");
        System.out.println(orders.getMostProfitableHomeDelivery().getTotal());

        System.out.println("\n--- Total cost of all orders ---");
        System.out.println(orders.getTotalCostOfAllOrders());

        System.out.println("\n--- Sorted unique emails ---");
        orders.getUniqueSortedEmails().forEach(System.out::println);
    }
}