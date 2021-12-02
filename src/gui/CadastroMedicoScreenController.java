package gui;

import java.time.LocalDate;

import exceptions.ElementoJaExisteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import negocios.Fachada;
import negocios.beans.Especialidade;
import negocios.beans.Medico;

public class CadastroMedicoScreenController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private ComboBox<Especialidade> cbBoxEspecialidade;

    @FXML
    private DatePicker dtNascimento;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtNome;
    
    @FXML
    public void initialize() {
    	this.cbBoxEspecialidade.getItems().addAll(Especialidade.values());
    }

    @FXML
    public void btnCancelarClicked(ActionEvent event) {
    	this.limparCampos();
    }

    @FXML
    public void btnConfirmarClicked(ActionEvent event) {
    	
    	Medico m = new Medico();
    	if(this.txtCPF.getText().equals("")||
    			this.txtNome.getText().equals("")||
    			this.dtNascimento.getValue()== null ||
    			this.dtNascimento.getValue().isAfter(LocalDate.now().minusYears(18))||
    			this.cbBoxEspecialidade.getValue() == null){
    		
    		this.limparCampos();
    			
    		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setHeaderText("");
			errorAlert.setTitle("Erro");
			errorAlert.setContentText("Formulário apresenta campos que não foram preenchidos corretamente");
			errorAlert.show();
			
    	}else {
    		m.setNome(this.txtNome.getText());
    		m.setCpf(this.txtCPF.getText());
    		m.setDataDeNascimento(this.dtNascimento.getValue());
    		m.setAreaDeAtuacao(this.cbBoxEspecialidade.getValue());
    		
    		this.limparCampos();
        	
        	try {
        		Fachada.getInstance().cadastrarMedico(m);
        		
        		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
    			confirmAlert.setTitle("Confirmation");
    			confirmAlert.setHeaderText("");
    			confirmAlert.setContentText("Cadastro realizado com sucesso!");
    			confirmAlert.show();
    			            	
        	}catch(ElementoJaExisteException e) {
        		e.printStackTrace();
        		
        		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    			errorAlert.setHeaderText("");
    			errorAlert.setTitle("Erro");
    			errorAlert.setContentText("Este CPF já está cadastrado no sistema");
    			errorAlert.show();
        	}
    	}
    	
    }
    
    public void limparCampos() {
    	
    	this.txtNome.setText("");
    	this.txtCPF.setText("");
    	this.dtNascimento.setValue(null);
    	this.cbBoxEspecialidade.getSelectionModel().clearSelection();
    }

}
