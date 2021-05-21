package com.example.zooponedemo.ui.contact

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.zooponedemo.data.db.entity.Receivers
import com.example.zooponedemo.data.db.entity.SenderEntity
import com.example.zooponedemo.databinding.FragmentSecondBinding
import com.example.zooponedemo.databinding.FragmentThirdBinding
import com.example.zooponedemo.util.Mapper.SIMPLE_DATE_FORMAT
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.sql.Timestamp
import java.text.SimpleDateFormat

class ThirdFragment : Fragment() {
    /**
     * Binder to bind data with the view.
     */
    private lateinit var binding: FragmentThirdBinding

    /**
     * PeopleViewModel injected bu dependency injection.
     */
    private val viewModel by viewModel<ContactViewModel>()

    private val safeArgs: ThirdFragmentArgs by navArgs()
    lateinit var sender: SenderEntity
    lateinit var randomNumber: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        sender = safeArgs.arg1
        randomNumber = safeArgs.arg2
        binding.sender = sender

        var randNumber = randomNumber.toCharArray()
        binding.tvOne.text = randNumber.get(0).toString()
        binding.tvTwo.text = randNumber.get(1).toString()
        binding.tvThree.text = randNumber.get(2).toString()
        binding.tvFour.text = randNumber.get(3).toString()
        binding.tvFive.text = randNumber.get(4).toString()
        binding.tvSix.text = randNumber.get(5).toString()

        binding.btnProceed.setOnClickListener {
            if(binding.etMsg.text.isNotEmpty()){
                val timestamp = Timestamp(System.currentTimeMillis())
                val currentTime = SIMPLE_DATE_FORMAT.format(timestamp)
                val msg = binding.etMsg.text

                var receivers = Receivers(randomNumber.toInt(),binding.etMsg.text.toString(),
                    currentTime.toString())
                sender.receivers.add(receivers)
                viewModel.insertReceiver(sender)

                Toast.makeText(context,"inserted successfully", Toast.LENGTH_SHORT).show()
                val directions = ThirdFragmentDirections.navigateToFirstFragment()
                findNavController().navigate(directions)
            }
            else{
                Toast.makeText(context,"please enter message", Toast.LENGTH_SHORT).show()
            }
        }
    }
}