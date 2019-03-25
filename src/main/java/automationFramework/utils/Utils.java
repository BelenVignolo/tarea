package automationFramework.utils;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static String applyDefaultIfMissing(String variable, String defaultValue) {
        if (variable == null) {
            variable = defaultValue;
            System.out.println("Default " + defaultValue + " execution was applied since was not provided");
            System.out.println();
        }

        return variable;
    }

    public static int calcularDigitoVerificador(String cedula) {
        int a  = 0;
        for (int i = cedula.length(); i < 7; i++) {
            cedula = "0" + cedula;
        }
        for (int i = 0; i < 7; i++) {
            a += (Integer.parseInt("2987634".charAt(i) + "") * Integer.parseInt(cedula.charAt(i) + "")) % 10;
        }
        return a % 10 == 0 ? 0 : 10 - a % 10;
    }


    public static boolean verificarPago(int mesActual,int mesAnt) {
        boolean rechaza=false;
        if(mesAnt!=mesActual) {
            if (mesActual != 1) {
                if (mesAnt + 1 == mesActual)
                    rechaza = false;
                else
                    rechaza = true;

            } else if (mesActual == 1 || mesAnt == 12)
                rechaza = false;
            else
                rechaza = true;
        }
        else
            rechaza=false;


        return rechaza;
    }


    public static int getMonthFromDate(Date date) {
        if (null == date) {
            return 0;
        } else {
            String formatMonth = "MM";
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatMonth);
            return Integer.parseInt(dateFormat.format(date));
        }
    }

    public static Date formatoDate( String date) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.parse(date);
    }

    public static Boolean nombreEstado(String nombre, String productos){
        //"PLTTSM;PLTTEJ;PLKMIG;PLKBA3;PLKI32;PLTTSS;PLKSS2;PLTTSM;PLTTHO;Equipos"
        boolean existe=false;
        String[] productSplit=productos.split(";");
        for (int j = 0; j <= productSplit.length-1; j++) {
                if(nombre.contains(productSplit[j])){
                    existe=true;
                    break;
                }
        }
        return existe;
    }


    public static Date AddSixMonth( String fchCreacion)throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fchDate= dateFormat.parse(fchCreacion);
        Calendar c= Calendar.getInstance();
        c.setTime(fchDate);
        c.add(Calendar.MONTH,6);
        fchDate=c.getTime();
        return fchDate;
    }


    public static String today() {
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(today);
    }

    public static String now() {
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        return dateFormat.format(today);
    }

    public static String numeroTarjetaParaOCA(String numeroTarjeta) {
        if (numeroTarjeta.length() > 9) {
            numeroTarjeta = numeroTarjeta.substring(numeroTarjeta.length() - 9);
        }
        if (numeroTarjeta.equals("")) {
            numeroTarjeta = "000000000";
        }
        return numeroTarjeta;
    }

    public static String numeroTarjetaParaCreditel(String numeroTarjeta) {
        if (numeroTarjeta.length() == 6) {
            numeroTarjeta = "0000000" + numeroTarjeta + "000";
        }
        return numeroTarjeta;
    }

    public static String cedulaParaOCA(String ci) {
        int digito = Utils.calcularDigitoVerificador(ci);
        return ci + digito;
    }

    public static void displayTray(String text) {
        try {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            //Alternative (if the icon is on the classpath):
            //Image image = Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));
            TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
            //Let the system resizes the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip("System tray icon demo");
            tray.add(trayIcon);
            trayIcon.displayMessage(text, "", TrayIcon.MessageType.INFO);
        }
        catch (Exception e) {}
    }


}

