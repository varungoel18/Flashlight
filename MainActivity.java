package com.varungoel.torch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnTorch;
    Boolean isTorchOn = false;
    CameraManager cameraManager;
    String cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Boolean isTorchAvailable = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        btnTorch = findViewById(R.id.btn_Torch);
        if(!isTorchAvailable) {
            btnTorch.setEnabled(false);
            btnTorch.setText("TORCH IS NOT AVAILABLE");
        }
        try {
            cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (Exception e) {}
        btnTorch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTorchOn) {
                    //Turn off the Torch
                    isTorchOn = false;
                    btnTorch.setText("TURN ON TORCH");

                }
                else{
                    //Turn On The Torch
                    isTorchOn = true;
                    btnTorch.setText("TURN OFF TORCH");
                }
                switchTorch(isTorchOn);
            }
        });
    }
    public void switchTorch(boolean status) {
        try {
            cameraManager.setTorchMode(cameraId, status);
        } catch (Exception e) {
        }
    }
}