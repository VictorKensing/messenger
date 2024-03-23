package org.koushik.javabrains.database;

import org.koushik.javabrains.model.Message;
import org.koushik.javabrains.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {

    private static final Map<Long, Message> messages = new HashMap<>();
    private static final Map<String, Profile> profiles = new HashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }
}
