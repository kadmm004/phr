package com.phr.entity;

public class Hospital {
    private int hosImage;
    private String hosName;
    private String hosClass;
    private String hosType;
    private String hosDept;
    private String hosComment;
    //ListView用不到的
    private String hosId;
    private String hosAddr;
    private String hosIntro;

    public int getHosImage(){
        return hosImage;
    }
    public void setHosImage(int hosImage){
        this.hosImage=hosImage;
    }

    public String getHosName(){
        return hosName;
    }
    public void setHosName(String hosName){
        this.hosName=hosName;
    }

    public String getHosClass() {
        return hosClass;
    }
    public void setHosClass(String hosClass)
    {
        this.hosClass=hosClass;
    }


    public String getHosType()
    {
        return hosType;
    }
    public void setHosType(String hosType)
    {
        this.hosType=hosType;
    }

    public String getHosDept()
    {
        return hosDept;
    }
    public void setHosDept(String hosDept)
    {
        this.hosDept=hosDept;
    }

    public String getHosComment(){return hosComment;}
    public void setHosComment(String hosComment){ this.hosComment=hosComment; }

    public String getHosAddr() { return hosAddr; }
    public void setHosAddr(String hosAddr) {
        this.hosAddr = hosAddr;
    }

    public String getHosId() {
        return hosId;
    }
    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public String getHosIntro() {
        return hosIntro;
    }
    public void setHosIntro(String hosIntro) {
        this.hosIntro = hosIntro;
    }

    public Hospital(String hosId,String hosName,String hosAddr,
                    String hosDept,String hosIntro,String hosClass,
                    String hosType,String hosComment){
        this.hosId=hosId;
        this.hosName=hosName;
        this.hosAddr=hosAddr;
        this.hosDept=hosDept;
        this.hosIntro=hosIntro;
        this.hosClass=hosClass;
        this.hosType=hosType;
        this.hosComment=hosComment;
    }

    public Hospital(String hosName, String hosDept,String hosClass,
                    String hosType,String hosComment){
        this.hosName=hosName;
        this.hosDept=hosDept;
        this.hosClass=hosClass;
        this.hosType=hosType;
        this.hosComment=hosComment;
    }

    public Hospital(){

    }
}
