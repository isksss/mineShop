package net.isksss.mc.mineshop.model;

import org.bukkit.inventory.ItemStack;

public class Product {
    public Product(int chestId, int index, String item, int amount,int price) {
        this.chestId = chestId;
        this.index = index;
        this.item = item;
        this.price = price;
        this.amount = amount;
    }

    private int chestId;
    private int index;
    private String item;
    private int amount;
    private int price;

    public int getChestId() {
        return chestId;
    }

    public void setChestId(int chestId) {
        this.chestId = chestId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
