package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import modelo.Animal;
import persistencia.AnimalDAO;


public class ControladorAlterarAnimal implements Initializable{
    
    static ControladorAlterarAnimal controller;
    AnimalDAO aDAO = new AnimalDAO();
    Animal a;

    @FXML 
    TextField categoria, raça, gmd, quantidade;
    
    @FXML 
    Button voltar, alterar, cancelar; 
    
    @FXML
    ComboBox<String> sexo;
    
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = this;
        ObservableList <String> options = 
                FXCollections.observableArrayList(
                    "M",
                    "F"
                );

        sexo.setItems(options);   
    }

    
    public void preencherForm(Animal a) {
	   this.a = a;
	   categoria.setText(a.getCategoria_animal());
	   raça.setText(a.getRaca_animal());
           sexo.setValue(a.getSexo_animal());
           gmd.setText(Double.toString(a.getGMD_animal()));
           quantidade.setText(Integer.toString(a.getQuantidade_animal()));
   }
    
    @FXML
    private void voltar(){
        try {
			BorderPane animais = (BorderPane) FXMLLoader.load(getClass().getResource("/visao/Animais.fxml"));
			
                        ControladorPrincipal.controller.borderPrincipal.setCenter(animais);
                        
		} catch (IOException e) {
			e.printStackTrace();
		}	
    }
    
    public boolean isNumeric () {
    try {
        Double.parseDouble (gmd.getText()); 
        Integer.parseInt(quantidade.getText());
        return true;
    } catch (NumberFormatException ex) {
        return false;
    }
  }
     
    public boolean isString () {
    try {
        Integer.parseInt(categoria.getText());
        Integer.parseInt(raça.getText());
        return false;
    } catch (NumberFormatException ex) {
        return true;
    }
  }
    
    
    
    @FXML
    private void alterar(){
        if(categoria.getText().isEmpty() || raça.getText().isEmpty() || sexo.getValue()==null || gmd.getText().isEmpty() || quantidade.getText().isEmpty()) {
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
                a.setCategoria_animal(categoria.getText());
                a.setRaca_animal(raça.getText());
                a.setSexo_animal(sexo.getValue());
                a.setGMD_animal(Double.parseDouble(gmd.getText()));
                a.setQuantidade_animal(Integer.parseInt(quantidade.getText()));
                aDAO.alterar(a);
                limparTextFields();
            }
        }
    }
    
    @FXML
    private void cancelar(){
       limparTextFields();
    }
    
    private void limparTextFields() {
    		categoria.clear();
    		raça.clear();
                sexo.getSelectionModel().clearSelection(); 
                gmd.clear();
                quantidade.clear();
    }
}