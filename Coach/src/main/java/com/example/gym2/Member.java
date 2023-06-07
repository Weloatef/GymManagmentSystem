
package com.example.gym2;

public class Member {
    String Name, Coach, username;

    public Member() {
    }

    public Member(String name, String coach, String username) {
        Name = name;
        Coach = coach;
        this.username = username;

    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCoach() {
        return Coach;
    }

    public void setCoach(String coach) {
        Coach = coach;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
