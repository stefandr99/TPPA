package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView productListView;
    ArrayList<String> productArrayList;
    ArrayList<String> myBasketArrayList;
    private static final String tag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, "onCreate");

        productListView = (ListView) findViewById(R.id.productList);
        Button addToBasket = (Button) findViewById(R.id.addToBasketButton);
        productArrayList = new ArrayList<>();
        myBasketArrayList = new ArrayList<>();

        productArrayList.add("Headphones on-ear Sony");
        productArrayList.add("Headphones in-ear Apple");
        productArrayList.add("Headphones in-ear Samsung");
        productArrayList.add("Headphones over-ear Philips");
        productArrayList.add("Mouse Microsoft");
        productArrayList.add("Mouse Hama");
        productArrayList.add("Keyboard Hama");
        productArrayList.add("Keyboard Android");

        ArrayAdapter productsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productArrayList);
        productListView.setAdapter(productsAdapter);

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView productView = (TextView) findViewById(R.id.productView);
                productView.setText(productArrayList.get(position));
            }
        });

        addToBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView productView = (TextView) findViewById(R.id.productView);
                myBasketArrayList.add(productView.getText().toString());
                showProductAddedToast();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(tag, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(tag, "onRestart");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        TextView productView = (TextView) findViewById(R.id.productView);
        outState.putString("clickedProduct", productView.getText().toString());

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        TextView productView = (TextView) findViewById(R.id.productView);
        productView.setText(savedInstanceState.getString("clickedProduct"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myBasket:
                openSecondActivity();
                return true;
            case R.id.credentials:
                openCredentialDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        TextView productView = (TextView) findViewById(R.id.productView);
        intent.putExtra("productsAdded", myBasketArrayList);
        startActivity(intent);
    }

    public void openCredentialDialog() {
        CredentialsDialog credentialsDialog = new CredentialsDialog();
        credentialsDialog.show(getSupportFragmentManager(), "Credentials");
    }

    public void showProductAddedToast() {
        Toast.makeText(MainActivity.this, "Product added to basket", Toast.LENGTH_SHORT).show();
    }
}