-- Write your PostgreSQL query statement below
-- WITH employees_ranked_by_salary AS (
--     SELECT d.name AS department,
--            e.name AS employee,
--            salary,
--            DENSE_RANK() OVER(PARTITION BY e.departmentId ORDER BY salary DESC) AS salary_rank
--     FROM employee AS e
--     JOIN department AS d
--     ON e.departmentId = d.id
-- )

-- SELECT department, employee, salary
-- FROM employees_ranked_by_salary
-- WHERE salary_rank <= 3;

-- Without using window functions
WITH distinct_salaries_per_department AS (
    SELECT departmentId, salary
    FROM employee
    GROUP BY departmentId, salary
),

employees_ranked_by_salary AS (
    SELECT e.id AS id,
           e.name,
           e.departmentId,
           e.salary,
           COUNT(e.id) AS salary_rank
    FROM employee AS e
    JOIN distinct_salaries_per_department AS d
    ON e.departmentId = d.departmentId AND e.salary <= d.salary
    GROUP BY e.id, e.name, e.departmentId, e.salary
)

SELECT d.name AS department,
       e.name AS employee,
       e.salary AS salary
FROM employees_ranked_by_salary AS e
JOIN department AS d
ON e.departmentId = d.id
WHERE e.salary_rank <= 3;