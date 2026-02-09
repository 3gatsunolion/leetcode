-- Write your PostgreSQL query statement below
-- SELECT DISTINCT l1.num AS ConsecutiveNums
-- FROM logs AS l1
-- JOIN logs AS l2
-- ON l1.id = l2.id - 1 AND l1.num = l2.num
-- JOIN logs AS l3
-- ON l2.id = l3.id - 1 AND l2.num = l3.num;

WITH consecutive_nums AS (
    SELECT num,
           LAG(num) OVER(ORDER BY id) AS prev_num,
           LEAD(num) OVER(ORDER BY id) AS next_num
    FROM logs
)

SELECT DISTINCT num AS ConsecutiveNums
FROM consecutive_nums
WHERE num = prev_num AND num = next_num;