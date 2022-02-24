package com.example.gebit_internal

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.gebit_internal.api.Repo
import com.example.gebit_internal.api.RetrofitInstance
import com.example.gebit_internal.databinding.ActivityKlarnaBinding
import com.example.gebit_internal.model.json.PaymentData
import com.google.gson.Gson
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentCategory
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentView
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentViewCallback
import com.klarna.mobile.sdk.api.payments.KlarnaPaymentsSDKError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KlarnaActivity : AppCompatActivity(), KlarnaPaymentViewCallback {

    private lateinit var binding: ActivityKlarnaBinding

    private val paymentData by lazy {
        intent.getSerializableExtra("paymentData")
    }

    private var repo: Repo = Repo(RetrofitInstance.service)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKlarnaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.paymentView.registerPaymentViewCallback(this@KlarnaActivity)
        initViews()
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val response = repo.start()
                println(response)
                if (response.isSuccessful) {
                    val paymentData = parsePaymentData(response.body()?.paymentData)

                    withContext(Dispatchers.Main) {
                        binding.paymentView.initialize(
                            paymentData.clientToken,
                            "kp://kp-sample"
                        )
                    }
                    // klarnaView?.authorize(true, null
                } else {
                    println("${response.code()} ${response.message()}")
                }

            }
        }
    }

    private fun initViews() {
        with(binding) {

            paymentView.category = KlarnaPaymentCategory.PAY_LATER
            authorizeBtn.setOnClickListener {
                Observer.updateData("AUTH")
                paymentView.authorize(true, null)
            }
            backBtn.setOnClickListener {
                finish()
            }
            cancelBtn.setOnClickListener {
                lifecycleScope.launch {
                    repo.cancel()
                }
            }
            createOrderBtn.setOnClickListener {
                lifecycleScope.launch {
                    repo.createOrder()
                }
            }
            payBtn.setOnClickListener {
                lifecycleScope.launch{
                    repo.finalize()
                }
            }
        }
    }

    private fun parsePaymentData(paymentData: String?): PaymentData {
        val gson = Gson()
        return gson.fromJson(paymentData, PaymentData::class.java)
    }

    override fun onAuthorized(
        view: KlarnaPaymentView,
        approved: Boolean,
        authToken: String?,
        finalizedRequired: Boolean?
    ) {
        repo.authorizationToken = authToken
        if (authToken != null) {
            Observer.updateData("Success")
        } else {

        }
    }

    override fun onErrorOccurred(view: KlarnaPaymentView, error: KlarnaPaymentsSDKError) {
        AlertDialog.Builder(this)
            .setTitle("KlarnaError")
            .setMessage(error.message)
            .setPositiveButton("OK") { dialog, which ->
                Observer.updateData("error")
                finish()
            }.show()
    }

    override fun onFinalized(view: KlarnaPaymentView, approved: Boolean, authToken: String?) {
        repo.authorizationToken = authToken
    }

    override fun onInitialized(view: KlarnaPaymentView) {
        view.load(null)
    }

    override fun onLoadPaymentReview(view: KlarnaPaymentView, showForm: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onLoaded(view: KlarnaPaymentView) {}

    override fun onReauthorized(view: KlarnaPaymentView, approved: Boolean, authToken: String?) {
        repo.authorizationToken = authToken
    }
}