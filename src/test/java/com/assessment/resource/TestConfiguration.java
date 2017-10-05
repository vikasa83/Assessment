package com.assessment.resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.assesment.AssessmentConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;

import io.dropwizard.jackson.Jackson;

public class TestConfiguration extends AssessmentConfiguration {

	private Map<String, Object> configuration = null;

	@SuppressWarnings("unchecked")
	public TestConfiguration() {
		Yaml yamlConfiguration = new Yaml();
		try {
			String filename = "local.yml";
			configuration = (Map<String, Object>) yamlConfiguration
					.load(new String(Files.readAllBytes(Paths.get(filename))));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static TestConfiguration load() {

		ObjectMapper objectMapper = Jackson.newObjectMapper(new YAMLFactory());

		try {
			String config = "local.yml";
			return objectMapper.readValue(new File(config),
					TestConfiguration.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
