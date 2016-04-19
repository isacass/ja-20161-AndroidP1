package com.example.androidp1;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Inicio extends Activity{

	EditText editUsuario;
	Button btnEnviar;
	Button btnSair;
	TextView textoRetorno;
	EditText editEmail = null;
	EditText editCidade = null;
	Spinner spinnerEstado;
	String[] paths1 = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RO", "RS", "RR", "SC", "SE", "SP", "TO" };
	Spinner spinnerAval;
	String[] paths2 = {"�timo","Bom","Normal","Ruim","P�ssimo"};
	boolean autentica = false;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicial);		
		
		setEditUsuario((EditText)findViewById(R.id.editUsuario));
		btnEnviar = (Button)findViewById(R.id.btnEnviar);	
		btnSair = (Button)findViewById(R.id.btnSair);	
		textoRetorno = (TextView) findViewById(R.id.retornoTextView);
		editEmail = (EditText)findViewById(R.id.editTextEmail);
		editCidade = (EditText) findViewById(R.id.editTextCidade);
		
		Bundle bundle = getIntent().getExtras();
		if (bundle.containsKey("USUARIO")){
			String usuario = bundle.getString("USUARIO");				
			textoRetorno.setText(usuario);
		}	
		
		ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, paths1);			
		spinnerEstado = (Spinner)findViewById(R.id.spinnerEstado);
		spinnerEstado.setAdapter(adapter1);

		
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, paths2);	
		spinnerAval  = (Spinner)findViewById(R.id.spinnerAval);		
		spinnerAval.setAdapter(adapter2);
		
		
		btnEnviar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {	
				
				String email = editEmail.getText().toString();	
				String usuario = textoRetorno.getText().toString();
				String cidade = editCidade.getText().toString();	
				
				if(email.equals("") || cidade.equals("")){
					Toast.makeText(getApplicationContext(), "Aten��o! Favor verifique se os seus dados foram inseridos!", Toast.LENGTH_LONG).show();
					
					}else{
						
						AlertDialog.Builder mensagem = new AlertDialog.Builder(Inicio.this);
						mensagem.setTitle("Ol�  " + usuario);
						mensagem.setMessage("Obrigada pela sua colabora��o! Seus dados foram enviados com sucesso! As informa��es fornecidas para contato s�o:   email:" +email+ "   cidade:" +cidade+" . Agradecemos sua colabora��o!");						
						mensagem.setNeutralButton("fechar",null);
						mensagem.show();				
					}
				}		
	       
		});
		
		btnSair.setOnClickListener(new OnClickListener() {
		
		public void onClick(View v) {				
			finish();
				}
			});		
       }


	public EditText getEditUsuario() {
		return editUsuario;
	}


	public void setEditUsuario(EditText editUsuario) {
		this.editUsuario = editUsuario;
	}	
	

	@Override
	protected void onPause() {
		super.onPause();		
		finish();		
	}

}