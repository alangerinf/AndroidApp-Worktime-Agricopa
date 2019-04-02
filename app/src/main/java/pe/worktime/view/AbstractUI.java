/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.worktime.view;

import android.app.Activity;

/**
 *
 * @author Shai Almog
 * Modificado por  Alpha
 * 
 */
public abstract class AbstractUI {
/*
    /* TITULO DE PANTALLA */
    public abstract String getTitle();

    /* TEXTO DE AYUDA */
    public abstract String getHelp();

    /* FONDO FRM AYUDA */
    //public Image getBgHelp() {
      //  return null;
    //}

    /* ICONO MUESTRA */
    //public Image getIcon() {
      //  return null;
   // }

    /* CORRE - CREA COMPONENTE */
    protected abstract void execute(Activity f) throws Exception;

    protected abstract void showInfo(Activity f) throws Exception; 

    /* CORE - MUESTRA UI */
    public final void run() throws Exception {
        
    }

    /* GET LISTENER */
   // protected ActionListener getActionListener() {
    //    return null;
    //}

    /* BACK COMMMAND */
    //protected Command getCommandBack() {
        
    //}

    /* HELP COMMMAND */
    //protected Command getCommandHelp(final Form bgFrm) {
        
    //}

    protected final void showHelpForm(final Activity bgFrm) {
       
    }
    
}
