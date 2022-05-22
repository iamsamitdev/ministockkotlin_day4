package com.itgenius.ministockkotlin.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itgenius.ministockkotlin.adapter.ProductAdapter
import com.itgenius.ministockkotlin.databinding.FragmentProductBinding
import com.itgenius.ministockkotlin.network.RetrofitService
import com.itgenius.ministockkotlin.repository.MainRepository
import com.itgenius.ministockkotlin.viewmodel.MainViewModel
import com.itgenius.ministockkotlin.viewmodelfactory.MainViewModelFactory

class ProductFragment : Fragment() {

    // สร้างตัวแปรสำหรับการ binding
    private lateinit var binding: FragmentProductBinding

    // สร้างตัวแปรสำหรับเรียกใช้ viewModel
    private lateinit var viewModel: MainViewModel

    // สร้างตัวแปรสำหรับเรียกใช้ RetrofitService
    private val retrofitService = RetrofitService.getInstance()

    // สร้างตัวแปรสำหรบเรียกใช้ Adapter
    private val adapter = ProductAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    // เมื่อ View สร้างเสร็จแล้ว
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(MainRepository(retrofitService))
        ).get(MainViewModel::class.java)

        // create  layoutManager
        // val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)

        // pass it to recyclerView layoutManager
        // binding.recyclerview.layoutManager = layoutManager

        binding.recyclerview.adapter = adapter

        viewModel.productList.observe(viewLifecycleOwner, Observer {
            adapter.setProductList(it)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.d("Message","Error Call ViewModel")
        })

        viewModel.getAllProducts()

    }

}