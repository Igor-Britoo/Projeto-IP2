package gui;

import java.time.LocalDate;

import exceptions.ElementoJaExisteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import negocios.Fachada;
import negocios.beans.*;

public class CadastroScreenController {

	private int contador;

    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnCancelarFuncionario;
    @FXML
    private Button btnCancelarPaciente;
    @FXML
    private Button btnConfirmarFuncionario;
    @FXML
    private Button btnConfirmarPaciente;

    @FXML
    private ComboBox<String> cbBoxPlano;

    @FXML
    private DatePicker dtNascimentoFuncionario;
    @FXML
    private DatePicker dtNascimentoPaciente;

    @FXML
    private PasswordField psdSenhaFuncionario;
    @FXML
    private PasswordField psdSenhaPaciente;

    @FXML
    private Tab tabFuncionario;
    @FXML
    private Tab tabPaciente;

    @FXML
    private TextField txtCPFFuncionario;
    @FXML
    private TextField txtCPFPaciente;
    @FXML
    private TextField txtNomeFuncionario;
    @FXML
    private TextField txtNomePaciente;
    
    @FXML
    public void initialize() {
    	for(PlanoDeSaude p: PlanoDeSaude.values()) {
    		this.cbBoxPlano.getItems().add(p.getPlano());
    	}
    	this.contador=0;
    }

    @FXML
    public void btnGoToLogin(ActionEvent event) {
    	this.limparCampos();
    	
    	((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    	
    	ScreenManager.getInstance().setPrimaryStage((Stage)((Node)event.getSource()).getScene().getWindow());
    	ScreenManager.getInstance().getPrimaryStage().setScene(ScreenManager.getInstance().getLoginScene());
    	ScreenManager.getInstance().getPrimaryStage().show();
    }

    @FXML
    public void btnCancelarClicked(ActionEvent event) {
    	this.limparCampos();
    }

    @FXML
    public void btnConfirmarFuncionarioClicked(ActionEvent event){
    	Funcionario funcionario = new Funcionario();
		funcionario.setNome(txtNomeFuncionario.getText());
		funcionario.setCpf(txtCPFFuncionario.getText());
		funcionario.setDataDeNascimento(dtNascimentoFuncionario.getValue());
		funcionario.setSenha(psdSenhaFuncionario.getText());
    	
    	if(txtNomeFuncionario.getText().equals("") ||
    			txtCPFFuncionario.getText().equals("") ||
    			dtNascimentoFuncionario.getValue() == null ||
    			dtNascimentoFuncionario.getValue().isAfter(LocalDate.now().minusYears(18))||
    			psdSenhaFuncionario.getText().equals("")) {
    		
    		this.limparCampos();
			
    		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setHeaderText("");
			errorAlert.setTitle("Erro");
			errorAlert.setContentText("Formulário apresenta campos que não foram preenchidos corretamente");
			errorAlert.show();
        
    	}else {

    		this.limparCampos();
    		
    		try {
        		Fachada.getInstance().cadastrarFuncionario(funcionario);
        		
        		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        		confirmAlert.setTitle("Confirmation");
        		confirmAlert.setHeaderText("");
        		confirmAlert.setContentText("Cadastro realizado com sucesso!\n"
        				+ "Você será redirecionado para tela de Login ");
        		confirmAlert.showAndWait();
        		
    			((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    			
    			ScreenManager.getInstance().setPrimaryStage((Stage)((Node)event.getSource()).getScene().getWindow());
    			ScreenManager.getInstance().getPrimaryStage().setScene(ScreenManager.getInstance().getLoginScene());
    			ScreenManager.getInstance().getPrimaryStage().show();
            	
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

    @FXML
    public void btnConfirmarPacienteClicked(ActionEvent event){
    
    	Paciente paciente = new Paciente();
		paciente.setNome(txtNomePaciente.getText());
		paciente.setCpf(txtCPFPaciente.getText());
		paciente.setDataDeNascimento(dtNascimentoPaciente.getValue());
		paciente.setPlanoDeSaudeString(cbBoxPlano.getValue());
		paciente.setSenha(psdSenhaPaciente.getText());
    	
    	if(txtNomePaciente.getText().equals("")||
    			txtCPFPaciente.getText().equals("") ||
    			dtNascimentoPaciente.getValue()== null ||
    			dtNascimentoPaciente.getValue().isAfter(LocalDate.now().minusYears(18)) ||
    			cbBoxPlano.getValue() == null ||
    			psdSenhaPaciente.getText().equals("")){
    		
    		this.limparCampos();
    			
    		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setHeaderText("");
			errorAlert.setTitle("Erro");
			errorAlert.setContentText("Formulário apresenta campos que não foram preenchidos");
			errorAlert.show();
			
    	}else {

    		this.limparCampos();
        	
        	try {
        		Fachada.getInstance().cadastrarPaciente(paciente);
        		
        		Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
    			confirmAlert.setTitle("Confirmation");
    			confirmAlert.setHeaderText("");
    			confirmAlert.setContentText("Cadastro realizado com sucesso!\n"
        				+ "Você será redirecionado para tela de Login ");
    			confirmAlert.showAndWait();
        		
        		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        		
    			ScreenManager.getInstance().setPrimaryStage((Stage)((Node)event.getSource()).getScene().getWindow());
    			ScreenManager.getInstance().getPrimaryStage().setScene(ScreenManager.getInstance().getLoginScene());
    			ScreenManager.getInstance().getPrimaryStage().show();
            	
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
    
    @FXML
    public void tabFuncionarioClicked() {
    	if(this.contador>0) {
    		txtNomePaciente.setText("");
    		txtCPFPaciente.setText("");
    		psdSenhaPaciente.setText("");
    		dtNascimentoPaciente.setValue(null);
    		cbBoxPlano.getSelectionModel().clearSelection();
    	}
    	contador++;
    	
    }
    @FXML
    public void tabPacienteClicked() {
    	txtCPFFuncionario.setText("");
    	txtNomeFuncionario.setText("");
    	psdSenhaFuncionario.setText("");
    	dtNascimentoFuncionario.setValue(null);
    }
    
    public void limparCampos() {
    	txtCPFFuncionario.setText("");
    	txtNomeFuncionario.setText("");
    	psdSenhaFuncionario.setText("");
    	dtNascimentoFuncionario.setValue(null);
    	
    	txtNomePaciente.setText("");
    	txtCPFPaciente.setText("");
    	psdSenhaPaciente.setText("");
    	dtNascimentoPaciente.setValue(null);
    	cbBoxPlano.getSelectionModel().clearSelection();
    }

}
