package com.endava.config;

import com.yammer.dropwizard.config.Configuration;

import javax.validation.Valid;

public class ServiceConfiguration extends Configuration {

    @Valid
    private MessagesConfiguration messages;

    public MessagesConfiguration getMessages() {
        return messages;
    }

    public void setMessages(MessagesConfiguration messages) {
        this.messages = messages;
    }
}