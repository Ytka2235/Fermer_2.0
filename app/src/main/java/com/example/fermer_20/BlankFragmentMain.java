package com.example.fermer_20;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragmentMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragmentMain extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public boolean horizontal;


    // интерфейс для связи с активити
    interface OnFragmentSendDataMainListener
    {
        void clickImg();
        void clickUpgrade();
        void clickNextImg();
        void clickBackImg();
        void initMain();
    }
    private OnFragmentSendDataMainListener fragmentSendDataMainListener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataMainListener = (OnFragmentSendDataMainListener) context;
        } catch (ClassCastException e) {}
    }
    public BlankFragmentMain() {
        // Required empty public constructor
        super(R.layout.fragment_main);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragmentMain newInstance(String param1, String param2) {
        BlankFragmentMain fragment = new BlankFragmentMain();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    // вызов метода для получения обновлённых данных
    @Override
    public void onResume () {
        fragmentSendDataMainListener.initMain();
        super.onResume();
    }
    // определения слушателей для каждого элемента фрагмента
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ImageView img = view.findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSendDataMainListener.clickImg();
            }
        });
        Button but_next = view.findViewById(R.id.but_next);
        but_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSendDataMainListener.clickNextImg();
            }
        });
        Button but_back = view.findViewById(R.id.but_back);
        but_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSendDataMainListener.clickBackImg();
            }
        });
        Button but_up = view.findViewById(R.id.but_up);
        but_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSendDataMainListener.clickUpgrade();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    // Отоброжениа полученых данных
    public void init(Integer score,Integer power_click,Integer position ,boolean horizontal)
    {
        setScore(score);
        setPowerClick(power_click);
        setPosition(position);
        this.horizontal = horizontal;
        if(horizontal)
        {
            LinearLayout layout = getView().findViewById(R.id.master);
            Button but = getView().findViewById(R.id.but_back);
            layout.removeView(but);
        }
    }
    // отоброжение очков
    public void setScore(Integer score)
    {
        TextView view = getView().findViewById(R.id.text_score);
        view.setText(""+score);
    }

    // отоброжение силы клика
    public void setPowerClick(Integer powerClick)
    {
        TextView txt = getView().findViewById(R.id.text_power_click);
        txt.setText(""+powerClick);
    }
    // переключение между картинками
    public void setPosition(Integer position) {

        if(position == 1)
        {
        ImageView img1 = getView().findViewById(R.id.imageView);
        img1.setBackgroundResource(R.drawable.potato);
        Button but1 = getView().findViewById(R.id.but_back);
        but1.setEnabled(false);
        Button but2 = getView().findViewById(R.id.but_next);
        but2.setEnabled(true);
        }
        if(position == 2) {
            ImageView img2 = getView().findViewById(R.id.imageView);
            img2.setBackgroundResource(R.drawable.carrot);
            Button but3 = getView().findViewById(R.id.but_back);
            but3.setEnabled(true);
            Button but4 = getView().findViewById(R.id.but_next);
            but4.setEnabled(true);
        }
        if(position == 3)
        {
                ImageView img3 = getView().findViewById(R.id.imageView);
                img3.setBackgroundResource(R.drawable.cabbage);
                Button but5 = getView().findViewById(R.id.but_back);
                but5.setEnabled(true);
                Button but6 = getView().findViewById(R.id.but_next);
                but6.setEnabled(false);
        }
    }
}