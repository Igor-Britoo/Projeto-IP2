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
import negocios.beans.Paciente;

public class ListaPacientesScreenController {

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnRemover;

    @FXML
    private TableColumn<Paciente, String> columnCPF;

    @FXML
    private TableColumn<Paciente, String> columnDtNascimento;

    @FXML
    private TableColumn<Paciente, String> columnNome;

    @FXML
    private TableView<Paciente> tbViewPacientes;
    
    @FXML
    public void initialize() {
    	columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	columnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    	columnDtNascimento.setCellValueFactory(
    			dataDeNascimento -> {
    				SimpleStringProperty property = new SimpleStringProperty();
    				
    				if(dataDeNascimento.getValue().getDataDeNascimento()!= null) {
                        property.setValue((dataDeNascimento.getValue().getDataDeNascimentoString()));
    				}    				
                    return property;
    			});
    	
    	this.updateListaPacientes();
    	
    }

    @FXML
    public void btnAdicionarClicked(ActionEvent event) {
    	
    	ScreenManager.getInstance().setTertiaryStage(new Stage());
    	ScreenManager.getInstance().getTertiaryStage().setScene(ScreenManager.getInstance().getCadastroPacienteScene());
    	ScreenManager.getInstance().getTertiaryStage().setResizable(false);
    	ScreenManager.getInstance().getTertiaryStage().setTitle("");
    	ScreenManager.getInstance().getTertiaryStage().initOwner(((Node) event.getSource()).getScene().getWindow());
    	ScreenManager.getInstance().getTertiaryStage().initModality(Modality.APPLICATION_MODAL);
    	ScreenManager.getInstance().getTertiaryStage().getIcons().add(new Image("/gui/img/appIcon.png"));
    	ScreenManager.getInstance().getTertiaryStage().showAndWait();
    	
    	
    	this.updateListaPacientes();
    	
    }

    @FXML
    public void btnRemoverClicked(ActionEvent event) {
    	Paciente p = tbViewPacientes.getSelectionModel().getSelectedItem();
    	
   	 	if (p != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("");
            alert.setTitle("Confirmação de exclusão de paciente");
            alert.setContentText("Tem certeza que deseja remover o paciente a seguir?\n" + p.toString());
            Optional<ButtonType> btnPressionado = alert.showAndWait();
            if (btnPressionado.isPresent()
                    && btnPressionado.get().equals(ButtonType.OK)) {
               
            	tbViewPacientes.getItems().remove(
            			tbViewPacientes.getSelectionModel().getSelectedItem());
            	try {
					Fachada.getInstance().removerPaciente(p);
				} catch (ElementoNaoExisteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	this.updateListaPacientes();
            } 
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("");
            alert.setTitle("Aviso");
            alert.setContentText("Selecione um paciente antes de tentar exclui-lo");
            alert.show();
        }
   	 	        
    }
    
    public void updateListaPacientes() {
    	ObservableList<Paciente> result = FXCollections.observableArrayList();
        result.addAll(Fachada.getInstance().listarPacientes());
        this.tbViewPacientes.setItems(result);
    }


}
