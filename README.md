# Сurrency-rate-service

1. This service returns currency rate from https://api.privatbank.ua.

2. How to run:
   
   2.1. Test mode:
    - You should set "spring.profiles.active"=dev in application.properties;
    
    - After running of main method in CurrencyRateServiceApplication, you should be access postman 
      by this URL: http://localhost:8080/v1.0/currency/rate/{currency},
      "{currency}" - path variable of currency (e.g. "EUR", "GBP", "RUR", "USD");
      
    - For authorization: type: "Basic Auth", username: freeman@gmail.com, password: pass.

   2.2. Production mode:
    - You should install PostgreSQL and create service_db database;
   
    - Set "spring.profiles.active"=prod in application.properties;

    - After running of main method in CurrencyRateServiceApplication, you should be access postman 
      by this URL: http://localhost:8080/v1.0/currency/rate/{currency},
      "{currency}" - path variable of currency (e.g. "EUR", "GBP", "RUR", "USD");
      
    - For authorization: type: "Basic Auth", username: freeman@gmail.com, password: pass.
