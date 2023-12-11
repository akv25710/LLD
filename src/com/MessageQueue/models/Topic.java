package com.lld.MessageQueue.models;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private final String id;
    private final String name;
    private final List<Message> messages;
    private final List<TopicSubscriber> subscribers;

    public Topic(String id, String name) {
        this.id = id;
        this.name = name;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<TopicSubscriber> getSubscribers() {
        return subscribers;
    }

    public synchronized void addMessage(Message message) {
        this.messages.add(message);
    }

    public void addSubscriber(TopicSubscriber subscriber) {
        this.subscribers.add(subscriber);
    }
}
