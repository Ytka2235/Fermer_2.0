package com.example.fermer_20;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity  implements BlankFragmentMain.OnFragmentSendDataMainListener{
    public Integer score;
    public Integer power_click;
    public Boolean carrot_flag;
    public Boolean cabbage_flag;
    public Integer position;
    public boolean horizontal;

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // принимаем и сохраняем результаты
                    if(result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent intent = result.getData();
                        score = intent.getIntExtra("score",0);
                        power_click = (intent.getIntExtra("power_click",1));
                        carrot_flag = intent.getBooleanExtra("carrot",false);
                        cabbage_flag = intent.getBooleanExtra("cabbage",false);
                        initMain();
                    }
                }
            });

    // определение начальных значений
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = 0;
        power_click = 1;
        position = 1;
        cabbage_flag = false;
        carrot_flag = false;
        horizontal = false;
    }
    // обработка нажатия на изображение
    @Override
    public void clickImg()
    {
        score = score + power_click * position;
        BlankFragmentMain fragmentMain = (BlankFragmentMain) getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        fragmentMain.setScore(score);
        if(horizontal)
        {
            BlankFragmentShop fragmentShop = (BlankFragmentShop) getSupportFragmentManager().findFragmentById(R.id.fragment_shop);
            fragmentShop.setScore(score,power_click);
        }
    }

    // переход в дочерние активити
    @Override
    public void clickUpgrade()
    {
        if(!horizontal)
        {
            Intent intent = new Intent(this,ActivityShop.class);
            intent.putExtra("score", score);
            intent.putExtra("power_click", power_click);
            intent.putExtra("carrot",carrot_flag);
            intent.putExtra("cabbage", cabbage_flag);
            mStartForResult.launch(intent);
        }
    }
    // следуещие ихображение
    @Override
    public void clickNextImg()
    {
        if(position == 2 && cabbage_flag)
        {
            position++;
            BlankFragmentMain fragmentMain = (BlankFragmentMain) getSupportFragmentManager().findFragmentById(R.id.fragment_main);
            fragmentMain.setPosition(position);
        }
        if(position == 1 && carrot_flag)
        {
            position++;
            BlankFragmentMain fragmentMain = (BlankFragmentMain) getSupportFragmentManager().findFragmentById(R.id.fragment_main);
            fragmentMain.setPosition(position);
        }
    }
    // предыдущее изображение
    @Override
    public void clickBackImg()
    {
        if(position > 1)
        {
            position--;
            BlankFragmentMain fragmentMain = (BlankFragmentMain) getSupportFragmentManager().findFragmentById(R.id.fragment_main);
            fragmentMain.setPosition(position);
        }
    }
    // передача значений фрагменту при его создании
    @Override
    public void initMain()
    {
        BlankFragmentMain fragmentMain = (BlankFragmentMain) getSupportFragmentManager().findFragmentById(R.id.fragment_main);
        fragmentMain.init(score,power_click,position,horizontal);
    }

}