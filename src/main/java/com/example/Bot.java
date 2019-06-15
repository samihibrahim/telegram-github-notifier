package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This class contains the chat-bot logic: use your fantasy to implement your own Bot.
 */
@Component
public class Bot {

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * This method echos incoming messages back as responses.
     *
     * @param message a message coming from a human user in a chat
     * @return the reply of the bot as a string or null for none.
     */
    public String echoBack(String message) {
        if (message == null) {
            return null; // skip non-text messages
        }

        log.info("Received message from bot: {}", message);

        return "Why did you say \"" + message.replace("\"", "-") + "\"?";
    }

    /**
     * This method processed Github notifications and returns a summary.
     *
     * @param githubNotifications an array of Github Notifications or emtpy if none
     * @return the reply of the bot as a string or null for no notifications.
     */
    public String processGithubNotifications(GithubNotification[] githubNotifications){
        if(githubNotifications.length > 0)
            return "You go a new Github Notification";
        else
            return null;
    }

}
