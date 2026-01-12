package org.example.kafka;

public class KafkaRunner_v2 {
    public void run() {
        Kafka_v2 kafka = Kafka_v2.getInstance();

        String orderPlacedTopic = "order_placed";
        String inventorySubscription = "inventory";
        String fraudDetectionSubscription = "fraud";
        kafka.createTopic(orderPlacedTopic);
        kafka.registerSubscriber(orderPlacedTopic, inventorySubscription, new InventorySubscriber());
        kafka.registerSubscriber(orderPlacedTopic, fraudDetectionSubscription, new FraudDetectionSubscriber());




        String orderCancelledTopic = "order_cancelled";
        kafka.createTopic(orderCancelledTopic);
        kafka.registerSubscriber(orderCancelledTopic, inventorySubscription, new InventorySubscriber());
        kafka.registerSubscriber(orderCancelledTopic, fraudDetectionSubscription, new FraudDetectionSubscriber());

        kafka.publish(orderPlacedTopic, new OrderMessage("order_123"));
        kafka.publish(orderPlacedTopic, new OrderMessage("order_456"));
        kafka.publish(orderCancelledTopic, new OrderMessage("order_789_cancelled"));
        kafka.publish(orderCancelledTopic, new OrderMessage("order_321_cancelled"));
        kafka.publish(orderPlacedTopic, new OrderMessage("order_345"));
        kafka.publish(orderPlacedTopic, new OrderMessage("order_812"));
        kafka.publish(orderCancelledTopic, new OrderMessage("order_765_cancelled"));
        kafka.publish(orderPlacedTopic, new OrderMessage("order_864"));
    }
}
