package com.umut.videostream.model.exceptions;

public class SubscriptionTypeNotFound extends Exception {

    public SubscriptionTypeNotFound(String subscriptionType) {
        super("Subscription type " + subscriptionType + "not found");
    }

}
