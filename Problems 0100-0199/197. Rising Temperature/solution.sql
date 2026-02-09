-- Write your PostgreSQL query statement below
SELECT w1.id AS id
FROM weather AS w1
JOIN weather AS w2
ON w1.recordDate = w2.recordDate + 1 -- interval '1 day' works too
WHERE w1.temperature > w2.temperature;