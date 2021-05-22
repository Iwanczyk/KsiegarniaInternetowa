
package dbAccess;
import dbClasses.*;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.*;

public class databaseAccess {

    public void insertUser(String name,String lastname,String email, String login, String password){
        
        Uzytkownicy user = new Uzytkownicy();
        user.setImie(name);
        user.setNazwisko(lastname);
        user.setEmail(email);
        user.setLogin(login);
        user.setHaslo(password);
        
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
        
    
    }
    
    public void insertBook(String title, String author, String genre, String avalibility, double price)
    {
    Ksiazki book = new Ksiazki();
    book.setTytul(title);
    book.setAutor(author);
    book.setGatunek(genre);
    book.setDostepnosc(avalibility);
    book.setCena(price);
  
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    
    }
    
    public void insertCart(Uzytkownicy user, Ksiazki book, double price)
    {
    Koszyk cart = new Koszyk();
    cart.setIdUzytkownika(user);
    cart.setIdKsiazki(book);
    cart.setWartosc(price);
    
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(cart);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    
    }
    

    //LOGIN
    //WERYFIKACJA DANYCH
    public boolean checkUserLogin(String login, String password)
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    String hql = "SELECT login, haslo FROM Uzytkownicy WHERE login='"+login+"' AND haslo='"+password+"'";
    Query query = entityManager.createQuery(hql);
    List result = query.getResultList();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
    
    
    if(result.isEmpty()) //Zwraca fa³sz, jeœli nie znaleziono u¿ytkownika
    {
    return false;
    }
    else return true;
    }
    
    //REJESTRACJA
    //SPRAWDZENIE CZY LOGIN NIE JEST ZAJÊTY
    public boolean checkUserRegister(String login)
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    String hql = "SELECT login FROM Uzytkownicy WHERE login='"+login+"'";
    Query query = entityManager.createQuery(hql);
    List result = query.getResultList();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
    
    if(result.isEmpty())
    {
    return true;
    }
    else 
    {
    return false;
    }
    }
    
    
    //EDYCJA DANYCH -------------------------------------------------------------------------------------------------------------------------
    //ZMIANA DANYCH W KONCIE PRZEZ U¯YTKOWNIKA
    public int updateAccountData(String previousLogin, String name,String lastname,String email, String newlogin, String password)
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    String hql = "UPDATE Uzytkownicy SET imie='"+name+"', nazwisko='"+lastname+"', email='"+email+"', login='"+newlogin+"', haslo='"+password+"' WHERE login='"+previousLogin+"'";
    Query query = entityManager.createQuery(hql);
    int result = query.executeUpdate();
