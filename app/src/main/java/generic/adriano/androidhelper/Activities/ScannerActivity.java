package generic.adriano.androidhelper.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.zxing.Result;

import generic.adriano.androidhelper.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static generic.adriano.androidhelper.Helpers.UI.CreateBasicSnack;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler  {

    // Class elements
    Context classContext;
    View classView;
    ZXingScannerView mScannerView;

    // View elements declaration
    Button bScan, bFlash;

    public ScannerActivity(Context context, View view){

        classContext = context;
        classView = view;

        ((Activity)classContext).setTitle("Scanner");

        // Request permissions
        ActivityCompat.requestPermissions((Activity) classContext,
                new String[]{Manifest.permission.CAMERA},
                1);

        ViewGroup contentFrame = (ViewGroup) classView.findViewById(R.id.content_frame);
        mScannerView = new ZXingScannerView(classContext);
        contentFrame.addView(mScannerView);

        // View elements initialization
        bScan = classView.findViewById(R.id.bScan);
        bFlash = classView.findViewById(R.id.bFlash);

        // View elements listeners
        bScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission( classContext, Manifest.permission.CAMERA ) == PackageManager.PERMISSION_GRANTED){  // Check camera permissions
                    mScannerView.setResultHandler(ScannerActivity.this);
                    mScannerView.startCamera();
                }else{
                    CreateBasicSnack("Access to the camera is required to use this application.", null, null, classContext);
                }
            }
        });

        bFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScannerView.setFlash(!mScannerView.getFlash());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(ScannerActivity.this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        String result = rawResult.getText();
        String format = rawResult.getBarcodeFormat().toString();

        Log.i("SCNR.HANDLE.FORMAT", format);
        CreateBasicSnack(result, null, null, classContext);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(ScannerActivity.this);
            }
        }, 2000);
    }
}
