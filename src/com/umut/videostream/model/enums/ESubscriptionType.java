package com.umut.videostream.model.enums;

import com.umut.videostream.model.exceptions.SubscriptionTypeNotFound;

public enum ESubscriptionType {
    FREE("FREE"),
    MEDIUM("MEDIUM"),
    VIP("VIP");

    private final String value;

    ESubscriptionType(String value) {
        this.value = value;
    }

    // Only use in json deserialization
    // TODO user EnumMap
    public static ESubscriptionType createSubscriptionTypeFromString(String string) throws SubscriptionTypeNotFound {
        if (string.equals("FREE")) {
            return FREE;
        } else if (string.equals("MEDIUM")) {
            return MEDIUM;
        } else if (string.equals("VIP")) {
            return VIP;
        }

        throw new SubscriptionTypeNotFound(string);
    }

}
