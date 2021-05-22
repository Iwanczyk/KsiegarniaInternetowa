package GUI;

import assistantClass.createBookTableView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import dbAccess.databaseAccess;
import dbClasses.Ksiazki;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShopingCartController implements Initializable {

    String userLogin="Tymczasowy login";
    public ObservableList<createBookTableView> cartDatatoDisplay = FXCollections.observableArrayList();
    databaseAccess db = new databaseAccess();

    @FXML
    private TableView<createBookTableView> cartTable;

    @FXML
    private TableColumn<createBookTableView, String> cartTitleCol;

    @FXML
    private TableColumn<createBookTableView, String> authorCartCol;

    @FXML
    private TableColumn<createBookTableView, String> priceCartCol;

      @FXML
    private TextField payField;

    @FXML
    private Button payButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button deletePositionButton;

    @FXML
    void ClearCart(ActionEvent event) {
       Alert a = new Alert(Alert.AlertType.WARNING,"",ButtonType.OK);
       a.setHeaderText("Czy na pewno chcesz usun¹æ wszystkie pozycje z koszyka?");
       a.setTitle("Uwaga!");
       a.showAndWait();
       
        if (a.getResult() == ButtonType.OK) {
            cartDatatoDisplay.clear();
            Refresh();
            }
    }
    
    private void clearAuto()
    {
    cartDatatoDisplay.clear();
    Refresh();
    }

    @FXML
    void DeletePosition(ActionEvent event) {
        createBookTableView selectedBookToDelete = cartTable.getSelectionModel().getSelectedItem();
        cartDatatoDisplay.remove(selectedBookToDelete);
        Refresh();
    }
    
    @FXML
    void Refresh() {
        //ActionEvent event
        setTableCart2();
        showCashToPay();
    }

    
    public void setDatatoDisplay(ObservableList<createBookTableView> data) {
        cartDatatoDisplay = data;

    }

    public void setTableCart() {
        cartTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCartCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        priceCartCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }

    public void setTableCart2() {
        cartTable.setItems((ObservableList<createBookTableView>) cartDatatoDisplay);
        showCashToPay();
    }

    @FXML
    void Purchase(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.INFORMATION,"",ButtonType.OK);
       a.setHeaderText("Pomyœlnie z³o¿ono zamówienie \nJeœli w Twoim koszyku znajduj¹ siê ksi¹¿ki niedostêpne, zamówienie opóŸni siê\n Za chwilê skontaktujemy siê z tob¹ w celu finalizacji zamówienia");
       a.setTitle("Sukces!");
       a.showAndWait();
       clearAuto();

    }

    @FXML
    void showCashToPay() {
        //ActionEvent event
        double total = 0;
        double finalPrice = 0;
        for(int i=0; i<cartDatatoDisplay.size();i++)
        {
        total +=cartDatatoDisplay.get(i).getPrice();
        }
        finalPrice = Math.round(total * 100.0) / 100.0;
        payField.setText(""+finalPrice);
    }

    public void setUserCartLogin(String loginFromMController) //Ustawienie loginu u¿ytkownika
    {
        userLogin = loginFromMController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableCart();
    
    }

}
