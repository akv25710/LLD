package com.lld.MessageQueue.handlers;

import com.lld.MessageQueue.models.Message;
import com.lld.MessageQueue.models.Topic;
import com.lld.MessageQueue.models.TopicSubscriber;

public class SubscriberWorker implements Runnable {
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscriberWorker(Topic topic, TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @Override
    public void run() {
        synchronized (topicSubscriber) {
            do {
                int cutOffSet = topicSubscriber.getOffset().get();
                while (cutOffSet >= topic.getMessages().size()) {
                    try {
                        topicSubscriber.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message message = topic.getMessages().get(cutOffSet);
                try {
                    topicSubscriber.getSubscriber().consume(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                topicSubscriber.getOffset().compareAndSet(cutOffSet, cutOffSet+1);
            } while (true);
        }
    }

    synchronized public void wakeupIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
