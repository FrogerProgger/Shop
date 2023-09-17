package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.shop.Additionals.DBHelper;
import com.example.shop.Additionals.Product;

public class MainActivity extends AppCompatActivity {

    private View rootView;
    private Animation fadeIn;
    private  Animation fadeOut;
    SharedPreferences preferences;

    private void setAnimationsListeners() {
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new CountDownTimer(3000, 1000) {
                    @Override
                    public void onTick(long l) {
                    }

                    @Override
                    public void onFinish() {
                        rootView.startAnimation(fadeOut);
                    }
                }.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(MainActivity.this, MainScreen.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void startAnimation() {
        rootView.startAnimation(fadeIn);
    }

    public void addDataToDatabase() {
        DBHelper helper = new DBHelper(this);
        helper.dropTables();
        helper.firstCreate();

        // Добавляем продукты.
        helper.insertProduct(new Product("Мармеладки", 0, R.drawable.marmelados, 55.66, getString(R.string.marmelados_description)), getString(R.string.Products));
        helper.insertProduct(new Product("Ягодки мармеладки", 0, R.drawable.berries_marmelados, 75.41, getString(R.string.berries_marmelados_description)), getString(R.string.Products));

        helper.insertProduct(new Product("Святой источник", 0, R.drawable.holy_spring, 60, getString(R.string.holy_spring_description)), getString(R.string.Products));
        helper.insertProduct(new Product("Милка карамельная", 0, R.drawable.chocolate, 100, getString(R.string.chocolate_description)), getString(R.string.Products));
        helper.insertProduct(new Product("Батон хлеба", 0, R.drawable.bread, 35.20, getString(R.string.bread_description)), getString(R.string.Products));

        // Добавляем компьютеры и доп товар.
        helper.insertProduct(new Product("Компуктер Жоский", 1, R.drawable.cyberpower_gaming, 80320.21, getString(R.string.cuber_comp_description)), getString(R.string.Products));
        helper.insertProduct(new Product("Компьютер iRU Game 310H5GMA", 1, R.drawable.game_comp, 89212.22, getString(R.string.game_comp_description)), getString(R.string.Products));

        helper.insertProduct(new Product("Мышка bloody", 1, R.drawable.mouse1, 1200.20, getString(R.string.mouse1_description)), getString(R.string.Products));
        helper.insertProduct(new Product("Игровая мышка bloody", 1, R.drawable.mouse2, 1609.56, getString(R.string.mouse2_description)), getString(R.string.Products));

        helper.insertProduct(new Product("Клавиатура игровая bloody", 1, R.drawable.klava1, 2065, getString(R.string.klava1_description)), getString(R.string.Products));
        helper.insertProduct(new Product("Клавиатура для офиса", 1, R.drawable.klava2, 1300, getString(R.string.klava2_description)), getString(R.string.Products));

        // Добавляем жабок.
        helper.insertProduct(new Product("Жабка красивая", 2, R.drawable.frog1, 10000, getString(R.string.frog1_description)), getString(R.string.Products));
        helper.insertProduct(new Product("Узкорот миловидный", 2, R.drawable.frog2, 13000, getString(R.string.frog2_description)), getString(R.string.Products));
        helper.insertProduct(new Product("Узкоротик второй", 2, R.drawable.frog3, 12000, getString(R.string.frog3_description)), getString(R.string.Products));

        // Добавляем игры.
        helper.insertProduct(new Product("Ведьмак 3", 3, R.drawable.game1, 1200, getString(R.string.game1_description)), getString(R.string.Products));
        helper.insertProduct(new Product("The Elder Scrolls V: Skyrim", 3, R.drawable.game2, 1500, getString(R.string.game2_description)), getString(R.string.Products));
        helper.insertProduct(new Product("Warcraft 3", 3, R.drawable.game3, 256, getString(R.string.game3_description)), getString(R.string.Products));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = getWindow().getDecorView().findViewById(android.R.id.content);

        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        preferences = getSharedPreferences("ShopApp", MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean("isFirstRun", true);

        if (isFirstRun)
            addDataToDatabase();

        setAnimationsListeners();
        startAnimation();
    }
}