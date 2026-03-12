package pubsubsystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import pubsubsystem.entities.Message;
import pubsubsystem.entities.Topic;
import pubsubsystem.subscriber.Subscriber;

public class PubSubService {
    private static final PubSubService INSTANCE = new PubSubService();
    private final ExecutorService deliveryExecutor;
    private final Map<String, Topic> topicRegistry;

    private PubSubService() {
        this.topicRegistry = new ConcurrentHashMap<>();
        // A cached thread pool is suitable for handling many short-lived, bursty tasks (message deliveries).
        deliveryExecutor = Executors.newCachedThreadPool();
    }

    public static PubSubService getInstance() {
        return INSTANCE;
    }

    public void createTopic(String topicName) {
        topicRegistry.putIfAbsent(topicName, new Topic(topicName, deliveryExecutor));
        System.out.println("Topic " + topicName + " created");
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        Topic topic = topicRegistry.get(topicName);
        if (topic == null)
            throw new IllegalArgumentException("Topic not found: " + topicName);
        topic.addSubscriber(subscriber);
        System.out.println("Subscriber '" + subscriber.getId() + "' subscribed to topic: " + topicName);
    }

    public void unsubscribe(String topicName, Subscriber subscriber) {
        Topic topic = topicRegistry.get(topicName);
        if (topic != null)
            topic.removeSubscriber(subscriber);
        System.out.println("Subscriber '" + subscriber.getId() + "' unsubscribed from topic: " + topicName);
    }

    public void publish(String topicName, Message message) {
        System.out.println("Publishing message to topic: " + topicName);
        Topic topic = topicRegistry.get(topicName);
        if (topic == null) throw new IllegalArgumentException("Topic not found: " + topicName);
        topic.broadcast(message);
    }

    public void shutdown() {
        System.out.println("PubSubService shutting down...");
        deliveryExecutor.shutdown();
        try {
            // Wait a reasonable time for existing tasks to complete
            if (!deliveryExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                deliveryExecutor.shutdownNow();
            }
        } catch (InterruptedException ie) {
            deliveryExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("PubSubService shutdown complete.");
    }
}

// Option A: Executor inside Topic

// Example:

// class Topic {
//     private final ExecutorService executor =
//             Executors.newCachedThreadPool();
// }
// What happens?

// If you have:

// 1 topic → 1 thread pool ✅

// 10 topics → 10 thread pools ⚠️

// 100 topics → 100 thread pools ❌

// Each thread pool:

// Manages its own threads

// Has its own lifecycle

// Consumes memory

// Needs shutdown logic

// That can become heavy quickly.

// Option B: Shared Executor (Your Current Design)
// // Created once
// deliveryExecutor = Executors.newCachedThreadPool();

// // Injected into each Topic
// new Topic(name, deliveryExecutor);

// Now:

// 1 thread pool

// Shared across all topics

// Centralized lifecycle management

// Easier shutdown

// Better resource utilization

// Why Shared Executor is Better Here
// 1️⃣ Resource efficiency

// Thread pools are expensive.

// Creating one per topic is wasteful.