package com.example.demo.utilities

import java.sql.Connection
import java.sql.DriverManager

class Database {
    var connection : Connection

    init {
        Class.forName("org.sqlite.JDBC")
        connection = DriverManager.getConnection("jdbc:sqlite:ZeroBank.db")
    }
}