package com.example.licenta;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    private int reqCode;

    private DatabaseReference mRef;

    private List<ClientCar> carsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mRef = FirebaseDatabase.getInstance().getReference("Client_Cars");

        this.mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> data_snapshot = dataSnapshot.getChildren();
                carsList = new ArrayList<>();
                for (DataSnapshot ds : data_snapshot)
                    carsList.add(ds.getValue(ClientCar.class));
                setListView();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Login.this,"Error! onCancelled Method!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void goToMaps(View view){
        Intent i = new Intent(this, MapsActivity.class);
        startActivityForResult(i, 25);
    }

    private void setListView(){
        ClientCarAdapter adapter = new ClientCarAdapter(Login.this, carsList);
        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClientCar c = adapter.getCars().get(position);
                DatabaseReference childRef = mRef.child(String.valueOf(c.hashCode()));
                c.updateLastLogin();
                childRef.setValue(c);

                Toast.makeText(Login.this,"Successful login!", Toast.LENGTH_LONG).show();
                // New intent
                //////////////////////////////////////
            }
        });

        lv.setLongClickable(true);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ClientCar c = adapter.getCars().get(position);
                DatabaseReference childRef = mRef.child(String.valueOf(c.hashCode()));
                c.setRemembered(false);
                childRef.setValue(c);
                Toast.makeText(Login.this,c.getmIP() + " is not remembered anymore!", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void LoginFunction(View view) {
        try {
            EditText et_ip = findViewById(R.id.editTextTextPersonName);
            String str_ip = et_ip.getText().toString();
            EditText et_pass = findViewById(R.id.editTextTextPassword);
            String str_pass = MySHA256.getDigest(et_pass.getText().toString());
            CheckBox checkBox = findViewById(R.id.checkBox);

            for (ClientCar cc : carsList) {
                if (str_ip.equals(cc.getmIP()) && str_pass.equals(cc.getmPassword())){
                    DatabaseReference childRef = mRef.child(String.valueOf(cc.hashCode()));
                    cc.updateLastLogin();
                    cc.setRemembered(checkBox.isChecked());
                    childRef.setValue(cc);

                    Toast.makeText(Login.this,"Successful Login!", Toast.LENGTH_LONG).show();
                    // New intent
                    Intent it = new Intent(Login.this, VideoActivity.class);
                    reqCode = 1001;
                    startActivityForResult(it, reqCode);

                    return;
                }
            }
            Toast.makeText(Login.this,"Wrong IP or password!", Toast.LENGTH_LONG).show();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(reqCode == requestCode){
            if(resultCode == RESULT_OK)
            {
                return;
            }
        }
    }
}
