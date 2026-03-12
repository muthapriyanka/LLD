package pubsubsystem.entities;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import pubsubsystem.subscriber.Subscriber;


public class Topic {
    private final String name;
    private final Set<Subscriber> subscribers;
    private final ExecutorService deliveryExecutor;

    public Topic(String name, ExecutorService deliveryExecutor) {
        this.name = name;
        this.deliveryExecutor = deliveryExecutor;
        this.subscribers = new CopyOnWriteArraySet<>();
    }

    public String getName() {
        return name;
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void broadcast(Message message) {
        for (Subscriber subscriber : subscribers) {
            deliveryExecutor.submit(() -> {
                try {
                    subscriber.onMessage(message);
                } catch (Exception e) {
                    System.err.println("Error delivering message to subscriber " + subscriber.getId() + ": " + e.getMessage());
                }
            });
        }
    }
}

// Suppose you have:
// 100 subscribers
// 1 message
// Without ExecutorService:
// If you call subscriber.onMessage() directly,
// One slow subscriber blocks others.
// With ExecutorService:
// Each subscriber processing runs in parallel.
// One slow subscriber does not block others.

// What Happens Internally

// Imagine:

// ThreadPool:
//   Worker1
//   Worker2
//   Worker3

// When you call:

// submit(task)

// The task goes to a queue.
// Workers pick tasks from queue and execute them.

// 🔹 Why Not Just Call subscriber.onMessage() Directly?

// If you did:

// subscriber.onMessage(message);

// Then:

// All subscribers execute sequentially.

// One slow subscriber delays others.

// Publisher is blocked until all finish.

// ExecutorService makes delivery asynchronous.

// 🔹 What Type of Executor You Used

// You used:

// Executors.newCachedThreadPool();

// This means:

// Thread pool grows as needed.

// Reuses idle threads.

// Good for short, bursty tasks (like message delivery).

// 🔹 What If You Removed ExecutorService?

// Then your broadcast becomes:

// for (Subscriber s : subscribers) {
//     s.onMessage(message);
// }

// Now:

// All subscribers run in the same thread.

// Slow subscriber blocks entire system.