package com.example;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
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

        /*
         * A Telegram testing route that echos back with a prefix.
         */
        from("telegram:bots/{{TELEGRAM_AUTH_TOKEN}}?delay={{poll.time.telegram}}")
                .bean(bot,"echoBack")
                .to("telegram:bots/{{TELEGRAM_AUTH_TOKEN}}");

        /*
         * Fetches notifications from a predetermined Github account & pings the associated telegram chat if any new
         * unread
         */
        from("timer://fetch_github_notifications?fixedRate=true&period={{poll.time.github}}")
                .setHeader("Authorization", constant("token {{GITHUB_AUTH_TOKEN}}"))
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("https4://api.github.com/notifications")
                .unmarshal().json(JsonLibrary.Jackson, GithubNotification[].class)
                .bean(bot,"processGithubNotifications")
                .choice()
                    .when(simple("${body}"))
                        .multicast().to("seda:sendToTelegram", "seda:markGithubNotificationsAsRead")
                        .endChoice()
                    .otherwise()
                        .log(LoggingLevel.DEBUG,"No new notifications");

        /*
         * Set all Github notifications as Read
         */
        from("seda:markGithubNotificationsAsRead")
            .setHeader("Authorization", constant("token {{GITHUB_AUTH_TOKEN}}"))
            .setHeader(Exchange.HTTP_METHOD, constant("PUT"))
            .to("https4://api.github.com/notifications")
            .process(exchange -> exchange.getIn().setBody("Github Notifications Reset"))
            .to("seda:sendToTelegram");

        /*
         * Sends whatever the body is to a predetermined telegram chat.
         */
        from("seda:sendToTelegram")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getIn().setBody("Testing");
                    }
                })
                .removeHeaders("*")
                .setHeader("CamelTelegramChatId",constant("{{TELEGRAM_CHAT_ID}}"))
                .to("telegram:bots/{{TELEGRAM_AUTH_TOKEN}}");

        /*
         * Dummy route to trick Heroku into keeping the app up.
         */
        from("jetty:http://0.0.0.0:{{port}}?matchOnUriPrefix=true")
                .log(LoggingLevel.INFO,"JETTY MESSAGE");
    }
}
