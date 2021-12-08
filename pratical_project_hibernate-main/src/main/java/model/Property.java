package model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "propertyId")
    private int propertyId;

    @Column(name = "propertyType")
    private String propertyType;

    @Column(name = "address")
    private String address;

    @Column(name = "numberOfRooms")
    private int numberOfRooms;

    @Column(name = "floorNumber")
    private int floorNumber;

    @Column(name = "price")
    private int price;

    @Column(name = "dateOfRegister")
    private LocalDate dateOfRegister;

    @ManyToOne
    @JoinColumn(name = "brokerId")
    private Broker broker;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    private Owner owner;

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public LocalDate getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(LocalDate dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", propertyType='" + propertyType + '\'' +
                ", address='" + address + '\'' +
                ", numberOfRooms=" + numberOfRooms +
                ", floorNumber=" + floorNumber +
                ", price=" + price +
                ", dateOfRegister=" + dateOfRegister +
                ", broker=" + broker +
                ", owner=" + owner +
                '}';
    }
}
