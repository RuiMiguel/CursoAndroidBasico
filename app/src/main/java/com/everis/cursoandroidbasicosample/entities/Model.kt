package com.everis.cursoandroidbasicosample.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CarList(
    @SerializedName("cars") var cars: List<Car>
)

data class Car(
    @SerializedName("carColorId") var colorId: String,
    @SerializedName("carImageUrl") var imageUrl: String,
    @SerializedName("carModelName") val modelName: String,
    @SerializedName("carSpecs") val specs: CarFeatures
) : Serializable

data class CarFeatures(
    @SerializedName("carHorsepower") var horsepower: String,
    @SerializedName("carSalePrice") var salePrice: String,
    @SerializedName("carWeight") var weight: String
)