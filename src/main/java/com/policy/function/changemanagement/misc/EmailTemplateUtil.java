package com.policy.function.changemanagement.misc;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class EmailTemplateUtil {
    private static final String TEMPLATE_PATH = "src/main/resources/email-templates.json";
    private static final Map<String, Map<String, String>> templates;

    static {
        try {
            String json = new String(Files.readAllBytes(Paths.get(TEMPLATE_PATH)));
            ObjectMapper objectMapper = new ObjectMapper();
            templates = objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load email templates", e);
        }
    }

    public static String getSubject(String type, Map<String, String> params) {
        return replacePlaceholders(templates.get(type).get("subject"), params);
    }

    public static String getBody(String type, Map<String, String> params) {
        return replacePlaceholders(templates.get(type).get("body"), params);
    }

    private static String replacePlaceholders(String template, Map<String, String> params) {
        String result = template;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            result = result.replace("{" + entry.getKey() + "}", entry.getValue());
        }
        return result;
    }
}
