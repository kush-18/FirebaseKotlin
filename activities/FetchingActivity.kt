package com.example.firebasekotlin.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasekotlin.EmpAdapter.EmpAdapter
import com.example.firebasekotlin.R
import com.example.firebasekotlin.models.EmployeeModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class FetchingActivity : AppCompatActivity() {

    private lateinit var dbRef : DatabaseReference

    private lateinit var empList: ArrayList<EmployeeModel>

    private lateinit var empRecyclerView : RecyclerView

    private lateinit var tvLoadingData : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        empRecyclerView = findViewById(R.id.rvEmployee)


        tvLoadingData = findViewById(R.id.tvLoadingData)

        empRecyclerView.layoutManager = LinearLayoutManager(this)

        empRecyclerView.setHasFixedSize(true)



        empList = arrayListOf<EmployeeModel>()



        getEmployeesData()


    }

    private fun getEmployeesData() {
        empRecyclerView.visibility = View.GONE

        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Employees")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if(snapshot.exists())
                {
                    for (empSnap in snapshot.children)
                    {
                        val empData = empSnap.getValue(EmployeeModel::class.java)
                        empList.add(empData!!)
                    }

                    val mAdapter = EmpAdapter(empList)
                    empRecyclerView.adapter = mAdapter

                    empRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}