package com.example.JFood_Android.Model;

/**
 * This class creates model for food object. Everytime this client side creates food object or
 * retrieve food data from server-side, this class will be invoked.
 *
 * @author Muhammad Sulton Tauhid
 * @version June 7th, 2020
 */
public class Food {

    // Instances Variables
    private int id;
    private String name;
    private int price;
    private String category;
    private Seller seller;

    /**
     * Food constructor
     * @param id food's id
     * @param name food's name
     * @param price food's price
     * @param category food's category
     * @param seller food's seller object
     */
    public Food(int id, String name, int price, String category, Seller seller) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.seller = seller;
    }

    /**
     * Return food's id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set new food's id to the object
     * @param id food's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return food's name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set new food's name to the object
     * @param name food's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return food's price
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Set new food's price to the object
     * @param price food's price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Return food's category
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set new food's category to the object.
     * @param category food's category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Return food's seller
     * @return seller
     */
    public Seller getSeller() {
        return seller;
    }

    /**
     * Set new food's seller to the object
     * @param seller food's seller object
     */
    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
