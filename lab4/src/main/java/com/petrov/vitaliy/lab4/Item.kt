package com.petrov.vitaliy.lab4

class Item {
    var author: String = ""
    var title: String = ""
    var journal: String = ""
    var year: String = ""
    var volume: String = ""
    var number: String = ""
    var pages: String = ""

    fun changeAuthor(text: String) {
        this.author = text
    }

    fun changeTitle(text: String) {
        this.title = text
    }

    fun changeJournal(text: String) {
        this.journal = text
    }

    fun changeYear(text: String) {
        this.year = text
    }

    fun changeVolume(text: String) {
        this.volume = text
    }

    fun changeNumber(text: String) {
        this.number = text
    }

    fun changePages(text: String) {
        this.pages = text
    }

    override fun toString(): String = author
}