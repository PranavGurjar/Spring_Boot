package com.orm.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

//import javax.persistence.*;

@Entity
@Table(name = "jpa_student")
public class Student {

    @Id
    private int studentId;
    private String studentName;
    private String about;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)   //cascade use for save student and laptop without creating laptopRepo
    private Laptop laptop;

    //many address:
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Address> addressList = new ArrayList<>();

    public Student() {
    }

    public Student(int studentId, String studentName, String about) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.about = about;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
