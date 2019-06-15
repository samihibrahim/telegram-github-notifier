package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for routing the messages from and to the Telegram chat.
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Autowired
    private Bot bot;

    @Override
    public void configure() throws Exception {

        from("telegram:bots/{{TELEGRAM_AUTH_TOKEN}}")
                .bean(bot,"echoBack")
                .to("telegram:bots/{{TELEGRAM_AUTH_TOKEN}}");

        from("timer://fetch_github_notifications?fixedRate=true&period=10s")
                .setHeader("Authorization", constant("token {{GITHUB_AUTH_TOKEN}}"))
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("https4://api.github.com/notifications")
                .unmarshal().json(JsonLibrary.Jackson, GithubNotification[].class)
                .bean(bot,"processGithubNotifications")
                .removeHeaders("*")
                .setHeader("CamelTelegramChatId",constant("{{TELEGRAM_CHAT_ID}}"))
                .to("telegram:bots/{{TELEGRAM_AUTH_TOKEN}}");

        from("jetty:http://0.0.0.0:{{port}}?matchOnUriPrefix=true")
            .log(LoggingLevel.INFO,"JETTY MESSAGE");
    }
}
