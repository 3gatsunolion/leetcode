-- Write your PostgreSQL query statement below
WITH first_logins AS (
    SELECT player_id, MIN(event_date) AS date
    FROM activity
    GROUP BY player_id
)

SELECT ROUND(
        AVG(CASE WHEN activity.player_id IS NOT NULL THEN 1
            ELSE 0 END)
       , 2) AS fraction
FROM first_logins
LEFT JOIN activity
ON first_logins.player_id = activity.player_id AND
   first_logins.date = activity.event_date - 1;