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

    // OnSmartAdBannerListener

    @Override
    public void onSmartAdBannerDone(int adType) {
        Log.i("***", "onSmartAdBannerDone : " + MainActivity.adTypeToString(adType));
    }

    @Override
    public void onSmartAdBannerFail(int adType) {
        Log.i("***", "onSmartAdBannerFail : " + MainActivity.adTypeToString(adType));
    }
}
