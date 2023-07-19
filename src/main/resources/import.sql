insert into kitchen (id, name) values (1, 'Japonese');
insert into kitchen (id, name) values (2, 'Brazilian');
insert into kitchen (id, name) values (3, 'Thailandese');

insert into restaurant (id, name, delivery_fee, kitchen_id) values (1, 'Fuji', 3.0, 1);
insert into restaurant (id, name, delivery_fee, kitchen_id) values (2, 'Nice Restaurant', 3.0, 2);
insert into restaurant (id, name, delivery_fee, kitchen_id) values (3, 'Thai Delivery', 2.50, 3);

insert into state (id, name) values (1, 'Porto');
insert into state (id, name) values (2, 'Lisbon');

insert into city (id, name, state_id) values (1, 'Valongo', 1);

insert into payment_method (id, description) values (1, 'Credit card');
insert into payment_method (id, description) values (2, 'Debit card');
insert into payment_method (id, description) values (3, 'Money');

insert into permission (id, name, description) values (1, 'Admin', 'This allows you to make changes');
insert into permission (id, name, description) values (2, 'Consultation', 'This only allows you to make consultations');
