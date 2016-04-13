package com.example.ramjee.myapplication;

/**
 * Created by kcngan on 11/4/16.
 */
public class LoginRecord {
    private String userid;
    private String username;
    private String password;
    private String email;
    private double height;
    private double weight;
    private double BMI;
    private String BMIstatus;
    private String sex;
    private String dob;

    public LoginRecord(String userid, String username, String password, String email, double height, double weight, String sex, String dob) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.BMI = weight/(height*height);
        if ((weight/(height*height))<18.5){
            this.BMIstatus = "Underweight";
        }else if ((weight/(height*height))>18.5&&(weight/(height*height))<24.9){
            this.BMIstatus = "Normal";
        }else if ((weight/(height*height))>25.0&&(weight/(height*height))<29.9) {
            this.BMIstatus = "Overweight";
        }else if ((weight/(height*height))>=30.0) {
            this.BMIstatus = "Obese";
        }
        this.sex = sex;
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBMIstatus() {
        return BMIstatus;
    }

    public String getSex() {
        return sex;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public void setBMIstatus(String BMIstatus) {
        this.BMIstatus = BMIstatus;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


}
