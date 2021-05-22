
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import dbAccess.databaseAccess;
import javafx.scene.Parent;

public class LoginScreenController implements Initializable {

    databaseAccess db = new databaseAccess();
    
    @FXML
    private TextField loginField;

    @FXML
    private TextField nameRegister;

    @FXML
    private TextField lastnameRegister;

    @FXML
    private TextField emailRegister;

    @FXML
    private TextField loginRegister;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordRegister;

    @FXML
    private PasswordField repeatPasswordRegister;

    
    
    //LOGIN
    @FXML
    void LoginAction(ActionEvent event) throws IOException{
        String login, password;
        login = loginField.getText();
        password = passwordField.getText();

        //SPRAWDZENIE CZY POLA S� PUSTE
        if(loginField.getText().isBlank() || passwordField.getText().isBlank())
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("");
            a.setHeaderText("Pole Login lub Has�o jest puste!");
            a.setTitle("Ostrze�enie");
            a.show();
        }
        
        else if(loginField.getText().length()>33 || passwordField.getText().length()>33)
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("");
            a.setHeaderText("Wprowadzone dane s� zbyt du�e do przetworzenia!");
            a.setTitle("Ostrze�enie");
            a.show();
        
        }
        
        
        //LOGOWANIE DO PANELA ADMINA
        else if(login.equals("admin")&& password.equals("admin"))
        {
        Stage stageToClose = (Stage) loginButton.getScene().getWindow();    //Zamkni�cie ekranu logowania
        closeWindow(stageToClose);     
        
        FXMLLoader fxmlLoader = new FXMLLoader();                           //Otwarcie panelu admina
        fxmlLoader.setLocation(getClass().getResource("/AdminPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Ksi�garnia internetowa - Panel Admina");
        scene.getStylesheets().add("/style.css");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
        stage.setScene(scene);
        stage.show();
        

        }
        
        //SPRAWDZENIE CZY DANE S� POPRAWNE
        else if(!(db.checkUserLogin(login, password)))
        {
        Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("");
            a.setHeaderText("B��dny login lub has�o!");
            a.setTitle("Ostrze�enie");
            a.show();
        
        }
        
        else if(db.checkUserLogin(login, password)) //Loguje si� normalny u�ytkownik
        {         
        Stage stageToClose = (Stage) loginButton.getScene().getWindow();    //Zamkni�cie ekranu logowania
        closeWindow(stageToClose);
  
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/MainApp.fxml"));
        fxmlLoader.load();
        MainAppController maincontr = fxmlLoader.getController();
        maincontr.login = login;
        Parent p = fxmlLoader.getRoot();
        Scene scene = new Scene(p);
        Stage stage = new Stage();
        stage.setTitle("Ksi�garnia internetowa");
        scene.getStylesheets().add("/style.css");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
        stage.setScene(scene);
        stage.show();
        
        }
    }
    
    
    public String getLogin()
    {
    return loginField.getText();
    
    }

    private void closeWindow(Stage stage) //Funkcja zamykaj�ca dane okno
    {
    stage.hide();
    }
    
    
    //REJESTRACJA
    @FXML
    void RegisterAction(ActionEvent event) throws IOException  
    { 
        String name, lastname, email, login, password, repeatPassword;
        name = nameRegister.getText();
        lastname = lastnameRegister.getText();
        email = emailRegister.getText();
        login = loginRegister.getText();
        password = passwordRegister.getText();
        repeatPassword = repeatPasswordRegister.getText();

        //PUSTE POLA
        if(nameRegister.getText().isBlank() || lastnameRegister.getText().isBlank() || emailRegister.getText().isBlank() || loginRegister.getText().isBlank() || passwordRegister.getText().isBlank() || repeatPasswordRegister.getText().isBlank())
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Nie wype�niono wszystkich wymaganych p�l!");
            a.setTitle("Ostrze�enie");
            a.show();
            
        }
        
        //D�UGO�� LOGINU
        else if(loginRegister.getText().length()<4) 
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Login musi mie� d�ugo�� przynajmniej 4 znak�w!");
            a.setTitle("Ostrze�enie");
            a.show();
        }
        
        //D�UGO�� HAS�A
        else if(passwordRegister.getText().length()<4) 
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Has�o musi mie� d�ugo�� przynajmniej 4 znak�w!");
            a.setTitle("Ostrze�enie");
            a.show();
        
        }
        
        else if(nameRegister.getText().length()>21 || lastnameRegister.getText().length()>33 || emailRegister.getText().length()>49 || loginRegister.getText().length()>33 || passwordRegister.getText().length()>33)
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Jedno z p�l posiada zbyt du�� ilo�� znak�w");
            a.setTitle("Ostrze�enie");
            a.setContentText("Limity\nImi�: 20\nNazwisko: 32\nEmail: 48\nLogin: 32\nHas�o: 32");
            a.show();
        }
        
        
        //NIEZGODNO�� HASE�
        else if(!(passwordRegister.getText().equals(repeatPasswordRegister.getText())))
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Has�a nie s� takie same!");
            a.setTitle("Ostrze�enie");
            a.show();
        }
        
        //ZAJ�TY LOGIN
        else if(!(db.checkUserRegister(login)))
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Wybrany login jest ju� zaj�ty!");
            a.setTitle("Ostrze�enie");
            a.show();

        }
        
        //DODAWANIE NOWEGO U�YTKOWNIKA + OTWARCIE G��WNEJ APLIKACJI
        else
        {
        db.insertUser(name, lastname, email, login, password);
        Stage stageToClose = (Stage) loginButton.getScene().getWindow();    //Zamkni�cie ekranu logowania
        closeWindow(stageToClose);
            
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/MainApp.fxml"));
        fxmlLoader.load();
        MainAppController maincontr = fxmlLoader.getController();
        maincontr.login = login;
        Parent p = fxmlLoader.getRoot();
        Scene scene = new Scene(p);
        Stage stage = new Stage();
        stage.setTitle("Ksi�garnia internetowa");
        scene.getStylesheets().add("/style.css");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
        stage.setScene(scene);
        stage.show();
      
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        
    }

}
