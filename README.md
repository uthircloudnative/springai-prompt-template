# SpringAI Prompt Template

## Overview
This project is a Spring Boot application that provides a REST API for interacting with Large Language Models (LLMs) using prompt templates. It leverages Spring AI to deliver programming assistance restricted to a specified language.

## Features
- RESTful API endpoint for LLM interaction
- Dynamic system prompt template enforcing language-specific responses
- Integration with Spring AI and ChatModel (e.g., GPT-4o-mini)

## Architecture
- **SpringaiPromptTemplateApplication**: Main entry point for the Spring Boot application.
- **PromptTemplateController**: Exposes `/v1/prompttemplate/call` endpoint. Accepts `userPrompt` and `language` as query parameters.
- **PromptTemplateService**: Handles LLM interaction. Constructs a system prompt template to ensure responses are limited to the specified programming language.

## API Usage
### Endpoint
`GET /v1/prompttemplate/call?userPrompt=...&language=...`

#### Parameters
- `userPrompt` (string): The user's question or prompt.
- `language` (string): The programming language for context (e.g., `Java`, `Python`).

#### Response
Returns a map containing the LLM's response, strictly related to the specified language.

## Setup Instructions
1. **Prerequisites**
   - Java 24+
   - Maven
2. **Build and Run**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
3. **Configuration**
   - Edit `src/main/resources/application.properties` for custom settings.

## Extending
- To support additional LLM models, update the `ChatOptions` in `PromptTemplateService`.
- To add more endpoints, extend `PromptTemplateController`.



