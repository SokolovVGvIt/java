package com.CourseJava11;

import java.sql.*;

public class bd_DZ_BD {

    private final String HOST = "localhost";
    private final String PORT = "3307";
    private final String DB_NAME = "simple";
    private final String LOGIN = "root";
    private final String PASS = "root";


    private Connection dbConn = null;

    // функция которая создаёт подключение к базе данных и помещает его в переменную dbConn
    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String str = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        //"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        //"?characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConn = DriverManager.getConnection(str, LOGIN, PASS);
        return dbConn;
    }

    // проверка подключения к базе данных
    public void isConnected() throws SQLException, ClassNotFoundException {
        dbConn = getDbConnection();
        System.out.println(dbConn.isValid(1000));
    }

    // добавление новых записей в таблицу Orders
    public void insertOders(int user_id, int item_id) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `orders` (user_id, item_id) VALUES (?, ?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setInt(1, user_id);
        prSt.setInt(2, item_id);

        prSt.executeUpdate();
    }


    //выборка из таблицы user_id
    public ResultSet getArticles_users_id(String table) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM " + table + " WHERE `login` LIKE '%john%';";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        return res;
    }

    //выборка из таблицы item_id
    public ResultSet getArticles_item_id(String table) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM " + table + " WHERE `category` LIKE '%hats%';";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        return res;
    }


    //выборка из таблицы orders
    public ResultSet getArticlesALL() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM `orders` JOIN `users` ON orders.user_id = users.id JOIN `items` ON items.id = orders.item_id;";
        Statement statement = getDbConnection().createStatement();
        ResultSet res = statement.executeQuery(sql);
        return res;
    }

    }

