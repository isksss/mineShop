package net.isksss.mc.mineshop.database;

import net.isksss.mc.mineshop.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private Config config;
    private String dbPath;

    public DatabaseManager(){
        this.config = Config.getConfig();

        String dbName = config.getStringConfig("database.name","mineShop.db");
        this.dbPath = "jdbc:sqlite:plugins/mineShop/"+dbName;
    }

    public void InitDatabase(){
        // データベースの初期設定

        // チェストテーブル
        String createChestTableSQL = "CREATE TABLE IF NOT EXISTS MineShopChest ("
                + "chestId INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "x INTEGER,"
                + "y INTEGER,"
                + "z INTEGER,"
                + "worldName TEXT"
                + ")";
        executeCreateStatement(createChestTableSQL);

        String createProductTableSQL = "CREATE TABLE IF NOT EXISTS Product ("
                + "chestId INTEGER,"
                + "index INTEGER,"
                + "item BLOB,"
                + "price INTEGER,"
                + "PRIMARY KEY (chestId, index)"
                + ")";

        executeCreateStatement(createProductTableSQL);

    }
    public boolean executeCreateStatement (String createSQL) {
        try (Connection connection = DriverManager.getConnection(this.dbPath);
             Statement statement = connection.createStatement()) {
            // CREATE文を実行
            statement.execute(createSQL);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
