package com.example.demo.utilities

import com.example.demo.app.Styles
import tornadofx.*

class PopUpDialog : Fragment() {
    val message: String by param()
    override val root = vbox {
        label(message) {
            addClass(Styles.heading)
        }
    }
}