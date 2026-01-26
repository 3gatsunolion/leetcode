class Heap {
    private elements: any[];
    private comparator: (a: any, b: any) => number;

    constructor(elements=[], comparator=((a, b) => a - b)) {
        this.elements = elements;
        this.comparator = comparator;
        this.heapify();
    }

    // Siftdown faster than siftUp
    heapify(): void {
        if (this.elements.length === 0) return;
        // Leaf nodes don't need to sift down and take up half of nodes
        for (let i = Math.floor(this.elements.length / 2); i >= 0; i--) {
            this.siftDown(i);
        }
    }

    siftDown(start): void {
        let pos = start;
        const heap = this.elements;
        const cmp = this.comparator;
        let childPos = 2 * start + 1;
        while (childPos < heap.length) {
            const rightPos = childPos + 1;
            if (rightPos < heap.length && cmp(this.elements[rightPos], this.elements[childPos]) < 0) {
                childPos = rightPos;
            }

            if (cmp(heap[childPos], heap[pos]) < 0) {
                [heap[childPos], heap[pos]] = [heap[pos], heap[childPos]];
                pos = childPos;
                childPos = 2 * pos + 1;
            } else {
                break;
            }
        }
    }

    siftUp(start): void {
        let pos = start;
        const heap = this.elements;
        const cmp = this.comparator;
        while (pos > 0) {
            const parent = (pos - 1) >> 1;
            if (cmp(heap[pos], heap[parent]) < 0) {
                [heap[pos], heap[parent]] = [heap[parent], heap[pos]];
                pos = parent;
            } else {
                break;
            }
        }
    }

    push(element): void {
        this.elements.push(element);
        this.siftUp(this.elements.length - 1);
    }

    pop(): any {
        let res = this.elements[0];
        const heap = this.elements;
        if (heap.length === 1) {
            heap.pop();
        } else {
            [heap[0], heap[heap.length - 1]] = [heap[heap.length - 1], heap[0]];
            heap.pop();
            this.siftDown(0);
        }

        return res;
    }

    peek(): any {
        return this.elements[0];
    }

    isEmpty(): boolean {
        return this.elements.length === 0;
    }

    size(): number {
        return this.elements.length;
    }
}

class MedianFinder {
    left: Heap;
    right: Heap;
    constructor() {
        // left is maxHeap
        this.left = new Heap([], (a, b) => b - a);
        this.right = new Heap();
    }

    addNum(num: number): void {
        // If odd number of elements, let's just choose right
        // side to have the extra element
        if (this.left.size() === this.right.size()) {
            this.left.push(num);
            this.right.push(this.left.pop());
        } else {
            this.right.push(num);
            this.left.push(this.right.pop());
        }
    }

    findMedian(): number {
        if (this.left.size() === this.right.size()) {
            return (this.left.peek() + this.right.peek()) / 2;
        } else {
            return this.right.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = new MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */