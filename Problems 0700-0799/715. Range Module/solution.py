class SegmentNode:
    def __init__(self, start, end, isTracked=False, left=None, right=None):
        self.isTracked = isTracked
        self.start = start
        self.end = end
        self.left = left
        self.right = right

class SegmentTree:
    def __init__(self):
        self.root = SegmentNode(1, 10**9)

    def update(self, start, end, isTracked):
        self.updateTree(start, end, isTracked, self.root)

    def updateTree(self, start, end, isTracked, node):
        if node.start == start and node.end == end:
            node.isTracked = isTracked
            # No need to keep these child nodes
            node.left = node.right = None
            return

        # a node should have zero child or 2 child
        mid = node.start + (node.end - node.start) // 2
        if not node.left:
            # make sure to set node.isTracked in children
            node.left = SegmentNode(node.start, mid, node.isTracked)
            node.right = SegmentNode(mid+1, node.end, node.isTracked)
        if end <= mid:
            self.updateTree(start, end, isTracked, node.left)
        elif start > mid:
            self.updateTree(start, end, isTracked, node.right)
        else:
            self.updateTree(start, mid, isTracked, node.left)
            self.updateTree(mid+1, end, isTracked, node.right)

        node.isTracked = node.left.isTracked and node.right.isTracked

    def query(self, start, end):
        return self.queryTree(start, end, self.root)

    def queryTree(self, start, end, node):
        if not node.left:
            return node.isTracked
    
        if node.start == start and node.end == end:
            return node.isTracked
        
        mid = node.start + (node.end - node.start) // 2
        if end <= mid:
            return self.queryTree(start, end, node.left)
        elif start > mid:
            return self.queryTree(start, end, node.right)
        else:
            return self.queryTree(start, mid, node.left) and self.queryTree(mid+1, end, node.right)


# class RangeModule:

#     def __init__(self):
#         self.segTree = SegmentTree()
        

#     def addRange(self, left: int, right: int) -> None:
#         self.segTree.update(left, right-1, True)

#     def queryRange(self, left: int, right: int) -> bool:
#         return self.segTree.query(left, right-1)

#     def removeRange(self, left: int, right: int) -> None:
#         self.segTree.update(left, right-1, False)

class RangeModule:

    def __init__(self):
        # in sorted order:
        # [start1, end2, start2, end2]
        # even indices, start
        # odd indices, end
        self.ranges = []
        

    def addRange(self, left: int, right: int) -> None:
        start = bisect.bisect_left(self.ranges, left)
        end = bisect.bisect_right(self.ranges, right)

        interval = []
        # if start % 2 == 0, then either start is len of ranges
        # (so we need to add), or ranges[start-1] < left <= ranges[start]
        # so we should add or replace ranges[start] with left
        if start % 2 == 0:
            interval.append(left)

        # if start % 2 == 1, ranges[start-1] < left <= ranges[start],
        # meaning it's already in an existing range or it's possible
        # that left = ranges[start] aka end of an interval, but
        # right >= left, so we know that we don't need to add
        # a start, we just need to extend current interval with
        # new end

        # if end % 2 == 0, ranges[end-1] <= right < ranges[end]
        # so it's possible this is extending an existing interval,
        # or from new interval (depending on left) so we should add
        if end % 2 == 0:
            interval.append(right)

        # if end % 2 == 1, ranges[end-1] <= right < ranges[end]
        # part of existing range, so don't need to add

        # Like: splice(insertIndex, deleteHowMany, insertThis)
        self.ranges[start:end] = interval

    def queryRange(self, left: int, right: int) -> bool:
        start = bisect.bisect_right(self.ranges, left)
        end = bisect.bisect_left(self.ranges, right)

        # if start % 2 == 0, ranges[start-1] <= left < ranges[start]
        # which means it's not in range

        # if start % 2 == 1, ranges[start-1] <= left < ranges[start]
        # it's possible that this is within range if start == end

        # if end % 2 == 0, ranges[end-1] < right <= ranges[end],
        # not in range
        
        # if end % 2 == 1, ranges[end-1] < right <= ranges[end],
        # then it's within range if start == end

        return start == end and start % 2 == 1

    def removeRange(self, left: int, right: int) -> None:
        start = bisect.bisect_left(self.ranges, left)
        end = bisect.bisect_right(self.ranges, right)

        splits = []
        if start % 2 == 1:
            splits.append(left)
        if end % 2 == 1:
            splits.append(right)

        self.ranges[start:end] = splits


# Your RangeModule object will be instantiated and called as such:
# obj = RangeModule()
# obj.addRange(left,right)
# param_2 = obj.queryRange(left,right)
# obj.removeRange(left,right)