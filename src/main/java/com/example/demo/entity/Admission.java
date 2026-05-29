package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Admission")
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String fname;
    private String mname;
    private String dob;
    private long aadhar;
    private String category;

    private long t10roll;
    private long t10mark;
    private double t10per;

    private long t12roll;
    private long t12mark;
    private double t12per;

    private String village;
    private String district;
    private String state;
    private long pincode;
    private String country;

    private String choosecourse;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] document;

    private String documentName;

    // GETTERS & SETTERS

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFname() { return fname; }
    public void setFname(String fname) { this.fname = fname; }

    public String getMname() { return mname; }
    public void setMname(String mname) { this.mname = mname; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public long getAadhar() { return aadhar; }
    public void setAadhar(long aadhar) { this.aadhar = aadhar; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public long getT10roll() { return t10roll; }
    public void setT10roll(long t10roll) { this.t10roll = t10roll; }

    public long getT10mark() { return t10mark; }
    public void setT10mark(long t10mark) { this.t10mark = t10mark; }

    public double getT10per() { return t10per; }
    public void setT10per(double t10per) { this.t10per = t10per; }

    public long getT12roll() { return t12roll; }
    public void setT12roll(long t12roll) { this.t12roll = t12roll; }

    public long getT12mark() { return t12mark; }
    public void setT12mark(long t12mark) { this.t12mark = t12mark; }

    public double getT12per() { return t12per; }
    public void setT12per(double t12per) { this.t12per = t12per; }

    public String getVillage() { return village; }
    public void setVillage(String village) { this.village = village; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public long getPincode() { return pincode; }
    public void setPincode(long pincode) { this.pincode = pincode; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getChoosecourse() { return choosecourse; }
    public void setChoosecourse(String choosecourse) { this.choosecourse = choosecourse; }

    public byte[] getPhoto() { return photo; }
    public void setPhoto(byte[] photo) { this.photo = photo; }

    public byte[] getDocument() { return document; }
    public void setDocument(byte[] document) { this.document = document; }

    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }
}