package com.example.gymmanagmentsystem;

public class HelperClass {
    String Image,Name,Username,Email,PhoneNumber,Password;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public HelperClass(String Image,String name, String username, String email, String phoneNumber, String password) {
        Name = name;
        Email = email;
        Username = username;
        PhoneNumber = phoneNumber;
        Password = password;
        this.Image=Image;
    }

    public HelperClass() {
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
}
