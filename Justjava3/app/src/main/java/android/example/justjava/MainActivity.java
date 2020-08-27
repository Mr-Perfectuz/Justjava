package android.example.justjava;

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/



import java.text.NumberFormat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    CheckBox checkBox;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = (CheckBox)findViewById(R.id.whipped_cream_checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    Toast.makeText(getApplicationContext(), "Checked", Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    int quantity =0;

    public void decrement(View view) {
        quantity = quantity -1;
        displayQuantity(quantity);
    }


    public void increment(View view) {
        quantity = quantity +1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public String createOrderSummary(int price, boolean addWhippedCream) {
        String priceMessage = "Name: Anvar Ziyodov";
        priceMessage += "\nAdd Whipped Cream ? " + addWhippedCream;
        priceMessage += "\nQuantity " +quantity;
        priceMessage += "\nTotal:" + price;
        priceMessage +=  "\nThank you !";
        return priceMessage;
    }

    public void submitOrder(View view) {

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "Has whipped Cream : "+ hasWhippedCream);

        int price = calculatePrice();
        displayMessage(createOrderSummary(price, hasWhippedCream));
}

    private int calculatePrice() {
        return quantity * 5 ;

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView  orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}
