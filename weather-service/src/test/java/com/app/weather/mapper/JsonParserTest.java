package com.app.weather.mapper;

import com.app.weather.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import com.app.weather.dto.WeatherDataDto;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class JsonParserTest {

    @Test
    public void testJsonToWeatherDto() {
        String inputJson = "{\"coord\":{\"lon\":-0.1257,\"lat\":51.5085},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":290.67,\"feels_like\":290.7,\"temp_min\":289.69,\"temp_max\":291.63,\"pressure\":1005,\"humidity\":85},\"visibility\":10000,\"wind\":{\"speed\":4.63,\"deg\":250},\"clouds\":{\"all\":100},\"dt\":1690527843,\"sys\":{\"type\":2,\"id\":2075535,\"country\":\"GB\",\"sunrise\":1690517856,\"sunset\":1690574170},\"timezone\":3600,\"id\":2643743,\"name\":\"London\",\"cod\":200}";

        WeatherDataDto expectedWeatherData = new WeatherDataDto();
        expectedWeatherData.setWeather("Clouds");
        expectedWeatherData.setTemperature(17.52); // 290.52 - 273.15 ≈ 17.37
        expectedWeatherData.setWindSpeed(2.07); // 6.2 * 0.44704 ≈ 2.77
        expectedWeatherData.setVisibility(10000.0);

        WeatherDataDto actualWeatherData = JsonParser.jsonToWeatherDto(inputJson);

        assertEquals(expectedWeatherData.getWeather(), actualWeatherData.getWeather());
        assertEquals(expectedWeatherData.getTemperature(), actualWeatherData.getTemperature(), 0.01); //+-0.01
        assertEquals(expectedWeatherData.getWindSpeed(), actualWeatherData.getWindSpeed(), 0.01); //+-0.01
        assertEquals(expectedWeatherData.getVisibility(), actualWeatherData.getVisibility());
    }

    @Test
    public void testInvalidJson() {
        String invalidJson = "{\"coord\":{\"lon:-0.125ather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":290.67,\"feels_like\":290.7,\"temp_min\":289.69,\"temp_max\":291.63,\"pressure\":1005,\"humidity\":85},\"visibility\":10000,\"wind\":{\"speed\":4.63,\"deg\":250},\"clouds\":{\"all\":100},\"dt\":1690527843,\"sys\":{\"type\":2,\"id\":2075535,\"country\":\"GB\",\"sunrise\":1690517856,\"sunset\":1690574170},\"timezone\":3600,\"id\":2643743,\"name\":\"London\",\"cod\":200}";
        assertThrows(ResourceNotFoundException.class, () -> {
                    JsonParser.jsonToWeatherDto(invalidJson);
                }
        );    }

    @Test
    public void testNullJson() {
        assertThrows(ResourceNotFoundException.class, () -> {
                    JsonParser.jsonToWeatherDto(null);
                }
        );
    }
}
