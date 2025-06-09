package com.example.listadecomprascap7

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.AdapterView
import android.view.View
import java.text.NumberFormat
import java.util.Locale

import br.com.livrokotlin.listadecompras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val produtosAdapter = ProdutoAdapter(this)
        binding.listViewProdutos.adapter = produtosAdapter

        binding.btnAdicionar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        binding.listViewProdutos.setOnItemLongClickListener { adapterView: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = produtosAdapter.getItem(position)

            if (item != null) {
                produtosGlobal.remove(item)
                produtosAdapter.remove(item)
                produtosAdapter.notifyDataSetChanged()
                updateTotal() // Recalculate and update total
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        val adapter = binding.listViewProdutos.adapter as ProdutoAdapter
        adapter.clear()
        adapter.addAll(produtosGlobal)
        adapter.notifyDataSetChanged()

        updateTotal()
    }

    private fun updateTotal() {
        val sum = produtosGlobal.sumByDouble { it.valor * it.quantidade }
        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        binding.txtTotal.text = "TOTAL: ${f.format(sum)}"
    }
}