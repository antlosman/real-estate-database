package model;

public class PropertyBrokerOwner {
    private int propertyId;
    private String propertyAddress;
    private String brokerName;
    private String ownerName;

    @Override
    public String toString() {
        return "PropertyBrokerOwner{" +
                "propertyId=" + propertyId +
                ", propertyAddress='" + propertyAddress + '\'' +
                ", brokerName='" + brokerName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }

    public PropertyBrokerOwner(int propertyId, String propertyAddress, String brokerName, String ownerName) {
        this.propertyId = propertyId;
        this.propertyAddress = propertyAddress;
        this.brokerName = brokerName;
        this.ownerName = ownerName;


    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getAddress() {
        return propertyAddress;
    }

    public void setAddress(String address) {
        this.propertyAddress = address;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
