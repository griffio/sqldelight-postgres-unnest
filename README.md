# SqlDelight 2.1.x Postgresql UnNest 

https://github.com/cashapp/sqldelight

Snapshot version: 2.1.0-SNAPSHOT

Prototype support for Postgresql `unnest` table function 

* function
* table row function
* bulk insert, delete and upp

*Issues*
* IN PROGRESS https://github.com/sqldelight/sqldelight/issues/5346

e.g Supported
```sql
CREATE TABLE Business(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    zipcodes TEXT[] NOT NULL,
    headcounts INTEGER[] NOT NULL
);

CREATE TABLE Users (
   name TEXT NOT NULL,
   age INTEGER NOT NULL
);

select:
SELECT name, location.headcount, location.zipcode
FROM Business, UNNEST(zipcodes, headcounts) AS location(zipcode, headcount);

counts:
SELECT name, UNNEST(headcounts) AS headcount
FROM Business
ORDER BY headcount DESC;

array:
SELECT unnest(ARRAY[1,2]);

insertUsers:
INSERT INTO Users (name, age)
SELECT * FROM UNNEST(?::TEXT[], ?::INTEGER[]);

updateUsers:
UPDATE Users
SET age=updates.updated_age
FROM UNNEST(?::TEXT[], ?::INTEGER[]) AS updates(name, updated_age)
WHERE Users.name = updates.name;

deleteUsers:
DELETE FROM Users
WHERE (name, age) IN (
  SELECT *
  FROM UNNEST(?::TEXT[], ?::INTEGER[]) AS u(name, age)
);

selectLocations:
SELECT DISTINCT b.*
FROM Business b
JOIN LATERAL UNNEST(b.zipcodes) AS loc(zipcode) ON loc.zipcode ILIKE '%' || :query || '%';

```

```shell
createdb unnestdb &&
./gradlew build &&
./gradlew flywayMigrate
```

Flyway db migrations
https://documentation.red-gate.com/fd/gradle-task-184127407.html
