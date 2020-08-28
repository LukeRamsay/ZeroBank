package com.example.demo.view.crud

import com.example.demo.controller.AccountController
import com.example.demo.model.Account
import com.example.demo.model.Types
import com.example.demo.utilities.PopUpDialog
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.stage.StageStyle
import tornadofx.*

class UpdateFragment : Fragment("Change Account Type") {
    private val comboboxObject = SimpleObjectProperty<Account>()
    private val accountController: AccountController by inject()
    private val newBalanceString = SimpleIntegerProperty()
    private val comboboxObjectType = SimpleObjectProperty<Types>()

    override val root = vbox {
        form {
            combobox<Account>(comboboxObject) {
                items = accountController.accounts

                cellFormat {
                    text = this.item.holder + " " + this.item.balance + " " + this.item.type
                }
            }
            combobox<Types>(comboboxObjectType) {
                items = accountController.types

                cellFormat {
                    text = this.item.name
                }
            }
            button("Change Account Type") {
                action {
                    accountController.updateAccount(comboboxObject.get(),
                            comboboxObjectType.value.name)
                    comboboxObject.value = null

                    find<PopUpDialog>(mapOf("message" to "Type Changed!")).openModal(stageStyle = StageStyle.UTILITY)
                }
            }
        }
    }
}