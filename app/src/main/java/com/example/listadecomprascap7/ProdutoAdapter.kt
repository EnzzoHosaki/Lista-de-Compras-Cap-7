package com.example.listadecomprascap7

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import java.util.Locale

class ProdutoAdapter(contexto: Context) : ArrayAdapter<Produto>(contexto, 0) { // Specify type <Produto>

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v: View

        if (convertView != null) {
            v = convertView
        } else {
            // Inflate the custom layout for the list item
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)
        }

        // Get the current product item
        val item = getItem(position) // getItem() now returns Produto type

        // Access the views within the custom layout
        val txtProduto = v.findViewById<TextView>(R.id.txt_item_produto)
        val txtQtd = v.findViewById<TextView>(R.id.txt_item_qtd)
        val txtValor = v.findViewById<TextView>(R.id.txt_item_valor)
        val imgFoto = v.findViewById<ImageView>(R.id.img_item_foto)

        // Populate the views with product data
        if (item != null) { // Ensure item is not null
            txtProduto.text = item.nome
            txtQtd.text = "Qtd: ${item.quantidade}"

            // Format the value as Brazilian currency
            val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            txtValor.text = f.format(item.valor)

            // Set the product photo if available, otherwise set a default image
            if (item.foto != null) {
                imgFoto.setImageBitmap(item.foto)
            } else {
                // Set a default image if no photo is provided
                imgFoto.setImageResource(android.R.drawable.ic_menu_gallery)
            }
        }

        return v
    }
}