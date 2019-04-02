package pe.worktime;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.worktime.R;

import java.util.ArrayList;
import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.entity.Productividad;
import pe.worktime.util.AbstractActivity;
import pe.worktime.view.CustomAlertDialogBuilder;

public class TipoProductividadActivity extends AbstractActivity {

    public static int viewLoad = R.layout.tipo_productividad;

    protected Button btnManual = null;
    protected Button btnMasivo = null;
    protected Button btnCargarLista = null;

    private String permiso;

    private static AbstractActivity that;

    @Override
    protected void postLoadView() {

        //permiso = Application.context.getUsuarioController().getSelected().getFlagautoloadmaster();

        that = this;
        btnCargarLista = (Button) findViewById(R.id.btn_tipo_cargar_lista);
        btnCargarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater factory = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View textEntryView = factory.inflate(
                        R.layout.add_product, null);
                final CustomAlertDialogBuilder alert = new CustomAlertDialogBuilder(
                        that);
                alert.setTitle("Cargar Lista Trabajadores");
                alert.setMessage("");
                alert.setView(textEntryView);
                final AlertDialog loginPrompt = alert.create();
                alert.setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                try {
                                    cargarTrabajadores();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                loginPrompt.dismiss();
                            }

                        });
                alert.setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog,
                                 int whichButton) {
                                 loginPrompt.dismiss();
                                 }
                });
                alert.setCancelable(false);
                alert.show();

            }
        });

        btnManual = (Button) findViewById(R.id.btn_tipo_entrada_manual);
        btnManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContextTest.idxTipoEntradaProductividad = 0;
                Intent i = new Intent(getApplicationContext(),ProductividadListaActivity.class);
                startActivity(i);
            };
        });

        btnMasivo = (Button) findViewById(R.id.btn_tipoEntrada_masivo);
        btnMasivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContextTest.idxTipoEntradaProductividad = 1;
                Intent i = new Intent(getApplicationContext(),ProductividadListaActivity.class);
                startActivity(i);
            }
        });

        //if(permiso.equalsIgnoreCase("False")){ btnManual.setVisibility(View.GONE); }

    }

    public void cargarTrabajadores() throws Exception {

        String codConsumidorProd = null;
        List<HorasConsumidor> cabecera = Application.context.getHorasConsumidorController().listar();
        if(cabecera == null || cabecera.isEmpty() ){
            Toast.makeText(getApplicationContext(), "No hay planillas registradas", Toast.LENGTH_SHORT).show();
            try {
                throw new Exception("Lista planillas vacia.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(cabecera.size() != 0) {

            List<HorasConsumidor> cabeceraFiltro = new ArrayList<>();
            for (HorasConsumidor itemF : cabecera) {
                if(itemF.getMigradoProd() == 0 && !itemF.isPlanillaCerrada() ) {
                    cabeceraFiltro.add(itemF);
                }
            }

            for (HorasConsumidor item : cabeceraFiltro) {


                        Application.context.getHorasConsumidorController().setSelected(item);
                        Application.context.getProductividadController().InitProductividad();
                        List<HorasConsumidorDetalle> detalle = Application.context.getHorasConsumidorController().getSelected().getDetalle();
                        for (HorasConsumidorDetalle item2 : detalle) {
                            if (!item2.getCodTrabajador().equalsIgnoreCase("")) {
                                try {
                                    codConsumidorProd = "";
                                    if(item2.getCodTurno().isEmpty() && !item2.getCodConsumidor().isEmpty()){
                                        codConsumidorProd = item2.getCodConsumidor();
                                    }else if(!item2.getCodTurno().isEmpty() && item2.getCodConsumidor().isEmpty()){
                                        codConsumidorProd = item2.getCodTurno();                           }


                                    Application.context.getProductividadController().addDetalle(item2.getCodTrabajador(), 0,codConsumidorProd);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        try {
                            Application.context.getProductividadController().saveHorasProductividad();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
            }

            for (int i = 0; i < cabeceraFiltro.size(); i++) {
                cabeceraFiltro.get(i).setMigradoProd(1);
            }

            Application.context.getHorasConsumidorController().getDelegate().setHorasConsumidors(Application.context.getUser(), cabeceraFiltro);
        }
            //----------------------------------------------------------------------------------------

    }

    @Override
    protected int getViewId() {
        return viewLoad;
    }

    @Override
    protected String getTextTitle() {
        return "Menu Productividad";
    }
}
