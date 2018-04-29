package com.example.junyee.hackathon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText nameText;
    private EditText phoneText;
    private EditText emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;
        nameText = (EditText) this.findViewById(R.id.nameText);
        phoneText = (EditText) this.findViewById(R.id.phoneText);
        emailText = (EditText) this.findViewById(R.id.emailText);
        button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String text2Qr = nameText.getText().toString();
//                String text2Qr = "BIZCARD:N:Sean;X:Owen;T:Software Engineer;C:Google;A:76 9th Avenue, New York, NY";
                String text2Qr = "MECARD:N:" + nameText.getText().toString() + ";TEL:" + phoneText.getText().toString() + ";EMAIL:"+ emailText.getText().toString()+ ";";
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    Intent intent = new Intent(context, QrActivity.class);
                    intent.putExtra("pic", bitmap);
                    context.startActivity(intent);
                } catch (WriterException e){
                    e.printStackTrace();
                }
            }
        });
    }
}