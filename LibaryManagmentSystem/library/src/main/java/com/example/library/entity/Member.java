package com.example.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="members")
public class Member {

@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

private String name;
private String email;
private String phNo;



public Member(int id, String name, String email, String phNo) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phNo = phNo;
}



public Member() {
}



public Member(String name, String email, String phNo) {
    this.name = name;
    this.email = email;
    this.phNo = phNo;
}



public int getId() {
    return id;
}



public void setId(int id) {
    this.id = id;
}



public String getName() {
    return name;
}



public void setName(String name) {
    this.name = name;
}



public String getEmail() {
    return email;
}



public void setEmail(String email) {
    this.email = email;
}



public String getPhNo() {
    return phNo;
}



public void setPhNo(String phNo) {
    this.phNo = phNo;
}

    

private boolean deleted = false;

public boolean isDeleted() {
    return deleted;
}

public void setDeleted(boolean deleted) {
    this.deleted = deleted;
}

}
