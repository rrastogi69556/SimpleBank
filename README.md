# Spring Boot Security OAuth Example

#### Securing REST API with Spring Security OAuth2

To run this repo, please follow these command :
1. use java 17 to build
2. mvn clean install -DskipTests
3. use/install mysql and use below queries to insert 
DB queries: 

CREATE TABLE account_holder (
id bigint(20) NOT NULL,
account_number varchar(25) NOT NULL,
account_name varchar(25) NOT NULL,
password varchar(75) NOT NULL,
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO account_holder (id, account_number, account_name, password) VALUES (1, 'NL12RABO0123456789', 'John Smith', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu');
INSERT INTO tbl_user (id, account_number, account_name, password) VALUES (2, NL12RABO0123456790, 'John Smith', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu');

-- John Smith/password

-- Assumptions
1. Knew the concept and could explain the decisions, but would have needed more time.
2. Tried to integrate the Oauth, and other validations 
3. Assumption was made that few rest APIs as the dependent ones to provide the bank account specific details

Could Spend only 3-4 hours.
What could have done better: 
1.  With more time, improve
2. Testability - write unit, integration test case
3. Running end to end
4. Remove technical debt.
5. commit history by history
6. integrated code quality scans in maven, security plugins to detect security vulnerability during compile time