package com.elcha.uts.kuispencatatankeuangan;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "transaksi.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TRANSAKSI = "transaksi";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TANGGAL = "tanggal";
    private static final String COLUMN_JUMLAH = "jumlah";
    private static final String COLUMN_KETERANGAN = "keterangan";
    private static final String COLUMN_KATEGORI = "kategori";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_TRANSAKSI + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TANGGAL + " TEXT, " +
            COLUMN_JUMLAH + " INTEGER, " +
            COLUMN_KETERANGAN + " TEXT, " +
            COLUMN_KATEGORI + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSAKSI);
        onCreate(db);
    }

    // Menambahkan transaksi baru
    public void insertTransaksi(String tanggal, int jumlah, String keterangan, String kategori) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TANGGAL, tanggal);
        values.put(COLUMN_JUMLAH, jumlah);
        values.put(COLUMN_KETERANGAN, keterangan);
        values.put(COLUMN_KATEGORI, kategori);
        db.insert(TABLE_TRANSAKSI, null, values);
    }

    // Mendapatkan semua transaksi
    public List<String> getAllTransaksi() {
        List<String> transaksiList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TRANSAKSI, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int tanggalIndex = cursor.getColumnIndex(COLUMN_TANGGAL);
                int jumlahIndex = cursor.getColumnIndex(COLUMN_JUMLAH);
                int keteranganIndex = cursor.getColumnIndex(COLUMN_KETERANGAN);
                int kategoriIndex = cursor.getColumnIndex(COLUMN_KATEGORI);

                // Pastikan semua index kolom valid
                if (tanggalIndex != -1 && jumlahIndex != -1 && keteranganIndex != -1 && kategoriIndex != -1) {
                    String transaksi = cursor.getString(tanggalIndex) + " - " +
                        cursor.getInt(jumlahIndex) + " - " +
                        cursor.getString(keteranganIndex) + " (" +
                        cursor.getString(kategoriIndex) + ")";
                    transaksiList.add(transaksi);
                } else {
                    Log.e("Database Error", "Kolom tidak ditemukan di cursor");
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return transaksiList;
    }


    // Menghapus transaksi berdasarkan ID
    public void deleteTransaksi(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRANSAKSI, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }
}
