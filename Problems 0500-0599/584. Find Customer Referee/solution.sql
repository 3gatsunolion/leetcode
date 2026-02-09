-- Write your PostgreSQL query statement below
SELECT name
FROM customer
WHERE COALESCE(referee_id, 0) != 2; -- NULL != 2 IS NULL
