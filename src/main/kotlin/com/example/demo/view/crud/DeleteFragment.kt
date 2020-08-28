package com.example.demo.view.crud

import com.example.demo.controller.AccountController
import com.example.demo.model.Account
import com.example.demo.utilities.PopUpDialog
import javafx.beans.property.SimpleObjectProperty
import  tornadofx.*
import javafx.stage.StageStyle

class DeleteFragment : Fragment("Remove An Account") {
    private val comboboxObject = SimpleObjectProperty<Account>()
    private val accountController: AccountController by inject()

    override val root = vbox {
        form {
            combobox<Account>(comboboxObject) {
                items = accountController.accounts
                cellFormat {
                    text = this.item.holder + " " + this.item.balance
                }
            }
            button("Delete Account") {
                action {
                    accountController.deleteAccount(comboboxObject.get())
                    comboboxObject.value = null
                    find<PopUpDialog>(mapOf("message" to "Account Deleted!")).openModal(stageStyle = StageStyle.UTILITY)
                    accountController.getAllAccounts()
                }
            }
        }
    }
}