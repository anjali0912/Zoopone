package com.example.zooponedemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.zooponedemo.data.db.entity.SenderEntity
import com.example.zooponedemo.databinding.ItemContactsBinding
import com.example.zooponedemo.ui.contact.ContactViewModel

class ContactsAdapter (private val viewModel: ContactViewModel,
                       private val clickListener: (SenderEntity) -> Unit) :
    RecyclerView.Adapter<MyViewHolder>() {

    private var senderEntity = listOf<SenderEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = ItemContactsBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(senderEntity[position], clickListener)
    }

    override fun getItemCount(): Int {
        return senderEntity.size
    }

    fun setContacts(results: List<SenderEntity>) {
        senderEntity = results
        notifyDataSetChanged()
    }

}

class MyViewHolder(val binding: ItemContactsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: SenderEntity, clickListener: (SenderEntity) -> Unit) {
        binding.sender = item
        binding.layoutMain.setOnClickListener {
            clickListener(item)
        }
    }
}