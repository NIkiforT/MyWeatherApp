package nikifor.tatarkin.myweatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Constants {

    private static final String TAG = "MainActivity";
    private static final String KEY_TEXT_SPEED = "key speed";
    private static final String KEY_TEXT_PRESSURE = "key pressure";
    private final static int REQUEST_CODE = 1;

    Button buttonSetting;
    Button buttonAboutCityBrowser;
    TextView textSpeedWind;
    TextView textPressure;
    TextView textNameCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtonSetting();
        initTextPressure();
        initTextSpeed();
        initNameCity();
        initButtonAboutCityBrowser();

        //метод перехода на Activity настроек
        openSetting();

        //метод перехода в Браузер на страницу Wiki c выбранным городом
        openBrowser();

        //Вывод информации onCreate
        Toast.makeText(this, TAG +" onCreate", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");
    }

    private void initButtonSetting() {
        buttonSetting = findViewById(R.id.button_setting);
    }
    private void initButtonAboutCityBrowser() {
        buttonAboutCityBrowser = findViewById(R.id.button_browser);
    }
    private void initTextSpeed() {
        textSpeedWind = findViewById(R.id.textSpeedWind);
    }
    private void initTextPressure() {
        textPressure = findViewById(R.id.textPressure);
    }
    private void initNameCity(){textNameCity = findViewById(R.id.idCity);}

    //переход на экран настроек и передача в EditText название текущего города.
    private void openSetting() {
        buttonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Setting.class);
                intent.putExtra(NAME_CITY_FOR_SETTING, textNameCity.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    //метод открытия Браузера с информацией о выбранном городе в Wiki.
    private void openBrowser() {
        buttonAboutCityBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = getString(R.string.wiki_url).toString() + textNameCity.getText().toString();
                Uri uri = Uri.parse(url);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
            }
        });
    }

    //Получение данных из Activity Setting. Название города, и показать/скрыть скорость ветра и давление.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode != REQUEST_CODE){
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if(resultCode == RESULT_OK){
            textNameCity.setText(data.getStringExtra(NAME_CITY));
            textSpeedWind.setVisibility(data.getBooleanExtra(SPEED_VISIBLE, true)? View.VISIBLE : View.GONE);
            textPressure.setVisibility(data.getBooleanExtra(PRESSURE_VISIBLE, true)? View.VISIBLE : View.GONE);
        }
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
