package com.example.jim.wangxuewei20180115;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {
    protected static final int ERROR_DOWNLOAD = 0;
    protected static final int SET_PROGRESS_MAX = 1;
    protected static final int UPDATE_PROGRESS = 2;
    @BindView(R.id.ed_path)
    EditText edPath;
    @BindView(R.id.btn_download)
    Button btnDownload;
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.tv_info)
    TextView tv_info;

    private DownloadManager manager;
    //handler操作
    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ERROR_DOWNLOAD:
                    //提示用户下载失败
                    Toast.makeText(Main2Activity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    break;
                case SET_PROGRESS_MAX:
                    //得到最大值
                    int max = (Integer) msg.obj;
                    //设置进度条的最大值
                    pb.setMax(max);
                    break;
                case UPDATE_PROGRESS:
                    //获取当前下载的长度
                    int currentprogress = pb.getProgress();
                    //获取新下载的长度
                    int len = (Integer) msg.obj;
                    //计算当前总下载长度
                    int crrrentTotalProgress = currentprogress + len;
                    pb.setProgress(crrrentTotalProgress);

                    //获取总大小
                    int maxProgress = pb.getMax();
                    //计算百分比
                    float value = (float) currentprogress / (float) maxProgress;
                    int percent = (int) (value * 100);
                    //显示下载的百分比
                    tv_info.setText("下载:" + percent + "%");
                    break;
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        this.manager = new DownloadManager();
    }

    @OnClick(R.id.btn_download)
    public void onViewClicked() {
        final String path = "http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4";

        //下载
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    manager.download(path, new ProgressBarListener() {
                        @Override
                        public void getMax(int length) {
                            // TODO Auto-generated method stub
                            Message message = new Message();
                            message.what = SET_PROGRESS_MAX;
                            message.obj = length;
                            mHandler.sendMessage(message);
                        }

                        @Override
                        public void getDownload(int length) {
                            // TODO Auto-generated method stub
                            Message message = new Message();
                            message.what = UPDATE_PROGRESS;
                            message.obj = length;
                            mHandler.sendMessage(message);
                        }
                    });
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                    Message message = new Message();
                    message.what = ERROR_DOWNLOAD;
                    mHandler.sendMessage(message);
                }
            }
        }).start();

    }
}
