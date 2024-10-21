package com.elcha.uts.kuispencatatankeuangan;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvTransaksi;
    private FloatingActionButton fabTambah;
    private ActivityRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi UI
        lvTransaksi = findViewById(R.id.lv_transaksi);
        fabTambah = findViewById(R.id.fab_tambah);

        // Inisialisasi Repository
        repository = new ActivityRepository(this);

        // Menampilkan daftar transaksi
        loadTransaksi();

        // Aksi tombol tambah transaksi
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Berpindah ke activity tambah transaksi
                Intent intent = new Intent(MainActivity.this, TambahTransaksiActivity.class);
                startActivity(intent);
            }
        });
    }

    // Fungsi untuk memuat transaksi dan menampilkan di ListView
    private void loadTransaksi() {
        List<String> transaksiList = repository.getAllTransaksi();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, transaksiList);
        lvTransaksi.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Memperbarui daftar transaksi ketika kembali dari activity tambah transaksi
        loadTransaksi();
    }
}
