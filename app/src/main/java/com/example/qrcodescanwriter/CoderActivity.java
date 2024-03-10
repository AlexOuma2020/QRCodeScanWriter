package com.example.qrcodescanwriter;

import android.content.ContentValues;

import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileNotFoundException;
import java.io.OutputStream;



public class CoderActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button genQR;
    private Bitmap bitmap;
    private Button shareButton;
    private EditText editor;
    private TextView imgText;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coder);

        imageView = findViewById(R.id.imgview);
        imgText = findViewById(R.id.imgText);
        imgText.setVisibility(View.VISIBLE);
        genQR = findViewById(R.id.generate);
        shareButton = findViewById(R.id.sharedBut);
        editor = findViewById(R.id.edittxt);
        shareButton = findViewById(R.id.sharedBut);
        shareButton.setEnabled(false);

        shareButton.setOnClickListener(v -> {
            saveQR(bitmap);
            Toast.makeText(CoderActivity.this, R.string.saveQR, Toast.LENGTH_LONG).show();
        });

        genQR.setOnClickListener(v -> {
            if (!editor.getText().equals("")){
                imgText.setVisibility(View.GONE);
                bitmap = QRCodeGenerator.generate(editor.getText().toString());
                imageView.setImageBitmap(bitmap);
                shareButton.setEnabled(true);
            }
            else{
                Toast.makeText(CoderActivity.this, R.string.editWarning, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveQR(Bitmap bitmap){
        //  Get path to new gallery image
        Uri path = CoderActivity.this.getContentResolver ().insert (MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        try
        {
            OutputStream stream = CoderActivity.this.getContentResolver ().openOutputStream (path);
            bitmap.compress (Bitmap.CompressFormat.PNG, 90, stream);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}