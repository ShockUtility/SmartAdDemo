package kr.docs.smartaddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import kr.docs.smartad.SmartAdBanner;

public class BannerActivity extends AppCompatActivity implements SmartAdBanner.OnSmartAdBannerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
    }

    @Override
    public void onSmartAdBannerDone(int type) {
        Log.d("BannerActivity", "onSmartAdBannerDone : " + type);
    }

    @Override
    public void onSmartAdBannerFail(String lastError) {
        Log.d("BannerActivity", "onSmartAdBannerFail : " + lastError);
    }
}
