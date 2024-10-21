package com.elcha.uts.kuispencatatankeuangan;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TambahTransaksiActivity extends AppCompatActivity {

    private EditText etTanggal, etJumlah, etKeterangan;
    private Spinner spinnerKategori;
    private Button btnSimpan;
    private ActivityRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_transaksi);

        // Inisialisasi UI
        etTanggal = findViewById(R.id.et_tanggal);
        etJumlah = findViewById(R.id.et_jumlah);
        etKeterangan = findViewById(R.id.et_keterangan);
        spinnerKategori = findViewById(R.id.spinner_kategori);
        btnSimpan = findViewById(R.id.btn_simpan);

        // Inisialisasi repository
        repository = new ActivityRepository(this);

        // Aksi simpan transaksi
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggal = etTanggal.getText().toString();
                int jumlah = Integer.parseInt(etJumlah.getText().toString());
                String keterangan = etKeterangan.getText().toString();
                String kategori = spinnerKategori.getSelectedItem().toString();

                // Menyimpan transaksi
                repository.insertTransaksi(tanggal, jumlah, keterangan, kategori);

                // Menampilkan pesan sukses
                Toast.makeText(TambahTransaksiActivity.this, "Transaksi berhasil ditambahkan", Toast.LENGTH_SHORT).show();

                // Kembali ke main activity
                finish();
            }
        });
    }
}

