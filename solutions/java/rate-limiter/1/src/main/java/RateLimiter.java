import java.time.*;
import java.util.*;

public class RateLimiter<K> {

    private record Request(Instant start, int count) {}
    
    private final Map<K, Request> requestLog = new HashMap<>();

    private final int limit;
    private final Duration windowSize;
    private final TimeSource timeSource;

    public RateLimiter(int limit, Duration windowSize, TimeSource timeSource) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timeSource = timeSource;
    }

    public boolean allow(K clientId) {
        Instant now = timeSource.now();
        Request request = requestLog.getOrDefault(clientId, new Request(now, 0));
        if (windowSize.compareTo(Duration.between(request.start(), now)) <= 0) {
            requestLog.put(clientId, new Request(now, 1));
            return true;
        }
        if (request.count() < limit) {
            requestLog.put(clientId, new Request(request.start(), request.count() + 1));
            return true;
        }
        return false;
    } 
}
