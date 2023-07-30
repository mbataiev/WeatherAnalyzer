package com.app.weather.service;

import com.app.weather.domains.WeatherDataDto;
import com.app.weather.exception.ProcessingException;
import com.app.weather.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String API_KEY = "&appid=00bfe34f2cd30617524339d4a409a99b";
    private static final String DATA_PROCESSOR = "http://localhost:8081/process?city=";

    @Override
    public WeatherDataDto getWeatherDataFromApi(String cityName) {
        String weatherDataJson = getWeatherData(cityName).orElseThrow(
                () -> new ResourceNotFoundException("City", "name", cityName)
        );
        log.debug(weatherDataJson);

        return postWeather(weatherDataJson, cityName).orElseThrow(
                () -> new ProcessingException(weatherDataJson)
        );
    }

    private Optional<WeatherDataDto> postWeather(String json, String city) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

        return Optional.ofNullable(restTemplate.postForObject(DATA_PROCESSOR + city, requestEntity, WeatherDataDto.class));
    }

    private Optional<String> getWeatherData(String city) {
        String url = BASE_URL + city + API_KEY;
        String weatherDataJson = null;

        try {
            weatherDataJson = restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            log.error(String.format("City with name %s is not found", city));
        }

        return Optional.ofNullable(weatherDataJson);
    }


}
