-- Write your PostgreSQL query statement below
SELECT patient_id, patient_name, conditions
FROM Patients
WHERE conditions LIKE 'DIAB1%' OR
CONDITIONS LIKE '% DIAB1%';