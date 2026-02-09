-- Write your PostgreSQL query statement below
-- -- NOTE: When a subquery doesn't produce any result, its return value is NULL,
-- -- so if we want to return null if there's no second highest, we need to
-- -- wrap it into a subquery
-- SELECT (
--     SELECT DISTINCT salary
--     FROM employee
--     ORDER BY salary DESC
--     LIMIT 1 OFFSET 1
-- )
-- AS SecondHighestSalary;

SELECT MAX(salary) AS SecondHighestSalary
FROM employee
WHERE salary < (SELECT MAX(salary) FROM employee);