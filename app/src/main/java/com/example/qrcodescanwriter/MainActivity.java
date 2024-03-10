package com.example.qrcodescanwriter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {
    private Button btScan;
    private Button btGen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btScan = findViewById(R.id.scanner);
        btGen = findViewById(R.id.create);

        btScan.setOnClickListener(v -> ScanCode());
        btGen.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CoderActivity.class);
            startActivity(intent);
        });
    }

    private void ScanCode(){
        ScanOptions options = new ScanOptions();
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureActivity.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        String text = result.getContents();
        if (text != null) {
            DialogClass dialog = new DialogClass();
            Bundle args = new Bundle();
            args.putString("msg", text);
            if (!URLMaster.checkUrl(text)){
                args.putInt("id", DialogClass.IDD_TEXT);
            }
            else{
                args.putInt("id", DialogClass.IDD_URI);

            }
            dialog.setArguments(args);
            dialog.show(getFragmentManager(), "custom");
        }
    });
}