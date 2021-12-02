package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenManager {
	private static ScreenManager instance;
	
	private Stage primaryStage;
	private Stage secondaryStage;
	private Stage tertiaryStage;
	
	private Scene loginScene;
	private Scene cadastroScene;
	private Scene funcionarioScene;
	private Scene pacienteScene;
	private Scene escolhaEspecialidadeScene;
	private Scene agendarConsultaScene;
	private Scene listaMedicosEspecialidadeScene;
	private Scene listaMedicosScene;
	private Scene listaPacientesScene;
	private Scene cadastroMedicoScene;
	private Scene cadastroPacienteScene;
	
	private ScreenManager() {
		this.initialize();
	}
	
	public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }
	
	@FXML
	private void initialize() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/LoginScreen.fxml"));
			this.loginScene = new Scene(root);
			
			root = FXMLLoader.load(getClass().getResource("/gui/CadastroScreen.fxml"));
			this.cadastroScene = new Scene(root);
			
			root = FXMLLoader.load(getClass().getResource(
					"/gui/EscolhaEspecialidadeScreen.fxml"));
			this.escolhaEspecialidadeScene = new Scene(root);
		
			root = FXMLLoader.load(getClass().getResource("/gui/ListaMedicosScreen.fxml"));
			this.listaMedicosScene = new Scene(root);
			
			root = FXMLLoader.load(getClass().getResource("/gui/ListaPacientesScreen.fxml"));
			this.listaPacientesScene = new Scene(root);
						
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Scene getLoginScene() {
		return loginScene;
	}

	public Scene getCadastroScene() {
		return cadastroScene;
	}
	
	public Scene getFuncionarioScene() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/MainScreenFuncionario.fxml"));
			funcionarioScene = new Scene(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return funcionarioScene;
	}

	public Scene getPacienteScene() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/MainScreenPaciente.fxml"));
			pacienteScene = new Scene(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return pacienteScene;
	}

	public Scene getEscolhaEspecialidadeScene() {
		return escolhaEspecialidadeScene;
	}

	public Scene getAgendarConsultaScene() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/AgendarConsultaScreen.fxml"));
			agendarConsultaScene = new Scene(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return agendarConsultaScene;
	}

	public Scene getListaMedicosEspecialidadeScene() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(
					"/gui/ListaMedicosEspecialidadeScreen.fxml"));
			listaMedicosEspecialidadeScene = new Scene(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaMedicosEspecialidadeScene;
	}

	public Scene getListaMedicosScene() {
		return listaMedicosScene;
	}

	public Scene getListaPacientesScene() {
		return listaPacientesScene;
	}

	public Scene getCadastroMedicoScene() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/CadastroMedicoScreen.fxml"));
			cadastroMedicoScene = new Scene(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cadastroMedicoScene;
	}

	public Scene getCadastroPacienteScene() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/CadastroPacienteScreen.fxml"));
			cadastroPacienteScene = new Scene(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cadastroPacienteScene;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Stage getSecondaryStage() {
		return secondaryStage;
	}

	public void setSecondaryStage(Stage secondaryStage) {
		this.secondaryStage = secondaryStage;
	}

	public Stage getTertiaryStage() {
		return tertiaryStage;
	}

	public void setTertiaryStage(Stage tertiaryStage) {
		this.tertiaryStage = tertiaryStage;
	}
	
	
	
}
