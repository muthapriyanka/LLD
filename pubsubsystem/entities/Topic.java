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


// Step-by-step flow
// 1. broadcast() is called
// Main thread enters broadcast()
// 2. Loop over subscribers
// Subscriber1
// Subscriber2
// Subscriber3
// ...
// SubscriberN
// 3. For each subscriber → create a task

// This line:

// deliveryExecutor.submit(() -> { ... });

// does NOT execute immediately.

// 👉 It creates a task (Runnable) and gives it to the thread pool.

// 4. Tasks go into Executor queue
// [ Task1, Task2, Task3, ... ]
// 5. Thread pool picks tasks

// Suppose:

// ExecutorService executor = Executors.newFixedThreadPool(3);

// Then:

// Thread-1 → Subscriber1
// Thread-2 → Subscriber2
// Thread-3 → Subscriber3

// Remaining tasks wait in queue.

// 6. Parallel execution happens

// Each thread runs:

// subscriber.onMessage(message);

// So:

// Subscriber1 receives message
// Subscriber2 receives message
// Subscriber3 receives message
// → all at the same time

// new Runnable() {
//     @Override
//     public void run() {
//         subscriber.onMessage(message);
//     }
// }

// Use ExecutorService when you have many tasks: Runnable represents the unit of work. 
// ExecutorService is used to execute many Runnable tasks efficiently using a thread pool. For a pub-sub broadcast, I prefer ExecutorService because it avoids creating a new thread per subscriber and gives better control over concurrency.