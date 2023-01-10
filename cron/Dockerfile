# Pulling Ubuntu image
FROM node:19-alpine

# Updating packages and installing cron
# RUN apk add cron

# Copying script file into the container.
COPY get_due_messages.sh /etc/cron/get_due_messages.sh

# Giving executable permission to the script file.
RUN chmod +x /etc/cron/get_due_messages.sh

# Adding crontab to the appropiate location
ADD due_messages_cron /etc/cron.d/due_messages_cron

# Giving executable permission to crontab file
RUN chmod 0644 /etc/cron.d/due_messages_cron

# Running crontab
RUN crontab /etc/cron.d/due_messages_cron

# Creating entry point for cron 
ENTRYPOINT ["crond", "-S", "-f", "-l", "4"]
