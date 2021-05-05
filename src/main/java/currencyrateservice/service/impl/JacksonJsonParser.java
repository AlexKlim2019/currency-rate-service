package currencyrateservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import currencyrateservice.config.Constants.ErrorMessage;
import currencyrateservice.exception.JsonParsingException;
import currencyrateservice.service.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class JacksonJsonParser implements JsonParser {

    private final ObjectMapper mapper;

    @Override
    public <T> T parseJson(String json, Class<T> targetClz) {
        try {
            return mapper.readValue(json, targetClz);
        } catch (JsonProcessingException exception) {
            throw new JsonParsingException(exception,
                    ErrorMessage.JSON_PARSING_EXCEPTION, json, targetClz);
        }
    }
}
