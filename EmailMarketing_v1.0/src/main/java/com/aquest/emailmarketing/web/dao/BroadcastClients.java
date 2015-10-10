/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author bdimic
 */
@Entity
@Table(name="cm_email_broadcast_contact")
public class BroadcastClients implements Serializable {
    @Id 
    @GeneratedValue
    @Column(name = "id")
    private long id;
    private long broadcast_id;
    private String response_track_cd;
    private String customer_rk;
    private String email;
    private String subject;
    private String baseurl;
    private String htmlbody;
    private String plaintext;
    private String status;
    private Timestamp processed_dttm;

    public BroadcastClients() {
    }
    
    public BroadcastClients(long broadcast_id, String response_track_cd, String customer_rk, String email, String subject, String baseurl, String htmlbody, String plaintext, String status, Timestamp processed_dttm) {
        this.broadcast_id = broadcast_id;
        this.response_track_cd = response_track_cd;
        this.customer_rk = customer_rk;
        this.email = email;
        this.subject = subject;
        this.baseurl = baseurl;
        this.htmlbody = htmlbody;
        this.plaintext = plaintext;
        this.status = status;
        this.processed_dttm = processed_dttm;
    }

    public BroadcastClients(long id, long broadcast_id, String response_track_cd, String customer_rk, String email, String subject, String baseurl, String htmlbody, String plaintext, String status, Timestamp processed_dttm) {
        this.id = id;
        this.broadcast_id = broadcast_id;
        this.response_track_cd = response_track_cd;
        this.customer_rk = customer_rk;
        this.email = email;
        this.subject = subject;
        this.baseurl = baseurl;
        this.htmlbody = htmlbody;
        this.plaintext = plaintext;
        this.status = status;
        this.processed_dttm = processed_dttm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBroadcast_id() {
        return broadcast_id;
    }

    public void setBroadcast_id(long broadcast_id) {
        this.broadcast_id = broadcast_id;
    }

    public String getResponse_track_cd() {
        return response_track_cd;
    }

    public void setResponse_track_cd(String response_track_cd) {
        this.response_track_cd = response_track_cd;
    }

    public String getCustomer_rk() {
        return customer_rk;
    }

    public void setCustomer_rk(String customer_rk) {
        this.customer_rk = customer_rk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    public String getHtmlbody() {
        return htmlbody;
    }

    public void setHtmlbody(String htmlbody) {
        this.htmlbody = htmlbody;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getProcessed_dttm() {
        return processed_dttm;
    }

    public void setProcessed_dttm(Timestamp processed_dttm) {
        this.processed_dttm = processed_dttm;
    }

    @Override
    public String toString() {
        return "BroadcastClients{" + "id=" + id + ", broadcast_id=" + broadcast_id + ", response_track_cd=" + response_track_cd + ", customer_rk=" + customer_rk + ", email=" + email + ", subject=" + subject + ", baseurl=" + baseurl + ", htmlbody=" + htmlbody + ", plaintext=" + plaintext + ", status=" + status + ", processed_dttm=" + processed_dttm + '}';
    }
}
