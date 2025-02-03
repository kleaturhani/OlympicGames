package it.programmazionemobile.olympicgames2024.viewmodel

import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var username: String = ""
    var email: String = ""
    var nome: String = ""
    var cognome: String = ""

    fun loadData(username: String, email: String, nome: String, cognome: String) {
        this.username = username
        this.email = email
        this.nome = nome
        this.cognome = cognome
    }

}