package com.rbac.entity;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "application", schema = "eis_auth")
@Data
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "application_name", nullable = false)
    private String applicationName;

    @Column(name = "access_control_model")
    private String accessControlModel;

	/*
	 * @Column(name = "policy") private JsonNode policy;
	 * 
	 * @Column(name = "others", columnDefinition = "jsonb")
	 * 
	 * @Convert(converter = JsonNodeConverter.class) private JsonNode others;
	 */

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "application")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "application_id")
    private List<Policy> policies;
 
    private String others;
    
    @Converter(autoApply = true)
    public static class JsonNodeConverter implements AttributeConverter<JsonNode, String> {
        private static final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String convertToDatabaseColumn(JsonNode attribute) {
            return attribute != null ? attribute.toString() : null;
        }

        @Override
        public JsonNode convertToEntityAttribute(String dbData) {
            try {
                return dbData != null ? objectMapper.readTree(dbData) : null;
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to convert JSON string to JsonNode", e);
            }
        }
    }
}