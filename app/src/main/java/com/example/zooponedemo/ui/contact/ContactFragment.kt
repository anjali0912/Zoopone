package com.example.zooponedemo.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.zooponedemo.adapters.TabAdapter
import com.example.zooponedemo.databinding.FragmentContactBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactFragment : Fragment() {
    /**
     * Binder to bind data with the view.
     */
    private lateinit var binding: FragmentContactBinding

    /**
     * Hold instance of tab adapter.
     */
    private lateinit var tabAdapter: TabAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        tabAdapter.addFragment(ContactInfoFragment(), "Contacts")
        tabAdapter.addFragment(OtpInfoFragment(), "Otp")
        binding.viewpager.adapter = tabAdapter
        binding.tabs.setupWithViewPager(binding.viewpager)
    }

    private fun initView() {
        tabAdapter = TabAdapter(childFragmentManager)
    }
}