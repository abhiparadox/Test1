package com.example.test_1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListFragment : Fragment() {
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

    val users = ApiClient.api.getData()
    users.enqueue(object : Callback<List<Users>>{
        override fun onResponse(
            call: Call<List<Users>>,
            response: Response<List<Users>>
        ) {
            val user=response.body()?:return
            val adapter=UserAdapter(user){
                    user ->
                val userDetailFragment=UserDetailFragment.newInstance(user)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_activitylayout,userDetailFragment)
                    .addToBackStack(null)
                    .commit()
            }
            recyclerView.adapter=adapter
        }

        override fun onFailure(call: Call<List<Users>>, t: Throwable) {
            Toast.makeText(context,"Fail to fetch",Toast.LENGTH_SHORT).show()
        }
    })

        return view
    }
}