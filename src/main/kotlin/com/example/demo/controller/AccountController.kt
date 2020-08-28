package com.example.demo.controller

import com.example.demo.model.Account
import com.example.demo.model.Types
import com.example.demo.utilities.AccountDao
import tornadofx.*

class AccountController : Controller() {
    val accounts = SortedFilteredList(items = getAllAccounts().observable())

    val types = SortedFilteredList(items = getAllTypes().observable())

    fun postAccount(holder: String, id: String, balance: Int, type: String) {
        val account = Account(holder, id, balance, type)
        val dao = AccountDao()
        dao.addAccount(account)
        accounts += account
    }

    fun getAllAccounts(): List<Account> = AccountDao().readAccount()

    fun getAllTypes(): List<Types> = AccountDao().readTypes()

    fun putAccount(oldAccount: Account, newHolderSting: String, newIdSting: String, newBalanceSting: Int, newTypeSting: String) {
        val newAccount = Account(newHolderSting, newIdSting, newBalanceSting, newTypeSting)
        val dao = AccountDao()
        dao.updateAccount(oldAccount.holder, newAccount)
        with(accounts) {
            remove(oldAccount)
            add(newAccount)
        }
    }

    fun deleteAccount(account: Account) {
        val dao = AccountDao()
        dao.deleteAccount(account.holder)
        accounts.remove(account)
    }

    fun depositAccount(oldAccount: Account, newBalanceString: Int) {
        val newAccount = Account(oldAccount.holder, oldAccount.id, oldAccount.balance.plus(newBalanceString), oldAccount.type)
        val dao = AccountDao()
        dao.depositAccount(oldAccount.holder, oldAccount.balance, newBalanceString)
        with(accounts){
            remove(oldAccount)
            add(newAccount)
        }
    }

    fun withdrawAccount(oldAccount: Account, newBalanceString: Int) {
        val newAccount = Account(oldAccount.holder, oldAccount.id, oldAccount.balance.minus(newBalanceString), oldAccount.type)
        val dao = AccountDao()
        dao.withdrawAccount(oldAccount.holder, oldAccount.balance, newBalanceString)
        with(accounts){
            remove(oldAccount)
            add(newAccount)
        }
    }
}