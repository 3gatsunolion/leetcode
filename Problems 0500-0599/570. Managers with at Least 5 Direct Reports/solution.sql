-- Write your PostgreSQL query statement below
-- SELECT e2.name AS name
-- FROM employee AS e1
-- JOIN employee AS e2
-- ON e1.managerId = e2.id
-- GROUP BY e1.managerId, e2.name
-- HAVING COUNT(e1.managerId) >= 5;

SELECT name
FROM employee
WHERE id IN (
    SELECT managerId
    FROM employee
    GROUP BY managerId
    HAVING COUNT(managerId) >= 5
);