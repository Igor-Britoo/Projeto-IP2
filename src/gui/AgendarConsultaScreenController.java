package gui;

import java.time.LocalDateTime;
import java.time.LocalTime;

import exceptions.ElementoJaExisteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import negocios.Fachada;
import negocios.TransicionaDadosScreens;
import negocios.beans.Paciente;
import negocios.beans.Consulta;
import negocios.beans.HorarioConsulta;
import negocios.beans.Medico;

public class AgendarConsultaScreenController {
		
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnConfirmar;

    @FXML
    private ComboBox<LocalTime> cbBoxHorarioConsulta;

    @FXML
    private DatePicker dtPickerDataConsulta;

    @FXML
    private TextField txtCPFPaciente;

    @FXML
    private TextField txtCPFMedico;

	@FXML
    public void initialize() {
    	for(HorarioConsulta h: HorarioConsulta.values()) {
    		this.cbBoxHorarioConsulta.getItems().add(h.getHorarioConsulta());
    	}
    	if(TransicionaDadosScreens.getInstance().getTypeUser().equals("PACIENTE")) {
    		this.txtCPFPaciente.setText(TransicionaDadosScreens.getInstance().getCpf());
    		this.txtCPFPaciente.setEditable(false);
    		this.txtCPFMedico.setText(
    				TransicionaDadosScreens.getInstance().getCpfMedicoSelected());
    		this.txtCPFMedico.setEditable(false);
    		
    	}
    	
    }

    @FXML
    public void btnCancelarClicked(ActionEvent event) {
    	this.limparCampos();
    }

    @FXML
    public void btnConfirmarClicked(ActionEvent event) {
    	
    	
    	if(this.cbBoxHorarioConsulta.getValue() == null ||
    			this.dtPickerDataConsulta.getValue()==null ||
    			LocalDateTime.of(
    				this.dtPickerDataConsulta.getValue(),
    				this.cbBoxHorarioConsulta.getValue()).isBefore(LocalDateTime.now()) ||
    			this.txtCPFPaciente.getText().equals("") ||
    			this.txtCPFMedico.getText().equals("")) {
    		
    		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        	errorAlert.setHeaderText("");
        	errorAlert.setTitle("Erro");
        	errorAlert.setContentText("Algum dos campos não foi preenchido corretamente");
        	errorAlert.show();
        	this.limparCampos();
        	
    	}else {
    		boolean existePaciente = false;
    		boolean existeMedico = false;
    		
    		Paciente paciente = new Paciente();
        	for(Paciente p : Fachada.getInstance().listarPacientes()) {
        		if(p.getCpf().equals(this.txtCPFPaciente.getText())) {
        			existePaciente = true;
        			
        			paciente.setNome(p.getNome());
        			paciente.setCpf(p.getCpf());
        			paciente.setDataDeNascimento(p.getDataDeNascimento());
        			paciente.setPlanoDeSaude(p.getPlanoDeSaude());
        			break;
        			
        		}
        	}
        	
        	Medico medico = new Medico();
        	for(Medico m: Fachada.getInstance().listarMedicos()) {
        		if(m.getCpf().equals(this.txtCPFMedico.getText())) {
        			existeMedico = true;
        			
        			medico.setNome(m.getNome());
        			medico.setCpf(m.getCpf());
        			medico.setDataDeNascimento(m.getDataDeNascimento());
        			medico.setCpf(m.getCpf());
        			medico.setAreaDeAtuacao(m.getAreaDeAtuacao());
        			break;
        		}
        	}
        	boolean naoCadastra = false;
        	if(!existePaciente || !existeMedico) {
        		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            	errorAlert.setHeaderText("");
            	errorAlert.setTitle("Erro");
            	errorAlert.setContentText("O paciente e/ou médico especificados não existem no sistema");
            	errorAlert.show();
            	naoCadastra = true;
        	}
        	
        	if(!naoCadastra) {
        		LocalDateTime dataHorarioConsulta = LocalDateTime.of
            			(this.dtPickerDataConsulta.getValue(), this.cbBoxHorarioConsulta.getValue());
            	
            	this.limparCampos();
            	
            	Consulta consulta = new Consulta(paciente, medico, dataHorarioConsulta);
            	
            	try {
        			Fachada.getInstance().cadastrarConsulta(consulta);
        			
        			Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION);
        			confirmAlert.setTitle("Confirmation");
        			confirmAlert.setHeaderText("");
        			confirmAlert.setContentText("Consulta agendada com sucesso");
        			confirmAlert.show();
        			
        		} catch (ElementoJaExisteException e) {
        			e.printStackTrace();
        			
        			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                	errorAlert.setHeaderText("");
                	errorAlert.setTitle("Erro");
                	errorAlert.setContentText("Já existe uma consulta agendada para a data e horário especificados");
                	errorAlert.show();
        		}
        	}
        	        	
    	}
    	
    }
    
    public void limparCampos() {
    	
    	if(TransicionaDadosScreens.getInstance().getTypeUser().equals("PACIENTE")) {
    		this.dtPickerDataConsulta.setValue(null);
        	this.cbBoxHorarioConsulta.getSelectionModel().clearSelection();
    	}else {
    		this.txtCPFPaciente.setText("");
    		this.txtCPFMedico.setText("");
    		this.dtPickerDataConsulta.setValue(null);
    		this.cbBoxHorarioConsulta.getSelectionModel().clearSelection();
    	}
    }
}
