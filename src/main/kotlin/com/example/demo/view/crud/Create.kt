package com.example.demo.view.crud

import com.example.demo.controller.AccountController
import com.example.demo.model.Account
import com.example.demo.model.Types
import com.example.demo.utilities.Database
import com.example.demo.utilities.PopUpDialog
import javafx.beans.property.SimpleStringProperty
import javafx.stage.StageStyle
import javafx.scene.Parent
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*
import tornadofx.Stylesheet.Companion.comboBox

class CreateFragment : Fragment("Create A New Account") {
    private val comboboxObject = SimpleObjectProperty<Types>()
    private val holderString = SimpleStringProperty()
    private val idString = SimpleStringProperty()
    private val accountBalance = SimpleIntegerProperty()
    private val accountType = SimpleStringProperty()
    private val accountController: AccountController by inject()


    override val root = vbox {
        form {
            fieldset {
                field ("Account Holder")
                textfield(holderString)
                field  ("ID Number")
                textfield(idString)
                field ("Balance")
                textfield(accountBalance)
                combobox<Types>(comboboxObject) {
                    items = accountController.types

                    cellFormat {
                        text = this.item.name.toString()
                    }
                }
            }
            button("Add Account") {
                action {
                    runAsync {
                        accountController.postAccount(holderString.value, idString.value, accountBalance.value, comboboxObject.value.name)
                    }.ui {
                        holderString.value = ""; idString.value = ""; accountBalance.value = 0
                        find<PopUpDialog>(params = mapOf("message" to "New Account Created!")).openModal(stageStyle = StageStyle.UTILITY)
                    }
                }
            }
        }
    }
}