package gui;

import java.util.Optional;

import exceptions.ElementoNaoExisteException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import negocios.Fachada;
import negocios.beans.Medico;

public class ListaMedicosScreenController {
	
    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnRemover;

    @FXML
    private TableColumn<Medico, String> columnCPF;

    @FXML
    private TableColumn<Medico, String> columnEspecialidade;

    @FXML
    private TableColumn<Medico, String> columnNome;

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
    	
    	this.updateListaMedicos();
    	
    }

    @FXML
    public void btnAdicionarClicked(ActionEvent event) {
    	
    	ScreenManager.getInstance().setTertiaryStage(new Stage());
    	ScreenManager.getInstance().getTertiaryStage().setScene(ScreenManager.getInstance().getCadastroMedicoScene());
    	ScreenManager.getInstance().getTertiaryStage().setResizable(false);
    	ScreenManager.getInstance().getTertiaryStage().setTitle("");
    	ScreenManager.getInstance().getTertiaryStage().initOwner(((Node) event.getSource()).getScene().getWindow());
    	ScreenManager.getInstance().getTertiaryStage().initModality(Modality.APPLICATION_MODAL);
    	ScreenManager.getInstance().getTertiaryStage().getIcons().add(new Image("/gui/img/appIcon.png"));
    	ScreenManager.getInstance().getTertiaryStage().showAndWait();
    	
    	this.updateListaMedicos();
    	
    }

    @FXML
    public void btnRemoverClicked(ActionEvent event) {
    	
    	Medico m = tbViewMedicos.getSelectionModel().getSelectedItem();
    	
   	 	if (m != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("");
            alert.setTitle("Confirmação de exclusão de médico");
            alert.setContentText("Tem certeza que deseja remover o médico a seguir?\n" + m.toString());
            Optional<ButtonType> btnPressionado = alert.showAndWait();
            if (btnPressionado.isPresent()
                    && btnPressionado.get().equals(ButtonType.OK)) {
               
                tbViewMedicos.getItems().remove(
               		 tbViewMedicos.getSelectionModel().getSelectedIndex());
                try {
					Fachada.getInstance().removerMedico(m);
				} catch (ElementoNaoExisteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                this.updateListaMedicos();
            } 
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setTitle("Aviso");
            alert.setContentText("Selecione um médico antes de tentar exclui-lo");
            alert.show();
        }   	 	
    }
    
    public void updateListaMedicos() {
    	ObservableList<Medico> result = FXCollections.observableArrayList();
        result.addAll(Fachada.getInstance().listarMedicos());
        this.tbViewMedicos.setItems(result);
    }

}
