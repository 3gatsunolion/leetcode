-- Write your PostgreSQL query statement below
SELECT 
    s.user_id AS "user_id",
    ROUND(AVG(CASE
        WHEN c.action = 'confirmed' THEN 1
        ELSE 0
    END), 2) AS "confirmation_rate"
FROM Signups AS "s"
LEFT JOIN Confirmations AS "c"
ON s.user_id = c.user_id
GROUP BY s.user_id;