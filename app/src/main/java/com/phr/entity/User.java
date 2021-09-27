package com.phr.entity;

public class User {
    private  int uid;
    private  String uidcard;
    private  String uname;
    private  String usex;
    private  String ubirth;
    private  int uage;
    private  String uaddr;
    private  String utele;
    private  String uloginname;
    private  String upwd;
    private  String unation;
    public User(int uid, String uidcard, String uname, String usex, String ubirth, int uage, String uaddr, String utele,
                String uloginname, String upwd, String unation) {
        super();
        this.uid = uid;
        this.uidcard = uidcard;
        this.uname = uname;
        this.usex = usex;
        this.ubirth = ubirth;
        this.uage = uage;
        this.uaddr = uaddr;
        this.utele = utele;
        this.uloginname = uloginname;
        this.upwd = upwd;
        this.unation = unation;
    }

    public User(String uloginname, String upwd) {
        super();
        this.uloginname = uloginname;
        this.upwd = upwd;
    }

    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getUidcard() {
        return uidcard;
    }
    public void setUidcard(String uidcard) {
        this.uidcard = uidcard;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUsex() {
        return usex;
    }
    public void setUsex(String usex) {
        this.usex = usex;
    }
    public String getUbirth() {
        return ubirth;
    }
    public void setUbirth(String ubirth) {
        this.ubirth = ubirth;
    }
    public int getUage() {
        return uage;
    }
    public void setUage(int uage) {
        this.uage = uage;
    }
    public String getUaddr() {
        return uaddr;
    }
    public void setUaddr(String uaddr) {
        this.uaddr = uaddr;
    }
    public String getUtele() {
        return utele;
    }
    public void setUtele(String utele) {
        this.utele = utele;
    }
    public String getUloginname() {
        return uloginname;
    }
    public void setUloginname(String uloginname) {
        this.uloginname = uloginname;
    }
    public String getUpwd() {
        return upwd;
    }
    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }
    public String getUnation() {
        return unation;
    }
    public void setUnation(String unation) {
        this.unation = unation;
    }
}
