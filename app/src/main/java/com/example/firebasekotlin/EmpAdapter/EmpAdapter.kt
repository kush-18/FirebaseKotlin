package com.example.firebasekotlin.EmpAdapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasekotlin.R
import com.example.firebasekotlin.models.EmployeeModel

class EmpAdapter(private val empList: ArrayList<EmployeeModel>) : RecyclerView.Adapter<EmpAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.emp_list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return empList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = empList[position]
        holder.tvEmpName.text = currentEmp.empName
    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val tvEmpName : TextView = itemView.findViewById(R.id.tvEmpName)



    }
}