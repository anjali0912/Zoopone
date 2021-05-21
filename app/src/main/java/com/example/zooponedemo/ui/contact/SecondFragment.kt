package com.example.zooponedemo.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.zooponedemo.data.db.entity.SenderEntity
import com.example.zooponedemo.databinding.FragmentRecyclerviewBinding
import com.example.zooponedemo.databinding.FragmentSecondBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondFragment : Fragment() {
    /**
     * Binder to bind data with the view.
     */
    private lateinit var binding: FragmentSecondBinding

    /**
     * PeopleViewModel injected bu dependency injection.
     */
    private val viewModel by viewModel<ContactViewModel>()

    private val safeArgs: SecondFragmentArgs by navArgs()
    lateinit var sender: SenderEntity
    lateinit var randomNumber: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        sender = safeArgs.arg
        binding.sender = sender

        randomNumber = viewModel.getRandomNumberString().toString()
        var randNumber = randomNumber.toCharArray()
        binding.tvOne.text = randNumber.get(0).toString()
        binding.tvTwo.text = randNumber.get(1).toString()
        binding.tvThree.text = randNumber.get(2).toString()
        binding.tvFour.text = randNumber.get(3).toString()
        binding.tvFive.text = randNumber.get(4).toString()
        binding.tvSix.text = randNumber.get(5).toString()

        binding.btnProceed.setOnClickListener {
            val directions = SecondFragmentDirections.navigateToThirdFragment(arg1 = sender,
                arg2 = randomNumber)
            findNavController().navigate(directions)
        }
    }
}