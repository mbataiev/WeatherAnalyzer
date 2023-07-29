package com.app.weather.service;

import com.app.weather.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public String getWeatherDataFromApi(String cityName) {
        String weatherDataJson = getWeatherData(cityName).orElseThrow(
                () -> new ResourceNotFoundException("City", "name", cityName)
        );
        log.debug(weatherDataJson);

        return weatherDataJson;
    }

    private Optional<String> getWeatherData(String city) {
        String url = BASE_URL + city + API_KEY;
        String weatherDataJson = null;

        try {
            weatherDataJson = restTemplate.getForObject(url, String.class);
        } catch (Exception e){
            log.error(String.format("City with name %s is not found",city));
        }

        return Optional.ofNullable(weatherDataJson);
    }


}
