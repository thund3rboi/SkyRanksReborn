package dev.micah.mysql;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLSetup {

    private String host, database, username, password;
    private int port;
    private static Connection connection;

    public MySQLSetup(String host, String database, String username, String password, int port) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public boolean connect() {
        try {
            synchronized (this) {
                if (this.getConnection() != null && !this.getConnection().isClosed()) {
                    return false;
                }
                Class.forName("com.mysql.jdbc.Driver");
                this.setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password));
                Bukkit.getLogger().info("[SkyRanksReborn] MySQL has been connected and is secure");
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public static Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
