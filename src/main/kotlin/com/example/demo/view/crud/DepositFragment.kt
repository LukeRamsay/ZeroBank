package com.example.demo.view.crud

import com.example.demo.controller.AccountController
import com.example.demo.model.Account
import com.example.demo.utilities.PopUpDialog
import javafx.beans.property.SimpleObjectProperty
import  tornadofx.*
import javafx.stage.StageStyle

class DepositFragment : Fragment("Deposit To An Account") {
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
            button("Deposit to Account") {
                action {
                    accountController.depositAccount(comboboxObject.get())
                    comboboxObject.value = null
                    find<PopUpDialog>(mapOf("message" to "Deposit Completed!")).openModal(stageStyle = StageStyle.UTILITY)
                }
            }
        }
    }
}