package com.example.demo.view.crud

import com.example.demo.controller.AccountController
import com.example.demo.model.Account
import com.example.demo.utilities.PopUpDialog
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.stage.StageStyle
import tornadofx.*

class UpdateFragment :Fragment("Update An Account") {
    private val comboboxObject = SimpleObjectProperty<Account>()
    private val newHolderString = SimpleStringProperty()
    private val newIdString = SimpleStringProperty()
    private val newTypeString = SimpleStringProperty()
    private val newBalanceString = SimpleIntegerProperty()
    private val accountController: AccountController by inject()

    override val root = vbox {
        form {
            combobox<Account>(comboboxObject) {
                items = accountController.accounts
                cellFormat {
                    text = this.item.holder + " " + this.item.balance
                }
            }
            fieldset {
                field ("New Account Holder")
                textfield(newHolderString)
                field  ("New ID Number")
                textfield(newIdString)
                field ("New Balance")
                textfield(newBalanceString)
                field ("New Account Type")
                textfield(newTypeString)
            }
            button("Update Account") {
                action {
                    accountController.putAccount(
                            comboboxObject.get(),
                            newHolderString.value,
                            newIdString.value,
                            newBalanceString.value,
                            newTypeString.value
                    )
                    newHolderString.value = ""; newIdString.value = ""; newBalanceString.value = 0; newTypeString.value = ""; comboboxObject.value = null
                    find<PopUpDialog>(mapOf("message" to "Account Updated")).openModal(stageStyle = StageStyle.UTILITY)
                    //need to fix
                }
            }
        }

    }
}