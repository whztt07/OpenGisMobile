package code.google.com;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;


	public class EnviarMail{
		
		private String emailUsuario;
		private String passUsuario;
		private String nombre;
		private String apellido;
	
	
	        public EnviarMail(String user,String emailUsuario,String passUsuario,String nombre,String apellido){
	                try{

	                    // Propiedades
	                    Properties prop = new Properties();
	                    //Host
	                    prop.setProperty("mail.smtp.host", "smtp.1and1.es"); //$NON-NLS-1$ //$NON-NLS-2$
	                    //Puerto
	                    prop.setProperty("mail.smtp.port", "587"); //$NON-NLS-1$ //$NON-NLS-2$
	                    //Usuario
	                    prop.setProperty("mail.smtp.user", "opengis@pipepito.es"); //$NON-NLS-1$ //$NON-NLS-2$
	                    prop.setProperty("mail.smtp.auth", "true"); //$NON-NLS-1$ //$NON-NLS-2$
	
	                    // Cargamos propiedades
	                    Session session = Session.getDefaultInstance(prop);
	                    
	                    MimeMessage message = new MimeMessage(session);
	                    message.setFrom(new InternetAddress("opengis@pipepito.es")); //$NON-NLS-1$
	                    message.addRecipient(
	                        Message.RecipientType.TO,
	                        //MAIL DEL USUARIO 
	                        new InternetAddress(emailUsuario));
	                    
	                    message.setSubject("msgMailPasswordRecovery"); //$NON-NLS-1$
	                   
	                    message.setText("msgMailHello"+" "+nombre+" "+apellido+" "+"msgMailYourPassword"+" "+passUsuario //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	                        );
	
	                    // Env’o
	                    Transport t = session.getTransport("smtp"); //$NON-NLS-1$
	                    t.connect("opengis@pipepito.es", "dai20112012"); //$NON-NLS-1$ //$NON-NLS-2$
	                    t.sendMessage(message, message.getAllRecipients());
	                    
	
	                    t.close();
	                    
	                }catch (Exception e)
	                {
	                    e.printStackTrace();
	                }
	                
	        }


	   
	}