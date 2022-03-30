package com.example.gebit_internal

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
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
import org.json.JSONObject

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
        if (InternetAccessDetector.hasInternetConnection(this)) {
            startPayment()
        } else showErrorAlertDialog("No Internet connection. Check your connection and try again.")

    }

    private fun startPayment() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val response = repo.start()
                println(response)
                if (response.isSuccessful) {
                    val paymentData = parsePaymentData(response.body()?.paymentData)
                    repo.paymentInfo.apply {
                        paymentId = response.body()?.paymentID
                        clientToken = paymentData.clientToken
                        amount = response.body()?.amount
                        amount?.currency = response.body()?.amount?.currency
                        amount?.scale = response.body()?.amount?.scale
                        amount?.value = response.body()?.amount?.value
                    }
                    Observer.getCurrentUserPaymentStatus(
                        response.body()?.amount?.currency,
                        response.body()?.amount?.scale,
                        response.body()?.amount?.value.toString(),
                        response.body()?.transactionID,
                        response.body()?.clientID
                    )
                    Observer.getOperationStatus(
                        OperationName.CREATED,
                        true,
                        response.body()?.paymentID,
                        null
                    )
                    withContext(Dispatchers.Main) {
                        binding.paymentView.initialize(
                            paymentData.clientToken,
                            "kp://kp-sample"
                        )
                    }
                } else {
                    println("${response.code()} ${response.message()}")
                    Observer.getOperationStatus(
                        OperationName.ERROR,
                        false,
                        response.body()?.paymentID,
                        response.errorBody().toString()
                    )
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
            backBtn.setOnClickListener { finish() }
            createOrderBtn.setOnClickListener { onCreateOrderButtonClicked() }
            cancelBtn.setOnClickListener { onCancelButtonClicked() }
            payBtn.setOnClickListener { onPayButtonClicked() }
        }
    }

    private fun onCreateOrderButtonClicked() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val response = repo.createOrder()
                if (response.isSuccessful) {
                    val json = JSONObject(response.body()?.paymentData ?: "")
                    repo.paymentInfo.apply {
                        customerToken = json.getString("token_id")
                        paymentId = response.body()?.paymentID
                        amount = response.body()?.amount
                        amount?.currency = response.body()?.amount?.currency
                        amount?.scale = response.body()?.amount?.scale
                        amount?.value = response.body()?.amount?.value
                    }
                    Observer.getCurrentUserPaymentStatus(
                        response.body()?.amount?.currency,
                        response.body()?.amount?.scale,
                        response.body()?.amount?.value.toString(),
                        response.body()?.transactionID,
                        response.body()?.clientId
                    )
                    Observer.getOperationStatus(
                        OperationName.CREATED,
                        true,
                        response.body()?.paymentID,
                        null
                    )
                    withContext(Dispatchers.Main) {
                        showInfoAlertDialog("Order successfully created!", false)
                        with(binding) {
                            createOrderBtn.visibility = View.GONE
                            payBtn.visibility = View.VISIBLE
                            paymentView.visibility = View.GONE
                            tvPaymentInfo.text = "Total to pay:\n${response.body()?.articlePositions?.get(0)?.unitPrice?.value ?: ""} €"
                            tvPaymentInfo.setBackgroundColor(Color.parseColor("#ffb5c8"))
                            linearLayout.gravity = Gravity.BOTTOM
                        }
                    }

                } else {
                    Observer.getOperationStatus(
                        OperationName.ERROR,
                        false,
                        response.body()?.paymentID,
                        response.errorBody().toString()
                    )
                    withContext(Dispatchers.Main) {
                        showErrorAlertDialog("Failed to create order: \nError ${response.code()}.}")
                    }
                }
            }
        }
    }

    private fun onCancelButtonClicked() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val response = repo.cancel()
                if (response.isSuccessful) {
                    if (response.body()?.paymentStatus == "VOIDED") {
                        repo.paymentInfo.amount = response.body()?.amount
                        Observer.getCurrentUserPaymentStatus(
                            response.body()?.amount?.currency,
                            response.body()?.amount?.scale,
                            response.body()?.amount?.value.toString(),
                            response.body()?.transactionID,
                            response.body()?.clientID
                        )
                        Observer.getOperationStatus(
                            OperationName.PAYMENT_CANCELLED,
                            true,
                            response.body()?.paymentID,
                            response.errorBody().toString()
                        )
                        withContext(Dispatchers.Main) {
                            showInfoAlertDialog("Payment cancelled successfully.", true)
                        }
                    }
                } else {
                    Observer.getOperationStatus(
                        OperationName.ERROR,
                        false,
                        response.body()?.paymentID,
                        response.errorBody().toString()
                    )
                    withContext(Dispatchers.Main) {
                        showErrorAlertDialog("Cancellation error: ${response.errorBody()}")
                    }
                }
            }
        }
    }

    private fun onPayButtonClicked() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val response = repo.finalize()
                if (response.isSuccessful) {
                    Observer.getCurrentUserPaymentStatus(
                        response.body()?.amount?.currency,
                        response.body()?.amount?.scale,
                        response.body()?.amount?.value.toString(),
                        response.body()?.transactionID,
                        response.body()?.clientID
                    )
                    Observer.getOperationStatus(
                        OperationName.PAYMENT_APPROVED,
                        true,
                        response.body()?.paymentId,
                        null
                    )
                    Observer.getOperationStatus(
                        OperationName.PAYMENT_COMPLETED,
                        true,
                        response.body()?.paymentId,
                        null
                    )
                    withContext(Dispatchers.Main) {
                        showInfoAlertDialog("Payment successful!", false)
                    }
                } else {
                    Observer.getOperationStatus(
                        OperationName.ERROR,
                        false,
                        response.body()?.paymentId,
                        null
                    )
                    withContext(Dispatchers.Main) {
                        showErrorAlertDialog("Payment failed!\nError ${response.code()}}")
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
        Observer.getOperationStatus(
            OperationName.AUTHORIZATION,
            true,
            repo.paymentInfo.paymentId,
            null
        )
        repo.authorizationToken = authToken
        if (authToken != null) {
            Observer.updateData("Success")
            showInfoAlertDialog("Successfully authorized!", false)
            with(binding) {
                authorizeBtn.visibility = View.GONE
                backBtn.visibility = View.GONE
                createOrderBtn.visibility = View.VISIBLE
                cancelBtn.visibility = View.VISIBLE
                tvPaymentInfo.text = "Successfully authorized"
                tvPaymentInfo.visibility = View.VISIBLE
            }

        } else {
            if (!InternetAccessDetector.hasInternetConnection(this)) {
                showErrorAlertDialog("No Internet connection. Check your connection and try again.")
            }
        }
    }

    override fun onErrorOccurred(view: KlarnaPaymentView, error: KlarnaPaymentsSDKError) {
        if (!InternetAccessDetector.hasInternetConnection(this)) {
            showErrorAlertDialog("No Internet connection. Check your connection and try again.")
            return
        }
        if (error.name == "InvalidClientTokenError") {
            Observer.getOperationStatus(
                OperationName.CLIENT_TOKEN_WAS_NOT_SET,
                false,
                repo.paymentInfo.paymentId,
                "Invalid client token."
            )
            showErrorAlertDialog("Invalid client token.")
            return
        }
        if (error.action == "Load" && error.name == "ShowFormFalseError") {
            Observer.getOperationStatus(
                OperationName.ERROR,
                false,
                repo.paymentInfo.paymentId,
                "Invalid payment method."
            )
            showErrorAlertDialog("Invalid payment method.")
            return
        }
        if (error.action == "Authorize" && error.name == "ShowFormFalseError") {
            Observer.getOperationStatus(
                OperationName.ERROR,
                false,
                repo.paymentInfo.paymentId,
                "Failed to authorize."
            )
            showErrorAlertDialog("Failed to authorize.")
            return
        } else {
            Observer.getOperationStatus(
                OperationName.ERROR,
                false,
                repo.paymentInfo.paymentId,
                "Unknown error."
            )
            showErrorAlertDialog("Unknown error.")
        }
    }

    private fun showErrorAlertDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("KlarnaError")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, which ->
                Observer.updateData("error")
                finish()
            }.show()
    }

    private fun showInfoAlertDialog(message: String, finishActivity: Boolean) {
        AlertDialog.Builder(this)
            .setTitle("Info")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, which ->
                dialog.cancel()
                if (finishActivity) {
                    finish()
                }
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

    override fun onLoaded(view: KlarnaPaymentView) {
        binding.authorizeBtn.isEnabled = true
        binding.authorizeBtn.background = AppCompatResources.getDrawable(this, R.drawable.button_background_black)
    }

    override fun onReauthorized(view: KlarnaPaymentView, approved: Boolean, authToken: String?) {
        repo.authorizationToken = authToken
    }

}