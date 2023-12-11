package com.lld.MessageQueue;

import com.lld.MessageQueue.handlers.TopicHandler;
import com.lld.MessageQueue.helpers.ISubscriber;
import com.lld.MessageQueue.models.Message;
import com.lld.MessageQueue.models.Topic;
import com.lld.MessageQueue.models.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Queue {
    private final Map<String, TopicHandler> topicHandlers;

    public Queue() {
        this.topicHandlers = new HashMap<>();
    }

    public Topic createTopic(String topicName) {
        final var topic = new Topic(UUID.randomUUID().toString(), topicName);
        var topicHandler = new TopicHandler(topic);
        topicHandlers.put(topic.getId(), topicHandler);
        System.out.println("Created topic: " + topic.getName());
        return topic;
    }

    public void subscribe(ISubscriber subscriber, Topic topic) {
        topic.addSubscriber(new TopicSubscriber(subscriber));
        System.out.println(subscriber.getId() + " subscribed to topic: " + topic.getName());
    }

    public void publish(Topic topic, Message message) {
        topic.addMessage(message);
        System.out.println(message.getMessage() + " published to topic: " + topic.getName());
        new Thread(() -> topicHandlers.get(topic.getId()).publish()).start();
    }

    public void resetOffSet(Topic topic, ISubscriber subscriber, Integer newOffset) {
        for (TopicSubscriber topicSubscriber : topic.getSubscribers()) {
            if (topicSubscriber.getSubscriber().equals(subscriber)) {
                topicSubscriber.getOffset().set(newOffset);
                System.out.println(topicSubscriber.getSubscriber().getId() + " offset reset to: " + newOffset);
                new Thread(() -> topicHandlers.get(topic.getId()).startSubscriberWorker(topicSubscriber)).start();
                break;
            }
        }
    }

}
