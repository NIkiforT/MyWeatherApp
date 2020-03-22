package nikifor.tatarkin.myweatherapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity implements Constants {
    private static final String TAG = "SettingActivity";
    private static final String KEY_CITY_TEXT = "key City";
    private static final String KEY_SPEED = "key speed";
    private static final String KEY_PRESSURE = "key pressure";

    Button buttonBack;
    EditText textCity;
    CheckBox checkSpeed;
    CheckBox checkPressure;
    TextView buttonMoscow;
    TextView buttonSaintPetersburg;
    TextView buttonSochi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

        initButtonBack();
        initTextCity();
        initCheckPressure();
        initCheckSpeed();
        initMoscow();
        initSaintPeterburg();
        initSochi();

        openingMain();
        checkSpeed();
        checkPressure();
        enterMoscow();
        enterSaintPetegburg();
        enterSochi();

        //Получение из MainActivity название текущего города
        nameCityFromMain();

    }

    //функция записывает в EditText название текущего города от MainActivity
    private void nameCityFromMain() {
        textCity.setText(getIntent().getExtras().getString(NAME_CITY_FOR_SETTING));
    }

    //Инициализация переменных
    private void initButtonBack() {
        buttonBack = findViewById(R.id.button_back);
    }
    private void initTextCity(){
        textCity = findViewById(R.id.textCity);
    }
    //В check box isChecked - true
    private void initCheckSpeed(){
        checkSpeed = findViewById(R.id.checkBoxSpeed);
        checkSpeed.setChecked(true);

    }
    private void initCheckPressure(){
        checkPressure = findViewById(R.id.checkBoxPressure);
        checkPressure.setChecked(true);
    }
    private void initMoscow(){buttonMoscow = findViewById(R.id.textViewMoscow);}
    private void initSaintPeterburg(){buttonSaintPetersburg = findViewById(R.id.textViewSaintPetersburg);}
    private void initSochi(){buttonSochi = findViewById(R.id.textViewSochi);}

    //возвращение на главный экран, передача данных: Город, скорость ветра, температура.
    private void openingMain() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResult = new Intent();
                intentResult.putExtra(NAME_CITY, textCity.getText().toString());
                intentResult.putExtra(SPEED_VISIBLE, checkSpeed.isChecked());
                intentResult.putExtra(PRESSURE_VISIBLE, checkPressure.isChecked());
                setResult(RESULT_OK, intentResult);
                finish();
            }
        });
    }

    //Нажатие на check box скорость ветра
    private void checkSpeed(){
        checkSpeed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(TAG, "Показать скорость");
            }
        });

    }

    //Нажатие на check box давление
    private void checkPressure(){
        checkPressure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(TAG, "Показать давление");
            }
        });
    }

    //Ввод поппулярных городов в EditText (textCity) - начало
    private void enterMoscow(){
        buttonMoscow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textCity.setText(R.string.moscow);
            }
        });
    }
    private void enterSaintPetegburg(){
        buttonSaintPetersburg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textCity.setText(R.string.saint_petersburg);
            }
        });
    }
    private void enterSochi(){
        buttonSochi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textCity.setText(R.string.sochi);
            }
        });
    }
    //Ввод поппулярных городов в EditText (textCity) - конец


    //Сохранение состояния Activity
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(this, TAG + " onSaveInstanceState", Toast.LENGTH_SHORT).show();
        Log.d(TAG, " onSaveInstanceState");

        outState.putString(KEY_CITY_TEXT, ((EditText)findViewById(R.id.textCity)).getText().toString());
        outState.putBoolean(KEY_SPEED, checkSpeed.isChecked());
        outState.putBoolean(KEY_PRESSURE, checkPressure.isChecked());
    }

    //Восстановление Activity
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(this, TAG + " onRestoreInstanceState", Toast.LENGTH_SHORT).show();
        Log.d(TAG, " onRestoreInstanceState");

        textCity.setText(savedInstanceState.getString(KEY_CITY_TEXT));
        checkSpeed.setChecked(savedInstanceState.getBoolean(KEY_SPEED));
        checkPressure.setChecked(savedInstanceState.getBoolean(KEY_PRESSURE));
    }
}
