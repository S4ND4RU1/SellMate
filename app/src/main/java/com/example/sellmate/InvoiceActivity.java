package com.example.sellmate;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class InvoiceActivity extends AppCompatActivity {

    private ListView invoiceListView;
    private FloatingActionButton fab;
    private ArrayList<String> invoices;
    private InvoiceAdapter invoiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        invoiceListView = findViewById(R.id.invoiceListView);
        fab = findViewById(R.id.fab);

        invoices = new ArrayList<>();
        invoiceAdapter = new InvoiceAdapter(this, invoices);
        invoiceListView.setAdapter(invoiceAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateInvoiceDialog();
            }
        });

        loadInvoicesForToday();
    }

    private void loadInvoicesForToday() {
        // TODO: Load invoices from the database for the current day
        // This is a placeholder example
        invoices.add("Invoice 001");
        invoices.add("Invoice 002");
        invoiceAdapter.notifyDataSetChanged();
    }

    private void showCreateInvoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_create_invoice, null);
        builder.setView(dialogView);

        EditText customerEditText = dialogView.findViewById(R.id.customerEditText);
        EditText itemsEditText = dialogView.findViewById(R.id.itemsEditText);
        Button createButton = dialogView.findViewById(R.id.createButton);

        AlertDialog alertDialog = builder.create();

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customer = customerEditText.getText().toString().trim();
                String items = itemsEditText.getText().toString().trim();
                if (!customer.isEmpty() && !items.isEmpty()) {
                    // TODO: Create new invoice and save to database
                    invoices.add("New Invoice for " + customer);
                    invoiceAdapter.notifyDataSetChanged();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(InvoiceActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alertDialog.show();
    }
}
