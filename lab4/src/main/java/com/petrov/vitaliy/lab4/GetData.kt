package com.petrov.vitaliy.lab4

import name.ank.lab4.BibConfig
import name.ank.lab4.BibDatabase
import name.ank.lab4.Keys
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

object GetData {

    val items: MutableList<Item> = ArrayList()
    private var database: BibDatabase? = null

    private fun addItems() {
        for (i in 0 until 5) {

            val item = Item()

            item.changeAuthor(database!!.getEntry(i).getField(Keys.AUTHOR) ?: "empty")
            item.changeTitle(database!!.getEntry(i).getField(Keys.TITLE) ?: "empty")
            item.changeJournal(database!!.getEntry(i).getField(Keys.JOURNAL) ?: "empty")
            item.changeYear(database!!.getEntry(i).getField(Keys.YEAR) ?: "empty")
            item.changeVolume(database!!.getEntry(i).getField(Keys.VOLUME) ?: "empty")
            item.changeNumber(database!!.getEntry(i).getField(Keys.NUMBER) ?: "empty")
            item.changePages(database!!.getEntry(i).getField(Keys.PAGES) ?: "empty")

            items.add(item)
        }
    }

    fun setResources(inputStream: InputStream) {
        val config = BibConfig().apply {
            shuffle = false
            strict = false
        }
        InputStreamReader(inputStream).use {
            database = BibDatabase(it, config)
        }

        addItems()
        inputStream.close()
    }
}
