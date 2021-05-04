package currencyrateservice.service;

import org.springframework.http.ResponseEntity;

public interface RestHandler {

    ResponseEntity<String> doGet();
}
