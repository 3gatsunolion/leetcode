class SegmentNode:
    def __init__(self, start, end, sumVal=0, maxVal=0):
        self.start = start
        self.end = end
        # left child
        self.left = None
        self.right = None
        self.sumVal = sumVal
        # row/node with max value, this is for
        # gather function to efficiently find
        # smallest row where we can seat k people
        self.maxVal = maxVal

class SegmentTree:
    def __init__(self, numRows, numSeatsPerRow):
        self.root = self.build(0, numRows-1, numSeatsPerRow)
        self.numSeatsPerRow = numSeatsPerRow

    def build(self, start, end, numSeatsPerRow):
        if start == end:
            return SegmentNode(start, end, numSeatsPerRow, numSeatsPerRow)
        
        mid = start + (end - start) // 2
        node = SegmentNode(start, end)
        node.left = self.build(start, mid, numSeatsPerRow)
        node.right = self.build(mid+1, end, numSeatsPerRow)
        node.sumVal = node.left.sumVal + node.right.sumVal
        # start off with every row being empty
        node.maxVal = numSeatsPerRow
        return node

    def updateRow(self, row, k):
        def updateTree(node):
            if node.start > row or node.end < row:
                return
            if node.start == node.end:
                node.sumVal -= k
                node.maxVal -= k
                return
            updateTree(node.left)
            updateTree(node.right)
            node.sumVal = node.left.sumVal + node.right.sumVal
            node.maxVal = max(node.left.maxVal, node.right.maxVal)
        
        updateTree(self.root)

    def findSmallestRowWithKSeats(self, maxRow, k):
        def queryMax(node):
            if node.start == node.end:
                if node.end > maxRow or node.sumVal < k:
                    return []
                return [node.start, self.numSeatsPerRow-node.sumVal]
            # we want smallest row
            if node.left.maxVal >= k:
                return queryMax(node.left)
            return queryMax(node.right)
        return queryMax(self.root)

    def sumRange(self, start, end):
        def sumRangeHelper(node):
            if node.start > end or node.end < start:
                return 0
            if node.start >= start and node.end <= end:
                return node.sumVal
            return sumRangeHelper(node.left) + sumRangeHelper(node.right)
        return sumRangeHelper(self.root)

    def updateRows(self, maxRow, k):
        def updateSmallestRows(node, numToReserve):
            if node.start > maxRow or numToReserve == 0:
                return
            if node.start == node.end:
                node.maxVal -= numToReserve
                node.sumVal -= numToReserve
                return
            
            if node.right.start > maxRow or node.left.sumVal >= numToReserve:
                updateSmallestRows(node.left, numToReserve)
            else:
                numToReserveRight = numToReserve - node.left.sumVal
                updateSmallestRows(node.left, node.left.sumVal)
                updateSmallestRows(node.right, numToReserveRight)
            
            node.sumVal = node.left.sumVal + node.right.sumVal
            node.maxVal = max(node.left.maxVal, node.right.maxVal)
        updateSmallestRows(self.root, k)

class BookMyShow:

    def __init__(self, n: int, m: int):
        self.numRows = n
        self.numSeatsPerRow = m
        self.segTree = SegmentTree(n, m)
    # // row 0: 0
    # // row 1: 0
    # // row 2: 0
    # // row 3: 31 left
    def gather(self, k: int, maxRow: int) -> List[int]:
        if k > self.numSeatsPerRow: return []

        # Since we always buy from lowest seat, we'll
        # always have continuous seats in a row until
        # none are left
        res = self.segTree.findSmallestRowWithKSeats(maxRow, k)
        if res:
            row = res[0]
            self.segTree.updateRow(row, k)
        return res

    def scatter(self, k: int, maxRow: int) -> bool:
        numAvailable = self.segTree.sumRange(0, maxRow)
        if numAvailable >= k:
            # Allocate
            self.segTree.updateRows(maxRow, k)
            return True
        return False


# Your BookMyShow object will be instantiated and called as such:
# obj = BookMyShow(n, m)
# param_1 = obj.gather(k,maxRow)
# param_2 = obj.scatter(k,maxRow)