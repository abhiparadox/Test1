package com.example.test_1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class UserDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_user_detail,container,false)
        val user=arguments?.getParcelable<Users>("user")


        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        val tvAddress = view.findViewById<TextView>(R.id.tvAddress)
        val tvPhone = view.findViewById<TextView>(R.id.tvPhone)
        val tvWebsite = view.findViewById<TextView>(R.id.tvWebsite)
        val tvCompany = view.findViewById<TextView>(R.id.tvCompany)

        user?.let {
            tvName.text = "Name: ${it.name}"
            tvUsername.text = "Username: ${it.username}"
            tvEmail.text = "Email: ${it.email}"
            tvAddress.text = "Address: ${it.address.street}, ${it.address.suite}, ${it.address.city}, ${it.address.zipcode}, ${it.address.geo}"
            tvPhone.text = "Phone: ${it.phone}"
            tvWebsite.text = "Website: ${it.website}"
            tvCompany.text = "Company: ${it.company.name}, ${it.company.bs}, ${it.company.catchPhrase}"
        }


        return view
    }

    companion object {
        fun newInstance(user:Users):UserDetailFragment{
            val fragment=UserDetailFragment()
            val args=Bundle()
            args.putParcelable("user",user)
            fragment.arguments=args
            return fragment
        }
    }
}