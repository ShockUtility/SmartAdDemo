package kr.docs.smartaddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import kr.docs.smartad.SmartAd;
import kr.docs.smartad.SmartAdAlert;
import kr.docs.smartad.SmartAdAward;
import kr.docs.smartad.SmartAdInterstitial;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
        , SmartAdInterstitial.OnSmartAdInterstitialListener
        , SmartAdAward.OnSmartAdAwardListener
{

    int mType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Setting Your Test Device Hash
        ////////////////////////////////////////////////////////////////////////////////////////////
        SmartAd.addTestDevice(SmartAd.AD_TYPE_GOOGLE,   "DDBBB66635665E4CCC3BAB2F16387525");
        SmartAd.addTestDevice(SmartAd.AD_TYPE_GOOGLE,   "E00E9E00ED1B543E38E01E0741305BC0");
        SmartAd.addTestDevice(SmartAd.AD_TYPE_FACEBOOK, "1d421c239cf937cfb568a6d343568f5a");
        SmartAd.addTestDevice(SmartAd.AD_TYPE_FACEBOOK, "cee0c18d9bc1e1a4027782d21b4eff9c");
        ////////////////////////////////////////////////////////////////////////////////////////////

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);

        findViewById(R.id.radioButton1).setOnClickListener(this);
        findViewById(R.id.radioButton2).setOnClickListener(this);
        findViewById(R.id.radioButton3).setOnClickListener(this);
    }

    @SmartAd.SmartAdType
    int getAdOrder() {
        if (mType == SmartAd.AD_TYPE_GOOGLE) return SmartAd.AD_TYPE_GOOGLE;
        if (mType == SmartAd.AD_TYPE_FACEBOOK) return SmartAd.AD_TYPE_FACEBOOK;
        return SmartAd.AD_TYPE_RANDOM;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1      : showBanner();       break;
            case R.id.button2      : showInterstitial(); break;
            case R.id.button3      : showAward();        break;
            case R.id.button4      : showAlert();        break;
            case R.id.button5      : showConfirm();      break;
            case R.id.button6      : showSelect();       break;
            case R.id.radioButton1 : mType = 0;           break;
            case R.id.radioButton2 : mType = 1;           break;
            case R.id.radioButton3 : mType = 2;           break;
        }
    }

    static public String adTypeToString(int adType) {
        switch (adType) {
            case SmartAd.AD_TYPE_GOOGLE: return "Google Ad";
            case SmartAd.AD_TYPE_FACEBOOK: return "Facebook Ad";
        }
        return "Pass Ad";
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // SmartAdBanner ///////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void showBanner() {
        Intent intent = new Intent(this, BannerActivity.class);
        startActivity(intent);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////
    // SmartAdInterstitial
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void showInterstitial() {
        SmartAdInterstitial.showAd(this,
                getAdOrder(),
                SmartAd.TEST_BANNER_GOOGLE,     // Setting your Google ad ID
                SmartAd.TEST_BANNER_FACEBOOK,   // Setting your Facebook ad ID
                true);
    }

    // OnSmartAdInterstitialListener

    @Override
    public void onSmartAdInterstitialDone(int adType) {
        Log.i("***", "onSmartAdInterstitialDone: "+adTypeToString(adType));
    }

    @Override
    public void onSmartAdInterstitialFail(int adType) {
        Log.i("***", "onSmartAdInterstitialDone: "+adTypeToString(adType));
    }

    @Override
    public void onSmartAdInterstitialClose(int adType) {
        Log.i("***", "onSmartAdInterstitialClose: "+adTypeToString(adType));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////
    // SmartAdAward
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void showAward() {
        SmartAdAward.showAd(this,
                getAdOrder(),
                SmartAd.TEST_BANNER_GOOGLE,     // Setting your Google ad ID
                SmartAd.TEST_BANNER_FACEBOOK);  // Setting your Facebook ad ID
    }

    // OnSmartAdAwardListener

    @Override
    public void onSmartAdAwardDone(int adType, boolean isAwardShown, boolean isAwardClicked) {
        Log.i("***", "onSmartAdAwardFail: "+adTypeToString(adType)+", Ad Shown = "+isAwardShown+", Ad Clicked = "+isAwardClicked);
    }

    @Override
    public void onSmartAdAwardFail(int adType) {
        Log.i("***", "onSmartAdAwardFail: "+adTypeToString(adType));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////
    // SmartAdAlert
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void showAlert() {
        SmartAdAlert.alert(this,
                getAdOrder(),
                SmartAd.TEST_BANNER_GOOGLE,     // Setting your Google ad ID
                SmartAd.TEST_BANNER_FACEBOOK,   // Setting your Facebook ad ID
                "Alert Dialog",
                new SmartAdAlert.SmartAdAlertListener() {
                    @Override
                    public void result(int buttonType) {
                        switch (buttonType) {
                            case SmartAdAlert.BUTTON_OK:
                                Toast.makeText(MainActivity.this, "SmartAdAlert Alert : OK", Toast.LENGTH_LONG).show();
                                break;
                            case SmartAdAlert.BUTTON_BACK:
                                Toast.makeText(MainActivity.this, "SmartAdAlert Alert : Back", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
    }

    private void showConfirm() {
        SmartAdAlert.confirm(this,
                getAdOrder(),
                SmartAd.TEST_BANNER_GOOGLE,     // Setting your Google ad ID
                SmartAd.TEST_BANNER_FACEBOOK,   // Setting your Facebook ad ID
                "Confirm Dialog",
                new SmartAdAlert.SmartAdAlertListener() {
                    @Override
                    public void result(int buttonType) {
                        switch (buttonType) {
                            case SmartAdAlert.BUTTON_OK:
                                Toast.makeText(MainActivity.this, "SmartAdAlert Confirm : OK", Toast.LENGTH_LONG).show();
                                break;
                            case SmartAdAlert.BUTTON_CANCEL:
                                Toast.makeText(MainActivity.this, "SmartAdAlert Confirm : Cancel", Toast.LENGTH_LONG).show();
                                break;
                            case SmartAdAlert.BUTTON_BACK:
                                Toast.makeText(MainActivity.this, "SmartAdAlert Confirm : Back", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
    }

    private void showSelect() {
        SmartAdAlert.select(this,
                getAdOrder(),
                SmartAd.TEST_BANNER_GOOGLE,     // Setting your Google ad ID
                SmartAd.TEST_BANNER_FACEBOOK,   // Setting your Facebook ad ID
                "Select Dialog",
                "Yes",
                "No",
                new SmartAdAlert.SmartAdAlertListener() {
                    @Override
                    public void result(int buttonType) {
                        switch (buttonType) {
                            case SmartAdAlert.BUTTON_OK:
                                Toast.makeText(MainActivity.this, "SmartAdAlert Select : OK", Toast.LENGTH_LONG).show();
                                break;
                            case SmartAdAlert.BUTTON_CANCEL:
                                Toast.makeText(MainActivity.this, "SmartAdAlert Select : Cancel", Toast.LENGTH_LONG).show();
                                break;
                            case SmartAdAlert.BUTTON_BACK:
                                Toast.makeText(MainActivity.this, "SmartAdAlert Select : Back", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                });
    }
}
