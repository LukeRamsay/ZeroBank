package com.example.demo.view.crud

import com.example.demo.controller.AccountController
import com.example.demo.model.Account
import com.example.demo.model.Types
import com.example.demo.utilities.PopUpDialog
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.stage.StageStyle
import tornadofx.*

class IntrestFragment : Fragment("Add Interest") {
    private val comboboxObject = SimpleObjectProperty<Account>()
    private val accountController: AccountController by inject()
    private val newBalanceString = SimpleIntegerProperty()
    private val comboboxObjectType = SimpleObjectProperty<Types>()

    override val root = vbox {
        form {
            combobox<Account>(comboboxObject) {
                items = accountController.accounts

                cellFormat {
                    text = this.item.holder + " " + this.item.balance
                }
            }
            combobox<Types>(comboboxObjectType) {
                items = accountController.types

                cellFormat {
                    text = this.item.intrest_rate.toString()
                }
            }
            button("Deposit To Account") {
                action {
                    accountController.intrestAccount(comboboxObject.get(),
                            comboboxObjectType.value.intrest_rate)
                    comboboxObject.value = null; newBalanceString.value = 0

                    find<PopUpDialog>(mapOf("message" to "Interest Added!")).openModal(stageStyle = StageStyle.UTILITY)
                }
            }
        }
    }
}