CREATE OR REPLACE FUNCTION NthHighestSalary(N INT) RETURNS TABLE (Salary INT) AS $$
BEGIN
  IF N < 1 THEN RETURN;
  END IF;
  RETURN QUERY (
    -- Write your PostgreSQL query statement below.
    SELECT DISTINCT employee.salary
    FROM employee
    ORDER BY salary DESC
    LIMIT 1 OFFSET N - 1  
  );
END;
$$ LANGUAGE plpgsql;