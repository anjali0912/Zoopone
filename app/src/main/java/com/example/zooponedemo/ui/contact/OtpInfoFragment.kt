package com.example.zooponedemo.ui.contact

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.zooponedemo.adapters.ContactsAdapter
import com.example.zooponedemo.adapters.OtpAdapter
import com.example.zooponedemo.adapters.TabAdapter
import com.example.zooponedemo.data.db.entity.Receivers
import com.example.zooponedemo.data.db.entity.SenderEntity
import com.example.zooponedemo.databinding.FragmentContactBinding
import com.example.zooponedemo.databinding.FragmentRecyclerviewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OtpInfoFragment : Fragment() {
    /**
     * Binder to bind data with the view.
     */
    private lateinit var binding: FragmentRecyclerviewBinding

    /**
     * PeopleViewModel injected bu dependency injection.
     */
    private val viewModel by viewModel<ContactViewModel>()

    /**
     * Converts the simple data into view and set to the recycler view.
     */
    private lateinit var adapter: OtpAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observerReceiver()
    }

    private fun initView() {
        adapter = OtpAdapter(viewModel)
        binding.recyclerView.adapter = adapter
    }

    /**
     * Observes the receiver data and set to the recycler view.
     */
    private fun observerReceiver() {
        viewModel.contacts.observe(viewLifecycleOwner, Observer<List<SenderEntity>> {
            var receiverList = it.filter {
                it.receivers.size != 0
            }
            if (!receiverList.isNullOrEmpty()) {
                binding.tvEmpty.visibility = View.GONE
            }
            var recList = ArrayList<Receivers>()
            for (item in receiverList) {
                recList.addAll(item.receivers)
            }
            adapter.setContacts(recList)
        })
    }
}