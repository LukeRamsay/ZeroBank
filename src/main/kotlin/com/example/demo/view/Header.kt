package com.example.demo.view

import com.example.demo.view.crud.*
import javafx.scene.control.TabPane
import tornadofx.*

class Header : View() {
    override val root = tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        tab<ReadFragment>()
        tab<CreateFragment>()
        tab<UpdateFragment>()
        tab<DeleteFragment>()
        tab<IntrestFragment>()
        tab<WithdrawFragment>()
        tab<DepositFragment>()
    }
}