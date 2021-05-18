package com.example.licenta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UploadModel extends AppCompatActivity {

    List<String> arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_model);

        arraySpinner = new ArrayList<>();
        arraySpinner.add("model_cnn1");
        arraySpinner.add("model_k_means");
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void getModelPath(View view){
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        startActivityForResult(i, 2001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2001) {
            if(data==null) {
                Toast.makeText(this, "No model has been selected!", Toast.LENGTH_LONG).show();
                return;
            }
            Uri uri = data.getData();
            DocumentFile documentFile = DocumentFile.fromTreeUri(this, uri);
            for (DocumentFile file : documentFile.listFiles()) {
                if(file.isDirectory()){ // if it is sub directory
                    // Do stuff with sub directory
                    Toast.makeText(this, "You must select a file not a directory!", Toast.LENGTH_LONG).show();
                }else{
                    // Do stuff with normal file
                    String model_path = file.getUri().toString();
                    String[] ret = model_path.split("%2F");
                    model_path = ret[ret.length - 1];
                    arraySpinner.add(model_path);
                    Spinner s = (Spinner) findViewById(R.id.spinner);
                    s.invalidate();

//                    Intent it = new Intent();
//                    it.putExtra("model_path", model_path);
//                    setResult(RESULT_OK, it);
//                    finish();
                    Toast.makeText(this, model_path, Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void retFunction(View view){
        Spinner s = findViewById(R.id.spinner);
        String model_name = s.getSelectedItem().toString();

        Intent it = new Intent();
        it.putExtra("model_name", model_name);
        setResult(RESULT_OK, it);
        finish();
    }
}
