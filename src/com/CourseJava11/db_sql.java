package com.CourseJava11;

import javax.xml.crypto.Data;
import java.sql.*;
import java.sql.Date;


public class db_sql {

    private final String HOST = "localhost";
    private final String PORT = "3307";
    private final String DB_NAME = "java_db";
    private final String LOGIN = "root";
    private final String PASS = "root";



    // создаём обьект dbConn, который содержит подключение к базе данных
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

// создание новой таблицы
public void createTable(String tableName) throws SQLException, ClassNotFoundException {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(55), password VARCHAR(100)) ENGINE=MYISAM;";

    Statement statement = getDbConnection().createStatement();
    statement.executeUpdate(sql);
}

// добавление новых записей в таблицу
public void insertArticle (String title, String name, String data, String autor) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `articles` (title, text, data, autor) VALUES (?, ?, ?, ?)";
    //Object Dat = Date.valueOf(data);
    PreparedStatement prSt = getDbConnection().prepareStatement(sql);
    prSt.setString(1, title);
    prSt.setString(2, name);
    prSt.setString(3, data);
    prSt.setString(4, autor);

    prSt.executeUpdate();
}

    //выборка из таблицы
public void getArticles(String table) throws SQLException, ClassNotFoundException {
     String sql = "SELECT * FROM " + table + " WHERE `text` LIKE '%text%' AND `id` = 9 OR `id` = 8"
             + " ORDER BY `data` ASC LIMIT 2 OFFSET 1";
     //SELECT * FROM `articles` ORDER BY `articles`.`title` DESC   - сортировка по убыванию
    //SELECT * FROM `articles` ORDER BY `articles`.`title` ASC  - сортировка по возрастанию
    // WHERE - условия (если, при условии)
    // ORDER BY - сортировка
    // LIMIT 2 - выводим только 2 записи(лимит, ограничение)
    // OFFSET 1 - пропускаем одну запись

    Statement statement = getDbConnection().createStatement();
    ResultSet res = statement.executeQuery(sql);
    while (res.next()) {
        System.out.println(res.getString("text"));
        //res.getString("id") + " " + res.getString("title") + " " +
    }
    }

    // изменяем данные в таблице (обновляем)
public void updateArticles () throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `articles` SET `title` = ? WHERE `id` = 8";
    PreparedStatement prSt = getDbConnection().prepareStatement(sql);
    prSt.setString(1, "text7");

    prSt.executeUpdate();
}

    // удаляем данные
public  void  deleteSomething() throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM `articles` WHERE `id` = 8";
        // "DELETE FROM `articles` - удалить всё из таблицы
    // DROP TABLE `articles` - удалить таблицу
    Statement statement = getDbConnection().createStatement();
    statement.executeUpdate(sql);

}





}



