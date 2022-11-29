package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list)

        val days = listOf(
            "Lundi",
            "Mardi",
            "Mercredi",
            "Mardi",
            "Lundi",
            "Mardi",
            "Lundi",
            "Mardi",
            "Lundi","Mardi",
            "Lundi",
            "Mardi",
            "Lundi",
            "Mardi",
            "Lundi",
            "Mardi", "Lundi",
            "Mardi",
            "Lundi",
            "Mardi",
            "Lundi",
            "Mardi",
            "Lundi",
            "Mardi",
            "Lundi",
            "Mardi",
        )

        findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = LinearLayoutManager(this@MainActivity,)
            //layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = ListAdapter(days)
        }

    }
}

class ListAdapter(val days: List<String>) : RecyclerView.Adapter<DayViewHolder>() {


    override fun getItemCount(): Int = days.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_cell, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.updateDay(
            days[position]
        )
    }


}

class DayViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val dayOfWeekTextView = v.findViewById<TextView>(R.id.day_of_week)

    fun updateDay(day: String) {
        dayOfWeekTextView.text = day
    }

}

data class Product(
    val name: String,
    val brand: String,
    val nutriScore: NutriScore,
    val barcode: String,
    val thumbnail: String,
    val quantity: String,
    val countries: List<String>,
    val ingredients: List<String>,
    val allergens: List<String>,
    val additives: List<String>,
)

enum class NutriScore(val text: String) {
    A("A"), B("B"), C("C"), D("D"), E("E")
}


fun generateFakeProduct() = Product(
    name = "Petits pois et carottes",
    brand = "Cassegrain",
    nutriScore = NutriScore.A,
    barcode = "3083680085304",
    thumbnail = "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg",
    quantity = "400g",
    countries = listOf("France", "Japon", "Suisse"),
    ingredients = listOf(
        "Petits pois 66%",
        "Eau",
        "Garniture 2,8% (salade, oignon grelot)",
        "Sucre",
        "Sel",
        "Arôme naturel"
    ),
    allergens = emptyList(),
    additives = emptyList(),
)