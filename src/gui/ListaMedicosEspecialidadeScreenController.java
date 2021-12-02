package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import negocios.Fachada;
import negocios.TransicionaDadosScreens;
import negocios.beans.Medico;

public class ListaMedicosEspecialidadeScreenController {
		
    @FXML
    private Button btnAgendar;

    @FXML
    private Button btnSair;

    @FXML
    private TableColumn<Medico, String> columnCPF;

    @FXML
    private TableColumn<Medico, String> columnEspecialidade;

    @FXML
    private TableColumn<Medico, String> columnNome;

    @FXML
    private Label labelEspecialidade;
    
    @FXML
    private Label labelEspecialidade2;

    @FXML
    private TableView<Medico> tbViewMedicos;
    
	@FXML
    public void initialize() {
    	
    	columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    	columnEspecialidade.setCellValueFactory(
    			areaDeAtuacao -> {
    				SimpleStringProperty property = new SimpleStringProperty();
    				if(areaDeAtuacao.getValue().getAreaDeAtuacao() != null) {
    					property.setValue(areaDeAtuacao.getValue().getAreaDeAtuacao().getEspecialidade());
    				}
    				return property;
    			}
    			);
    	columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	
    	this.updateTitle(TransicionaDadosScreens.getInstance()
    			.getEspecialidadeSelected().getEspecialidade());
    	
    	this.updateListaMedicos();

    }

    @FXML
    public void btnAgendarClicked(ActionEvent event) {
    	    	
    	Medico medicoSelected = new Medico();
    	medicoSelected = this.tbViewMedicos.getSelectionModel().getSelectedItem();
    	    	
    	if(medicoSelected != null) {
    		TransicionaDadosScreens.getInstance().setCpfMedicoSelected(
    				medicoSelected.getCpf());
    		
    		ScreenManager.getInstance().setTertiaryStage(new Stage());
        	ScreenManager.getInstance().getTertiaryStage().setScene(ScreenManager.getInstance().getAgendarConsultaScene());
        	ScreenManager.getInstance().getTertiaryStage().setResizable(false);
        	ScreenManager.getInstance().getTertiaryStage().setTitle("");
        	ScreenManager.getInstance().getTertiaryStage().initOwner(((Node) event.getSource()).getScene().getWindow());
        	ScreenManager.getInstance().getTertiaryStage().initModality(Modality.APPLICATION_MODAL);
        	ScreenManager.getInstance().getTertiaryStage().getIcons().add(new Image("/gui/img/appIcon.png"));
        	ScreenManager.getInstance().getTertiaryStage().showAndWait();
    	}
    	else {
    		
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("");
            alert.setTitle("Aviso");
            alert.setContentText("Selecione um médico antes de tentar agendar uma consulta");
            alert.show();
            
    	}
    
    }
    
    public void updateListaMedicos() {
    	ObservableList<Medico> result = FXCollections.observableArrayList();
    	if(Fachada.getInstance().listarMedicosEspecialidade(
    			TransicionaDadosScreens.getInstance()
    			.getEspecialidadeSelected()) != null) {
    		result.addAll(Fachada.getInstance().listarMedicosEspecialidade(
    				TransicionaDadosScreens.getInstance()
        			.getEspecialidadeSelected()));
    		this.tbViewMedicos.setItems(result);
    	}
    }
    
    public void updateTitle(String e) {
    	this.labelEspecialidade.setText(TransicionaDadosScreens.getInstance()
    			.getEspecialidadeSelected().getEspecialidade() + "s");
    	this.labelEspecialidade2.setText(TransicionaDadosScreens.getInstance()
    			.getEspecialidadeSelected().getEspecialidade() + "s");
    }

}
