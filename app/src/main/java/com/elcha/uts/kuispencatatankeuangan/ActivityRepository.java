package com.elcha.uts.kuispencatatankeuangan;
import android.content.Context;

import java.util.List;

public class ActivityRepository {

    private DatabaseHelper dbHelper;

    public ActivityRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Fungsi untuk menambah transaksi ke dalam database
    public void insertTransaksi(String tanggal, int jumlah, String keterangan, String kategori) {
        dbHelper.insertTransaksi(tanggal, jumlah, keterangan, kategori);
    }

    // Fungsi untuk mendapatkan semua transaksi
    public List<String> getAllTransaksi() {
        return dbHelper.getAllTransaksi();
    }

    // Fungsi untuk menghapus transaksi berdasarkan ID
    public void deleteTransaksi(int id) {
        dbHelper.deleteTransaksi(id);
    }
}
