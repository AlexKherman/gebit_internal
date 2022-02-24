package com.example.gebit_internal

import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink

class EventChannelHandler: EventChannel.StreamHandler {

     var eventSink:  EventChannel.EventSink? = null

    override fun onListen(arguments: Any?, events: EventSink?) {
        eventSink = events
    }

    override fun onCancel(arguments: Any?) {
        eventSink = null
    }
}