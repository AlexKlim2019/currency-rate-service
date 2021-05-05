package currencyrateservice.service.impl;

import currencyrateservice.exception.ExternalDataSourceException;
import currencyrateservice.service.RestHandler;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;


@NoArgsConstructor
@Service
public class RestHandlerImpl implements RestHandler {

    private static final String DATE_PATTERN = "dd.MM.yyyy";

    private static final String JSON_PARAM = "json";

    private static final String DATE_PARAM = "date";

    @Value("${network.bank-data-url}")
    private String bankDataUrl;

    private RestTemplate restTemplate;

    @Autowired
    public RestHandlerImpl(
            RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> doGet() {
        HttpHeaders requestHeaders = createRequestHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        Map<String, String> params = populateParams();
        URI requestUrlWithParams = buildRequestUriWithParams(bankDataUrl, params);

        try {
            return restTemplate.exchange(
                    requestUrlWithParams,
                    HttpMethod.GET,
                    requestEntity,
                    String.class);
        } catch (RestClientException exception) {
            throw new ExternalDataSourceException(exception.getMessage());
        }

    }

    private HttpHeaders createRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return now().format(formatter);
    }

    private Map<String, String> populateParams() {
        Map<String, String> result = new HashMap<>();
        result.put(JSON_PARAM, null);
        result.put(DATE_PARAM, getCurrentDate());

        return result;
    }

    private URI buildRequestUriWithParams(String url, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        for (Map.Entry<String, String> param : params.entrySet()) {
            String paramName = param.getKey();
            String paramValue = param.getValue();
            builder.queryParam(paramName, paramValue);
        }
        return builder.encode(StandardCharsets.UTF_8).build().toUri();
    }
}
