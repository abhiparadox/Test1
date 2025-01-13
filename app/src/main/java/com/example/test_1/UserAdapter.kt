package com.example.test_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val users:List<Users>,val onItemClick:(Users)->Unit):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view:View): RecyclerView.ViewHolder(view){
        val name:TextView=view.findViewById(R.id.tvName)
//        val email:TextView=view.findViewById(R.id.tvEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user=users[position]
        holder.name.text=user.name
//        holder.email.text=user.email
        holder.itemView.setOnClickListener{
            onItemClick(user)
        }
    }
}