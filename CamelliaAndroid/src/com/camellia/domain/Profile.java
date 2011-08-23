package com.camellia.domain;

public class Profile {
    private final ProfileSection homeSection;
    private final String         name;
    private final ProfileSection workSection;

    public Profile(String name) {
        this.name = name;
        homeSection = new ProfileSection();
        homeSection.add(new ContactPoint(ContactPointType.ADDRESS, "Somewhere Hilvill VIC 3999"));
        homeSection.add(new ContactPoint(ContactPointType.EMAIL, "james@james.com"));
        homeSection.add(new ContactPoint(ContactPointType.MOBILE, "040312345678"));
        homeSection.add(new ContactPoint(ContactPointType.PHONE, "(03) 1234 5678"));

        workSection = new ProfileSection();
        workSection.add(new ContactPoint(ContactPointType.ADDRESS, "Somewhere else Hilvill VIC 3999"));
        workSection.add(new ContactPoint(ContactPointType.EMAIL, "james@work.com"));
        workSection.add(new ContactPoint(ContactPointType.MOBILE, "040312312678"));
        workSection.add(new ContactPoint(ContactPointType.PHONE, "(03) 1231 3678"));

    }

    public ProfileSection getHomeSection() {
        return homeSection;
    }

    public ProfileSection getWorkSection() {
        return workSection;
    }
}