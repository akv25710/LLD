package com.lld.MessageQueue.helpers;

import com.lld.MessageQueue.models.Message;

public interface ISubscriber {
    String getId();
    void consume(Message message) throws InterruptedException;
}
