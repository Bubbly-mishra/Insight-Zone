package com.example.OPenAI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiProConfiguration {

    @Bean
    public GeminiProClient geminiProClient(
            @Value("${project.id}") String projectId, @Value("${gemini.location}") String location)
            throws Exception {
        return new GeminiProClient(projectId, location);
    }
}