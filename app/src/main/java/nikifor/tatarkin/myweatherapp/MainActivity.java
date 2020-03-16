package nikifor.tatarkin.myweatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_TEXT_SPEED = "key speed";
    private static final String KEY_TEXT_PRESSURE = "key pressure";

    Button buttonSetting;
    TextView textSpeedWind;
    TextView textPressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtonSetting();
        initTextPressure();
        initTextSpeed();


        openSetting();


        //Вывод информации onCreate
        Toast.makeText(this, TAG +" onCreate", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");
    }

    private void initButtonSetting() {
        buttonSetting = findViewById(R.id.button_setting);
    }
    private void initTextSpeed() {
        textSpeedWind = findViewById(R.id.textSpeedWind);
    }
    private void initTextPressure() {
        textPressure = findViewById(R.id.textPressure);
    }


    //переход на экран настроек
    private void openSetting() {
        buttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Setting.class);
                startActivity(intent);
            }
        });
    }

    //Сохранение данных о скорости ветра и давлении
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TEXT_SPEED, ((TextView)findViewById(R.id.textSpeedWind)).getText().toString());
        outState.putString(KEY_TEXT_PRESSURE, ((TextView)findViewById(R.id.textPressure)).getText().toString());

        Toast.makeText(this, TAG + " Данные сохранены", Toast.LENGTH_SHORT).show();
        Log.d(TAG, " Данные сохранены");
    }

    //Восстановление данных
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textSpeedWind.setText(savedInstanceState.getString(KEY_TEXT_SPEED));
        textPressure.setText(savedInstanceState.getString(KEY_TEXT_PRESSURE));

        Toast.makeText(this, TAG + " Данные восстановлены", Toast.LENGTH_SHORT).show();
        Log.d(TAG, " Данные восстановлены");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, TAG + " onStart", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, TAG + " onResume", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, TAG + " onPause", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, TAG + " onStop", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, TAG + " onRestart", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, TAG + " onDestroy", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy");
    }
}
