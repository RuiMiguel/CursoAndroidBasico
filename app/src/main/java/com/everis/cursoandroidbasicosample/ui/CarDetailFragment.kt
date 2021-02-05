package com.everis.cursoandroidbasicosample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.everis.cursoandroidbasicosample.R
import com.everis.cursoandroidbasicosample.databinding.FragmentCarDetailBinding
import com.everis.cursoandroidbasicosample.entities.Car

class CarDetailFragment : Fragment(R.layout.fragment_car_detail) {
    private lateinit var binding: FragmentCarDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (arguments?.get("CAR") as Car?)?.let { car ->
            binding.run {
                with(car) {
                    title.text = modelName
                    weight.text = specs.weight
                    power.text = specs.horsepower
                    price.text = specs.salePrice
                }
            }
        }
    }
}