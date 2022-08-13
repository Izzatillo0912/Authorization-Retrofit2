package arfo.developer.apitoken

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var token = ""
class MainActivity : AppCompatActivity() {
    lateinit var login:EditText
    lateinit var password:EditText
    lateinit var btn:Button
    val dataSources  = DataSources()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn= findViewById(R.id.btnLog)
        login= findViewById(R.id.log)
        password= findViewById(R.id.pas)

        checkUser()

        btn.setOnClickListener {
            RetrofitClient.getRetrofit().checkUser("${login.text}","${password.text}").enqueue(object : Callback<LoginModel>{
                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    Log.d("Response", "onResponse: ${response.code()}")
                    if (response.code() == 200){
                        Toast.makeText(this@MainActivity, "True", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity,MainActivity2::class.java))
                        dataSources.setLogin(this@MainActivity,true)
                        dataSources.saveToken(this@MainActivity,response.body()!!.access_token)
                    }
                    else{
                        Toast.makeText(this@MainActivity, "false", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    Log.d("", "onFailure: ${t.localizedMessage}")
                    Toast.makeText(this@MainActivity, "False failure", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    fun checkUser(){
        if (dataSources.isLogin(this)){
            startActivity(Intent(this@MainActivity,MainActivity2::class.java))
            token = dataSources.getToken(this)
        }
    }
}