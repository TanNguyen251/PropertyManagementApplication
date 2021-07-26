package com.example.propertymanagementapplication.remotedatasrouce

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.propertymanagementapplication.models.RegisterUserResponse
import com.example.propertymanagementapplication.models.User
import com.example.propertymanagementapplication.repository.RetrofitProperty
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.adapter.rxjava2.HttpException

class RemoteData {
    var disposable: Disposable? = null

    fun postUser(user: User): MutableLiveData<RegisterUserResponse> {
        val userLiveData: MutableLiveData<RegisterUserResponse> = MutableLiveData()
        val postRequest = RetrofitProperty.getRetrofitClient().registerUser(user)
        postRequest
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(getUserObserver(userLiveData))
        /*postRequest.enqueue(object: Callback<RegisterUserResponse> {
            override fun onResponse(
                call: Call<RegisterUserResponse>,
                response: Response<RegisterUserResponse>
            ) {
                //Log.d("abc", response.body().toString())

                if (response.isSuccessful) {
                    liveData.value = response.body()
                } else {
                    val errorResponseJson = JSONObject(response.errorBody()?.string())
                    Log.d("abc", errorResponseJson.toString())
                    liveData.value = RegisterUserResponse(null, errorResponseJson.getBoolean("error"), errorResponseJson.getString("message"))
                }
            }

            override fun onFailure(call: Call<RegisterUserResponse>, t: Throwable) {
                Log.d("abc", "Unable to register user")
                liveData.value = RegisterUserResponse(null, false, t.message?:"Unable to register")
            }

        })*/
        return userLiveData
    }

    fun getUserObserver(liveData: MutableLiveData<RegisterUserResponse>): SingleObserver<RegisterUserResponse>{
        return object: SingleObserver<RegisterUserResponse>{
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onSuccess(t: RegisterUserResponse) {
                liveData.value = t
                Log.d("abc","register success user: ${t.data?.name}")

                disposable?.dispose()
            }

            override fun onError(e: Throwable) {

                Log.d("abc", "Unable to register user")
                val errorResponseJson = JSONObject((e as HttpException).response()?.errorBody()?.string())
                //Log.d("abc", errorResponseJson.toString())
                liveData.value = RegisterUserResponse(null, errorResponseJson.getBoolean("error"), errorResponseJson.getString("message"))
                //liveData.value = RegisterUserResponse(null, true, e.message?:"Unable to register")
            }
        }
    }

}