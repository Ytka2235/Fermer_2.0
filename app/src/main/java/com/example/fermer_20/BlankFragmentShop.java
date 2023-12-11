package com.example.fermer_20;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentShop#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentShop extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentSendDataShopListener fragmentSendDataShopListener;
    public boolean horizontal;

    // интерфейс для связи с активити
    interface OnFragmentSendDataShopListener
    {
        void clickUpShop();
        void clickCarrot();
        void clickCabbage();
        void clickBackShop();
        void initShop();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataShopListener = (BlankFragmentShop.OnFragmentSendDataShopListener) context;
        } catch (ClassCastException e) {}
    }


    public BlankFragmentShop() {
        // Required empty public constructor
        super(R.layout.fragment_blank_shop);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragmentShop.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragmentShop newInstance(String param1, String param2) {
        BlankFragmentShop fragment = new BlankFragmentShop();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // определение слушателей для каждой кнопки
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_shop, container, false);
        Button but_back = view.findViewById(R.id.but_back);
        but_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSendDataShopListener.clickBackShop();
            }
        });
        Button but_up = view.findViewById(R.id.but_up);
        but_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSendDataShopListener.clickUpShop();
            }
        });
        Button but_carrot = view.findViewById(R.id.but_carrot);
        but_carrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSendDataShopListener.clickCarrot();
            }
        });
        Button but_cabbage = view.findViewById(R.id.but_cabbage);
        but_cabbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSendDataShopListener.clickCabbage();
            }
        });

        return view;
    }

    // вызов метода для получения обновлённых данных
    @Override
    public void onResume() {
        super.onResume();
        fragmentSendDataShopListener.initShop();
    }

    // метод для отоброжения счёта
    public void setScore(Integer score, Integer power_click)
    {
        TextView txt = getView().findViewById(R.id.txt_score);
        txt.setText("Ваши очки:" + score);
        TextView txt_click = getView().findViewById(R.id.text_power);
        txt_click.setText("Cила клика: "+power_click);
        TextView txt_click_up = getView().findViewById(R.id.text_up);
        txt_click_up.setText("Апгрейд стоит: " + (int)(power_click * 10 * Math.sqrt(power_click)));
        if(score >= (int)(power_click * 10 * Math.sqrt(power_click)))
        {
            Button but = getView().findViewById(R.id.but_up);
            but.setEnabled(true);
        }
        if(score < (int)(power_click * 10 * Math.sqrt(power_click)))
        {
            Button but = getView().findViewById(R.id.but_up);
            but.setEnabled(false);
        }
        try
        {
            if(score < 50)
            {
                Button but = getView().findViewById(R.id.but_carrot);
                but.setEnabled(false);
            }
            if(score >= 50)
            {
                Button but = getView().findViewById(R.id.but_carrot);
                but.setEnabled(true);
            }
        }
        catch (Exception e) {}
        try
        {
            if(score < 100)
            {
                Button but = getView().findViewById(R.id.but_cabbage);
                but.setEnabled(false);
            }
            if(score >= 100)
            {
                Button but = getView().findViewById(R.id.but_cabbage);
                but.setEnabled(true);
            }
        }
        catch (Exception e) {}
    }

    // метод для удаления моркови
    public void delCarrot()
    {
        LinearLayout layout = getView().findViewById(R.id.master);
        TextView txt_carrot = getView().findViewById(R.id.text_carrot);
        Button but = getView().findViewById(R.id.but_carrot);
        layout.removeView(txt_carrot);
        layout.removeView(but);
    }

    //метод для удаления капусты
    public void delCabbage()
    {
        LinearLayout layout = getView().findViewById(R.id.master);
        TextView txt_cabbage = getView().findViewById(R.id.text_cabbage);
        Button but = getView().findViewById(R.id.but_cabbage);
        layout.removeView(txt_cabbage);
        layout.removeView(but);
    }

    // Ввод первоночальных значений
    public void init(Integer score,Integer power_click,Boolean carrot,Boolean cabbage, Boolean horizontal)
    {
        this.horizontal = horizontal;
        setScore(score,power_click);
        if(carrot) delCarrot();
        if(cabbage) delCabbage();
        if(horizontal)
        {
            LinearLayout layout = getView().findViewById(R.id.master);
            Button but = getView().findViewById(R.id.but_back);
            layout.removeView(but);
        }
    }
}