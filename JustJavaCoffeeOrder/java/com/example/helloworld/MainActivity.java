package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if(quantity == 100){
            //show an error message as a toast
            Toast.makeText(this, "You can't have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 0) {
            //show an error message as a toast
            Toast.makeText(this, "You can't have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     *
     * @param
     */
    public void submitOrder(View view) {
        // find the user's name
        EditText nameInput = (EditText) findViewById(R.id.name_input);
        String name = nameInput.getText().toString();

        // figure out  if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        //figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // calculate the price
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        // display the order summary on the screen
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

//        //use an intent to launch an email app
//        //send the order summary in the email body
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setData(Uri.parse("mailto:")); //only email apps should handle this
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
        displayMessage(priceMessage);

    }

    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @ return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        //price of 1 cup of coffee
        int basePrice = 5;

        // add $1 if the user wants whipped cream
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        //add $2 if the user wants chocolate
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        //calculate the total order price by multiplying by quantity
        return basePrice * quantity;
    }

    /**
     * This method create an order summary: name, quantity, total price.
     *
     * @param name is user name
     * @param price is the total price
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param hasChocolate is whether or not the user wants chocolate topping
     * @return order summary
     */
    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you !";
        return priceMessage;



    }
 /*
    /**
     * This method edit name of order.
     *
     * @return
     */
   /* private String displayName() {
        EditText nameInput = (EditText) findViewById(R.id.name_input);
        return nameInput.getText().toString();

    }

    /**
     * This method create an checkbox of chocolate.
     * return whether the chocolate checkbox is clicked or not
     */
    /*private boolean isHasChocolate() {

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        return hasChocolate;
    }
    */

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given message on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
