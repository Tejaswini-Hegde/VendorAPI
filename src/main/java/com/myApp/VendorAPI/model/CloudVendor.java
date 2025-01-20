package com.myApp.VendorAPI.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


@Entity
@Table (name = "cloudvendor")
public class CloudVendor {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "vendorid")
    private int vendorId;

    //If the values are being considered as null in Postman/client side, use @Column and @JsonProperty
    //Also check Jackson / JSON dependency in POM.xml

    @Column(nullable = false)
    @JsonProperty("vendorname")
    private String vendorName;

    @Column(nullable = false)
    @JsonProperty("vendoraddress")
    private String vendorAddress;

    @Column(nullable = false)
    @JsonProperty("vendorphone")
    private String vendorPhone;


    //Default Contructor
    public CloudVendor() {
    }

    // Parameterized constructor
    public CloudVendor(String vendorName, String vendorAddress, String vendorPhone) {
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorPhone = vendorPhone;
    }
    //Getter and Setter
    public int getVendorId() {

        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    @Override
    public String toString() {
        return "CloudVendor{" +
                "vendorId=" + vendorId +
                ", vendorName='" + vendorName + '\'' +
                ", vendorAddress='" + vendorAddress + '\'' +
                ", vendorPhone='" + vendorPhone + '\'' +
                '}';
    }

}
