package com.lld.MessageQueue.handlers;

import com.lld.MessageQueue.models.Topic;
import com.lld.MessageQueue.models.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {
    private final Topic topic;
    private final Map<String, SubscriberWorker> subscriberWorkers;

    public TopicHandler(Topic topic) {
        this.topic = topic;
        this.subscriberWorkers = new HashMap<>();
    }

    public void publish() {
        for (var subscriber: topic.getSubscribers()) {
            startSubscriberWorker(subscriber);
        }
    }

    public void startSubscriberWorker(TopicSubscriber topicSubscriber) {
        final var subscriberId = topicSubscriber.getSubscriber().getId();
        if (!subscriberWorkers.containsKey(subscriberId)) {
            var subscriberWorker = new SubscriberWorker(topic, topicSubscriber);
            subscriberWorkers.put(subscriberId, subscriberWorker);
            new Thread(subscriberWorker).start();
        }

        var subscriberWorker = subscriberWorkers.get(subscriberId);
        subscriberWorker.wakeupIfNeeded();
    }
}
