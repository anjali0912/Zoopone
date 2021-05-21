package com.example.zooponedemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zooponedemo.data.db.entity.Receivers
import com.example.zooponedemo.data.db.entity.SenderEntity
import com.example.zooponedemo.databinding.ItemContactsBinding
import com.example.zooponedemo.databinding.ItemOtpBinding
import com.example.zooponedemo.ui.contact.ContactViewModel

class OtpAdapter (private val viewModel: ContactViewModel) :
    RecyclerView.Adapter<OtpViewHolder>() {

    private var receivers = listOf<Receivers>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtpViewHolder {
        var binding = ItemOtpBinding.inflate(LayoutInflater.from(parent.context))
        return OtpViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtpViewHolder, position: Int) {
        holder.bind(receivers[position])
    }

    override fun getItemCount(): Int {
        return receivers.size
    }

    fun setContacts(results: List<Receivers>) {
        receivers = results
        notifyDataSetChanged()
    }

}

class OtpViewHolder(val binding: ItemOtpBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Receivers) {
        binding.receiver = item
    }
}