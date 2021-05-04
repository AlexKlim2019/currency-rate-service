package currencyrateservice.service;

public interface JsonParser {

    <T> T parseJson(String json, Class<T> targetClass);
}
