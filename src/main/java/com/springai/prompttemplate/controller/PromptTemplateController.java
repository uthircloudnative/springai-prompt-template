package com.springai.prompttemplate.controller;

import com.springai.prompttemplate.service.PromptTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * PromptTemplateController
 *
 * @author Uthiraraj Saminathan
 */
@RestController
@RequestMapping(value = "/v1/prompttemplate")
public class PromptTemplateController {

    private static final Logger logger = LoggerFactory.getLogger(PromptTemplateController.class);

    private final PromptTemplateService promptTemplateService;

    public PromptTemplateController(PromptTemplateService promptTemplateService) {
        this.promptTemplateService = promptTemplateService;
    }

    @GetMapping("/call")
    public Map<String, String> chatLlm(@RequestParam(value = "userPrompt") String userPrompt,
                                       @RequestParam(value = "language") String language) {
        return promptTemplateService.callLLM(userPrompt,language);
    }

}
