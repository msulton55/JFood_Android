package com.example.JFood_Android.Model;

/**
 * This class creates model for promo object. Everytime this client side creates promo object or
 * retrieve promo data from server-side, this class will be invoked.
 *
 * @author Muhammad Sulton Tauhid
 * @version June 7th, 2020
 */
public class Promo {

    //Instances variables
    private int id;
    private String code;
    private int discount;
    private int minPrice;
    private boolean active;

    /**
     * Promo constructor
     * @param id promo's id
     * @param code promo's unique code
     * @param discount promo's discount price
     * @param minPrice promo's discount minimum price
     * @param active promo's active : true means active, false means deactive.
     */
    public Promo(int id, String code, int discount, int minPrice, boolean active) {
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.minPrice = minPrice;
        this.active = active;
    }

    /**
     * Return promo's id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set new promo's id to the object
     * @param id new promo's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return promo's code
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set new promo's unique code to the object
     * @param code new promo's unique code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Return promo's discount price
     * @return discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Set new promo's discount price to the object
     * @param discount new promo's discount price
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * Return promo's discount minimum price
     * @return minPrice
     */
    public int getMinPrice() {
        return minPrice;
    }

    /**
     * Set new promo's discount minimum price to the object
     * @param minPrice new promo's discount minimum price.
     */
    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * Return promo's active status. true means active, false means deactive
     * @return active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set new promo's active status to the object.
     * @param active new promo's active status
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}
