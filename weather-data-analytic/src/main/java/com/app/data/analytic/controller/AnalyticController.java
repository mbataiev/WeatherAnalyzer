package com.app.data.analytic.controller;

import com.app.data.analytic.service.DataAnalyticService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytic")
@Slf4j
@Tag(
        name = "Weather Analytic",
        description = "Analyse storage data"
)
public class AnalyticController {

    private DataAnalyticService service;

}
