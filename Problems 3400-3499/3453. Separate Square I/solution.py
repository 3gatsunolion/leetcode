class Solution:
    def separateSquares(self, squares: List[List[int]]) -> float:
        total_area = sum(l * l for _, _, l in squares)
        events = [(y, 1, l) for _, y, l in squares] + [(y + l, -1, l) for _, y, l in squares]

        events.sort()

        combined_width = 0
        area = 0
        prev_y = 0 # Constraints: x, y >= 0
        for y, isStart, l in events:
            h = y - prev_y

            area_to_add = combined_width * h

            if area + area_to_add >= total_area / 2.0:
                y_add = (total_area / 2.0 - area) / combined_width
                return prev_y + y_add

            combined_width += l * isStart
            area += area_to_add
            prev_y = y

        return -1
