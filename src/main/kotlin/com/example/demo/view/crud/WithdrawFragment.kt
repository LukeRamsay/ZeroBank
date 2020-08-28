package com.example.demo.view.crud

import com.example.demo.controller.AccountController
import com.example.demo.model.Account
import com.example.demo.utilities.PopUpDialog
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import  tornadofx.*
import javafx.stage.StageStyle

class WithdrawFragment : Fragment("Withdraw From An Account") {
    private val comboboxObject = SimpleObjectProperty<Account>()
    private val accountController: AccountController by inject()
    private val newBalanceString = SimpleIntegerProperty()

    override val root = vbox {
        form {
            combobox<Account>(comboboxObject) {
                items = accountController.accounts

                cellFormat {
                    text = this.item.holder + " " + this.item.balance
                }
            }
            fieldset {
                field ("Withdrawal amount")
                textfield(newBalanceString)
            }
            button("Withdrawal from Account") {
                action {
                    val newAccounts = comboboxObject.get()
                    accountController.withdrawAccount(comboboxObject.get(),
                            newBalanceString.value)
                    comboboxObject.value = null; newBalanceString.value = 0

                    find<PopUpDialog>(mapOf("message" to "Withdrawal Completed!")).openModal(stageStyle = StageStyle.UTILITY)
                    accountController.getAllAccounts()
                }
            }
        }
    }
}