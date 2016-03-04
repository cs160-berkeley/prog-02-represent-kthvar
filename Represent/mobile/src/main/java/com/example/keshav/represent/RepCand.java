package com.example.keshav.represent;

import java.io.Serializable;

/**
 * Created by Keshav on 3/1/16.
 */
public class RepCand implements Serializable {
    private int rep_resource;
    private int rep_twot;
    private String rep_name;
    private String rep_party;
    private String rep_email;
    private String rep_web;
    private String rep_tweet;
    private String rep_termend;
    private String rep_committees;
    private String rep_bills;

    public RepCand(int rep_resource, int rep_twot, String rep_name, String rep_party, String rep_email, String rep_web, String rep_tweet,
                   String rep_termend, String rep_committees, String rep_bills){
        super();
        this.setRep_resource(rep_resource);
        this.setTwot(rep_twot);
        this.setRep_name(rep_name);
        this.setRep_party(rep_party);
        this.setRep_email(rep_email);
        this.setRep_web(rep_web);
        this.setRep_tweet(rep_tweet);
        this.setRep_termend(rep_termend);
        this.setRep_committees(rep_committees);
        this.setRep_bills(rep_bills);
    }

    public int getRep_resource() {
        return rep_resource;
    }

    public void setRep_resource(int rep_resource) {
        this.rep_resource = rep_resource;
    }

    public String getRep_name() {
        return rep_name;
    }

    public void setRep_name(String rep_name) {
        this.rep_name = rep_name;
    }

    public String getRep_party() {
        return rep_party;
    }

    public void setRep_party(String rep_party) {
        this.rep_party = rep_party;
    }

    public String getRep_email() {
        return rep_email;
    }

    public void setRep_email(String rep_email) {
        this.rep_email = rep_email;
    }

    public String getRep_web() {
        return rep_web;
    }

    public void setRep_web(String rep_web) {
        this.rep_web = rep_web;
    }

    public String getRep_tweet() {
        return rep_tweet;
    }

    public void setRep_tweet(String rep_tweet) {
        this.rep_tweet = rep_tweet;
    }

    public int getTwot() {
        return rep_twot;
    }

    public void setTwot(int rep_twot) {
        this.rep_twot = rep_twot;
    }

    public String getRep_termend() {
        return rep_termend;
    }

    public void setRep_termend(String rep_termend) {
        this.rep_termend = rep_termend;
    }

    public String getRep_committees() {
        return rep_committees;
    }

    public void setRep_committees(String rep_committees) {
        this.rep_committees = rep_committees;
    }

    public String getRep_bills() {
        return rep_bills;
    }

    public void setRep_bills(String rep_bills) {
        this.rep_bills = rep_bills;
    }
}
