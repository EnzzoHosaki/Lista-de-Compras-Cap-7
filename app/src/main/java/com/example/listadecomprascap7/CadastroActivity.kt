package com.example.listadecomprascap7

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.livrokotlin.listadecompras.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {

    val COD_IMAGE = 101
    private var imageBitMap: Bitmap? = null

    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgFotoProduto.setOnClickListener {
            abrirGaleria()
        }

        binding.btnInserir.setOnClickListener {
            val nomeProduto = binding.txtProduto.text.toString()
            val qtdString = binding.txtQtd.text.toString()
            val valorString = binding.txtValor.text.toString()

            if (nomeProduto.isNotEmpty() && qtdString.isNotEmpty() && valorString.isNotEmpty()) {
                val quantidade = qtdString.toInt()
                val valor = valorString.toDouble()

                val prod = Produto(nomeProduto, quantidade, valor, imageBitMap)

                produtosGlobal.add(prod)

                binding.txtProduto.text.clear()
                binding.txtQtd.text.clear()
                binding.txtValor.text.clear()
                binding.imgFotoProduto.setImageResource(android.R.drawable.ic_menu_camera)

                finish()

            } else {
                binding.txtProduto.error = if (nomeProduto.isEmpty()) "Preencha o nome do produto" else null
                binding.txtQtd.error = if (qtdString.isEmpty()) "Preencha a quantidade" else null
                binding.txtValor.error = if (valorString.isEmpty()) "Preencha o valor" else null
            }
        }
    }

    private fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), COD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null && data.data != null) {
                val inputStream = contentResolver.openInputStream(data.data!!)
                imageBitMap = BitmapFactory.decodeStream(inputStream)
                binding.imgFotoProduto.setImageBitmap(imageBitMap)
            }
        }
    }
}