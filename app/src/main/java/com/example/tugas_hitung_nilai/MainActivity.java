package com.example.tugas_hitung_nilai; //deklarasi file

//import paket class yg akan digunakan

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button; //deklarasi variable Button yg diberi id button pada xml
    TextView nilaiAKhir; //deklarasi variable TextView yg diberi id nilaiAkhir pada xml
    TextView grade; //deklarasi variable TextView yg diberi id grade pada xml
    EditText keaktifan; //deklarasi variable EditText yg diberi id keaktifan pada xml
    EditText uts; // deklarasi variable EditText yg sudah diberi id uts pada xml
    EditText uas; //deklarasi variable EditText yg sudah diberi id uas pada xml
    EditText quis;

    //menginisialisasikan variable-variable dalam metode onCreate, untuk mengakses dan memanipulasi elemen-elemen selama aktifitas berlangsung
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //menentukan layout yg akan ditampilkan dilayar yaitu dengan layout activity_main

        nilaiAKhir = findViewById(R.id.nilaiAkhir); //menemukan id dari elemen TextView nilaiAkhir dan menetapkannya ke variable 'nilaiAKhir'
        grade = findViewById(R.id.grade);
        button = findViewById(R.id.button);
        keaktifan = findViewById(R.id.keaktifan);
        uts = findViewById(R.id.uts);
        uas = findViewById(R.id.uas);
        quis = findViewById(R.id.quis);

        //mengatur setting button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            //metode onClick yg akan dipanggil jika butoon di klik
            public void onClick(View view) {
                //metode yg mendapatkan text masuk dari EditTeks dengan nama id variable dan menyimpannya ke variable string
                String keaktifanStr = keaktifan.getText().toString();
                String utsStr = uts.getText().toString();
                String uasStr = uas.getText().toString();
                String quisStr = quis.getText().toString();

                //kondisional untuk memeriksa jika salah satu inputan kosong/null
                if (keaktifanStr.isEmpty() || utsStr.isEmpty() || uasStr.isEmpty() || quisStr.isEmpty()){
                    nilaiAKhir.setText("value not valid"); //nilai dari nilaiAkhir dan grade tidak ada jika salah satu inputan kosong
                    grade.setText("value not valid");
                    Toast.makeText(getApplicationContext(), "All values must be filled", Toast.LENGTH_SHORT).show();
                    return; //jika salah satu inputan kosong program akan berhenti di sini, || = or
                }

                //menghitung nilai di setiap komponen, yg mengkonversikan text yg diambil dari variable string menjadi tipe data int.
                double nilaiKeaktifan = Integer.parseInt(keaktifanStr) * 0.10; //hasil dari perhitungan akan disimpan dalam variable 'nilaiKeaktifan' dengan type data double
                double nilaiQuis = Integer.parseInt(quisStr) * 0.15; //hasil dari perhitungan akan disimpan dalam variable 'nilaiTugas' dengan type data double
                double nilaiUts = Integer.parseInt(utsStr) * 0.30; //hasil dari perhitungan akan disimpan dalam variable 'nilaiUts' dengan type data double
                double nilaiUas = Integer.parseInt(uasStr) * 0.45; //hasil dari perhitungan akan disimpan dalam variable 'nilaiUas' dengan type data double

                //blok try-catch menangani kesalahan yg terjadi saat menghitung nilai akhir dan menampilkan nilai/grade
                try {
                    double value = nilaiKeaktifan + nilaiQuis + nilaiUts + nilaiUas; //perhitungan nilaiAkhir hasilnya disimpan dalam variable value
                    nilaiAKhir.setText(String.valueOf(value)); //pengatur TextView 'nilaiAkhir' untuk menampilkan nilai akhir, menggunakan string untuk
                    // mengkonversikan nilai 'value' ke dalam format string
                    //menentukan grade berdasarkan hasil 'value'
                    if (value >= 85) {
                        grade.setText("Grade: A");
                    } else if (value >= 75 && value < 85) {
                        grade.setText("Grade: B");
                    } else if (value >= 65 && value < 75) {
                        grade.setText("Grade: C");
                    } else {
                        grade.setText("Grade: D");
                    }
                //akan menangkap error dimana jika terjadi kesalahan saat mengkonversi nilai inputan selain angka
                } catch (NumberFormatException error) {
                    nilaiAKhir.setText(""); //akan menghapus text dari 'nilaAkhir'
                    grade.setText("Grade not exist"); //menampilkan pesan
                }

            }
        });
    }
}