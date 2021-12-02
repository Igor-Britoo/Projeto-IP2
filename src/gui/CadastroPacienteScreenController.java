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
import negocios.beans.Paciente;
import negocios.beans.PlanoDeSaude;

public class CadastroPacienteScreenController {

    @FXML
    private Button btnCancelarPaciente;

    @FXML
    private Button btnConfirmarPaciente;

    @FXML
    private ComboBox<String> cbBoxPlano;

    @FXML
    private DatePicker dtNascimentoPaciente;

    @FXML
    private TextField txtCPFPaciente;

    @FXML
    private TextField txtNomePaciente;
    
    @FXML
    public void initialize() {
    	for(PlanoDeSaude p: PlanoDeSaude.values()) {
    		this.cbBoxPlano.getItems().add(p.getPlano());
    	}
    }

    @FXML
    public void btnCancelarClicked(ActionEvent event) {
    	this.limparCampos();
    }

    @FXML
    public void btnConfirmarPacienteClicked(ActionEvent event) {
    	Paciente paciente = new Paciente();
    	
    	if(this.txtNomePaciente.getText().equals("")||
    			this.txtCPFPaciente.getText().equals("") ||
    			this.dtNascimentoPaciente.getValue()== null ||
    			this.dtNascimentoPaciente.getValue().isAfter(LocalDate.now().minusYears(18))||
    			this.cbBoxPlano.getValue() == null){
    		this.limparCampos();
    			
    		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setHeaderText("");
			errorAlert.setTitle("Erro");
			errorAlert.setContentText("Formulário apresenta campos que não foram preenchidos corretamente");
			errorAlert.show();

    	}else {
    		paciente.setNome(this.txtNomePaciente.getText());
    		paciente.setCpf(this.txtCPFPaciente.getText());
    		paciente.setDataDeNascimento(this.dtNascimentoPaciente.getValue());
    		paciente.setPlanoDeSaudeString(this.cbBoxPlano.getValue());
    		
    		this.limparCampos();
        	
        	try {
        		Fachada.getInstance().cadastrarPaciente(paciente);
        		
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
    	
    	this.txtNomePaciente.setText("");
    	this.txtCPFPaciente.setText("");
    	this.dtNascimentoPaciente.setValue(null);
    	this.cbBoxPlano.getSelectionModel().clearSelection();
    }
    
}
