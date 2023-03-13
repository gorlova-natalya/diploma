package com.teachmeskills.security.config;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templateresolver.FileTemplateResolver;

public enum ThymeLeafConfig {
    INSTANCE;
    private TemplateEngine templateEngine;

    private ThymeLeafConfig() {
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix(getTemplatePath());
        templateResolver.setTemplateMode("HTML");
        templateEngine = new TemplateEngine();
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.setTemplateResolver(templateResolver);
    }

    private String getTemplatePath() {
        return ThymeLeafConfig.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "templates/";
    }

    public static TemplateEngine getTemplateEngine() {
        return INSTANCE.templateEngine;
    }
}
