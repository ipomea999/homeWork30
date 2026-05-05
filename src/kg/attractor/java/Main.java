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

        System.out.println("--- Grouped by Customer Name ---");
        var groupedOrders = orders.groupOrdersByCustomerName();
        System.out.println("Unique customers count: " + groupedOrders.size());

        System.out.println("\n--- Total Sum by Customer ---");
        var customerTotals = orders.getTotalSumByCustomerName();
        customerTotals.forEach((name, total) -> System.out.println(name + " : " + total));

        System.out.println("\n--- Customer with Max Total Sum ---");
        System.out.println(orders.getCustomerWithMaxTotalSum());

        System.out.println("\n--- Customer with Min Total Sum ---");
        System.out.println(orders.getCustomerWithMinTotalSum());

        System.out.println("\n--- Items Grouped by Total Amount Sold ---");
        var itemsSold = orders.getItemsGroupedByAmount();
        itemsSold.forEach((item, amount) -> System.out.println(item + " : " + amount + " pcs"));
    }
}