package net.isksss.mc.mineshop.model;

import org.bukkit.inventory.ItemStack;

public class Product {
    public Product(int chestId, int index, ItemStack item, int price) {
        this.chestId = chestId;
        this.index = index;
        this.item = item;
        this.price = price;
    }

    private int chestId;
    private int index;

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

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private ItemStack item;
    private int price;
}
