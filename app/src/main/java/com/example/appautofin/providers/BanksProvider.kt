package com.example.appautofin.providers

import com.example.appautofin.R
import com.example.appautofin.model.Banks

class BanksProvider {
    companion object{
        val banksList = listOf<Banks>(
            Banks("Banco Autofin", "40 años", "El mejor banco para créditos automotrices", R.drawable.logo_banco_autofin),
            Banks("Citibanamex", "55 años", "Citibanamez, lo mejor de México, lo mejor del mundo.",  R.drawable.logo_banco_citibanamex),
            Banks("BBVA Bancomer", "70 años", "BBVA Bancomer, creando oportunidades",  R.drawable.logo_banco_bancomer),
            Banks("Banregio", "25 años", "Somos el banco de creadores", R.drawable.logo_banco_banregio),
            Banks("Banco Azteca", "50 años", "Banco Azteca. Siempre disponibles y en todos lados", R.drawable.logo_banco_azteca),
            Banks("Inbursa", "45 años", "Inbursa, El mejor rendimiento para tus movimientos", R.drawable.logo_banco_inbursa)
            )
    }
}