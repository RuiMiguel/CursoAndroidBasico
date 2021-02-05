package com.everis.cursoandroidbasicosample.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.everis.cursoandroidbasicosample.R
import com.everis.cursoandroidbasicosample.databinding.ViewCarItemBinding
import com.everis.cursoandroidbasicosample.entities.Car

class CarsAdapter(
    val onClickCar: (Car) -> Unit = {}
) : ListAdapter<Car, CarsAdapter.CarViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Car>() {
            override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem.modelName == newItem.modelName
            }

            override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CarViewHolder(inflater.inflate(R.layout.view_car_item, parent, false))
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewCarItemBinding.bind(itemView)

        fun bind(item: Car) {
            binding.run {
                name.text = item.modelName
                image.load(item.imageUrl)
            }

            itemView.setOnClickListener { onClickCar(item) }
        }
    }
}