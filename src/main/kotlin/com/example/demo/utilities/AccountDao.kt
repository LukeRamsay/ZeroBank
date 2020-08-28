package com.example.demo.utilities

import com.example.demo.model.Account
import java.sql.Timestamp

class AccountDao {
    fun addAccount(account: Account) {
        val connection = Database().connection
        val preparedStatement = connection.prepareStatement("INSERT INTO Account(holder, id, balance, type) VALUES(?, ?, ?, ?)")
        preparedStatement.setString(1, account.holder)
        preparedStatement.setString(2, account.id)
        preparedStatement.setInt(3, account.balance)
        preparedStatement.setString(4, account.type)
        preparedStatement.executeUpdate()
        preparedStatement.close()
        connection.close()
    }

    fun readAccount(): List<Account> {
        val connection = Database().connection
        val resultSet = connection
                .createStatement()
                .executeQuery("SELECT * FROM Account WHERE date_deleted IS NULL")
        val accountList = ArrayList<Account>()
        while (resultSet.next()) {
            val holder = resultSet.getString("holder")
            val id = resultSet.getString("id")
            val balance = resultSet.getInt("balance")
            val type = resultSet.getString("type")
            accountList += Account(holder, id, balance, type)
        }
        resultSet.close()
        connection.close()
        return accountList
    }

    fun updateAccount(holder: String, account: Account) {
        val connection = Database().connection
        var param = ""
        val paramId = ", balance = ?"
        var optionalParamIndex = 2
        if (account.id.isNotEmpty()) param = paramId
        val preparedStatement = connection.prepareStatement("UPDATE Account SET holder = ? $param WHERE holder = ?")
        preparedStatement.setString(1, account.holder)
        if (param.isNotEmpty()) {
            preparedStatement.setString(optionalParamIndex, account.id)
            optionalParamIndex = optionalParamIndex.inc()
        }
        preparedStatement.setString(optionalParamIndex, holder)
        preparedStatement.executeUpdate()
        preparedStatement.close()
        connection.close()
        //return account
        //Not yet properly working
    }

    fun deleteAccount(holder: String) {
        val timestamp = Timestamp(System.currentTimeMillis())
        val connection = Database().connection
        val preparedStatement = connection.prepareStatement("UPDATE Account SET date_deleted = ? WHERE holder = ?")
        preparedStatement.setString(1, timestamp.toString())
        preparedStatement.setString(2, holder)
        preparedStatement.executeUpdate()
        preparedStatement.close()
        connection.close()
    }

    fun depositAccount(holder: String, balance : Int, addAmount : Int) {
        val connection = Database().connection
        val preparedStatement = connection.prepareStatement("UPDATE Account SET balance = ? WHERE holder = ?")
        preparedStatement.setString(1, balance.plus(addAmount).toString())
        preparedStatement.setString(2, holder)
        preparedStatement.executeUpdate()
        preparedStatement.close()
        connection.close()
    }

    fun withdrawAccount(holder: String, balance : Int, minusAmount : Int) {
        val connection = Database().connection
        val preparedStatement = connection.prepareStatement("UPDATE Account SET balance = ? WHERE holder = ?")
        preparedStatement.setString(1, balance.minus(minusAmount).toString())
        preparedStatement.setString(2, holder)
        preparedStatement.executeUpdate()
        preparedStatement.close()
        connection.close()
    }

    fun intrestAccount(holder: String, balance : Int, intrestAmount : Int) {
        val connection = Database().connection
        val intrest = 0
        val preparedStatement = connection.prepareStatement("UPDATE Account SET balance = ? WHERE holder = ?")
        preparedStatement.setString(1, balance.plus(intrest).toString())
        preparedStatement.setString(2, holder)
        preparedStatement.executeUpdate()
        preparedStatement.close()
        connection.close()
    }
}