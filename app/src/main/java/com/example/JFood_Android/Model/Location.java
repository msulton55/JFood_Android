package com.example.JFood_Android.Model;

/**
 * This class creates model for location object. Everytime this client side creates location object or
 * retrieve location data from server-side, this class will be invoked.
 *
 * @author Muhammad Sulton Tauhid
 * @version June 7th, 2020
 */
public class Location {

    // Instances variable
    private String province;
    private String description;
    private String city;

    /**
     * Location constructor
     * @param province location's province
     * @param description location's description about the seller (pusat or cabang or etc)
     * @param city location's city
     */
    public Location(String province, String description, String city) {
        this.province = province;
        this.description = description;
        this.city = city;
    }

    /**
     * Return province of the location object
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * Set new location's province of the object
     * @param province location's province
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Return location's description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set new location's description
     * @param description new location's description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return location's city
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set new location's city
     * @param city new location's city
     */
    public void setCity(String city) {
        this.city = city;
    }
}
