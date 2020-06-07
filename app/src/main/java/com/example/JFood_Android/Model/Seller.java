package com.example.JFood_Android.Model;

/**
 * This class creates model for seller object. Everytime this client side creates seller object or
 * retrieve seller data from server-side, this class will be invoked.
 *
 * @author Muhammad Sulton Tauhid
 * @version June 7th, 2020
 */
public class Seller {
    // Instances variable
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private Location location;

    /**
     * Seller constructor
     * @param id seller's id
     * @param name seller's name
     * @param email seller's email
     * @param phoneNumber seller's phone number
     * @param location seller's actual location
     */
    public Seller(int id, String name, String email, String phoneNumber, Location location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    /**
     * Return seller's id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set new seller's id to the object
     * @param id new seller's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return seller's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set new seller's name to the object
     * @param name new seller's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return seller's email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set new seller's email to the object
     * @param email new seller's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return seller's phone number
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set new seller's phone number to the object
     * @param phoneNumber new seller's phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Return seller's actual location
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set new seller's location object
     * @param location new seller's location
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}
