package code.google.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * Clase encargada de hacer las conversiones de RC a Coordenada XY, para posteriormente mostrar una parcela en concreto
 * @author knals
 *
 */
public class W3CSigPac {

        private String auxx="";
    private String auxy="";
    private String prueba="";
        
        
        public W3CSigPac (String u) throws IOException, KeyManagementException, NoSuchAlgorithmException{
        	
        	

       URL url = new URL(u);

        HttpsURLConnection conexion = (HttpsURLConnection) url.openConnection();
        //conexion.setDoOutput(true); // Con esto as’ funciona en Android 4.0 y dem‡s
        conexion.setDoInput(true);
        conexion.setRequestMethod("GET");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
      
        

        
        //JOptionPane.showMessageDialog(null, reader);
        String line;
        String x,y;
        int p=1;
        boolean bolx=true;
        boolean boly=true;
        do {
                line=reader.readLine();
                x=reader.readLine();
                y=reader.readLine();
                //tomamos la quinta linea donde se encuentran las coordenadas
                if (p==5){

                	
                	x = x.trim();
                	y = y.trim();
                	
                	x = x.substring(6);
                	x = x.substring(0,x.length() - 7);
                	
                	y = y.substring(6);
                	y = y.substring(0,y.length() - 7);
                	
                	auxx = x.trim();
                	auxy = y.trim();
                	
            }
            p=p+1;
        }while (line != null);
                System.out.println(auxx);
                System.out.println(auxy);
        
        conexion.setHostnameVerifier(new HostnameVerifier() {
        public boolean verify(String arg0, SSLSession arg1) {
             return true;
         }
        });
        //System.out.println(conn.getResponseCode());
        //System.out.println(conn);
        conexion.disconnect();
    }
    /**
     * Clase que nos aceptar‡ todos los certificados.
     * @author knals
     *
     */
    private class DefaultTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }
    public String getAuxx() {
                return auxx;
        }
        public String getAuxy() {
                return auxy;
        }
        
}