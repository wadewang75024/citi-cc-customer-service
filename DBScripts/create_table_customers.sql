-- Dont forget to add AUTO_INCREMENT to avoid "No Default value" exception in Java
-- Use timestamp to see both date and time
use mydb;
CREATE TABLE citi_customers 
(customer_id bigInt not null AUTO_INCREMENT, 
first_name VARCHAR(20),
last_name VARCHAR(20),
address VARCHAR(100),
phone_number VARCHAR(20),
ssn VARCHAR(11),
primary key (customer_id));

-- Misc
delete from citi_customers
where customer_id=4

select * from citi_customers

drop table citi_customers;

insert into citi_customers
values (0, 'Wade', 'Wang', '1000 Main Street', '2140000000', '000-00-0000');

