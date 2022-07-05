package com.example.testpermission

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
private val bluetootAdapter:BluetoothAdapter?=BluetoothAdapter.getDefaultAdapter()
private val REQUEST_CODE_ENABLE_BT=100
//private val REQUEST_CODE_DISCOVERABLE_BT=200
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bluetoothAvailable()
        //Boton inicio
        onBtn.setOnClickListener {
            BluetoothOn()
        }
        offBtn.setOnClickListener{
            BluetoothOff()
        }

//        DiscoverableBtn.setOnClickListener {
  //          BluetoothDiscoverable()
  //      }

    }

/*
    private fun BluetoothDiscoverable() {
        val bluetoothAdapter:BluetoothAdapter?= BluetoothAdapter.getDefaultAdapter()
        if(bluetoothAdapter?.isDiscovering==false){
            Toast.makeText(this,"Buscando", Toast.LENGTH_SHORT).show()
            val intent=Intent(Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE))
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADVERTISE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
               requestBluetoothPermission()
            }
            startActivityForResult(intent, REQUEST_CODE_DISCOVERABLE_BT)

        }
    }
*/
    private fun BluetoothOff() {
        val bluetoothAdapter:BluetoothAdapter?= BluetoothAdapter.getDefaultAdapter()
        if(bluetoothAdapter?.isEnabled==false){
            Toast.makeText(this,"No esta papu", Toast.LENGTH_SHORT).show()
        }else{
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestBluetoothPermission()

            }
            bluetoothAdapter?.disable()
            Toast.makeText(this,"Ya lo apague Oto", Toast.LENGTH_SHORT).show()
        }
    }

    /*
        private fun checkPermissionOfBluetooth() {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.BLUETOOTH_CONNECT)
            !=PackageManager.PERMISSION_GRANTED) {
                requestBluetoothPermission()
            }else{
                //BluetoothOn()
            }
        }
    */
    private fun BluetoothOn() {
        val intent=Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED) {
            requestBluetoothPermission()
        }
        startActivityForResult(intent, REQUEST_CODE_ENABLE_BT)
        Toast.makeText(this, "Bluetooth On", Toast.LENGTH_SHORT).show()

    }

    private fun requestBluetoothPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.BLUETOOTH_CONNECT)
            /*&& ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.BLUETOOTH_ADVERTISE)*/){
            Toast.makeText(this,"permisos rechazados", Toast.LENGTH_SHORT).show()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH_CONNECT), REQUEST_CODE_ENABLE_BT)
            //ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH_ADVERTISE), REQUEST_CODE_DISCOVERABLE_BT)
        }

    }
/*
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_ENABLE_BT){
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                BluetoothOn()
            } else{
                //El permiso no ha sido aceptado
                Toast.makeText(this,"permisos rechazados por primera vez", Toast.LENGTH_SHORT).show()
            }
        }
    }
*/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when(requestCode){
            REQUEST_CODE_ENABLE_BT->
                if(requestCode== Activity.RESULT_OK){
                    Toast.makeText(this, "Listo Ome Gonorre", Toast.LENGTH_SHORT).show()
                    BluetoothOn()
                }
                else{
                //El permiso no ha sido aceptado
                Toast.makeText(this,"permisos rechazados por primera vez", Toast.LENGTH_SHORT).show()
                }
/*
            REQUEST_CODE_DISCOVERABLE_BT->
                if(requestCode==Activity.RESULT_OK){
                    Toast.makeText(this,"Discoverable papu", Toast.LENGTH_SHORT).show()
                    BluetoothDiscoverable()

                }else{
                    Toast.makeText(this,"Discoverablen't papu", Toast.LENGTH_SHORT).show()
                }*/
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun bluetoothAvailable() {
        val bluetoothAdapter:BluetoothAdapter?=BluetoothAdapter.getDefaultAdapter()
        if(bluetoothAdapter == null){
            statusBluetooth.text="Bluetooth is not available"
        }else {
            statusBluetooth.text = "Bluetooth is available"
        }

    }
}