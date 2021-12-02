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
import negocios.Fachada;
import negocios.TransicionaDadosScreens;
import negocios.beans.Consulta;
import negocios.beans.Paciente;

public class MainScreenPacienteController {
			
    @FXML
    private Button btnAgendar;
    @FXML
    private Button btnSair;

    @FXML
    private Label labelCPFPaciente;
    @FXML
    private Label labelPlano;
    @FXML
    private Label labelDtNascimentoPaciente;
    @FXML
    private Label labelNomePaciente;
    
    @FXML
    private TableView<Consulta> tbViewConsultas;
    @FXML
    private TableColumn<Consulta, String> tbColumnData;
    @FXML
    private TableColumn<Consulta, String> tbColumnEspecialidade;
    @FXML
    private TableColumn<Consulta, String> tbColumnMedico;
    

	@FXML
    public void initialize() {
    	
    	tbColumnData.setCellValueFactory(
    			inicio ->{
    				SimpleStringProperty property = new SimpleStringProperty();
    				if(inicio.getValue().getInicio() != null) {
    					property.setValue(inicio.getValue().getInicioString());
    				}
    				return property;
    			}
    			);
    	tbColumnEspecialidade.setCellValueFactory(
    			medicoDaConsultaEspecialidade ->{
    				SimpleStringProperty property = new SimpleStringProperty();
    				
    				if(medicoDaConsultaEspecialidade.getValue()
    						.getMedicoDaConsulta().getAreaDeAtuacao().getEspecialidade() != null) {
    					
    					property.setValue(medicoDaConsultaEspecialidade.getValue()
    							.getMedicoDaConsulta().getAreaDeAtuacao().getEspecialidade());
    				}
    				return property;
    			}
    			);
    	tbColumnMedico.setCellValueFactory(
    			medicoDaConsulta -> {
    				SimpleStringProperty property = new SimpleStringProperty();
    				if(medicoDaConsulta.getValue().getMedicoDaConsulta().getCpf() != null &&
    						medicoDaConsulta.getValue().getMedicoDaConsulta().getNome() != null) {
    					
    					property.setValue(medicoDaConsulta.getValue().getMedicoDaConsulta().getNome()+ ": " +
    							medicoDaConsulta.getValue().getMedicoDaConsulta().getCpf());
    				}
    				return property;
    			}
    			);
    	
    	this.updateInformation();
    	this.updateListConsultas();
    	
    }

    @FXML
    public void btnAgendarClicked(ActionEvent event) {
    	
    	ScreenManager.getInstance().setSecondaryStage(new Stage());
    	ScreenManager.getInstance().getSecondaryStage().setScene(
    			ScreenManager.getInstance().getEscolhaEspecialidadeScene());
    	ScreenManager.getInstance().getSecondaryStage().setResizable(false);
    	ScreenManager.getInstance().getSecondaryStage().setTitle("");
    	ScreenManager.getInstance().getSecondaryStage().initOwner(
    			((Node) event.getSource()).getScene().getWindow());
    	ScreenManager.getInstance().getSecondaryStage().initModality(
    			Modality.APPLICATION_MODAL);
    	ScreenManager.getInstance().getSecondaryStage().getIcons().add(new Image("/gui/img/appIcon.png"));
    	ScreenManager.getInstance().getSecondaryStage().showAndWait();
    	
    	this.updateListConsultas();

    }

    @FXML
    public void btnSairClicked(ActionEvent event) {
    	
    	this.labelCPFPaciente.setText("");
    	this.labelDtNascimentoPaciente.setText("");
    	this.labelNomePaciente.setText("");
    	this.labelPlano.setText("");
    	
    	
		((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    	
    	ScreenManager.getInstance().setPrimaryStage(
    			(Stage)((Node)event.getSource()).getScene().getWindow());
    	ScreenManager.getInstance().getPrimaryStage().setScene(
    			ScreenManager.getInstance().getLoginScene());
    	ScreenManager.getInstance().getPrimaryStage().show();
    }
    
    public void updateListConsultas() {
    	Paciente p = Fachada.getInstance().listarPacientes().get(
    			TransicionaDadosScreens.getInstance().getIndexUser());
    	
    	ObservableList<Consulta> result = FXCollections.observableArrayList();
    	for(Consulta c: Fachada.getInstance().listarConsultasPorDataHorario()) {
    		if(c.getPacienteDaConsulta().getCpf().equals(p.getCpf())){
        		result.add(c);
    		}
    	}
        this.tbViewConsultas.setItems(result);
    }
    
    public void updateInformation() {
    	Paciente p = Fachada.getInstance().listarPacientes().get(
    			TransicionaDadosScreens.getInstance().getIndexUser());
    	
    	this.labelCPFPaciente.setText(p.getCpf());
    	this.labelDtNascimentoPaciente.setText(p.getDataDeNascimentoString());
    	this.labelNomePaciente.setText(p.getNome());
    	this.labelPlano.setText(p.getPlanoDeSaude().getPlano());
    }

}
