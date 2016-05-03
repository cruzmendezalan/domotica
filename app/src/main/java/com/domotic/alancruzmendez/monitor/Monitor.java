package com.domotic.alancruzmendez.monitor;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by alancruzmendez on 22/04/16.
 */
public class Monitor {
    private BluetoothAdapter bluetooth;
    private Activity activity;
    private ArrayList<String> dispositivos;
    public Monitor(Activity activity){
        this.activity = activity;
        this.bluetooth = BluetoothAdapter.getDefaultAdapter();
        if (bluetooth == null){
            //Toast.makeText(getApplicationContext())
            return;
        }
        if (!bluetooth.isEnabled()){
            Intent enableBtIntent  = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            int REQUEST_ENABLE_BT = 0;
            this.activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

    }
    public boolean setInfoBluetooth(){
        String res = "";
        Set<BluetoothDevice> dispositivos = this.bluetooth.getBondedDevices();
        if (dispositivos.size() > 0){
            for (BluetoothDevice device: dispositivos){
                this.dispositivos.add(device.getName()+"\n"+device.getAddress());
            }
            return true;
        }
        return false;
    }
}
