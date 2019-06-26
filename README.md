# TelegramBot Github Notifier
Based on Nicola Ferraro's [quickstart repo](https://github.com/nicolaferraro/telegram-quickstart). This project provides a simple telegram bot integration for Github Notifications.

The premise is simple; you (the user) should create a [telegram bot](https://core.telegram.org/bots). During the bot creation process take note of the HTTP API access token (Lets call it TELEGRAM_AUTH_TOKEN). Then head over to your Github account and generate a [personal access token](https://help.github.com/en/articles/creating-a-personal-access-token-for-the-command-line) (Lets call this one GITHUB_AUTH_TOKEN). Grant it at least the notifications & repo privileges. Finally, start a chat with your telegram bot and get that chat id (Unfortunately this requires a bit of creativity and Googling from your part). Lets assume you did it and now we call that final parameter TELEGRAM_CHAT_ID.

As for how to run it, refer to the Heroku's Procfile.