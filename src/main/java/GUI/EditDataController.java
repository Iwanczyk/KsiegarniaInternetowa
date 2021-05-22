package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import dbAccess.databaseAccess;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EditDataController implements Initializable {

    databaseAccess db = new databaseAccess();

    public String userLogin;

    @FXML
    private TextField newNameField;

    @FXML
    private TextField newLastnameField;

    @FXML
    private TextField newEmailField;

    @FXML
    private Button saveChangesButton;

    @FXML
    private TextField newLoginField;

    @FXML
    private PasswordField newRepeatPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    void SaveChangesAction(ActionEvent event) throws IOException {
        String editLogin, editName, editLastname, editEmail, editPassword, editRepeatPassword;
        editLogin = newLoginField.getText();
        editName = newNameField.getText();
        editLastname = newLastnameField.getText();
        editEmail = newEmailField.getText();
        editPassword = newPasswordField.getText();
        editRepeatPassword = newRepeatPasswordField.getText();

        //PUSTE POLA
        if (newLoginField.getText().isBlank() || newNameField.getText().isBlank() || newLastnameField.getText().isBlank() || newEmailField.getText().isBlank() || newPasswordField.getText().isBlank() || newRepeatPasswordField.getText().isBlank()) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("");
            a.setHeaderText("Nie wype³niono wszystkich wymaganych pól!");
            a.setTitle("Ostrze¿enie!");
            a.show();    
        }
        
         else if(newLoginField.getText().length()<3)
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("");
            a.setHeaderText("Login musi mieæ przynajmniej 4 znaki!");
            a.setTitle("Ostrze¿enie!");
            a.show();    
        }
         
         else if(newPasswordField.getText().length()<3)
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("");
            a.setHeaderText("Has³o musi mieæ przynajmniej 4 znaki!");
            a.setTitle("Ostrze¿enie!");
            a.show();    
        }
         
         else if(newNameField.getText().length()>21 || newLastnameField.getText().length()>33 || newEmailField.getText().length()>49 || newLoginField.getText().length()>33 || newPasswordField.getText().length()>33)
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Jedno z pól posiada zbyt du¿¹ iloœæ znaków");
            a.setTitle("Ostrze¿enie");
            a.setContentText("Limity\nImiê: 20\nNazwisko: 32\nEmail: 48\nLogin: 32\nHas³o: 32");
            a.show();
        }
        
        //NIEZGODNOŒÆ HASE£
        else if (!(newPasswordField.getText().equals(newRepeatPasswordField.getText()))) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Has³a nie s¹ takie same!");
            a.setTitle("Ostrze¿enie!");
            a.show();
        } //ZAJÊTY LOGIN
        else if (!userLogin.equals(editLogin) && !(db.checkUserRegister(editLogin))) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Wybrany login jest ju¿ zajêty!");
            a.setTitle("Ostrze¿enie!");
            a.show();

        } else {
            int result = db.updateAccountData(userLogin, editName, editLastname, editEmail, editLogin, editPassword);
            Alert a = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
            a.setHeaderText("Dane zaktualizowano pomyœlnie! \nW celu dalszej pracy z programem nale¿y ponownie uruchomiæ aplikacjê");
            a.setTitle("Sukces!");
            a.showAndWait();

            if (a.getResult() == ButtonType.OK) {
            System.out.println("Wy³¹czenie aplikacji!");
            System.exit(0);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setEditLogin(String loginFromLController) //Ustawienie loginu u¿ytkownika
    {
        userLogin = loginFromLController;
    }
}
