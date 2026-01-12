package org.example.stockexchange.runner;

import org.example.stockexchange.model.Order;
import org.example.stockexchange.model.OrderType;
import org.example.stockexchange.model.StockTicker;
import org.example.stockexchange.service.StockExchangeService;

import java.util.Scanner;

public class StockExchangeRunnerInteractive {
    public static void main(String[] args) {
        StockExchangeService service = new StockExchangeService();

        for(StockTicker ticker : StockTicker.values()) {
            service.registerTradeWorker(ticker);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("(userId, BUY/SELL,INFY/TCS/TM/ELXSI,quantity,price)");

        while (true) {
            try {
                System.out.print("Your Order :");
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");

                    Order order = new Order(parts[0], OrderType.valueOf(parts[1]), StockTicker.valueOf(parts[2]), Integer.parseInt(parts[3]), Double.parseDouble(parts[4]));
                    service.placeOrder(order);
                }
            } catch (Exception ez) {

            }

        }
    }
}
