package com.example.prueba7;


import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements OnClickListener {
	Button btnEnviar;
	EditText dato1;
	EditText result;
	
	private static final String METODO = "GetCitiesByCountry";
	// Namespace definido en el servicio web
	private static final String NAMESPACE = "http://www.webserviceX.NET/";
	// namespace + metodo
	private static final String ACCIONSOAP = "http://www.webserviceX.NET/GetCitiesByCountry";
	// Fichero de definicion del servcio web
	private static final String URL = "http://www.webservicex.net/globalweather.asmx?WSDL";
	//Declaracion de variables para consuymir el web service
			private SoapObject request=null;
			private SoapSerializationEnvelope envelope=null;
			private SoapPrimitive  resultsRequestSOAP=null;
			private HttpTransportSE transporte=null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dato1 = (EditText) findViewById(R.id.editText1);
		btnEnviar = (Button) findViewById(R.id.button1);
		result = (EditText) findViewById(R.id.editText2);
				
		//agregamos el escuchador del botón.
		btnEnviar.setOnClickListener((android.view.View.OnClickListener) this);
        
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		// Modelo el request
	    request = new SoapObject(NAMESPACE, METODO);
	    request.addProperty("CountryName", "peru"); // Paso parametros al WS
	
	 
	    // Modelo el Sobre
	    envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.dotNet = true;
	    envelope.setOutputSoapObject(request);
	 
	    // Modelo el transporte
	    transporte = new HttpTransportSE(URL);
	    try {
		    // Llamada
		    transporte.call(ACCIONSOAP, envelope);
		 // Resultado
		    resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
		    result.setText(resultsRequestSOAP.toString()); 
	    } catch (IOException e){
	    	e.printStackTrace();
	    } catch (XmlPullParserException e) {
	    	e.printStackTrace();
	    }
		
			
	}
}
