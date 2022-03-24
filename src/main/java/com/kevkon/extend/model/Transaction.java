package com.kevkon.extend.model;

public class Transaction {

    private String id;
    private String cardholderId;
    private String recipientId;
    private String virtualCardId;
    private String type;
    private String status;
    private String authBillingCurrency;
    private String authBillingAmountCents;
    private String authedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardholderId() {
        return cardholderId;
    }

    public void setCardholderId(String cardholderId) {
        this.cardholderId = cardholderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getVirtualCardId() {
        return virtualCardId;
    }

    public void setVirtualCardId(String virtualCardId) {
        this.virtualCardId = virtualCardId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthBillingCurrency() {
        return authBillingCurrency;
    }

    public void setAuthBillingCurrency(String authBillingCurrency) {
        this.authBillingCurrency = authBillingCurrency;
    }

    public String getAuthBillingAmountCents() {
        return authBillingAmountCents;
    }

    public void setAuthBillingAmountCents(String authBillingAmountCents) {
        this.authBillingAmountCents = authBillingAmountCents;
    }

    public String getAuthedAt() {
        return authedAt;
    }

    public void setAuthedAt(String authedAt) {
        this.authedAt = authedAt;
    }
}
