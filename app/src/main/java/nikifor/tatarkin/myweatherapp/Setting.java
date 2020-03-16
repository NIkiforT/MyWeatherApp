package nikifor.tatarkin.myweatherapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Setting extends AppCompatActivity {
    private static final String TAG = "SettingActivity";
    private static final String KEY_CITY_TEXT = "key City";
    private static final String KEY_SPEED = "key speed";
    private static final String KEY_PRESSURE = "key pressure";

    Button buttonBack;
    EditText textCity;
    CheckBox checkSpeed;
    CheckBox checkPressure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);


        initButtonBack();
        initTextCity();
        initCheckPressure();
        initCheckSpeed();

        openingMain();
        checkSpeed();
        checkPressure();
    }

    //Инициализация переменных
    private void initButtonBack() {
        buttonBack = findViewById(R.id.button_back);
    }
    private void initTextCity(){
        textCity = findViewById(R.id.textCity);
    }
    private void initCheckSpeed(){
        checkSpeed = findViewById(R.id.checkBoxSpeed);
    }
    private void initCheckPressure(){
        checkPressure = findViewById(R.id.checkBoxPressure);
    }

    //возвращение на главный экран
    private void openingMain() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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


    //Сохранение состояния Activity
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(this, TAG + " onSaveInstanceState", Toast.LENGTH_SHORT).show();
        Log.d(TAG, " onSaveInstanceState");

        outState.putString(KEY_CITY_TEXT, ((EditText)findViewById(R.id.textCity)).getText().toString());
        outState.putBoolean(KEY_SPEED, checkSpeed.isPressed());
        outState.putBoolean(KEY_PRESSURE, checkPressure.isPressed());
    }

    //Восстановление Activity
    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(this, TAG + " onRestoreInstanceState", Toast.LENGTH_SHORT).show();
        Log.d(TAG, " onRestoreInstanceState");

        textCity.setText(savedInstanceState.getString(KEY_CITY_TEXT));
        checkSpeed.setPressed(savedInstanceState.getBoolean(KEY_SPEED));
        checkPressure.setPressed(savedInstanceState.getBoolean(KEY_PRESSURE));
    }
}
