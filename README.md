2 Task, 0120
Duration: 10 working days or 60 working hours

Business Requirements

Create a backend for simple News Management application. Pay attention that your application should be without view layer (UI).
The system should expose REST APIs to perform the following operations:
1 CRUD operations for News.
1.a If new tags and(or) Author are passed during creation or modification of a News, then they should be created in the database. (Save
    news message with author and tags should go as one step in service layer.)
2 CRUD operations for Tag.
3 CRUD operations for Author.
4 Get news. All request parameters are optional and can be used in conjunction.
    4.a Sort news by date or tag or author;
    4.b Search according SearchCriteria (see details in Tools and Implementation Requirements section);
5 Add tag/tags for news message;
6 Add news author;
7 Each news should have only one author;
8 Each news may have more than 1 tag;
9 Count all news;


Technical requirements

General Requirements:

1 Code should be clean and should not contain any “developer-purpose” constructions.
2 Application should be designed and written with respect to OOD and SOLID principles.
3 Code should contain valuable comments where appropriate.
4 Public APIs should be documented using Javadoc.
5 A clear layered structure should be used: responsibilities of each application layer should be defined.
6 JSON should be used as a format of client-server communication messages.
7 Convenient error and exception handling mechanism should be implemented: all errors should be meaningful and localized on backend side.
8 Abstraction should be used to avoid code duplication.
9 Database schema should be adjusted as described on image. Create 2 separate db scripts: one for db schema generation and one for loading
default data (near 20 items for each table);

Tools and Implementation Requirements:

Please note that only GA versions of tools, frameworks, and libraries are allowed.

JDK version: 8. Use Streams, java.time.*, etc. where it is appropriate.
Application package root: com.epam.lab
Database connection pooling: HikariCP.
Spring JDBC Template should be used for data access.
Java Code Convention is mandatory. Exception: margin size – 120 chars.
Build tool: Apache Maven 3.5+. Multi-module project.
Web server: Apache Tomcat.
Application container: Spring IoC. Spring Framework +.
Spring should be configured via Java config (no spring.xml).
Database: PostgreSQL 9.+ or 10.+
Testing: JUnit 4.+ or 5.+, Mockito.
Service layer should be covered with unit tests. Unit tests coverage should be not less than 80%.
Repository layer should be tested using integration tests with an in-memory embedded database. All operations with news should be covered with
integration tests.
APIs should be demonstrated using Postman tool. Postman collections with APIs should be prepared for the demo.
For search functionality use SeachCriteria object which may contain author entity and list of tags (will be using for constructing SQL queries in
DAO layer);