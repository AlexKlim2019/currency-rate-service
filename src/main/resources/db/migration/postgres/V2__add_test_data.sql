INSERT INTO users (first_name, last_name, email, password, role, status)
    VALUES  ('Gordon', 'Freeman', 'freeman@gmail.com', '$2y$12$6Xm/gCQ/me9WtFgk1kwxC.S2j.BVAHBEo2VV8ESVMRWUyrCgo9KGi', 'USER', 'ACTIVE'),
            ('Silvia', 'Drake', 'drake@gmail.com', '$2y$12$6Xm/gCQ/me9WtFgk1kwxC.S2j.BVAHBEo2VV8ESVMRWUyrCgo9KGi', 'USER', 'ACTIVE'),
            ('Martin', 'Last', 'last@gmail.com', '$2y$12$6Xm/gCQ/me9WtFgk1kwxC.S2j.BVAHBEo2VV8ESVMRWUyrCgo9KGi', 'USER', 'ACTIVE'),
            ('Marcus', 'Snake', 'snake@gmail.com', '$2y$12$6Xm/gCQ/me9WtFgk1kwxC.S2j.BVAHBEo2VV8ESVMRWUyrCgo9KGi', 'USER', 'ACTIVE');


INSERT INTO rates (currency, base_currency, sale_rate, purchase_rate, updated_date)
VALUES ('CHF', 'UAH', 17.0000000, 15.5000000, now()),
       ('EUR', 'UAH', 20.0000000, 19.2000000, now()),
       ('USD', 'UAH', 15.7000000, 15.3500000, now()),
       ('CAD', 'UAH', 15.0000000, 13.0000000, now());
