package com.example.demo.Controller;

import com.example.demo.OpenApiManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;



@RestController
@RequiredArgsConstructor
@Slf4j
public class Controller {
    private final OpenApiManager openApiManager;

    @GetMapping("open-api")
    public void fetch() throws IOException, ParseException {
        openApiManager.fetch();
    }

}
