package urlshortner.src.entities;

public class AtomicIdGenerator implements IdGenerator {
    private long currentId;

    public AtomicIdGenerator() {
        this.currentId = 1000;
    }

    @Override
    public long nextId() {
        return ++currentId;
    }
}
