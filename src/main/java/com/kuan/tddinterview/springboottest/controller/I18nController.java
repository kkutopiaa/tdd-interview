package com.kuan.tddinterview.springboottest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@RestController
@RequestMapping(path = "/api/v1")
public class I18nController {


    @GetMapping("/messages")
    public String getMessage(@RequestParam(defaultValue = "default", required = false) String param,
                             HttpServletRequest request) {
        String lang = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        Locale locale = Objects.isNull(lang) ? Locale.CHINA : new Locale(lang);
        ResourceBundle messages = getMessages(locale);
        String prefix = messages.getString("prefix");
        String suffix = new MessageFormat(messages.getString("suffix")).format(new Object[]{param});
        return prefix + suffix;

    }


    private static ResourceBundle getMessages(Locale locale) {
        return ResourceBundle.getBundle("messages", locale);
    }

}
