package com.example.OPenAI;

import com.google.cloud.vertexai.v1.EndpointServiceClient;
import com.google.cloud.vertexai.v1.EndpointServiceSettings;
import com.google.cloud.vertexai.v1.GenerateContentRequest;
import com.google.cloud.vertexai.v1.GenerateContentResponse;

public class GeminiProClient {

    private final EndpointServiceClient client;

    public GeminiProClient(String projectId, String location) throws Exception {
        EndpointServiceSettings settings =
                EndpointServiceSettings.newBuilder()
                        .setEndpoint("us-central1-aiplatform.googleapis.com:443")
                        .setCredentialsProvider(() -> {
                            /* Set up your Google Cloud credentials here, e.g., using Application Default Credentials */
                        })
                        .build();
        this.client = EndpointServiceClient.create(settings);
    }

    public String generateText(String prompt, String modelName) {
        GenerateContentRequest request =
                GenerateContentRequest.newBuilder()
                        .setEndpoint("projects/" + projectId + "/locations/" + location + "/endpoints/" + modelName)
                        .setContent(ContentMaker.fromText(prompt))
                        .build();
        GenerateContentResponse response = client.generateContent(request);
        return ResponseHandler.getText(response);
    }

    public String generateTextWithImage(String prompt, String modelName, byte[] imageBytes) {
        GenerateContentRequest request =
                GenerateContentRequest.newBuilder()
                        .setEndpoint("projects/" + projectId + "/locations/" + location + "/endpoints/" + modelName)
                        .setContent(
                                ContentMaker.fromMultiModalData(
                                        prompt, PartMaker.fromMimeTypeAndData("image/jpeg", imageBytes)))
                        .build();
        GenerateContentResponse response = client.generateContent(request);
        return ResponseHandler.getText(response);
    }
}