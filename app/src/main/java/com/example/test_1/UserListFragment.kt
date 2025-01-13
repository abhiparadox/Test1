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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var recyclerView: RecyclerView
    val apiService=ApiClient.retrofitBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//                recyclerView.adapter = UserAdapter(users) { user ->
//                    val bundle = Bundle().apply {
//                        putParcelable("user", user)
//                    }
//
//                    val userDetailFragment = UserDetailFragment().apply {
//                        arguments = bundle
//                    }
//
//                    parentFragmentManager.beginTransaction()
//                        .replace(R.id.main_activitylayout, userDetailFragment)
//                        .addToBackStack(null)
//                        .commit()
//                }

        }




//    @SuppressLint("MissingInflatedId")
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}