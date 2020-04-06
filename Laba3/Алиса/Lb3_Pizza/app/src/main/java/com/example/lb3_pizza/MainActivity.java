package com.example.lb3_pizza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Pizza pizza;
    TextView textViewReceipt;
    double receipt_price;
    Switch switcher;
    String text;
    EditText editTextCard;
    EditText editTextDate;
    EditText editTextCVC;
    EditText editTextEMail;

    String sizePizza;
    String checkBox[] = new String[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        pizza = new Pizza();
        textViewReceipt = findViewById(R.id.textViewReceipt);

        //eMail = findViewById(R.id.editText);
        //eMail.clearFocus();

        editTextCard = findViewById(R.id.editTextCard);
        editTextDate = findViewById(R.id.editTextDate);
        editTextCVC = findViewById(R.id.editTextCVC);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pay,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        pizza.setPizza_size_price(450);
        textViewReceipt.setText("Итого: " + calculate_receipt());

        switcher = findViewById(R.id.switch1);

        editTextEMail = findViewById(R.id.editTextEMail);
        sizePizza = "Средняя";
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    editTextEMail.setEnabled(true);
                }else{
                    editTextEMail.setEnabled(false);
                }
            }
        });
    }


    public void onRadio(View view){

        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){

            case R.id.radioButtonSmall:
                if (checked)
                    pizza.setPizza_size_price(300);
                sizePizza = "Маленькая";
                break;

            case R.id.radioButtonMedium:
                if (checked)
                    pizza.setPizza_size_price(450);
                sizePizza = "Средняя";
                break;

            case R.id.radioButtonLarge:
                if (checked)
                    pizza.setPizza_size_price(500);
                sizePizza = "Большая";
                break;
        }
        textViewReceipt.setText("Итого: " + calculate_receipt());
    }

    public void onCheckbox (View view){

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.checkBox1:
                if (checked)
                {
                    pizza.setMeat_price(350);
                    checkBox[0] = "Гуанчале ";
                }
                else
                    {
                    pizza.setMeat_price(0);
                    checkBox[0] = "";
                }
                break;

            case R.id.checkBox2:
                if (checked)
                {
                    pizza.setCheese_price(450);
                    checkBox[1] = "Горгонзола ";
                }
                else
                    {
                    pizza.setCheese_price(0);
                    checkBox[1] = "";
                }
                break;
            case R.id.checkBox3:
                if (checked){
                    pizza.setMango_price(200);
                    checkBox[2] = "Тайское манго ";
                }
                else{
                    pizza.setMango_price(0);
                    checkBox[2] = "";}
                break;
            case R.id.checkBox4:
                if (checked){
                    pizza.setNut_price(100);
                    checkBox[3] = "Кедровый орех ";}
                else{
                pizza.setNut_price(0);
                checkBox[3] = "";
            }
                break;
        }
        textViewReceipt.setText("Итого: " + calculate_receipt());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        if(text.equals("Карта Visa")){
            editTextCard.setEnabled(true);
            editTextDate.setEnabled(true);
            editTextCVC.setEnabled(true);
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        }
        else{
            editTextCard.setEnabled(false);
            editTextDate.setEnabled(false);
            editTextCVC.setEnabled(false);
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        }


        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }


    private String calculate_receipt()
    {

        receipt_price = pizza.getPizza_size_price()+pizza.getMeat_price()+pizza.getCheese_price()+pizza.getMango_price()+pizza.getNut_price();
        text = String.valueOf(receipt_price);
        return text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void pay(View view)
    {
        double size = pizza.getPizza_size_price() + 0;
        double full = pizza.getMeat_price() + pizza.getCheese_price() + pizza.getMango_price() + pizza.getNut_price();

        if(checkBox[0] == null)
        {
            checkBox[0] = "";
        }

        if(checkBox[1] == null)
        {
            checkBox[1] = "";
        }

        if(checkBox[2] == null)
        {
            checkBox[2] = "";
        }

        if(checkBox[3] == null)
        {
            checkBox[3] = "";
        }

        if(size == 0 || full == 0)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Внимание");
            alert.setMessage("Вы не выбрали наполнитель или размер или пиццу!");
            alert.setPositiveButton("Окей", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Продолжайте покупку", Toast.LENGTH_SHORT).show();
                }
            });
            alert.create().show();
        }
        else
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Заказ принят");
            alert.setMessage("\nРазмер пиццы: " + sizePizza + "\nНаполнители: " + String.valueOf(checkBox[0]) + " " + String.valueOf(checkBox[1]) + String.valueOf(checkBox[2]) + String.valueOf(checkBox[3]) + "\nИтого сумма заказа: " + text);
            //alert.setMessage("пицца");
            alert.setPositiveButton("Окей", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "Спасибо что выбираете нас !!!", Toast.LENGTH_SHORT).show();
                }
            });
            alert.create().show();
        }
    }

}