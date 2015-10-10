/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.dao;

import java.io.Serializable;
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
@Table(name="cm_email_communication_contacts")
public class CampClients implements Serializable {
    @Id 
    @GeneratedValue
    @Column(name = "id")
    private long id;
    private String response_track_cd;
    private String customer_rk;
    private String email;
    private String var1;
    private String var2;
    private String var3;
    private String var4;
    private String var5;
    private String var6;

    public CampClients() {
    }

    public CampClients(long id, String response_track_cd, String customer_rk, String email, String var1, String var2, String var3, String var4, String var5, String var6) {
        this.id = id;
        this.response_track_cd = response_track_cd;
        this.customer_rk = customer_rk;
        this.email = email;
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
        this.var5 = var5;
        this.var6 = var6;
    }  
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public String getVar3() {
        return var3;
    }

    public void setVar3(String var3) {
        this.var3 = var3;
    }

    public String getVar4() {
        return var4;
    }

    public void setVar4(String var4) {
        this.var4 = var4;
    }

    public String getVar5() {
        return var5;
    }

    public void setVar5(String var5) {
        this.var5 = var5;
    }

    public String getVar6() {
        return var6;
    }

    public void setVar6(String var6) {
        this.var6 = var6;
    }

    @Override
    public String toString() {
        return "CampClients{" + "id=" + id + ", response_track_cd=" + response_track_cd + ", customer_rk=" + customer_rk + ", email=" + email + ", var1=" + var1 + ", var2=" + var2 + ", var3=" + var3 + ", var4=" + var4 + ", var5=" + var5 + ", var6=" + var6 + '}';
    }
}
