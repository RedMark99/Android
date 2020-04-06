package com.example.pizza;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"Пепперони", "С Грибами", "Итальянские чоризо"};
    String mDescription[] = {"Пепперони описание", "С Грибами описание", "Итальянские чоризо описание"};
    String Cost[] = {"350","370","400"};
    String haveCheck[] = new String[4];
    int image[] = {R.drawable.pizza1, R.drawable.pizza2, R.drawable.pizza3};
    int Summa = 0;
    TextView SummaString;
    boolean choose = false;
    int radiosum = 0;
    int Itog = 0;
    String res;
    int checks = 0;
    EditText editNumber;
    EditText editCVC;
    EditText editEmail;
    Switch switchs;
    Button accept;
    String size;
    String pizza;

    RadioGroup radioGroupDiffLevel;
    RadioButton radioSmall;
    RadioButton radioMedium;
    RadioButton radioLarge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(this,R.array.payment, android.R.layout.simple_spinner_item);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapters);

        editNumber = findViewById(R.id.editText2);
        editCVC = findViewById(R.id.editText3);
        editEmail = findViewById(R.id.editText4);
        switchs = findViewById(R.id.switch1);
        accept = findViewById(R.id.accept);



        switchs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    editEmail.setEnabled(true);
                }else{
                    editEmail.setEnabled(false);
                }
            }
        });



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
                if(text.equals("Visa")){
                    editNumber.setEnabled(true);
                    editCVC.setEnabled(true);
                    Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
                }
                else if(text.equals("Master Card"))
                {
                    editNumber.setEnabled(true);
                    editCVC.setEnabled(true);
                    Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
                }
                else{
                    editNumber.setEnabled(false);
                    editCVC.setEnabled(false);
                    Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, Cost, image);
        listView.setAdapter(adapter);
        SummaString = findViewById(R.id.summa);
        this.radioGroupDiffLevel= (RadioGroup) this.findViewById(R.id.radioGroup_diffLevel);

        this.radioSmall = (RadioButton)this.findViewById(R.id.small);
        this.radioMedium = (RadioButton)this.findViewById(R.id.medium);
        this.radioLarge = (RadioButton)this.findViewById(R.id.large);
        radioSmall.setOnClickListener(radioButtonClickListener);
        radioMedium.setOnClickListener(radioButtonClickListener);
        radioLarge.setOnClickListener(radioButtonClickListener);

        radiosum = 100;
        Summa = 0;
        checks = 0;
        Itog = Summa + radiosum + checks;
        res = Itog + "";
        SummaString.setText(res);
        size = "Маленькая";


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                 if(position == 0)
                 {
                     Toast.makeText(MainActivity.this, "Пепероне описание", Toast.LENGTH_SHORT).show();
                     SummaString.setText(Cost[0]);
                     Summa = 350;
                     choose = true;
                     pizza = "Пепероне";
                 }
                if(position == 1)
                {
                    Toast.makeText(MainActivity.this, "С Грибами описание", Toast.LENGTH_SHORT).show();
                    SummaString.setText(Cost[1]);
                    Summa = 370;
                    choose = true;
                    pizza = "С грибами";
                }
                if(position == 2)
                {
                    Toast.makeText(MainActivity.this, "Итальянские чоризо описание", Toast.LENGTH_SHORT).show();
                    SummaString.setText(Cost[2]);
                    Summa = 400;
                    choose = true;
                    pizza = "Итальянские чоризо";
                }

                Itog = Summa + radiosum + checks;

                res = Itog + "";

                SummaString.setText(res);

            }
        });
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v)
        {
            RadioButton radioButton = (RadioButton) v;

            switch (radioButton.getId()){
                case R.id.small:
                    radiosum = 100;
                    Itog = Summa + radiosum + checks;
                    res = Itog + "";
                    SummaString.setText(res);
                    size = "Маленькая";
                    break;
                case R.id.medium:
                    radiosum = 150;
                    Itog = Summa + radiosum + checks;
                    res = Itog + "";
                    SummaString.setText(String.valueOf(res));
                    size = "Средняя";
                    break;
                case R.id.large:
                    radiosum = 200;
                    Itog = Summa + radiosum + checks;
                    SummaString.setText(String.valueOf(Itog));
                    size = "Большая";
                    break;
            }
        }
    };

    public void onCheckboxClicked(View view) {
        // Получаем флажок
        CheckBox language = (CheckBox) view;
        // Получаем, отмечен ли данный флажок
        boolean checked = language.isChecked();

        // Смотрим, какой именно из флажков отмечен
        switch(view.getId()) {
            case R.id.chesse:
                if (checked)
                {
                    checks = checks + 20;
                    Itog = Summa + radiosum + checks;
                    res = Itog + "";
                    SummaString.setText(String.valueOf(res));
                    haveCheck[0] = "Сыр ";
                }
                else
                {
                    checks = checks - 20;
                    Itog = Summa + radiosum + checks;
                    res = Itog + "";
                    SummaString.setText(String.valueOf(res));
                    haveCheck[0] = "";
                }
                break;
            case R.id.meat:
                if (checked)
                {
                    checks = checks + 25;
                    Itog = Summa + radiosum + checks;
                    res = Itog + "";
                    SummaString.setText(String.valueOf(res));
                    haveCheck[1] = "Колбаски ";
                }
                else
                {
                    checks = checks - 25;
                    Itog = Summa + radiosum + checks;
                    res = Itog + "";
                    SummaString.setText(String.valueOf(res));
                    haveCheck[1] = "";
                }
                break;

            case R.id.mushrooms:
                if (checked) {
                    checks = checks + 30;
                    Itog = Summa + radiosum + checks;
                    res = Itog + "";
                    SummaString.setText(String.valueOf(res));
                    haveCheck[2] = "Грибы ";
                }
                else
                {
                    checks = checks - 30;
                    Itog = Summa + radiosum + checks;
                    res = Itog + "";
                    SummaString.setText(String.valueOf(res));
                    haveCheck[2] = "";
                }
                break;

            case R.id.tomatos:
                if (checked)
                {
                    checks = checks + 35;
                    Itog = Summa + radiosum + checks;
                    res = Itog + "";
                    SummaString.setText(String.valueOf(res));
                    haveCheck[3] = "Томаты ";
                }
                else
                {
                    checks = checks - 35;
                    Itog = Summa + radiosum + checks;
                    res = Itog + "";
                    SummaString.setText(String.valueOf(res));
                    haveCheck[3] = "";
                }

        }
    }

    public void Mark(View view)
    {
        if(haveCheck[0] == null)
        {
            haveCheck[0] = "";
        }

        if(haveCheck[1] == null)
        {
            haveCheck[1] = "";
        }

        if(haveCheck[2] == null)
        {
            haveCheck[2] = "";
        }

        if(haveCheck[3] == null)
        {
            haveCheck[3] = "";
        }

        if(Summa == 0 || radiosum == 0 || checks == 0)
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
        alert.setMessage("Пицца: " + String.valueOf(pizza) + "\nРазмер пиццы: " + String.valueOf(size) + "\nНаполнители: " + String.valueOf(haveCheck[0]) + " " + String.valueOf(haveCheck[1]) + String.valueOf(haveCheck[2]) + String.valueOf(haveCheck[3]) + "\nИтого сумма заказа: " + res);
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


    class MyAdapter extends ArrayAdapter<String>
    {

        Context context;
        String rTitle[];
        String rDescription[];
        String Cost[];
        int rImgs[];

        MyAdapter(Context c, String title[], String description[],String Cost[], int imgs[])
        {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.Cost = Cost;
            this.rImgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView cost = row.findViewById(R.id.textView3);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            images.setImageResource((rImgs[position]));
            myTitle.setText((rTitle[position]));
            myDescription.setText(rDescription[position]);
            cost.setText(Cost[position]);

            return row;
        }
    }

}
