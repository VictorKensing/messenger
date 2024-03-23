package org.koushik.javabrains.service;

import org.koushik.javabrains.database.DatabaseClass;
import org.koushik.javabrains.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private final Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService() {
        profiles.put("koushik", new Profile(1L, "koushik", "Koushik", "Kothagal"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.values().stream().map(Profile::getId).reduce(Long::max).orElse(0L) + 1L);
        profiles.put(profile.getProfileName(), profile);

        return profiles.get(profile.getProfileName());
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }

        profiles.put(profile.getProfileName(), profile);

        return profiles.get(profile.getProfileName());
    }

    public Profile removeProfile(String profileName) {
        return profiles.remove(profileName);
    }
}
