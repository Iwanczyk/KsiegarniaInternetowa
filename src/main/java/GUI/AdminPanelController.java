
package GUI;

import assistantClass.createBookTableView;
import assistantClass.createUserTableView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import dbAccess.databaseAccess;
import dbClasses.Ksiazki;
import dbClasses.Uzytkownicy;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminPanelController implements Initializable {

    databaseAccess db = new databaseAccess();
    public ObservableList<createBookTableView> bookData = FXCollections.observableArrayList();
    public ObservableList<createUserTableView> userData = FXCollections.observableArrayList();
    
    @FXML
    private TableView<createBookTableView> adminTable;

    @FXML
    private TableColumn<createBookTableView, Integer> adminColID;

    @FXML
    private TableColumn<createBookTableView, String> adminColTitle;

    @FXML
    private TableColumn<createBookTableView, String> adminColAuthor;

    @FXML
    private TableColumn<createBookTableView, String> adminColType;

    @FXML
    private TableColumn<createBookTableView, String> adminColIsAvalible;

    @FXML
    private TableColumn<createBookTableView, Double> adminColPrice;

    @FXML
    private AnchorPane addPositionField;

    @FXML
    private TextField adminColTitleAddField;

    @FXML
    private TextField adminColAuthorAddField;

    @FXML
    private TextField adminColTypeAddField;

    @FXML
    private TextField adminColIsActiveAddField;

    @FXML
    private TextField adminColPriceAddField;

    @FXML
    private Button addBookButton;

    @FXML
    private Label errorAddLabel;

    @FXML
    private Label successAddLabel;

    @FXML
    private AnchorPane editPositionField;

    @FXML
    private TextField adminColTitleEditField;

    @FXML
    private TextField adminColAuthorEditField;

    @FXML
    private TextField adminColTypeEditField;

    @FXML
    private TextField adminColIsActiveEditField;

    @FXML
    private TextField adminColPriceEditField;

    @FXML
    private TextField adminColIdEditField;

    @FXML
    private Button editBookButton;

    @FXML
    private Button deleteBookButton;

    @FXML
    private Label errorEditLabel;

    @FXML
    private Label successEditLabel;

    @FXML
    private TableView<createUserTableView> adminUsersTab;

    @FXML
    private TableColumn<createUserTableView, Integer> adminUserId;

    @FXML
    private TableColumn<createUserTableView, String> adminUserName;

    @FXML
    private TableColumn<createUserTableView, String> adminUserLastname;

    @FXML
    private TableColumn<createUserTableView, String> adminUserEmail;

    @FXML
    private TableColumn<createUserTableView, String> adminUserLogin;

    @FXML
    private TableColumn<createUserTableView, String> adminUserPassword;

    @FXML
    private AnchorPane addUserField;

    @FXML
    private TextField adminColUserNameAddField;

    @FXML
    private TextField adminColUserAddNameField;

    @FXML
    private TextField adminColUserAddEmailField;

    @FXML
    private TextField adminColUserAddLoginField;

    @FXML
    private TextField adminColUserAddPasswordField;

    @FXML
    private Button addUserButton;

    @FXML
    private Label errorAddUserLabel;

    @FXML
    private Label successAddUserLabel;

    @FXML
    private AnchorPane editUserField;

    @FXML
    private TextField adminColNameEditField;

    @FXML
    private TextField adminColLastnameEditField;

    @FXML
    private TextField adminColEmailEditField;

    @FXML
    private TextField adminColLoginEditField;

    @FXML
    private TextField adminColPasswordEditField;

    @FXML
    private TextField adminColIdEditUserField;

    @FXML
    private Button editUserButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Label errorEditUserLabel;

    @FXML
    private Label successEditUserLabel;

    @FXML
    private AnchorPane editUserField1;




     //INICJALIZACYJNE FUNKCJE
    public void setTableBooks() {
        adminColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        adminColTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        adminColAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        adminColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        adminColType.setCellValueFactory(new PropertyValueFactory<>("genre"));
        adminColIsAvalible.setCellValueFactory(new PropertyValueFactory<>("avalibility"));

    }

    
    public void setTableBooks2() {

        adminTable.getItems().clear();

        List<Ksiazki> bookList = db.getBooks();

        bookData.clear();
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i).getTytul());

            bookData.add((createBookTableView) new createBookTableView(bookList.get(i).getIdKsiazki(), bookList.get(i).getTytul(), bookList.get(i).getAutor(), bookList.get(i).getCena(), bookList.get(i).getGatunek(), bookList.get(i).getDostepnosc()));
        }
        adminTable.setItems((ObservableList<createBookTableView>) bookData);

    }
    
    public void setTableUsers()
    {   
        adminUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
        adminUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
        adminUserLastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        adminUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        adminUserLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        adminUserPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    }
    
    public void setTableUsers2()
    {
        adminUsersTab.getItems().clear();

        List<Uzytkownicy> userList = db.getUsers() ;

        userData.clear();
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i).getLogin());

            userData.add((createUserTableView) new createUserTableView(userList.get(i).getIdUzytkownika(),userList.get(i).getImie(),userList.get(i).getNazwisko(),userList.get(i).getEmail(),userList.get(i).getLogin(),userList.get(i).getHaslo()));
        }
        adminUsersTab.setItems((ObservableList<createUserTableView>) userData);

    }
    
    
    @FXML
    void AddBook(ActionEvent event) {
        String title,author,genre,avalibility;
        double price;
        title = adminColTitleAddField.getText();
        author = adminColAuthorAddField.getText();
        genre = adminColTypeAddField.getText();
        avalibility = adminColIsActiveAddField.getText();
        price = Double.parseDouble(adminColPriceAddField.getText());
        
        
        if(adminColTitleAddField.getText().isBlank() || adminColAuthorAddField.getText().isBlank() || adminColTypeAddField.getText().isBlank() || adminColIsActiveAddField.getText().isBlank() || adminColPriceAddField.getText().isBlank())
        {
        Alert a = new Alert(Alert.AlertType.WARNING,"" ,ButtonType.OK);
            a.setHeaderText("Nie wype³niono wszystkich wymaganych pól!");
            a.setTitle("Ostrze¿enie!");
            a.show();
        
        }
        
        else{
            db.insertBook(title, author, genre, avalibility, price);
            Alert a = new Alert(Alert.AlertType.INFORMATION,"" ,ButtonType.OK);
            a.setHeaderText("Pomyœlnie dodano ksi¹¿kê do asortymentu!");
            a.setTitle("Sukces!");
            a.show();
        clearBookFields();
        setTableBooks2();
        
        }

    }
    
     

    @FXML
    void AddUser(ActionEvent event) {

        String name,lastname,email,login,password;
        name = adminColUserNameAddField.getText();
        lastname = adminColUserAddNameField.getText();
        email = adminColUserAddEmailField.getText();
        login = adminColUserAddLoginField.getText();
        password = adminColUserAddPasswordField.getText();
        
        
        if(name.isBlank() || lastname.isBlank() || email.isBlank() || login.isBlank() || password.isBlank())
        {
            Alert a = new Alert(Alert.AlertType.WARNING,"" ,ButtonType.OK);
            a.setHeaderText("Nie wype³niono wszystkich wymaganych pól!");
            a.setTitle("Ostrze¿enie!");
            a.show();
        
        }
        
        else if(!(db.checkUserRegister(login)))
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Wybrany login jest ju¿ zajêty!");
            a.setTitle("Ostrze¿enie");
            a.show();

        }
        else{
        db.insertUser(name, lastname, email, login, password);
        
        Alert a = new Alert(Alert.AlertType.INFORMATION,"" ,ButtonType.OK);
            a.setHeaderText("Pomyœlnie dodano u¿ytkownika!");
            a.setTitle("Sukces!");
            a.show();
            setTableUsers2();
            clearUserFields();
        }
        
    }

    @FXML
    void DeleteBook(ActionEvent event) {
        int id = Integer.parseInt(adminColIdEditField.getText());
        
        if(adminColIdEditField.getText().isBlank())
        {
        Alert a = new Alert(Alert.AlertType.WARNING,"" ,ButtonType.OK);
            a.setHeaderText("Nie wype³niono pola ID!");
            a.setTitle("Ostrze¿enie!");
            a.show();
        }
        
        else{
        db.deleteBookAdmin(id);
        
         Alert a = new Alert(Alert.AlertType.INFORMATION,"" ,ButtonType.OK);
            a.setHeaderText("Pomyœlnie usuniêto ksi¹¿kê z asortymentu!");
            a.setTitle("Sukces!");
            a.show();
            
        setTableBooks2();
        clearBookFields();
        }
    }

    @FXML
    void DeleteUser(ActionEvent event) {
        int id = Integer.parseInt(adminColIdEditUserField.getText());
        
        if(adminColIdEditUserField.getText().isBlank())
        {
        Alert a = new Alert(Alert.AlertType.WARNING,"" ,ButtonType.OK);
            a.setHeaderText("Nie wype³niono pola ID!");
            a.setTitle("Ostrze¿enie!");
            a.show();
        }
        else{
        db.deleteAccountAdmin(id);
        
        Alert a = new Alert(Alert.AlertType.INFORMATION,"" ,ButtonType.OK);
            a.setHeaderText("Pomyœlnie usuniêto u¿ytkownika!");
            a.setTitle("Sukces!");
            a.show();
            setTableUsers2();
            clearUserFields();
        }
        
    }

    @FXML
    void EditBook(ActionEvent event) {

        int id = Integer.parseInt(adminColIdEditField.getText());
        String title,author,genre,avalibility;
        double price;
        title = adminColTitleEditField.getText();
        author = adminColAuthorEditField.getText();
        genre = adminColTypeEditField.getText();
        avalibility = adminColIsActiveEditField.getText();
        price = Double.parseDouble(adminColPriceEditField.getText());
        
        if(adminColTitleEditField.getText().isBlank() || adminColAuthorEditField.getText().isBlank() || adminColTypeEditField.getText().isBlank() || adminColIsActiveEditField.getText().isBlank() || adminColPriceEditField.getText().isBlank() || adminColIdEditField.getText().isBlank())
        {
        Alert a = new Alert(Alert.AlertType.WARNING,"" ,ButtonType.OK);
            a.setHeaderText("Nie wype³niono wszystkich wymaganych pól!");
            a.setTitle("Ostrze¿enie!");
            a.show();
        
        }
        
        else{
        db.updateBookAdmin(id, title, author, genre, avalibility, price);
 
       Alert a = new Alert(Alert.AlertType.INFORMATION,"" ,ButtonType.OK);
            a.setHeaderText("Pomyœlnie zaktualizowano ksi¹¿kê w asortymencie!");
            a.setTitle("Sukces!");
            a.show();
            
        setTableBooks2();
        clearBookFields();
        }
        
    }

    @FXML
    void EditUser(ActionEvent event) {
        int id = Integer.parseInt(adminColIdEditUserField.getText());
        String name,lastname,email,login,password;
        name = adminColNameEditField.getText();
        lastname = adminColLastnameEditField.getText();
        email = adminColEmailEditField.getText();
        login = adminColLoginEditField.getText();
        password = adminColPasswordEditField.getText();
        
        if(adminColIdEditUserField.getText().isBlank() || name.isBlank() || lastname.isBlank() || email.isBlank() || login.isBlank() || password.isBlank())
        {
         Alert a = new Alert(Alert.AlertType.WARNING,"" ,ButtonType.OK);
            a.setHeaderText("Nie wype³niono wszystkich wymaganych pól!");
            a.setTitle("Ostrze¿enie!");
            a.show();
        
        }
        
         //ZAJÊTY LOGIN
        else if(!(db.checkUserRegister(login)))
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setHeaderText("Wybrany login jest ju¿ zajêty!");
            a.setTitle("Ostrze¿enie!");
            a.show();

        }
        else{
        db.updateUserAdmin(id, name, lastname, email, login, password);
        
         Alert a = new Alert(Alert.AlertType.INFORMATION,"" ,ButtonType.OK);
            a.setHeaderText("Pomyœlnie zaktualizowano dane u¿ytkownika!");
            a.setTitle("Sukces!");
            a.showAndWait();
            
            setTableUsers2();
            clearUserFields();
        }
    }

    
    public void clearBookFields() //Czyszczenie pól
    {
    adminColTitleAddField.clear();
    adminColAuthorAddField.clear();
    adminColTypeAddField.clear();
    adminColIsActiveAddField.clear();
    adminColPriceAddField.clear();
    adminColIdEditField.clear();
    adminColTitleEditField.clear();
    adminColAuthorEditField.clear();
    adminColTypeEditField.clear();
    adminColIsActiveEditField.clear();
    adminColPriceEditField.clear();
    }
    
    public void clearUserFields() //Czyszczenie pól
    {
    adminColIdEditUserField.clear();
    adminColNameEditField.clear();
    adminColLastnameEditField.clear();
    adminColEmailEditField.clear();
    adminColLoginEditField.clear();
    adminColPasswordEditField.clear();      
    adminColUserNameAddField.clear();
    adminColUserAddNameField.clear();
    adminColUserAddEmailField.clear();
    adminColUserAddLoginField.clear();
    adminColUserAddPasswordField.clear();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        setTableBooks();
        setTableBooks2();
        
        setTableUsers();
        setTableUsers2();
    }    
    
}
