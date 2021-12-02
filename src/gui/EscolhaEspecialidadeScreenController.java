package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import negocios.Fachada;
import negocios.TransicionaDadosScreens;
import negocios.beans.Especialidade;

public class EscolhaEspecialidadeScreenController {
	
    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private ComboBox<Especialidade> cbBoxEspecialidade;
    
    @FXML
    public void initialize() {
    	this.cbBoxEspecialidade.getItems().addAll(Especialidade.values());
    }

    @FXML
    void btnCancelarClicked(ActionEvent event) {
    	this.limparCampos();
    }

    @FXML
    void btnConfirmarClicked(ActionEvent event) {
    	
    	if(this.cbBoxEspecialidade.getValue() != null) {
    		
    		if(!Fachada.getInstance().listarMedicosEspecialidade(this.cbBoxEspecialidade.getValue()).isEmpty()) {
    			TransicionaDadosScreens.getInstance().setEspecialidadeSelected(
        				this.cbBoxEspecialidade.getValue());
        		this.limparCampos();
            	
            	ScreenManager.getInstance().getSecondaryStage().close();
            	ScreenManager.getInstance().setSecondaryStage(new Stage());
            	ScreenManager.getInstance().getSecondaryStage().setScene(
            			ScreenManager.getInstance().getListaMedicosEspecialidadeScene());
            	ScreenManager.getInstance().getSecondaryStage().setResizable(false);
            	ScreenManager.getInstance().getSecondaryStage().setTitle("");
            	ScreenManager.getInstance().getSecondaryStage().initOwner(
            			((Node) event.getSource()).getScene().getWindow());
            	ScreenManager.getInstance().getSecondaryStage().initModality(Modality.APPLICATION_MODAL);
            	ScreenManager.getInstance().getSecondaryStage().getIcons().add(new Image("/gui/img/appIcon.png"));
            	ScreenManager.getInstance().getSecondaryStage().showAndWait();
            	
    		}else {
    			Alert alert = new Alert(AlertType.ERROR);
    		    alert.setHeaderText("");
    		    alert.setTitle("Erro");
    		    alert.setContentText("Não existem médicos dessa especialidade no nosso sistema");
    		    alert.show();
    		}
    		
    	}else {
    		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        	errorAlert.setHeaderText("");
        	errorAlert.setTitle("Erro");
        	errorAlert.setContentText("Selecione uma especialidade");
        	errorAlert.show();
        	this.limparCampos();
    	}
    	
    }
    
    public void limparCampos(){
    	this.cbBoxEspecialidade.getSelectionModel().clearSelection();
    }
	
}
