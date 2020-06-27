package io.project.coronavirustracker.tracker.model;

public class Resultant {
    private String country;
    private int countryDeath;
    private int countryRecovery;
    private int countryConfirm;
    private String state;
    private int changes;

    public int getChanges() {
        return changes;
    }

    public void setChanges(int changes) {
        this.changes = changes;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCountryDeath() {
        return countryDeath;
    }

    public void setCountryDeath(int countryDeath) {
        this.countryDeath = countryDeath;
    }

    public int getCountryRecovery() {
        return countryRecovery;
    }

    public void setCountryRecovery(int countryRecovery) {
        this.countryRecovery = countryRecovery;
    }

    public int getCountryConfirm() {
        return countryConfirm;
    }

    public void setCountryConfirm(int countryConfirm) {
        this.countryConfirm = countryConfirm;
    }
}
