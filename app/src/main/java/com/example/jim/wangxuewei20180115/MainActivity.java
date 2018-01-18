package com.example.jim.wangxuewei20180115;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_goon)
    Button btnGoon;
    @BindView(R.id.pro_one)
    ProgressBar proOne;
    @BindView(R.id.btn_startTwo)
    Button btnStartTwo;
    @BindView(R.id.btn_goonTwo)
    Button btnGoonTwo;
    @BindView(R.id.pro_two)
    ProgressBar proTwo;
    @BindView(R.id.btn_startThree)
    Button btnStartThree;
    @BindView(R.id.btn_goonThree)
    Button btnGoonThree;
    @BindView(R.id.pro_three)
    ProgressBar proThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        proOne.setProgress(20);

    }

    @OnClick({R.id.btn_start, R.id.btn_goon, R.id.btn_startTwo, R.id.btn_goonTwo, R.id.btn_startThree, R.id.btn_goonThree})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                break;
            case R.id.btn_goon:
                break;
            case R.id.btn_startTwo:
                break;
            case R.id.btn_goonTwo:
                break;
            case R.id.btn_startThree:
                break;
            case R.id.btn_goonThree:
                break;
        }
    }

    public void downLoad(){

    }
}
