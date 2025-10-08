import java.time.*;
import java.util.*;

public class RateLimiter<K> {

    private static class Request {
        public Instant start;
        public int count;
        
        public Request(Instant start, int count) {
            this.start = start;
            this.count = count;
        }
    }
    
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
        requestLog.putIfAbsent(clientId, new Request(now, 0));
        Request request = requestLog.get(clientId);
        if (windowSize.compareTo(Duration.between(request.start, now)) <= 0) {
            request.start = now;
            request.count = 1;
            return true;
        }
        if (request.count < limit) {
            request.count += 1;
            return true;
        }
        return false;
    } 
}
