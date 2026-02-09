-- Write your PostgreSQL query statement below
-- DELETE FROM person
-- WHERE id NOT IN (
--     SELECT MIN(id)
--     FROM person
--     GROUP BY email
-- );

DELETE FROM person AS p1
USING person AS p2
WHERE p1.email = p2.email AND p1.id > p2.id;