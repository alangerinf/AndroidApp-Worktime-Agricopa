package pe.worktime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;
import com.worktime.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import pe.worktime.app.Application;

public class EscanearQR extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView vistaScanner;
    public static String dato = "";
    public static String mensaje = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escanear_qr);
        
        escanear();

    }

    private void escanear() {
        vistaScanner = new ZXingScannerView(this);
        setContentView(vistaScanner);
        vistaScanner.setResultHandler(this);
        vistaScanner.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        String dni = result.getText();
        if(isNumeric(dni) && dni.length()==8){

            dato = dni;



        }else{
            dato = "";
            mensaje = "QR Invalido";
        }
        vistaScanner.stopCamera();
        finish();
    }

    private boolean isNumeric(String dato) {
        try{
            double nro = Double.parseDouble(dato);
        }catch(NumberFormatException ex){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        vistaScanner.stopCamera();
        finish();
    }
}
