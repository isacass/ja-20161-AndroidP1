package com.example.androidp1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class Acesso<ExternalData> extends Activity {
	
	public  static String USUARIO_NOME = "Acesso";	
	
	Button buttonEntrar, buttonSair;
	EditText editUsuario, editSenha;
	Spinner spinnerTitulo;
	String[] paths1 = {"Professor","Aluno","Funcionário","Outro"};
	Spinner spinnerInstit;
	String[] paths2 = {"UCAM","Outro"};
	boolean autentica = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acesso);
		
		buttonEntrar = (Button)findViewById(R.id.buttonEntrar);
		buttonSair = (Button)findViewById(R.id.buttonSair);
		editUsuario = (EditText)findViewById(R.id.editUsuario);
		editSenha = (EditText)findViewById(R.id.editSenha);		
		
		
		ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, paths1);			
		spinnerTitulo = (Spinner)findViewById(R.id.spinnerTitulo);
		spinnerTitulo.setAdapter(adapter1);
		
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, paths2);	
		spinnerInstit  = (Spinner)findViewById(R.id.spinnerInstit);		
		spinnerInstit.setAdapter(adapter2);
		
		
		SharedPreferences acesso = getSharedPreferences(USUARIO_NOME, Context.MODE_PRIVATE);		
		
		final String usuarioLogin = acesso.getString("usuario","");
		final String senhaLogin = acesso.getString("senha","");
		
		
		if(usuarioLogin.equals("")){
			buttonEntrar.setText("Salvar");
			AlertDialog.Builder mensagem = new AlertDialog.Builder(Acesso.this);
			mensagem.setTitle("Olá");
			mensagem.setMessage("Para acessar o aplicativo, você deve registrar um nome de Usuário e uma Senha!");
			mensagem.setNeutralButton("fechar",null);
			mensagem.show();		
		}
		
		else{
			
			autentica = true;
		}
		
		buttonEntrar.setOnClickListener (new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				String usuario = editUsuario.getText().toString();	
				String senha = editSenha.getText().toString();	
				
				if(usuario.equals("") || senha.equals("")){
					Toast.makeText(getApplicationContext(), "Ops! Verifique se os todas as informações foram inseridas!", Toast.LENGTH_LONG).show();
					
					}else if(autentica == false){
					
							SharedPreferences acesso = getSharedPreferences(USUARIO_NOME, 0);
							SharedPreferences.Editor salvaLogin = acesso.edit();
							salvaLogin.putString("usuario", usuario);
							salvaLogin.putString("senha", senha);
							
							salvaLogin.commit();
							
							Toast.makeText(getApplicationContext(), "Usuário e Senha criados com sucesso!", Toast.LENGTH_LONG).show();
									
							irInicial();
					
								}else if(usuario.equals(usuarioLogin) & senha.equals(senhaLogin)){
								
									irInicial();
									
								}else if(senha != senhaLogin || usuario != usuarioLogin){
									Toast.makeText(getApplicationContext(), "Usuário e/ou Senha Inválidos!", Toast.LENGTH_LONG).show();					
									
									}
				}
			
		});		
		
		buttonSair.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				finish();				
			}
		});		
	}	
	
	public void irInicial() {
		Intent irInicial = new Intent(Acesso.this, Inicio.class); 
		irInicial.putExtra("USUARIO", editUsuario.getText().toString());		
		startActivity(irInicial);
	}


	@Override
	protected void onPause() {
		super.onPause();		
		finish();		
	}

	
}
