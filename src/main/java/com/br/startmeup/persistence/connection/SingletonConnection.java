package com.br.startmeup.persistence.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingletonConnection {
    private String dbUrl;
    private Connection connection;
    private static SingletonConnection instance;

    private SingletonConnection(){

    }

    public static synchronized SingletonConnection getInstance() {
        if (instance == null) {
            instance = new SingletonConnection();
        }
        return instance;
    }
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //dbUrl = System.getenv("JDBC_DATABASE_URL");
            dbUrl = "jdbc:mysql://localhost:3306/startmeup";
            String user = "username";
            String password = "password";
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch(Exception ex){
            Logger.getLogger(SingletonConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
