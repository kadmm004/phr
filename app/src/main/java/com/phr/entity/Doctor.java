package com.phr.entity;

public class Doctor {
    private int docImage;
    private String docName;
    private String docTitle;
    private String docScore;
    private String docNum;
    private String docMajor;

    public int getDocImage(){
        return docImage;
    }
    public void setDocImage(int docImage){
        this.docImage=docImage;
    }

    public String getDocName(){
        return docName;
    }
    public void setDocName(String DocName){
        this.docName=docName;
    }

    public String getDocTitle() {
        return docTitle;
    }
    public void setDocTitle(String docTitle)
    {
        this.docTitle = docTitle;
    }


    public String getDocScore()
    {
        return docScore;
    }
    public void setDocScore(String docScore)
    {
        this.docScore = docScore;
    }

    public String getDocNum()
    {
        return docNum;
    }
    public void setDocNum(String docNum)
    {
        this.docNum = docNum;
    }

    public String getDocMajor(){return docMajor;}
    public void setDocMajor(String docMajor){ this.docMajor = docMajor; }
    public Doctor() {

    }

    public Doctor(String docName,String docTitle,
                  String docScore,String docNum,String docMajor){
        this.docName=docName;
        this.docTitle=docTitle;
        this.docScore=docScore;
        this.docNum=docNum;
        this.docMajor=docMajor;
    }
}
