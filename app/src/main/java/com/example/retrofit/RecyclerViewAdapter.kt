package com.example.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(var context: Context, var arr: List<User1?>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    // Binds the given View to the position. The View can be a View previously retrieved via onCreateViewHolder it whould be iterate for all item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.cart_item_layout, parent, false)
        return ViewHolder(view)
    }

    //will be used to display items of the adapter using onBindViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var user : User1? = arr?.get(position)
       // val user = arr?.get(position)
        holder.id.text = user?.id.toString()
        holder.employee_name.text = user?.postId.toString()
        holder.employee_age.text = user?.title.toString()
        holder.employee_salary.text = user?.body.toString()
    }

    override fun getItemCount(): Int {
        return arr?.size!!
    }

    // it wholud be initialize the layout views so that we can access easly in onBindViewHolder method
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView
        var employee_name: TextView
        var employee_age: TextView
        var employee_salary: TextView

        init {
            id = itemView.findViewById(R.id.id)
            employee_name = itemView.findViewById(R.id.name)
            employee_age = itemView.findViewById(R.id.age)
            employee_salary = itemView.findViewById(R.id.salary)
        }
    }
}