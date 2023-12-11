package com.example.fermer_20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ActivityShop extends AppCompatActivity implements BlankFragmentShop.OnFragmentSendDataShopListener {

    Integer score;
    Integer power_click;
    public Boolean carrot_flag;
    public Boolean cabbage_flag;
    public Boolean horizontal;

    // Получение данных
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);
        Bundle arguments = getIntent().getExtras();
        power_click = (arguments.getInt("power_click"));
        carrot_flag = arguments.getBoolean("carrot");
        cabbage_flag = arguments.getBoolean("cabbage");
        score = (arguments.getInt("score"));
        horizontal = false;
    }
    // метод улучшения клика
    @Override
    public void clickUpShop()
    {
        score = score - (int)(power_click * 10 * Math.sqrt(power_click));
        power_click++;
        BlankFragmentShop fragmentShop = (BlankFragmentShop) getSupportFragmentManager().findFragmentById(R.id.fragment_shop);
        fragmentShop.setScore(score,power_click);
    }

    // метод разблокировки моркови
    @Override
    public void clickCarrot()
    {
        score = score - 50;
        carrot_flag = true;
        BlankFragmentShop fragmentShop = (BlankFragmentShop) getSupportFragmentManager().findFragmentById(R.id.fragment_shop);
        fragmentShop.delCarrot();
        fragmentShop.setScore(score,power_click);
    }

    // метод разблокировки капусты
    @Override
    public void clickCabbage()
    {
        score = score - 100;
        cabbage_flag = true;
        BlankFragmentShop fragmentShop = (BlankFragmentShop) getSupportFragmentManager().findFragmentById(R.id.fragment_shop);
        fragmentShop.delCabbage();
        fragmentShop.setScore(score,power_click);
    }

    // отправка дынных и возвращение в главную активити
    @Override
    public void clickBackShop()
    {
        Intent intent = new Intent();
        intent.putExtra("score", score);
        intent.putExtra("power_click", power_click);
        intent.putExtra("carrot",carrot_flag);
        intent.putExtra("cabbage", cabbage_flag);
        setResult(RESULT_OK,intent);
        finish();
    }

    // передача значений фрагменту при его создании
    @Override
    public void initShop()
    {
        BlankFragmentShop fragmentShop = (BlankFragmentShop) getSupportFragmentManager().findFragmentById(R.id.fragment_shop);
        fragmentShop.init(score,power_click,carrot_flag,cabbage_flag,horizontal);
    }


}
