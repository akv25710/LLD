package com.lld.MessageQueue;

import com.lld.MessageQueue.helpers.SleepingSubscriber;
import com.lld.MessageQueue.models.Message;
import com.lld.MessageQueue.models.Topic;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Queue queue = new Queue();
        Topic topic1 = queue.createTopic("topic1");
        Topic topic2 = queue.createTopic("topic2");

        SleepingSubscriber s1 = new SleepingSubscriber("s1", 10000);
        SleepingSubscriber s2 = new SleepingSubscriber("s2", 10000);
        queue.subscribe(s1, topic1);
        queue.subscribe(s2, topic1);

        SleepingSubscriber s3 = new SleepingSubscriber("s3", 5000);
        queue.subscribe(s3, topic2);

        queue.publish(topic1, new Message("m1"));
        queue.publish(topic1, new Message("m2"));
        queue.publish(topic2, new Message("m3"));

        Thread.sleep(15000);

        queue.publish(topic2, new Message("m4"));
        queue.publish(topic1, new Message("m5"));

        queue.resetOffSet(topic1, s1, 0);
    }
}
