package com.example.appautofin.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appautofin.activities.MainActivity
import com.example.appautofin.adapter.BanksAdapter
import com.example.appautofin.databinding.FragmentSecondBinding
import com.example.appautofin.model.Banks
import com.example.appautofin.providers.BanksProvider


class FragmentSecond : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var adapter: BanksAdapter
    private var myBankList: MutableList<Banks> =
        BanksProvider.banksList.toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fourth, container, false)
    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = (activity as MainActivity).activityContext

        binding.recyclerVBanksList.layoutManager = GridLayoutManager(context, 1)
        adapter = BanksAdapter(BanksProvider.banksList)
        binding.recyclerVBanksList.adapter = adapter

        binding.editTextFilter.addTextChangedListener {bankFilter ->
            val initBanks =
                myBankList.filter{
                        bank -> bank.name.lowercase().contains(bankFilter.toString().lowercase())
                }
            adapter.refreshBanks(initBanks)
            binding.recyclerVBanksList.adapter = adapter
        }
    }

}