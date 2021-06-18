package com.example.pemesanan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

import static android.R.string.no;
import static android.os.Build.VERSION_CODES.N;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void increment(View view){//perintah tombol tambah
        if(quantity==100){
            Toast.makeText(this,"pesanan maximal 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1 ;
        display(quantity);
    }
    public void decrement(View view){//perintah tombol tambah
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }


    public void Submitorder(View view) {
        EditText nameEditText=(EditText)findViewById(R.id.edt_name);
        String name=nameEditText.getText().toString();
        Log.v("MainActivity","Nama:"+name);

        CheckBox cappucinoChekBox= (CheckBox) findViewById(R.id.Cappucino_checkbox);
        boolean hascappucino=cappucinoChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has cappucino:"+hascappucino);

        CheckBox chocolateChekBox= (CheckBox) findViewById(R.id.Chocolate_checkbox);
        boolean haschocolate=chocolateChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has cappucino:"+haschocolate);

        CheckBox lemonteaChekBox= (CheckBox) findViewById(R.id.LemonTea_checkbox);
        boolean haslemontea=lemonteaChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has cappucino:"+haslemontea);

        CheckBox greenteaChekBox= (CheckBox) findViewById(R.id.GreenTea_checkbox);
        boolean hasgreentea=greenteaChekBox.isChecked();//mengidentifikasi check
        Log.v("MainActivity","has cappucino:"+hasgreentea);

        int price=calculateprice(hascappucino,haschocolate,haslemontea,hasgreentea);//memanggil method jumlah harga
        String pricemessage=createOrderSummary(price,name,hascappucino,haschocolate,haslemontea,hasgreentea);


        displayMessage(pricemessage);

    }
    private int calculateprice(boolean addcappucino,boolean addchocolate,boolean addlemontea,boolean addgreentea){//jumlah pesanan * harga
        int harga=0;

        if(addcappucino){
            harga=harga+15000;
        }

        if (addchocolate){
            harga=harga+12000;
        }

        if (addlemontea){
            harga=harga+10000;
        }

        if (addgreentea){
            harga=harga+13000;
        }

        return quantity * harga;
    }
    private String createOrderSummary(int price, String name, boolean addChocolate, boolean addCappucino, boolean addLemontea, boolean addGreentea) {//hasil pemesanan
        String pricemessage=" Nama ="+name;
        pricemessage+="\n add Cappucino"+addCappucino;
        pricemessage+="\n add Chocolate?"+addChocolate;
        pricemessage+="\n add Lemon Tea?"+addLemontea;
        pricemessage+="\n add Green Tea?"+addGreentea;
        pricemessage+="\n quantity"+quantity;
        pricemessage+="\n Total = Rp"+price;
        pricemessage+="\n Thankyou";
        return  pricemessage;
    }

    //method ini untuk mencetak hasil perintah yang di tampilkan dengan inisial quantity_textview di textview 0
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(message);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}