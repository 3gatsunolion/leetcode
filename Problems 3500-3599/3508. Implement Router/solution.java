class Packet {
    int source;
    int destination;
    int timestamp;

    Packet(int source, int destination, int timestamp) {
        this.source = source;
        this.destination = destination;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Packet packet = (Packet) other;
        return source == packet.source &&
                destination == packet.destination &&
                timestamp == packet.timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, timestamp);
    }
}

class Router {
    private int memoryLimit;
    private Queue<Packet> fifo;
    private Map<Integer, List<Integer>> timestamps;
    // private Map<Integer, Integer> starts;
    private Set<Packet> seen;

    public Router(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.fifo = new LinkedList<>();
        this.timestamps = new HashMap<>();
        // this.starts = new HashMap<>();
        this.seen = new HashSet<>();
    }
    
    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);
        if (seen.contains(packet)) {
            return false;
        }
        if (fifo.size() == memoryLimit) {
            forwardPacket(); // Pop first element from fifo queue
        }
        seen.add(packet);
        fifo.add(packet);
        timestamps.computeIfAbsent(destination, k -> new ArrayList<>())
                  .add(timestamp);
        return true;
    }
    
    public int[] forwardPacket() {
        if (fifo.isEmpty()) return new int[] {};
        Packet packet = fifo.poll();
        seen.remove(packet);
        // starts.put(packet.destination, starts.getOrDefault(packet.destination, 0) + 1);
        timestamps.get(packet.destination).remove(0);
        return new int[] {packet.source, packet.destination, packet.timestamp};
    }
    
    public int getCount(int destination, int startTime, int endTime) {
        if (!timestamps.containsKey(destination)) return 0;
        List<Integer> list = timestamps.get(destination);
        // int l = binarySearchLeft(list, startTime, starts.getOrDefault(destination, 0));
        // int r = binarySearchRight(list, endTime, starts.getOrDefault(destination, 0));
        int l = binarySearchLeft(list, startTime, 0);
        int r = binarySearchRight(list, endTime, 0);
        return r - l;
    }

    private int binarySearchLeft(List<Integer> arr, int target, int start) {
        int lo = start;
        int hi = arr.size();

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr.get(mid) < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private int binarySearchRight(List<Integer> arr, int target, int start) {
        int lo = start;
        int hi = arr.size();

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr.get(mid) > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */