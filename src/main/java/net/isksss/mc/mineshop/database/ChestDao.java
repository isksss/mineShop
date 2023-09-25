package net.isksss.mc.mineshop.database;

import net.isksss.mc.mineshop.model.Chest;

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

}
