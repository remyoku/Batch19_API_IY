package com.eurotech.apiPOJOTempletes;

public class EuroTechNewUser {
   /** """
                {
                         "name": "Speedy Gonzales",
                         "email": "speedyIy@gonzaless.com",
                         "password": "1234.Asdf",
                         "about": "from USA",
                         "terms": "3"
                         }
                """;*/

   private String name;
   private  String email;
   private String password;
   private  String about ;
   private String terms;

    public EuroTechNewUser() {
    }

    public EuroTechNewUser(String name, String email, String password, String about, String terms) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.terms = terms;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAbout() {
        return about;
    }

    public String getTerms() {
        return terms;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    @Override
    public String toString() {
        return "EuroTechNewUser{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", about='" + about + '\'' +
                ", terms='" + terms + '\'' +
                '}';
    }
}
