package com.example.kotlindemoapp.Activities.Forms

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kotlindemoapp.Activities.HomePages.HomePage
import com.example.kotlindemoapp.Activities.HomePages.Register
import com.example.kotlindemoapp.R
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var etUname: EditText
    private lateinit var etPass: EditText
    private lateinit var btnlogin: Button
    private lateinit var tvreg: TextView
    private lateinit var skip_txt: TextView
    private lateinit var forget_password: TextView
    private lateinit var show_pass: ImageButton
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    val SHARED_PREFERENCES_NAME = "login_portal"
    val USER_ID = "user_id"
    val FNAME = "fname"
    val LNAME = "lname"
    val EMAIL = "email"
    val PWD = "pwd"
    val PREMIUMUSER = "premiumuser"
    val SKIP = "skip"
    private val parent_view: View? = null
    var email: String? = null
    private lateinit var password: kotlin.String
    private lateinit var rl_pwd: RelativeLayout
    var ll_lay: LinearLayout? = null
    var progress_bar: ProgressBar? = null
    var pattern_pwd = Pattern.compile("^[a-zA-Z0-9]+$")
    var userid = ""
    var userfname: kotlin.String? = ""
    var userlname: kotlin.String? = ""
    var useremail: kotlin.String? = ""
    var userpwd: kotlin.String? = ""
    var userPremiumFlag: kotlin.String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        ll_lay = findViewById<LinearLayout>(R.id.ll_lay)
        rl_pwd = findViewById(R.id.rl_pwd)
        etUname = findViewById<EditText>(R.id.etemail)
        etPass = findViewById<EditText>(R.id.etpassword)
        btnlogin = findViewById<View>(R.id.btn) as Button
        progress_bar = findViewById(R.id.progress_bar)
        show_pass = findViewById(R.id.show_pass)

        tvreg = findViewById<View>(R.id.tvreg) as TextView
        forget_password = findViewById<View>(R.id.forgot_password) as TextView

        forget_password!!.setOnClickListener { //showDialogForgotPassword();
            Toast.makeText(this@LoginActivity, "Soon...", Toast.LENGTH_SHORT).show()
        }

//        skip_txt.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this@LoginActivity, HomePage::class.java)
//            finish()
//            startActivity(intent)
//        })

        tvreg.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            finish()
            startActivity(intent)
        })

        //hide and show password
        show_pass.setOnClickListener(View.OnClickListener { v ->
            v.isActivated = !v.isActivated
            if (v.isActivated) {
                etPass!!.transformationMethod = null
            } else {
                etPass!!.transformationMethod = PasswordTransformationMethod()
            }
            etPass!!.setSelection(etPass!!.text.length)
        })


        btnlogin.setOnClickListener(View.OnClickListener {
            email = etUname.getText().toString().trim { it <= ' ' }
            password = etPass.text.toString().trim { it <= ' ' }
            Log.d("userdata", "onClick: $email$password")
            if (!email!!.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (!password!!.isEmpty() && pattern_pwd.matcher(password).matches()) {
                    loginUser(email.toString(), password)
                } else {
                    Toast.makeText(this, "Enter the Valid Password", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Enter the Valid Email", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun loginUser(email: String, password: String) {
        Log.d("TAG_one", "loginUser: " + email + password)
        val LOGIN_URL = "http://pixeldev.in/webservices/digital_reader/login.php"
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, LOGIN_URL,
            Response.Listener { response ->
                showJSON(response)
                Log.d("login_response", "" + response)
            },
            Response.ErrorListener {
                Toast.makeText(this, R.string.cb_no_internet_error, Toast.LENGTH_LONG).show();
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["email"] = email
                params["password"] = password
                Log.d("TAG_log", "getParams: " + params)
                return params
            }
        }
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
        stringRequest.retryPolicy = DefaultRetryPolicy(
            5000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )


        // AppController.getInstance().addToRequestQueue(stringRequest, "Login_Module")

    }

    private fun showJSON(response: String) {
        Log.d("wel", "showJSON: welcome")
        try {
            val mainobject = JSONObject(response)
            val res = mainobject.getString("response")
            if (res.equals("failure", ignoreCase = true)) {
                try {
                    Toast.makeText(this, "invalid_username_password", Toast.LENGTH_SHORT)
                        .show()
                } catch (ignored: Exception) {
                }
            } else {
                try {
                    val `object` = JSONObject(res)
                    if (`object`.has("first_name")) {
                        // Entry already exist in db
                        val user_id = `object`.getString("user_id")
                        val first_name = `object`.getString("first_name")
                        val last_name = `object`.getString("last_name")
                        val email = `object`.getString("email")
                        val passwrd = `object`.getString("password")
                        val userpremiumflag = `object`.getString("userpremiumflag")

                        Log.d("your_self", "" + user_id)
                        sharedPreferences =
                            getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString(USER_ID, user_id)
                        editor.putString(FNAME, first_name)
                        editor.putString(LNAME, last_name)
                        editor.putString(EMAIL, email)
                        editor.putString(PWD, passwrd)
                        editor.putString(PREMIUMUSER, userpremiumflag)
                        editor.apply()

                        //#SHAREPERFENCE CODE
                        sharedPreferences =
                            getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)
                        val str_id = sharedPreferences.getString(FNAME, "")
                        Log.d("fname", "showJSON: " + str_id)
                        val intent = Intent(this, HomePage::class.java)
                        startActivity(intent)
                        finish()
                    }
                } catch (ignored: JSONException) {
                    Log.d("error", "showJSON: " + ignored.stackTrace.toString())

                }
            }
        } catch (e: JSONException) {
            Log.d("error", "showJSON: " + e.stackTrace.toString())
        }
    }

}