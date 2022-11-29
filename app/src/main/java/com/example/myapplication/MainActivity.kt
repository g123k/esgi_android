package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ListBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val days = listOf(
            generateFakeProduct(),
            generateFakeProduct(),
            generateFakeProduct(),
            generateFakeProduct(),
            generateFakeProduct(),
        )

        binding.list.apply {
            //layoutManager = LinearLayoutManager(this@MainActivity,)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = ListAdapter(days, object : OnProductListener {
                override fun onClicked(product: Product, position: Int) {
                    Toast.makeText(this@MainActivity, "Product $position clicked", Toast.LENGTH_SHORT).show()
                }

            })
        }


    }
}

class ListAdapter(
    private val products: List<Product>,
    private val listener: OnProductListener,
) : RecyclerView.Adapter<DayViewHolder>() {

    override fun getItemCount(): Int = products.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_cell, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val product = products[position]
        holder.updateDay(product)

        holder.itemView.setOnClickListener {
            listener.onClicked(product, position)
        }
    }


}

class DayViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val productNameTv = v.findViewById<TextView>(R.id.product_item_name)
    private val productImage = v.findViewById<ImageView>(R.id.product_item_image)

    fun updateDay(product: Product) {
        productNameTv.text = product.name
        Glide.with(itemView).load(product.thumbnail).into(productImage)
    }
}

interface OnProductListener {
    fun onClicked(product: Product, position: Int)
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