package com.example.gebit_internal

import android.os.Bundle
import android.widget.Toast
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

    private lateinit var authorizationToken: String

    private lateinit var repo: Repo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authorizationToken = intent.getStringExtra("authorizationToken")!!
        repo = Repo(RetrofitInstance.service, authorizationToken)
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
                    withContext(Dispatchers.IO) {
                        val response = repo.cancel()
                        if (response.isSuccessful) {
                            if (response.body()?.paymentStatus == "VOIDED") {

                            }
                        } else {

                        }

                    }
                }
            }
            createOrderBtn.setOnClickListener {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        val response = repo.createOrder()
                        if (response.isSuccessful) {

                        } else {

                        }
                    }
                }
            }
            payBtn.setOnClickListener {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        val response = repo.finalize()
                        if (response.isSuccessful) {

                        } else {

                        }
                    }
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
        Toast.makeText(this, "AUTH complete", Toast.LENGTH_SHORT).show()
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