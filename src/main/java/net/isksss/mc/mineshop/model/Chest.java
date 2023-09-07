package net.isksss.mc.mineshop.model;

import org.bukkit.Location;

public class Chest {
    private int chestId;

    // ワールド内のX座標
    private int x;

    // ワールド内のY座標
    private int y;

    // ワールド内のZ座標
    private int z;

    // ワールドの名前
    private String worldName;

    // コンストラクタ1: X, Y, Z, ワールド名を指定してChestオブジェクトを作成
    public Chest(int chestId, int x, int y, int z, String worldName) {
        this.chestId = chestId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldName = worldName;
    }

    // コンストラクタ2: Locationオブジェクトを指定してChestオブジェクトを作成
    public Chest(int chestId, Location loc) {
        this.chestId = chestId;
        this.x = (int) loc.getX();
        this.y = (int) loc.getY();
        this.z = (int) loc.getZ();
        this.worldName = loc.getWorld().getName();
    }

    public int getChestId() {
        return chestId;
    }

    public void setChestId(int chestId) {
        this.chestId = chestId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }
}
