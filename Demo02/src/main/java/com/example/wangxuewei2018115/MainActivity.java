package com.example.wangxuewei2018115;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.xiazai)
    Button xiazai;
    @BindView(R.id.zanting)
    Button zanting;
    @BindView(R.id.jixu)
    Button jixu;
    @BindView(R.id.quxiao)
    Button quxiao;
    @BindView(R.id.tv_progress)
    TextView tv;
    @BindView(R.id.pro)
    ProgressBar pro;
    @BindView(R.id.vv)
    VideoView vv;
    private DownLoadFile downLoadFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        File f = new File(Environment.getExternalStorageDirectory() + "/wenjian/");
        if (!f.exists()) {
            f.mkdir();
        }
        //存储地址
        String path = Environment.getExternalStorageDirectory() + "/wenjian/mm.mp4";
        //设置最大度
        pro.setMax(100);
        //实例化
        downLoadFile = new DownLoadFile(this,
                "http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4"
                , path, 10, new DownLoadFile.DownLoadListener() {
            @Override
            public void getProgress(int progress) {
                tv.setText("当前进度：" + progress + "%");
                pro.setProgress(progress);
            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
            }
        }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        downLoadFile.onDestroy();
    }

    @OnClick({R.id.xiazai, R.id.zanting, R.id.jixu, R.id.quxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xiazai:
                downLoadFile.downLoad();
                break;
            case R.id.zanting:
                downLoadFile.onPause();
                break;
            case R.id.jixu:
                downLoadFile.onStart();
                break;
            case R.id.quxiao:
                String path = Environment.getExternalStorageDirectory() + "/wenjian/mm.mp4";
                vv.setVideoPath(path);
                vv.start();
                break;
        }
    }
}
