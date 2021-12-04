package model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Broker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brokerId")
    private int brokerId;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    public Broker() {

    }

    public Broker(int brokerId, String name, String phone, String email) {
        this.brokerId = brokerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Broker{" +
                "brokerId=" + brokerId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(int brokerId) {
        this.brokerId = brokerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
