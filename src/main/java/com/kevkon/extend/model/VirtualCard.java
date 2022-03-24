package com.kevkon.extend.model;

public class VirtualCard {

    private String id;
    private String status;
    private String displayName;
    private String expires;
    private String currency;
    private String limitCents;
    private String balanceCents;
    private String last4;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLimitCents() {
        return limitCents;
    }

    public void setLimitCents(String limitCents) {
        this.limitCents = limitCents;
    }

    public String getBalanceCents() {
        return balanceCents;
    }

    public void setBalanceCents(String balanceCents) {
        this.balanceCents = balanceCents;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }
}
