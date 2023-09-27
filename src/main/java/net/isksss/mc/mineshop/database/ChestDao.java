package net.isksss.mc.mineshop.database;

import net.isksss.mc.mineshop.model.Chest;
import net.isksss.mc.mineshop.model.Product;
import org.bukkit.inventory.ItemStack;

import java.sql.*;

public class ChestDao extends DatabaseManager{

    public ChestDao(){
        super();
    }

    // Chestが登録されているか確認
    public Chest getChest(Chest c){
        Chest chest = null;
        String sql = "SELECT * FROM MineShopChest WHERE x = ? AND y = ? AND z = ? AND worldName = ?";
        try(Connection connection = DriverManager.getConnection(getDbPath());
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,c.getX());
            statement.setInt(2,c.getY());
            statement.setInt(3,c.getZ());
            statement.setString(4,c.getWorldName());

            ResultSet result =  statement.executeQuery();

            if(result.next()){
                int id = result.getInt("chestId");
                int x = result.getInt("x");
                int y = result.getInt("y");
                int z = result.getInt("z");
                String worldName = result.getString("worldName");

                chest = new Chest(id,x,y,z,worldName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return chest;
    }

    public void registerChest(Chest c){
        String sql = "INSERT INTO MineShopChest(x,y,z,worldName) VALUES(?,?,?,?);";
        try(Connection connection = DriverManager.getConnection(getDbPath());
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,c.getX());
            statement.setInt(2,c.getY());
            statement.setInt(3,c.getZ());
            statement.setString(4,c.getWorldName());

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product getProduct(int chestId,int index){
        Product p = null;
        String sql = "SELECT * FROM Product WHERE chestId = ? AND c_index = ?;";
        try(Connection connection = DriverManager.getConnection(getDbPath());
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,chestId);
            statement.setInt(2,index);

            ResultSet result =  statement.executeQuery();

            if(result.next()){
                int id = result.getInt("chestId");
                int newIndex = result.getInt("c_index");
                String item = result.getString("item");
                int amount = result.getInt("amount");
                int price = result.getInt("price");

                p = new Product(id,newIndex,item,amount,price);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return p;
    }

    public void AddProduct(Product p){
        String sql = "INSERT INTO Product(chestId,c_index,item,amount,price) VALUES(?,?,?,?,?);";
        try(Connection connection = DriverManager.getConnection(getDbPath());
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,p.getChestId());
            statement.setInt(2,p.getIndex());
            statement.setString(3, p.getItem());
            statement.setInt(4,p.getAmount());
            statement.setInt(5,p.getPrice());

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
