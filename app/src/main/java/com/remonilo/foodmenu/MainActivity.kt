package com.remonilo.foodmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.remonilo.foodmenu.ui.theme.FoodMenuTheme

// 1. "Cetakan" data untuk satu menu makanan.
//    Setiap MenuItem punya emoji, nama, deskripsi singkat, dan harga.
data class MenuItem(
    val emoji: String,
    val name: String,
    val description: String,
    val price: String
)

// 2. Daftar menu masih statis (hardcode dulu), belum dari internet/database.
//    Ini cukup untuk belajar dasar Compose: state, list, dan layout.
val sampleMenu = listOf(
    MenuItem("🍔", "Burger Sapi", "Daging sapi, keju, selada segar", "Rp 25.000"),
    MenuItem("🍕", "Pizza Margherita", "Saus tomat, mozzarella, basil", "Rp 45.000"),
    MenuItem("🍜", "Mie Ayam", "Mie, ayam suwir, pangsit goreng", "Rp 18.000"),
    MenuItem("🍛", "Nasi Goreng Spesial", "Nasi goreng, telur, ayam, kerupuk", "Rp 20.000"),
    MenuItem("🥗", "Salad Sayur", "Sayuran segar dengan dressing madu", "Rp 15.000"),
    MenuItem("🍰", "Kue Coklat", "Kue coklat lembut dengan topping ceres", "Rp 12.000"),
    MenuItem("🧋", "Es Teh Manis", "Teh manis dingin segar", "Rp 8.000")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodMenuTheme {
                FoodMenuApp()
            }
        }
    }
}

// 3. Layar utama: judul di atas (TopAppBar) + daftar menu (LazyColumn) di bawah.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodMenuApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Food Menu") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        // LazyColumn = versi "hemat" dari Column, hanya me-render item yang
        // terlihat di layar. Cocok untuk daftar yang panjang seperti menu ini.
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(sampleMenu) { menu ->
                MenuCard(menu)
            }
        }
    }
}

// 4. Satu "kartu" untuk menampilkan 1 item menu.
//    Dipisah jadi Composable sendiri supaya mudah dibaca & dipakai ulang.
@Composable
fun MenuCard(menu: MenuItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Emoji dipakai sebagai "gambar" sederhana, tanpa perlu load dari internet.
            Text(
                text = menu.emoji,
                fontSize = 36.sp,
                modifier = Modifier.padding(end = 16.dp)
            )
            Column {
                Text(
                    text = menu.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = menu.description,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = menu.price,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodMenuAppPreview() {
    FoodMenuTheme {
        FoodMenuApp()
    }
}
