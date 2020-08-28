package com.example.demo.view.crud

import com.example.demo.controller.AccountController
import com.example.demo.model.Account
import tornadofx.*

class ReadFragment : Fragment("Display All Accounts") {
    private val accountController: AccountController by inject()

    override val root = listview<Account> {
        items = accountController.accounts
        cellFormat {
            text = this.item.holder + " " + this.item.id  + " " + this.item.type + " " + this.item.balance.toString()
        }
    }
}