//    List result = query.getResultList();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
    
    return result;
    }
    
    
    //EDYCJA KSI¥¯KI PRZEZ ADMINA---------------------------------------------------------------------------------------------------------------------
    public int updateBookAdmin(int id, String title, String author, String genre, String avalibility, double price)
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    String hql = "UPDATE Ksiazki SET tytul='"+title+"', autor='"+author+"', gatunek='"+genre+"', dostepnosc='"+avalibility+"', cena="+price+" WHERE id_ksiazki="+id;
    Query query = entityManager.createQuery(hql);
    int result = query.executeUpdate();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
    
    return result;
    
    }

    
    //EDYCJA U¯YTKOWNIKA PRZEZ ADMINA---------------------------------------------------------------------------------------------------
    public int updateUserAdmin(int id, String name,String lastname,String email, String newlogin, String password)
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    String hql = "UPDATE Uzytkownicy SET imie='"+name+"', nazwisko='"+lastname+"', email='"+email+"', login='"+newlogin+"', haslo='"+password+"' WHERE id="+id;
    Query query = entityManager.createQuery(hql);
    int result = query.executeUpdate();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
    
    return result;
    }
    //____________________________________________________________________________________________________________________________________
    //____________________________________________________________________USUWANIE________________________________________________________
    //____________________________________________________________________________________________________________________________________
    
    //USUNIÊCIE KONTA PRZEZ U¯YTKOWNIKA --------------------------------------------------------------------------------------------------
    public int deleteAccount(String loginUserToDelete)
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    String hql = "DELETE FROM Uzytkownicy WHERE login='"+loginUserToDelete+"'";
    Query query = entityManager.createQuery(hql);
    int result = query.executeUpdate();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
    
    return result;
    }

    
    //USUNIÊCIE KSI¥¯KI PRZEZ ADMINA------------------------------------------------------------------------------------------------------
    public int deleteBookAdmin(int id)
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    String hql = "DELETE FROM Ksiazki WHERE id_ksiazki="+id;
    Query query = entityManager.createQuery(hql);
    int result = query.executeUpdate();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
    
    return result;
    }

    
    //USUNIÊCIE U¯YTKOWNIKA PRZEZ ADMINA--------------------------------------------------------------------------------------------------
    public int deleteAccountAdmin(int id)
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    String hql = "DELETE FROM Uzytkownicy WHERE idUzytkownika="+id;
    Query query = entityManager.createQuery(hql);
    int result = query.executeUpdate();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
    
    return result;
    }
    
    //USUNIÊCIE KOSZYKA-------------------------------------------------------------------------------------------------------------------
    public void deleteCart(int id)
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    String hql = "DELETE FROM Koszyk WHERE idUzytkownika="+id;
    Query query = entityManager.createQuery(hql);
    int result = query.executeUpdate();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
    
    
    
    }
    
    //____________________________________________________________________________________________________________________________________
    //____________________________________________________________________POBIERANIE DANYCH Z BAZY DANYCH_________________________________
    //____________________________________________________________________________________________________________________________________
    
    public List <Ksiazki> getBooks()
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    

    String hql = "FROM Ksiazki";
    Query query = entityManager.createQuery(hql);
    List result = query.getResultList();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
 
    return result;
    }
    
    
    public List getBooksGenre()
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    String hql = "SELECT DISTINCT gatunek FROM Ksiazki";
    Query query = entityManager.createQuery(hql);
    List<String> result = query.getResultList();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
    
    return result;
    
    }
    
    public List <Ksiazki> getBooksByGenre(String genre)
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    

    String hql = "FROM Ksiazki WHERE gatunek='"+genre+"'";
    Query query = entityManager.createQuery(hql);
    List result = query.getResultList();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
 
    return result;
    }
    
    public List <Ksiazki> getBooksByText(String text)
    {
    String textLowerCase = text.toLowerCase();
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    

    String hql = "FROM Ksiazki WHERE tytul LIKE '%"+text+"%' OR tytul Like '%"+textLowerCase+"%' OR autor LIKE '%"+text+"%' OR autor LIKE '%"+textLowerCase+"%'";
    Query query = entityManager.createQuery(hql);
    List result = query.getResultList();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
 
    return result;
    }
    
    public List <Ksiazki> getBooksByTextAndGenre(String text, String genre)
    {
        String textLowerCase = text.toLowerCase();
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    

    String hql = "FROM Ksiazki WHERE gatunek='"+genre+"' AND (tytul LIKE '%"+text+"%' OR tytul Like '%"+textLowerCase+"%' OR autor LIKE '%"+text+"%' OR autor LIKE '%"+textLowerCase+"%')";
    Query query = entityManager.createQuery(hql);
    List result = query.getResultList();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
 
    return result;
    }
    
    public List <Uzytkownicy> getUsers()
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    

    String hql = "FROM Uzytkownicy";
    Query query = entityManager.createQuery(hql);
    List result = query.getResultList();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
 
    return result;
    }
    
    public Uzytkownicy getCurrentUser(String login)
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    
    String hql = "FROM Uzytkownicy WHERE login='"+login+"'";
    Query query = entityManager.createQuery(hql);
    List <Uzytkownicy> result = query.getResultList();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
    
    return result.get(0);
    }
    
    public List <Koszyk> getCart(int id)
    {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Persistance");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();
    

    String hql = "FROM Koszyk WHERE idUzytkownika="+id;
    Query query = entityManager.createQuery(hql);
    List result = query.getResultList();
    entityManager.getTransaction().commit();
    entityManagerFactory.close();
 
    return result;

    }
    
}
