/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Function;

import java.util.Date;

/**
 *
 * @author Ramazan Cinardere & Ali Hannoun
 */
public class Registry {
    
    
    /**
     * Class Attributes
     */
    
    private String username,email,prename,surname,address,password,password2,birthday,day,month,year,city,zipcode,bic,iban;
    private String pattern = "^[_A-Za-z0-9-](?=.*[!@#$%]).{7,50}";
    private String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String dayreg = "(0?[1-9]|[12][0-9]|3[01])";
    private String monthreg = "(0?[1-9]|1[012])";
    private String yearreg = "((19|20)\\d\\d)";
    private String ibanreg = "[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}";
    private String bicreg = "([a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}([a-zA-Z0-9]{3})?)";
    private double d,m,y;
    private Date birth = new Date();

    
    /**
     * Class Methods
     */
    
    public Registry(String username, String email, String prename, 
                    String surname, String password, String password2, 
                    String birthday, String day, String month, String year, 
                    String city, String zipcode, String bic, String iban) {
    
    this.username = username;
    this.email = email;
    this.prename = prename;
    this.surname = surname;
    this.password = password;
    this.password2 = password2;
    this.birthday = birthday;
    this.day = day;
    this.month = month;
    this.year = year;
    this.city = city;
    this.zipcode = zipcode;
    this.bic = bic;
    this.iban = iban;
    
        //jTextUsername.setToolTipText("beispiel@gmx.de");
    
    }//closing Registry

    
    /**
     * Get-/Setter for Class Attributes
     */
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
    
    
    
    
    
    
}
