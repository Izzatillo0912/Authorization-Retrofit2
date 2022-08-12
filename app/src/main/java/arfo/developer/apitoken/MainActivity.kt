package arfo.developer.apitoken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var token = ""
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitClient.getRetrofit().checkUser("agent","agent").enqueue(object : Callback<LoginModel>{
            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                Log.d("Response", "onResponse: ${response.code()}")
                if (response.code() == 200){
                    Toast.makeText(this@MainActivity, "True", Toast.LENGTH_SHORT).show()
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