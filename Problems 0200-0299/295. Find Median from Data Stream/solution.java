class MedianFinder {
    private Queue<Integer> lo;
    private Queue<Integer> hi;

    public MedianFinder() {
        lo = new PriorityQueue<Integer>((a, b) -> b - a);
        hi = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        if (lo.size() == hi.size()) {
            hi.offer(num);
            lo.offer(hi.poll());
        } else {
            // Note: lo.size() >= hi.size(), so here, lo.size() > hi.size()
            // So we want to add number so that lo.size() == hi.size()
            lo.offer(num);
            hi.offer(lo.poll());
        }
    }
    
    public double findMedian() {
        if (lo.size() == hi.size()) {
            return (lo.peek() + hi.peek()) / 2.0;
        } else {
            return lo.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */