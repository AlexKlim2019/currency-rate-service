INSERT INTO users (first_name, last_name, email, password)
    VALUES  ('Gordon', 'Freeman', 'freeman@gmail.com', 'pass'),
            ('Silvia', 'Drake', 'drake@gmail.com', 'pass'),
            ('Martin', 'Last', 'last@gmail.com', 'pass'),
            ('Marcus', 'Snake', 'snake@gmail.com', 'pass');


INSERT INTO rates (currency, base_currency, sale_rate, purchase_rate, updated_date)
VALUES ('CHF', 'UAH', 17.0000000, 15.5000000, now()),
       ('EUR', 'UAH', 20.0000000, 19.2000000, now()),
       ('USD', 'UAH', 15.7000000, 15.3500000, now()),
       ('CAD', 'UAH', 15.0000000, 13.0000000, now());
