package com.example.gebit_internal

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

import android.content.Intent
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel.Result

class MainActivity : FlutterActivity(), MethodChannel.MethodCallHandler, Subscriber,
EventChannel.StreamHandler{

    private var eventSink: EventSink? = null

    private lateinit var methodChannel: MethodChannel

    private lateinit var eventChannel: EventChannel

    val eventChannekHandler = EventChannelHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Observer.addSubscriber(this)
        methodChannel.setMethodCallHandler(this)
        eventChannel.setStreamHandler(this)

//        eventChannel.setStreamHandler(this)
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        methodChannel = MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "methodChannel"
        )
        eventChannel = EventChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            "eventChannel"
        )
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method.equals("getAuthorizationTokenMethod")) {
            val paymentData = call.arguments as HashMap<String, String>
            println("${paymentData.keys}  ${paymentData.values}")
            val intent = Intent(this, KlarnaActivity::class.java)
            for (key in paymentData.keys){
                intent.putExtra(key, paymentData[key])
            }
//            intent.putExtra("paymentData", paymentData)
            startActivity(intent)
        } else {
            result.notImplemented()
        }
    }

    override fun update(data: String) {
        println(data)
        eventSink?.success(data)
    }

    override fun onListen(arguments: Any?, events: EventSink?) {
        eventSink = events
    }

    override fun onCancel(arguments: Any?) {
        eventSink = null
    }
}
