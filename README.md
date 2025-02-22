# SqlDelight 2.1.x Postgresql UnNest 

https://github.com/cashapp/sqldelight

Snapshot version: 2.1.0-SNAPSHOT

Prototype support for Postgresql `unnest` table function 

*Issues*
* IN PROGRESS https://github.com/sqldelight/sqldelight/issues/5346

e.g
```sql
CREATE TABLE Business(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    zipcodes TEXT[] NOT NULL,
    headcounts INTEGER[] NOT NULL
);

select:
SELECT name, location.headcount, location.zipcode
FROM Business, UNNEST(zipcodes, headcounts) AS location(zipcode, headcount);
```

```shell
createdb unnestdb &&
./gradlew build &&
./gradlew flywayMigrate
```

Flyway db migrations
https://documentation.red-gate.com/fd/gradle-task-184127407.html
