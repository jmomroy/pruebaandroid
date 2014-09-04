package com.example.gol;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
//creamos estos tres elementos
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
//librerías para el servicio.
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
//librerías de apoyo para ksoap
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends Activity implements OnClickListener{

	Button btn;
	EditText result;	
	
	// Metodo que queremos ejecutar en el servicio web
		private static final String METODO = "GetCitiesByCountry";
		// Namespace definido en el servicio web
		private static final String NAMESPACE = "http://www.webserviceX.NET";
		// namespace + metodo
		private static final String ACCIONSOAP = "http://www.webserviceX.NET/GetCitiesByCountry";
		// Fichero de definicion del servcio web
		private static final String URL = "http://www.webservicex.net/globalweather.asmx";
	
	//Declaracion de variables para consuymir el web service
	private SoapObject request=null;
	private SoapSerializationEnvelope envelope=null;
	private SoapPrimitive  resultsRequestSOAP=null;
	private HttpTransportSE transporte=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		result = (EditText) findViewById(R.id.editText1);
		btn = (Button) findViewById(R.id.button1);
		

		//agregamos el escuchador del botón.
		btn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		//String num01 = etSumando1.getText().toString();
		//String num02 = etSumando2.getText().toString();
		//tvResult.setText(num01 + num02);
		
				 
		    // Modelo el request
		 request = new SoapObject(NAMESPACE, METODO);
		    request.addProperty("CountryName", "peru"); // Paso parametros al WS
		    //request.addProperty("ToCurrency", "PEN"); // Paso parametros al WS
		 
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
			    btn.setText(resultsRequestSOAP.toString());
			    
			    
		    } catch (IOException e){
		    	e.printStackTrace();
		    } catch (XmlPullParserException e) {
		    	e.printStackTrace();
		    }
		 
		    
		 
	}

}
