package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView basketListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        ArrayList<String> myProducts = intent.getStringArrayListExtra("productsAdded");

        basketListView = (ListView) findViewById(R.id.basketList);
        ArrayAdapter productsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myProducts);
        basketListView.setAdapter(productsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.second_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.productsListAct:
                openMainActivity();
                return true;
            case R.id.credentials:
                openCredentialDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openCredentialDialog() {
        CredentialsDialog credentialsDialog = new CredentialsDialog();
        credentialsDialog.show(getSupportFragmentManager(), "Credentials");
    }

}