package com.example.sellmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    private Button userCreationButton;
    private Button customerCreationButton;
    private Button salesmenCreationButton;
    private Button itemCreationButton;
    private Button invoiceButton;
    private Button reportsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        userCreationButton = findViewById(R.id.userCreationButton);
        customerCreationButton = findViewById(R.id.customerCreationButton);
        salesmenCreationButton = findViewById(R.id.salesmenCreationButton);
        itemCreationButton = findViewById(R.id.itemCreationButton);
        invoiceButton = findViewById(R.id.invoiceButton);
        reportsButton = findViewById(R.id.reportsButton);

        userCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, UserCreationActivity.class);
                startActivity(intent);
            }
        });

        customerCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CustomerCreationActivity.class);
                startActivity(intent);
            }
        });

        salesmenCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SalesmenCreationActivity.class);
                startActivity(intent);
            }
        });

        itemCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ItemCreationActivity.class);
                startActivity(intent);
            }
        });

        invoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, InvoiceActivity.class);
                startActivity(intent);
            }
        });

        reportsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, ReportsActivity.class);
                startActivity(intent);
            }
        });
    }
}
