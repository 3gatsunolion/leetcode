-- Write your PostgreSQL query statement below
WITH student_subject AS (
    SELECT st.student_id AS student_id,
           st.student_name AS student_name,
           sub.subject_name AS subject_name
    FROM students AS st
    CROSS JOIN subjects AS sub
)

SELECT s.student_id AS student_id,
       student_name,
       s.subject_name AS subject_name,
       COUNT(e.student_id) AS attended_exams
FROM student_subject AS s
LEFT JOIN examinations AS e
ON s.student_id = e.student_id AND e.subject_name = s.subject_name
GROUP BY s.student_id, s.student_name, s.subject_name
ORDER BY s.student_id, s.subject_name;