package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import modelo.Forragem;
import persistencia.ForragemDAO;

public class ControladorCadForragem implements Initializable
{
	ForragemDAO fDAO = new ForragemDAO();
    
    @FXML
    TextField especie, taxaDeAcumulo;
    
    @FXML 
    Button voltar, adicionar, cancelar; 
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void voltar(){
        try {
			BorderPane editar = (BorderPane) FXMLLoader.load(getClass().getResource("/visao/Forragem.fxml"));
			
                        ControladorPrincipal.controller.borderPrincipal.setCenter(editar);
                        
		} catch (IOException e) {
			e.printStackTrace();
		}	
    }
    
     public boolean isNumeric () {
    try {
        Double.parseDouble (taxaDeAcumulo.getText()); 
        return true;
    } catch (NumberFormatException ex) {
        return false;
    }
  }
     public boolean isString () {
    try {
        Integer.parseInt(especie.getText());
        return false;
    } catch (NumberFormatException ex) {
        return true;
    }
  }
    @FXML
    private void adicionar(){
        if(especie.getText().isEmpty() || taxaDeAcumulo.getText().isEmpty()) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Atenção");
		alert.setHeaderText("Algum campo está em branco");
		alert.setContentText("Preencha os campos");
		alert.showAndWait();
        }else{
            if(isNumeric()==false || isString()==false){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Atenção");
		alert.setHeaderText("Erro de valor em algum campo");
		alert.setContentText("Preencha os campos com valores válidos");
		alert.showAndWait();
            }else{ 
                Forragem f = new Forragem(ControladorFazendas.idFazendaEdit, especie.getText(), Double.parseDouble(taxaDeAcumulo.getText()));
                fDAO.inserir(f);
                limparTextFields();
            }
        }
    }

    @FXML
    private void cancelar(){
    	limparTextFields();
    }
    
    private void limparTextFields() {
    	especie.clear();
    	taxaDeAcumulo.clear();
    }
    
}
