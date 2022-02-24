package com.example.gebit_internal

object Observer {

    private val list = mutableListOf<Subscriber>()

    var currentData: String = ""

    fun addSubscriber(subscriber: Subscriber) {
        list.add(subscriber)
    }

    fun updateData(data: String,) {
        list.forEach {
            it.update(data)
        }
        currentData = data
    }

    fun removeSub(subscriber: Subscriber) {
        list.remove(subscriber)
    }
}