package com.camellia.domain;

import java.util.ArrayList;
import java.util.List;

public class ProfileSection {
    private final List<ContactPoint> contactPoints;

    public ProfileSection() {
        contactPoints = new ArrayList<ContactPoint>();
    }

    public void add(ContactPoint contactPoint) {
        contactPoints.add(contactPoint);
    }

    public List<ContactPoint> getContactPoints() {
        return contactPoints;
    }
}