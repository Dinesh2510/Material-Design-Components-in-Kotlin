package com.example.kotlindemoapp.Activities.Forms

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kotlindemoapp.Activities.HomePages.Login
import com.example.kotlindemoapp.R
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var pwd: EditText
    private lateinit var address: EditText
    private lateinit var email: EditText
    private lateinit var lname: EditText
    private lateinit var btn_submit: Button
    private lateinit var str_name: String
    private lateinit var str_pwd: kotlin.String
    private lateinit var str_email: kotlin.String
    private lateinit var str_lname: kotlin.String
    private lateinit var requestQueue: RequestQueue
    var HttpUrl = "http://pixeldev.in/webservices/digital_reader/register.php"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register2)

        val txt = findViewById<TextView>(R.id.go_login)
        txt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                finish()
                startActivity(intent)
            }
        })

        name = findViewById(R.id.e_name)
        pwd = findViewById(R.id.e_pwd)
        email = findViewById(R.id.e_email)
        lname = findViewById(R.id.e_lname)

        btn_submit = findViewById(R.id.submit_btn)

        btn_submit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                str_name = name.text.toString().trim()
                str_pwd = pwd.text.toString().trim()
                str_email = email.text.toString().trim()
                str_lname = lname.text.toString().trim()
                FormData()

            }
        })
    }

    private fun FormData() {
        val stringRequest: StringRequest = object : StringRequest(
            Request.Method.POST, HttpUrl,
            Response.Listener<String?> { ServerResponse ->
                // Hiding the progress dialog after all task complete.
                Log.d("server", "onResponse: " + ServerResponse + "==" + "success")
                val mainobject = JSONObject(ServerResponse)
                val res = mainobject.getString("response")
                if (res == "success") {
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    finish()
                    startActivity(intent)
                    // Showing response message coming from server.
                    Toast.makeText(this@RegisterActivity, ServerResponse, Toast.LENGTH_LONG)
                        .show()
                }
            },
            Response.ErrorListener { volleyError ->
                // Hiding the progress dialog after all task complete.

                // Showing error message if something goes wrong.
                Toast.makeText(this@RegisterActivity, volleyError.toString(), Toast.LENGTH_LONG)
                    .show()
            }) {
            override fun getParams(): Map<String, String> {

                // Creating Map String Params.
                val params: MutableMap<String, String> = HashMap()

                // Adding All values to Params.
                params["first_name"] = str_name
                params["password"] = str_pwd
                params["last_name"] = str_lname
                params["email"] = str_email
                Log.d("_POST_PARAMS", "" + params)
                return params
            }
        }
        stringRequest.retryPolicy = DefaultRetryPolicy(
            10000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        // Creating RequestQueue.
        requestQueue = Volley.newRequestQueue(this)

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest)
    }
}