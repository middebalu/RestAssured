package main.java.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown=true)
public class Data {
    private String test;
    private String contractName;
    private String party1Name;
    private String expirationDate;
    @JsonProperty("alertReceiverEmails")
    private String alertReceiverEmails;
    private int noOfPriorDays;
    @JsonProperty("featureResults")
    private String featureResults;

    public String getContractId() {
        return test;
    }

    public void setContractId(String contractId) {
        this.test = test;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getParty1Name() {
        return party1Name;
    }

    public void setParty1Name(String party1Name) {
        this.party1Name = party1Name;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    //@JsonProperty("alertReceiverEmails")
    public String getAAlertReceiverEmails() {
        return alertReceiverEmails;
    }
    //@JsonProperty("alertReceiverEmails")
    public void setAAlertReceiverEmails(String aalertReceiverEmails) {
        this.alertReceiverEmails = aalertReceiverEmails;
    }

    public int getNoOfPriorDays() {
        return noOfPriorDays;
    }

    public void setNoOfPriorDays(int noOfPriorDays) {
        this.noOfPriorDays = noOfPriorDays;
    }

   /* public String getFeatureResults() {
        return featureResults;
    }
    @JsonIgnore()
    public void setFeatureResults(String featureResults) {
        this.featureResults = featureResults;
    }*/
}
