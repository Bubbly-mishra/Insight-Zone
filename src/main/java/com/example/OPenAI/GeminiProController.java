package com.example.OPenAI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class GeminiProController {

    private final GeminiProClient geminiProClient;

    public GeminiProController(GeminiProClient geminiProClient) {
        this.geminiProClient = geminiProClient;
    }

    @GetMapping("/generate-text")
    public String generateText(@RequestParam String prompt) {
        return geminiProClient.generateText(prompt, "gemini-pro-text-2");
    }

    @PostMapping("/generate-text-with-image")
    public String generateTextWithImage(@RequestParam String prompt, @RequestParam MultipartFile image)
            throws IOException {
        byte[] imageBytes = image.getBytes();
        return geminiProClient.generateTextWithImage(prompt, "gemini-pro-vision", imageBytes);
    }
}