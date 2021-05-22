package GUI;

import assistantClass.createBookTableView;
import dbAccess.databaseAccess;
import dbClasses.Koszyk;
import dbClasses.Ksiazki;
import dbClasses.Uzytkownicy;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainAppController implements Initializable {

    databaseAccess db = new databaseAccess();
    public String login = "";
    public ObservableList<createBookTableView> bookData = FXCollections.observableArrayList();
    public ObservableList<createBookTableView> cartData = FXCollections.observableArrayList();
    @FXML
    private TableView<createBookTableView> mainTable;

    @FXML
    private TableColumn<createBookTableView, Integer> columnBookId;

    @FXML
    private TableColumn<createBookTableView, String> columnTitle; //<?, ?>

    @FXML
    private TableColumn<createBookTableView, String> columnAuthor;

    @FXML
    private TableColumn<createBookTableView, String> columnType;

    @FXML
    private TableColumn<createBookTableView, String> columnIsAvalible;

    @FXML
    private TableColumn<createBookTableView, Double> columnPrice;

//    @FXML
//    private TableColumn<?, ?> addToShopCart;

    @FXML
    private TextField search;

    @FXML
    private ComboBox<String> categories;

    @FXML
    private Button searchButton;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu programMenu;

    @FXML
    private MenuItem exitProgram;

    @FXML
    private MenuItem editPersonalData;

    @FXML
    private MenuItem deleteAccount;

    @FXML
    private Menu shopcart;

    @FXML
    private MenuItem saveShopcart;

    @FXML
    private MenuItem openShopcart;

    @FXML
    private MenuItem showShopcart;

    @FXML
    private MenuItem deleteShopCart;

    @FXML
    private Menu help;

    @FXML
    private MenuItem about;

    @FXML
    private MenuItem instruction;
    
     @FXML
    private Button addToCartbutton;

    @FXML
    void AddToCartAction(ActionEvent event) {

        createBookTableView selectedBook = mainTable.getSelectionModel().getSelectedItem();
        cartData.add(selectedBook);
    }
    

    //INICJALIZACYJNE FUNKCJE
    public void setTable() {
        columnBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("genre"));
        columnIsAvalible.setCellValueFactory(new PropertyValueFactory<>("avalibility"));

    }

    public void setTable2() {

        mainTable.getItems().clear();

        List<Ksiazki> bookList = db.getBooks();

        bookData.clear();
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i).getTytul());

            bookData.add((createBookTableView) new createBookTableView(bookList.get(i).getIdKsiazki(), bookList.get(i).getTytul(), bookList.get(i).getAutor(), bookList.get(i).getCena(), bookList.get(i).getGatunek(), bookList.get(i).getDostepnosc()));
        }
        mainTable.setItems((ObservableList<createBookTableView>) bookData);
    }

   
    public void setComboBoxBook() {
        categories.getItems().clear();

        List<String> genresArr = db.getBooksGenre();
        genresArr.sort(null);
        genresArr.add(0, "---BRAK---");
        for (int i = 0; i < genresArr.size(); i++) {

            categories.getItems().addAll(genresArr.get(i));

        }

    }

    //USUNIÊCIE ZAWARTOŒCI KOSZYKA
    @FXML
    void DeleteShopCart(ActionEvent event) {

        ButtonType potwierdz = new ButtonType("PotwierdŸ", ButtonBar.ButtonData.OK_DONE);
        ButtonType anuluj = new ButtonType("Anuluj", ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "", potwierdz, anuluj);

        alert.setHeaderText("Jesteœ pewny, ¿e chcesz usun¹æ zawartoœæ koszyka? \nTa operacja jest nieodwracalna");
        alert.setTitle("Uwaga!");
        alert.showAndWait();

        if (alert.getResult() == potwierdz) {
        
            cartData.clear();
        
        Alert a = new Alert(Alert.AlertType.INFORMATION,"",ButtonType.OK);
        a.setHeaderText("Usuniêto zawartoœæ koszyka");
        a.setTitle("Sukces!");
        a.show();
        }
        
    }

    //USUNIÊCIE KONTA
    @FXML
    void DeleteUser(ActionEvent event) throws InterruptedException {
        ButtonType potwierdz = new ButtonType("PotwierdŸ", ButtonBar.ButtonData.OK_DONE);
        ButtonType anuluj = new ButtonType("Anuluj", ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.WARNING, "", potwierdz, anuluj);

        alert.setHeaderText("Jesteœ pewny, ¿e chcesz usun¹æ konto? \nTa operacja jest nieodwracalna");
        alert.setTitle("Uwaga!");
        alert.showAndWait();

        if (alert.getResult() == potwierdz) {
            int id = db.getCurrentUser(login).getIdUzytkownika();
            db.deleteCart(id);
            db.deleteAccount(login);
            
            Alert a = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
            a.setHeaderText("Pomyœlnie usuniêto Twoje konto \nProgram zostanie zaraz wy³¹czony");
            a.setTitle("Sukces!");
            a.show();
            TimeUnit.SECONDS.sleep(5);
            System.exit(0);
        }
    }

    //EDYCJA DANYCH W KONCIE
    @FXML
    void EditAccountData(ActionEvent event) throws IOException {

        System.out.println("Login w main app: " + login);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/EditData.fxml"));
        fxmlLoader.load();
        EditDataController editcontroller = fxmlLoader.getController();
        editcontroller.setEditLogin(login);

        Parent p = fxmlLoader.getRoot();
        Scene scene = new Scene(p);
        Stage stage = new Stage();
        stage.setTitle("Ksiêgarnia internetowa - Edycja danych");
        scene.getStylesheets().add("/style.css");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
        stage.setScene(scene);
        stage.show();

    }

    //WYJŒCIE Z APLIKACJI
    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

    //OTWORZENIE DOKUMENTU O AUTORZE
    @FXML
    void OpenAbout(ActionEvent event) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File myFile = new File(classLoader.getResource("aboutAuthor.pdf").getFile());
            Desktop.getDesktop().open(myFile);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //OTWORZENIE INSTRUCKJI
    @FXML
    void OpenInstruction(ActionEvent event) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File myFile = new File(classLoader.getResource("instruction.pdf").getFile());
            Desktop.getDesktop().open(myFile);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //OTWARCIE KOSZYKA ZAPISANEGO W BAZIE DANYCH
    @FXML
    void OpenShopCart(ActionEvent event) throws IOException {

        int userID = db.getCurrentUser(login).getIdUzytkownika();
        List<Koszyk> cartList = db.getCart(userID);
        
        cartData.clear();
        
        for (int i = 0; i < cartList.size(); i++) {
            System.out.println(cartList.get(i).getIdKsiazki().getTytul());

            cartData.add((createBookTableView) new createBookTableView(cartList.get(i).getIdKsiazki().getIdKsiazki(), cartList.get(i).getIdKsiazki().getTytul(), cartList.get(i).getIdKsiazki().getAutor(),cartList.get(i).getIdKsiazki().getCena(), cartList.get(i).getIdKsiazki().getGatunek(), cartList.get(i).getIdKsiazki().getDostepnosc()));
//            System.out.println(bookData.get(i).toString());
        }
        ShowShopCart();
        
        
    }

    //ZAPISANIE ZAWARTOŒCI KOSZYKA W BAZIE DANYCH
    @FXML
    void SaveShopCart(ActionEvent event) {

        ButtonType potwierdz = new ButtonType("PotwierdŸ", ButtonBar.ButtonData.OK_DONE);
        ButtonType anuluj = new ButtonType("Anuluj", ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", potwierdz, anuluj);

        alert.setHeaderText("Chcesz zapisaæ aktualny koszyk w bazie danych?");
        alert.setTitle("Uwaga!");
        alert.showAndWait();

        if (alert.getResult() == potwierdz) {
        
        Uzytkownicy currentUser = db.getCurrentUser(login);
        Ksiazki tempBook = new Ksiazki();
        
        for(int i=0; i<cartData.size();i++)
        {
            tempBook.setIdKsiazki(cartData.get(i).getId());
            tempBook.setTytul(cartData.get(i).getTitle());
            tempBook.setAutor(cartData.get(i).getAuthor());
            tempBook.setCena(cartData.get(i).getPrice());
            tempBook.setGatunek(cartData.get(i).getGenre());
            tempBook.setDostepnosc(cartData.get(i).getAvalibility());
            
            db.insertCart(currentUser, tempBook, tempBook.getCena() );
        }
        
        Alert a = new Alert(Alert.AlertType.INFORMATION,"",ButtonType.OK);
        a.setHeaderText("Zapis powiód³ siê");
        a.setTitle("Sukces!");
        a.show();
        
        }
        
    }

    //WYSZUKIWANIE KSI¥¯EK
    @FXML
    void SearchBooks(ActionEvent event) {

        
        if (search.getText().isBlank() && categories.getValue().equals("---BRAK---")) { //Wszystko puste
            setTable2();

        }
        
        else if (search.getText().isBlank() && !categories.getValue().isBlank()) { //Pusty textfield
            String parameter = categories.getValue();
            mainTable.getItems().clear();
            List<Ksiazki> bookList = db.getBooksByGenre(parameter);

            bookData.clear();
            for (int i = 0; i < bookList.size(); i++) {

//                System.out.println(bookList.get(i).getTytul());
                bookData.add((createBookTableView) new createBookTableView(bookList.get(i).getIdKsiazki(), bookList.get(i).getTytul(), bookList.get(i).getAutor(), bookList.get(i).getCena(), bookList.get(i).getGatunek(), bookList.get(i).getDostepnosc()));
            }
            mainTable.setItems((ObservableList<createBookTableView>) bookData);
        } 
        
        else if (!search.getText().isBlank() && (categories.getValue().equals("---BRAK---") || categories.getValue().isBlank())) { //Pusta kategoria
            String parameter = search.getText();
            mainTable.getItems().clear();
            List<Ksiazki> bookList = db.getBooksByText(parameter);

            bookData.clear();
            for (int i = 0; i < bookList.size(); i++) {

//                System.out.println(bookList.get(i).getTytul());
                bookData.add((createBookTableView) new createBookTableView(bookList.get(i).getIdKsiazki(), bookList.get(i).getTytul(), bookList.get(i).getAutor(), bookList.get(i).getCena(), bookList.get(i).getGatunek(), bookList.get(i).getDostepnosc()));
            }
            mainTable.setItems((ObservableList<createBookTableView>) bookData);
            
                if(bookList.isEmpty())
                {
                Alert a = new Alert(Alert.AlertType.INFORMATION,"",ButtonType.OK);
                a.setTitle("Niepowodzenie!");
                a.setHeaderText("Nie uda³o siê znaleŸæ ksi¹¿ek o podanym kryterium!");
                
                }
            
        }
        
        else
        {
        String parameter = search.getText();
        String genre = categories.getValue();
            mainTable.getItems().clear();
            List<Ksiazki> bookList = db.getBooksByTextAndGenre(parameter,genre);

            bookData.clear();
            for (int i = 0; i < bookList.size(); i++) {

//                System.out.println(bookList.get(i).getTytul());
                bookData.add((createBookTableView) new createBookTableView(bookList.get(i).getIdKsiazki(), bookList.get(i).getTytul(), bookList.get(i).getAutor(), bookList.get(i).getCena(), bookList.get(i).getGatunek(), bookList.get(i).getDostepnosc()));
            }
            mainTable.setItems((ObservableList<createBookTableView>) bookData);
        
        if(bookList.isEmpty())
                {
                Alert a = new Alert(Alert.AlertType.INFORMATION,"",ButtonType.OK);
                a.setTitle("Niepowodzenie!");
                a.setHeaderText("Nie uda³o siê znaleŸæ ksi¹¿ek o podanym kryterium!");
                
                }
        }
        
            

    }

    //OTWARCIE EKRANU KOSZYK
    @FXML
    void ShowShopCart() throws IOException {
        //ActionEvent event
         System.out.println("Login w metodzie show cart w main app: " + login);

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/ShopingCart.fxml"));
        fxmlLoader.load();
        ShopingCartController shopcartcontroler = fxmlLoader.getController();
        shopcartcontroler.userLogin = login;
        shopcartcontroler.cartDatatoDisplay = cartData;

        Parent p = fxmlLoader.getRoot();
        Scene scene = new Scene(p);
        Stage stage = new Stage();
        stage.setTitle("Ksiêgarnia internetowa - Koszyk");
        scene.getStylesheets().add("/style.css");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setComboBoxBook();
        setTable();
        setTable2();
    }

}
