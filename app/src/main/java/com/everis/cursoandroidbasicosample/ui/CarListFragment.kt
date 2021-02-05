package com.everis.cursoandroidbasicosample.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.everis.cursoandroidbasicosample.R
import com.everis.cursoandroidbasicosample.databinding.FragmentCarListBinding
import com.everis.cursoandroidbasicosample.entities.Car
import com.everis.cursoandroidbasicosample.network.NetworkDatasource
import com.everis.cursoandroidbasicosample.ui.adapter.CarsAdapter
import kotlinx.coroutines.runBlocking

class CarListFragment : Fragment() {
    private lateinit var binding: FragmentCarListBinding
    private var carsAdapter: CarsAdapter? = null
    private val networkDatasource = NetworkDatasource()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        initRecyclerView()
        initListeners()
        getLamborghiniDataFromServiceAsync()
    }

    private fun initListeners() {
        binding.exitButton.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.exit))
                .setMessage(getString(R.string.want_to_exit).toUpperCase())
                .setPositiveButton(
                    getString(R.string.ok), object : DialogInterface.OnClickListener {
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            activity?.finish()
                        }
                    })
                .setNegativeButton(getString(R.string.no).toUpperCase(), null).show()
        }
    }

    private fun initRecyclerView() {
        binding.run {
            progress.visibility = View.GONE

            carsAdapter = CarsAdapter { onCarClicked(it) }

            carList.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = carsAdapter
            }
        }
    }

    private fun onCarClicked(item: Car) {
        val fragment = CarDetailFragment().apply {
            arguments = Bundle().apply { putSerializable("CAR", item) }
        }
        (activity as CarActivity).addFragment(R.id.container_frame, fragment)
    }

    private fun getLamborghiniDataFromServiceAsync() {
        binding.progress.visibility = View.VISIBLE

        networkDatasource.getLamborghinisAsync(
            onSuccess = { carList ->
                binding.progress.visibility = View.GONE
                carsAdapter?.submitList(carList.cars.sortedBy { it.modelName })
            },
            onFailure = {
                binding.progress.visibility = View.GONE
                val alertDialog = AlertDialog.Builder(requireContext())
                    .setTitle("Error")
                    .setMessage("Se ha producido un error")
                    .setPositiveButton("OK", null)
                alertDialog.show()
            }
        )
    }

    private fun getLamborghiniDataFromServiceSync() {
        binding.progress.visibility = View.VISIBLE

        runBlocking {
            val list = networkDatasource.getLamborghinisSync()
            list?.let {
                binding.progress.visibility = View.GONE
                carsAdapter?.submitList(it.cars.sortedBy { car -> car.modelName })
            }
        }
    }
}