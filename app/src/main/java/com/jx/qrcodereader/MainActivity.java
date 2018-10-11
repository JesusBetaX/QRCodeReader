package com.jx.qrcodereader;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blikoon.qrcodescanner.QrCodeActivity;

public class MainActivity extends AppCompatActivity {
    /**
     * Es un número único, ya que siempre podremos hacer peticiones, pero esas peticiones serán
     * hechas con un número único para que cuando respondan podamos ver a cuál de todas están
     * respondiendo. En este caso sólo haremos una, pero puede que luego agreguemos más.
     */
    private static final int REQUEST_CODE_QR_SCAN = 101;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent i = new Intent(MainActivity.this, QrCodeActivity.class);
        startActivityForResult(i, REQUEST_CODE_QR_SCAN);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(getApplicationContext(), "No se pudo obtener una respuesta", Toast.LENGTH_SHORT).show();
            String resultado = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (resultado != null) {
                Toast.makeText(getApplicationContext(), "No se pudo escanear el código QR", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data != null) {
                String lectura = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
                Toast.makeText(getApplicationContext(), "Leído: " + lectura, Toast.LENGTH_SHORT).show();

            }
        }
    }

}
