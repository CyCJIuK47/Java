# Setup
Login to mysql *(ensure that mysql server service is running)*:
```
mysql -u root -p
```

Create dev and test databases using [schema.sql](bookstore/src/main/resources/schema.sql):
```
source path_to_schema.sql
```

Configure [application.yml](bookstore/src/main/resources/application.yml) and [application-test.yml](bookstore/src/test/resources/application-test.yml) editing ``username``, ``password`` and ``url``*(port)* parameters.