package com.example.fetchrewardscodingexercise.api

import com.example.fetchrewardscodingexercise.model.Item
import org.json.JSONArray
import java.net.URL

class FetchRewardsAPI {

    fun getItems(): List<Item> {
        val itemsUrl = URL("https://fetch-hiring.s3.amazonaws.com/hiring.json").readText()
        val itemsArray = JSONArray(itemsUrl)
        val itemsArrayLength = itemsArray.length() - 1
        var itemsList: MutableList<Item> = mutableListOf()

        for (i in 0..itemsArrayLength) {
            val isValidName = itemsArray.getJSONObject(i).optString("name").toString()
            if (isValidName != "" && isValidName != "null") {
                itemsList.add(
                    0, Item(
                        itemsArray.getJSONObject(i).optString("listId"),
                        itemsArray.getJSONObject(i).optString("name").toString(),
                        itemsArray.getJSONObject(i).optString("id")
                    )
                )
            }
        }

        itemsList = itemsList.sortedWith(compareBy<Item> { it.listId.toInt() }.thenBy { it.name })
            .toMutableList()

        itemsList.add(0, Item("ListId", "Name", "Id"))

        return itemsList
    }
}