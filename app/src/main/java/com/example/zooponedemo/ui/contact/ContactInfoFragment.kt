package com.example.zooponedemo.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.zooponedemo.adapters.ContactsAdapter
import com.example.zooponedemo.data.db.entity.Receivers
import com.example.zooponedemo.data.db.entity.SenderEntity
import com.example.zooponedemo.databinding.FragmentRecyclerviewBinding
import org.json.JSONArray
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactInfoFragment : Fragment() {
    /**
     * PeopleViewModel injected bu dependency injection.
     */
    private val viewModel by viewModel<ContactViewModel>()

    /**
     * Binder to bind data with the view.
     */
    private lateinit var binding: FragmentRecyclerviewBinding

    /**
     * Converts the simple data into view and set to the recycler view.
     */
    private lateinit var adapter: ContactsAdapter

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
        observerPeoples()
    }

    private fun initView() {
        adapter = ContactsAdapter(viewModel, { selectedSenderItem: SenderEntity ->
            listItemClicked(selectedSenderItem)
        })
        binding.recyclerView.adapter = adapter
    }

    /**
     * Observes the peoples data and set to the recycler view.
     */
    private fun observerPeoples() {
        viewModel.contacts.observe(viewLifecycleOwner, Observer<List<SenderEntity>> {
            if (!it.isNullOrEmpty()) {
                binding.tvEmpty.visibility = View.GONE
            }
            adapter.setContacts(it)
        })
    }

    private fun listItemClicked(sender: SenderEntity) {
        val directions = ContactFragmentDirections.navigateToContactDetail(arg = sender)
        findNavController().navigate(directions)
    }
}