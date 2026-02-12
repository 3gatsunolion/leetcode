class Heap {
    array: number[];
    cmp: (a: number, b: number) => boolean;

    constructor(array = [], cmp = ((a, b) => a < b)) {
        this.array = array;
        this.cmp = cmp;

        this.heapify(this.array);
    }

    percDown(array, pos, end) {
        let childPos = 2 * pos + 1;
        while (childPos <= end) {
            const rightPos = childPos + 1;
            if (rightPos <= end && this.cmp(array[rightPos], array[childPos])) {
                childPos = rightPos;
            }

            if (this.cmp(array[childPos], array[pos])) {
                this.swap(array, childPos, pos);
                pos = childPos;
                childPos = 2 * pos + 1;
            } else {
                break;
            }
        }
    }

    percUp(start, pos) {
        while (pos > start) {
            const parent = (pos - 1) >> 1;
            if (this.cmp(this.array[pos], this.array[parent])) {
                this.swap(this.array, pos, parent);
                pos = parent;
            } else {
                break;
            }
        }
    }

    swap(array, i, j) {
        [array[i], array[j]] = [array[j], array[i]];
    }

    /**
    * Constructs the initial heap structure with a given `array`.
    * @param {Array} array The initial array.
    */
    heapify(array) {
        const n = array.length;
        for (let i = Math.floor(n / 2); i >= 0; i--) {
            this.percDown(array, i, n - 1);
        }
    }

    /**
    * Adds an item into the heap.
    * @param {*} item The item to be added into the heap.
    */
    insert(value) {
        this.array.push(value);
        this.percUp(0, this.array.length - 1);
    }

    /**
    * Removes the top of the heap / maximum element.
    * @return {*} The element removed.
    */
    pop() {
        if (this.isEmpty()) return;
        const lastEl = this.array.pop();
        let max;
        if (!this.isEmpty()) {
            max = this.array[0];
            this.array[0] = lastEl;
            this.percDown(this.array, 0, this.array.length - 1);
        } else {
            max = lastEl;
        }
        return max;
    }

    /**
    * Returns the value of the maximum element.
    * @return {number} The maximum element.
    */
    peek() {
        if (this.isEmpty()) return;
        return this.array[0];
    }

    isEmpty() {
        return this.array.length === 0;
    }
}

function maxEvents(events: number[][]): number {
    events.sort((a, b) => a[0] - b[0]);

    const minHeap = new Heap();
    const n = events.length;
    let i = 0;
    let day = 1;
    let res = 0;
    while (i < n || !minHeap.isEmpty()) {
        if (i < n && minHeap.isEmpty()) {
            day = events[i][0];
        }

        while (!minHeap.isEmpty() && minHeap.peek()! < day) {
            minHeap.pop();
        }

        while (i < n && events[i][0] === day) {
            minHeap.insert(events[i++][1]);
        }

        if (!minHeap.isEmpty()) {
            minHeap.pop();
            res++;
            day++;
        }
    }

    return res;
};