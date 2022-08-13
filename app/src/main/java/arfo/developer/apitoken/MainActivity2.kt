package arfo.developer.apitoken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Toast.makeText(this, "$token", Toast.LENGTH_SHORT).show()

        RetrofitClient.getRetrofit().getOta(1,0,"arranged").enqueue(object : Callback<List<OtaModel>> {
            override fun onResponse(call: Call<List<OtaModel>>, response: Response<List<OtaModel>>) {
                Log.d("GET OTA", "onResponse: ${response.code()}")
                if (response.code() == 200) {
                    nomer.text = response.body()!![0].nomer.toString()
                    phone_number.text = response.body()!![0].costumer.costumer_phone_1
                }
                else Toast.makeText(this@MainActivity2, "Damizi oling toshizi o'ynen eshigzi tagida qunizi o'ynen", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<OtaModel>>, t: Throwable) {
                Log.e("GET OTA", "onFailure: ${t.localizedMessage}")
                Toast.makeText(this@MainActivity2, "Damizi oling toshizi o'ynen eshigzi tagida qunizi o'ynen", Toast.LENGTH_SHORT).show()
            }
        })
    }
}