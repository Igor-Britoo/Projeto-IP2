package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import negocios.beans.Consulta;
import negocios.beans.Funcionario;
import negocios.Fachada;
import negocios.TransicionaDadosScreens;


public class MainScreenFuncionarioController {
			
    @FXML
    private Button btnAgendar;
    @FXML
    private Button btnListarMedico;
    @FXML
    private Button btnListarPaciente;
    @FXML
    private Button btnSair;

    @FXML
    private Label labelCPFFuncionario;
    @FXML
    private Label labelDtNascimentoFuncionario;
    @FXML
    private Label labelNomeFuncionario;

    @FXML
    private TableColumn<Consulta, String> tbColumnData;
    @FXML
    private TableColumn<Consulta, String> tbColumnMedico;
    @FXML
    private TableColumn<Consulta, String> tbColumnPaciente;
    @FXML
    private TableView<Consulta> tbViewConsultas;
    

	@FXML
    public void initialize() {

    	tbColumnData.setCellValueFactory(
    			inicio -> {
    				SimpleStringProperty property = new SimpleStringProperty();
    				if(inicio.getValue().getInicio() != null) {
    					property.setValue(inicio.getValue().getInicioString());
    				}
    				return property;
    			}
    			);
    	tbColumnPaciente.setCellValueFactory(
    			pacienteDaConsulta -> { 
    				SimpleStringProperty property = new SimpleStringProperty();
    				if(pacienteDaConsulta.getValue().getPacienteDaConsulta().getCpf() != null &&
    						pacienteDaConsulta.getValue().getPacienteDaConsulta().getNome() != null) {
    					property.setValue(pacienteDaConsulta.getValue().getPacienteDaConsulta().getNome()+ " | " +
    							pacienteDaConsulta.getValue().getPacienteDaConsulta().getCpf());
    				}
    				return property;
    			}
    			);
    	tbColumnMedico.setCellValueFactory(
    			medicoDaConsulta -> {
    				SimpleStringProperty property = new SimpleStringProperty();
    				if(medicoDaConsulta.getValue().getMedicoDaConsulta().getCpf() != null &&
    						medicoDaConsulta.getValue().getMedicoDaConsulta().getNome() != null) {
    					property.setValue(medicoDaConsulta.getValue().getMedicoDaConsulta().getNome()+ " | " +
    							medicoDaConsulta.getValue().getMedicoDaConsulta().getCpf());
    				}
    				return property;
    			}
    			);

    	this.updateListConsultas();
    	this.updateInformation();
    }

    @FXML
    public void btnAgendarClicked(ActionEvent event) {
    	
    	ScreenManager.getInstance().setSecondaryStage(new Stage());
    	ScreenManager.getInstance().getSecondaryStage().setScene(
    			ScreenManager.getInstance().getAgendarConsultaScene());
    	ScreenManager.getInstance().getSecondaryStage().setResizable(false);
    	ScreenManager.getInstance().getSecondaryStage().setTitle("");
    	ScreenManager.getInstance().getSecondaryStage().initOwner(
    			((Node) event.getSource()).getScene().getWindow());
    	ScreenManager.getInstance().getSecondaryStage().initModality(
    			Modality.APPLICATION_MODAL);
    	ScreenManager.getInstance().getSecondaryStage().showAndWait();
    	 
    	this.updateListConsultas();

    }

    @FXML
    public void btnListarMedicosClicked(ActionEvent event) {
    	
    	ScreenManager.getInstance().setSecondaryStage(new Stage());
    	ScreenManager.getInstance().getSecondaryStage().setScene(
    			ScreenManager.getInstance().getListaMedicosScene());
    	ScreenManager.getInstance().getSecondaryStage().setResizable(false);
    	ScreenManager.getInstance().getSecondaryStage().setTitle("");
    	ScreenManager.getInstance().getSecondaryStage().initOwner(
    			((Node) event.getSource()).getScene().getWindow());
    	ScreenManager.getInstance().getSecondaryStage().initModality(
    			Modality.APPLICATION_MODAL);
    	ScreenManager.getInstance().getSecondaryStage().getIcons().add(new Image("/gui/img/appIcon.png"));
    	ScreenManager.getInstance().getSecondaryStage().showAndWait();
    	
    }

    @FXML
    public void btnListarPacientesClicked(ActionEvent event) {
   
    	ScreenManager.getInstance().setSecondaryStage(new Stage());
    	ScreenManager.getInstance().getSecondaryStage().setScene(
    			ScreenManager.getInstance().getListaPacientesScene());
    	ScreenManager.getInstance().getSecondaryStage().setResizable(false);
    	ScreenManager.getInstance().getSecondaryStage().setTitle("");
    	ScreenManager.getInstance().getSecondaryStage().initOwner(
    			((Node) event.getSource()).getScene().getWindow());
    	ScreenManager.getInstance().getSecondaryStage().initModality(
    			Modality.APPLICATION_MODAL);
    	ScreenManager.getInstance().getSecondaryStage().getIcons().add(new Image("/gui/img/appIcon.png"));
    	ScreenManager.getInstance().getSecondaryStage().showAndWait();
    	
    }

    @FXML
    public void btnSairClicked(ActionEvent event) {
    	this.labelCPFFuncionario.setText("");
    	this.labelDtNascimentoFuncionario.setText("");
    	this.labelNomeFuncionario.setText("");
    	
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    	
    	ScreenManager.getInstance().setPrimaryStage((Stage)((Node)event.getSource()).getScene().getWindow());
    	ScreenManager.getInstance().getPrimaryStage().setScene(ScreenManager.getInstance().getLoginScene());
    	ScreenManager.getInstance().getPrimaryStage().show();
    }
    
    public void updateListConsultas() {
    	ObservableList<Consulta> result = FXCollections.observableArrayList();
        result.addAll(Fachada.getInstance().listarConsultasPorDataHorario());
        this.tbViewConsultas.setItems(result);
    }
    
    public void updateInformation() {
    	Funcionario f = Fachada.getInstance().listarFuncionarios().get(
    			TransicionaDadosScreens.getInstance().getIndexUser());
    	
    	this.labelCPFFuncionario.setText(f.getCpf());
    	this.labelDtNascimentoFuncionario.setText(f.getDataDeNascimentoString());
    	this.labelNomeFuncionario.setText(f.getNome());
    	
    }
    
    
}
