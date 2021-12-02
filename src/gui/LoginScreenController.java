package gui;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocios.Fachada;
import negocios.TransicionaDadosScreens;
import negocios.beans.Funcionario;
import negocios.beans.Paciente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

public class LoginScreenController {
	
	@FXML 
	private TextField txtCPF;
	@FXML 
	private PasswordField psdSenha;
	
	
	@FXML
    public void btnEntrarClicked(ActionEvent event) {
		
		if(txtCPF.getText().equals("") || psdSenha.getText().equals("")) {
			this.limparCampos();
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        	errorAlert.setHeaderText("");
        	errorAlert.setTitle("Erro");
        	errorAlert.setContentText("Algum dos campos não foi preenchido corretamente");
        	errorAlert.show();
        	
		}else {
			boolean isFuncionario = false;
			boolean senhaIncorreta = true;
			
			for(Funcionario f: Fachada.getInstance().listarFuncionarios()) {
	        	if(f.getCpf().equals(txtCPF.getText())) {
	        		isFuncionario = true;
	        		
	        		if(f.getSenha().equals(psdSenha.getText())) {
	        			senhaIncorreta = false;
	        			
	        			TransicionaDadosScreens.getInstance().setIndexUser(
	        					Fachada.getInstance().listarFuncionarios().indexOf(f));
	        			TransicionaDadosScreens.getInstance().setTypeUser(f.getTipo());
	        			
	        			this.limparCampos();
	        			
	        			Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
	        			confirmAlert.setTitle("Confirmation");
	        			confirmAlert.setHeaderText("");
	        			confirmAlert.setContentText("Login realizado com sucesso");
	        			confirmAlert.showAndWait();
	        			
	        			((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
	        			
	                	ScreenManager.getInstance().setPrimaryStage(
	                			(Stage)((Node)event.getSource()).getScene().getWindow());
	                	ScreenManager.getInstance().getPrimaryStage().setScene(
	                			ScreenManager.getInstance().getFuncionarioScene());
	                	ScreenManager.getInstance().getPrimaryStage().show();
	        		}
	        	}
	        }
	        if(!isFuncionario) {
	        	for(Paciente p: Fachada.getInstance().listarPacientes()) {
	            	if(p.getCpf().equals(txtCPF.getText())) {
	            		
	            		if(p.getSenha().equals(psdSenha.getText())) {
	            			senhaIncorreta = false;	            			
	            			
	            			TransicionaDadosScreens.getInstance().setIndexUser(
		        					Fachada.getInstance().listarPacientes().indexOf(p));
		        			TransicionaDadosScreens.getInstance().setTypeUser(p.getTipo());
		        			TransicionaDadosScreens.getInstance().setCpf(p.getCpf());
	            			
	            			this.limparCampos();
	            			
	            			Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
	            			confirmAlert.setTitle("Confirmation");
	            			confirmAlert.setHeaderText("");
	            			confirmAlert.setContentText("Login realizado com sucesso");
	            			confirmAlert.showAndWait();
	            				            			
	            			((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
	            			
	                    	ScreenManager.getInstance().setPrimaryStage(
	                    			(Stage)((Node)event.getSource()).getScene().getWindow());
	                    	ScreenManager.getInstance().getPrimaryStage().setScene(
	                    			ScreenManager.getInstance().getPacienteScene());
	                    	ScreenManager.getInstance().getPrimaryStage().show();
	            		}
	            	}
	            }
	        }
	        if(senhaIncorreta) {
	        	this.limparCampos();
	        	Alert errorAlert = new Alert(Alert.AlertType.ERROR);
	        	errorAlert.setHeaderText("");
	        	errorAlert.setTitle("Erro");
	        	errorAlert.setContentText("Login ou senha incorretos");
	        	errorAlert.show();
	        }
		}		
		                
    }
	
	@FXML
	public void btnGoToCadastro(ActionEvent event) {
		this.limparCampos();
		
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
		
    	ScreenManager.getInstance().setPrimaryStage(
    			(Stage)((Node)event.getSource()).getScene().getWindow());
    	ScreenManager.getInstance().getPrimaryStage().setScene(
    			ScreenManager.getInstance().getCadastroScene());
    	ScreenManager.getInstance().getPrimaryStage().show();
	}
	
	public void limparCampos() {
		this.txtCPF.setText("");
		this.psdSenha.setText("");
    }
	
}
