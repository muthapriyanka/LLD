package urlshortner.src.entities;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicIdGenerator implements IdGenerator {
    private final AtomicLong currentId;

    public AtomicIdGenerator() {
        this.currentId = new AtomicLong(1000);
    }

    @Override
    public long nextId() {
        return currentId.incrementAndGet();
    }
}
