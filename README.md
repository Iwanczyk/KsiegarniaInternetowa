# Księgarnia internetowa z implementacją koszyka oraz wyszukiwarką

## Autor: Rafał Iwańczyk
## Specyfikacja projektu
### Cel projektu : Utworzenie pseudo sklepu internetowego z książkami.
#### Cele szczegółowe:
   1. Symulacja sklepu internetowego.
   2. Symulacja koszyka sklepu internetowego.
   3. Administracja dostępnymi danymi w bazie danych.

### Funkcjonalności:
   1. Rejestracja nowych użytkowników.
   2. Logowanie do kont użytkowników.
   3. Edycja informacji dotyczących użytkowników.
   4. Wirtualny koszyk przetrzymujący informacje o obecnym zamówieniu.
   5. Funkcjonalności CRUD.
   6. Zarządzanie zasobami sklepu z poziomu aplikacji.

### Wykorzystane technologie:
* Java FX.
* Scene Builder
* Hibernate
* Maven
* CSS
* PostgreSQL

### Interfejs serwisu

   <details>
       <summary>Ekran logowania </summary>
	
![alt text](https://github.com/Iwanczyk/KsiegarniaInternetowa/blob/main/img/login.PNG "Ekran logowania")

           Screen przedstawiający ekran logowania/rejestracji użytkownika
	   
   <p>Przy uruchomieniu aplikacji pojawia się ekran logowania. Użytkownik posiadający konto może wprowadzić swój login oraz hasło, aby się zalogować i przejść do głównej części aplikacji. Jeśli osoba nie posiada konta, może je założyć, wypełniając odpowiednie linie swoimi danymi. Podczas logowania aplikacja weryfikuje poprawność danych z tymi, które znajdują się w bazie danych, odpowiednio komunikując użytkownikowi wszelkie problemy. </p>
  <p>Podczas rejestracji nowego konta aplikacja weryfikuje, czy wszystkie pola zostały w odpowiedni sposób wypełnione. Program weryfikuje, czy w polach nie znajdują się białe znaki, oraz sprawdza ich długość, aby nie doprowadzić do konfliktu z bazą danych. Przy próbie stworzenia nowego konta następuje weryfikacja, czy podany przez użytkownika login jest wolny. Jeśli weryfikacja danych przy logowaniu/rejestracji przejdzie pomyślnie, użytkownik zostanie zalogowany, a ekran logowania zmieni się na ekrean główny.</p>
   
   </details>
   <details>
       <summary>Ekran główny</summary>

![alt text](https://github.com/Iwanczyk/KsiegarniaInternetowa/blob/main/img/ekranGlowny1.PNG "Ekran główny")

           Screen przedstawiający główną część aplikacji
	   
   <p>Po ukończeniu procesu logowania/rejestracji oczom użytkownika ukazuje się główna część aplikacji umożliwiająca dokonywanie zakupu wybranych książek. Asortyment załadowany zostaje z bazy danych. Każda pozycja jest opisana przez: 
	<ul>   
     <li> Tytuł </li>
     <li> Autora</li>
     <li> Gatunek</li>
     <li> Dostępność</li>
     <li> Cenę</li>
	</ul>
	   
  </p>
  
![alt text](https://github.com/Iwanczyk/KsiegarniaInternetowa/blob/main/img/ekranGlowny2.PNG "Ekran główny")

           Screen przedstawiający listę umożlwiającą sortowanie książeg według gatunku
  
  <p>Użytkownik ma także możliwość sortowania asortymentu poprzez gatunek, jaki go interesuje. Aby tego dokonać należy rozwinąć listę, gdzie znajdują się wszystkie gatunki, jakie sklep ma aktualnie w swoim asortymencie (dane te zostają załadowane z bazy danych). Po wybraniu odpowiedniego gatunku oraz kliknięciu przycisku "Wyszukaj" w tabeli pojawią się jedynie książki z wybranego gatunku.</p>
  
![alt text](https://github.com/Iwanczyk/KsiegarniaInternetowa/blob/main/img/ekranGlowny3.PNG "Ekran główny - Horror")
  
          Screen przedstawiający przykładowe sortowanie według gatunku "Horror"
  
 ![alt text](https://github.com/Iwanczyk/KsiegarniaInternetowa/blob/main/img/ekranGlowny4.PNG "Ekran główny - Wyszukiwarka")

           Screen przedstawiający działanie wyszukiwarki w połączeniu z sortowaniem według gatunku
  
  <p>Użytkownik ma także do dyspozycji wyszukiwarkę pozycji, która umożliwia szukanie książek po tytule lub autorze. Wyszukiwarka jest w stanie odnaleźć daną pozycję po fragmencie tytułu lub fragmencie imienia, czy nazwiska autora. Wyszukiwanie działa w połączeniu z listą sortującą gatunki. Na przedstawionym powyżej screenie użytkownik szuka książki zawierającej w swoim opisie słowo "Zew", która będzie z gatunku "Horror". </p> 
  
  ![alt text](https://github.com/Iwanczyk/KsiegarniaInternetowa/blob/main/img/ekranGlowny5.PNG "Ekran główny - Wyszukiwarka")

           Screen przedstawiający działanie wyszukiwarki
  
  <p>Na powyższym screenie ukazana jest próba wyszukania pozycji zawierającej słowo "King" w swoim opisie, bez dodatkowego filtrowania gatunków. Jak widać znalezione zostały książki, których autorem jest Stephen King.</p> 
  
   </details>
   
   <details>
       <summary>Menu użytkownika</summary>

![alt text](https://github.com/Iwanczyk/KsiegarniaInternetowa/blob/main/img/menu1.PNG "Ekran główny - Menu użytkownika")

           Screen przedstawiający pasek menu
	   
   <p>W górnej części aplikacji znajduje się interaktywne menu, które umożliwia użytkownikowi wykonanie działań takich jak:
	   <ul>
  <li> Zamknięcie programu.  (Program -> Zamknij program) </li>
  <li> Edycja danych konta.  (Konto -> Edytuj dane) </li>
  <li> Usunięcie konta.      (Konto -> Usuń konto) </li>
  <li> Wyświetlenie koszyka. (Koszyk -> Pokaż koszyk) </li>
  <li> Zapisanie koszyka w bazie danych. (Koszyk -> Zapisz koszyk) </li>
  <li> Otwarcie zapisanego koszyka.      (Koszyk -> Otwórz koszyk) </li>
  <li> Uzyskanie informacji na temat aplikacji. (Pomoc -> O programie i autorach) </li>
  <li> Otworzenie instrukcji obsługi aplikacji. (Pomoc -> Instrukcja) </li>
	</ul>
  </p>
  
  ![alt text](https://github.com/Iwanczyk/KsiegarniaInternetowa/blob/main/img/menu2.PNG "Ekran główny - Menu użytkownika")

           Screen przedstawiający edycję danych konta
	   
   <p>Powyższy screen prezentuje możliwość edycji danych konta użytkownika. Aby dokonać edycji wystarczy wypełnić odpowiednie linie danymi, a następnie kliknąć przycisk "Zatwierdź zmiany", co spowoduje w pierwszej kolejności weryfikację poprawności danych wprowadzonych (analogicznie do ekranu rejestracji), a następnie zmiana zostanie wykonana w bazie danych.
  </p>
   </details>
   
   
<details>
       <summary>Koszyk</summary>

![alt text](https://github.com/Iwanczyk/KsiegarniaInternetowa/blob/main/img/koszyk1.PNG "Ekran główny - Koszyk")

           Screen przedstawiający przykładowy koszyk użytkownika wraz z jego zawartością
	   
   <p>Użytkownik, po wybraniu interesujących go pozycji, może przejść do ekranu "Koszyk", gdzie zobaczy podsumowanie swojego wyboru (Tytuł, Autor, Cena) wraz z ilością wybranych książek. Na dole tego ekranu znajduje się informacja o koszcie zamówienia, która jest obliczana na bieżąco przy dodawaniu lub odejmowaniu kolejnych pozycji w koszyku. Do dyspozycji użytkownika są także przyciski:
	   <ul>
  <li> Złóż zamówienie - aktualnie przycisk jedynie dziękuje za złożone zamówienie i usuwa zawartośc koszyka <b>(w planach jest dodanie symulowanych metod płatności oraz ekranu dostawy)</b>. </li>
  <li> Odśwież koszyk - odświeżenie zawartości koszyka oraz ponowne przeliczenie kosztów zamówienia. </li>
  <li> Usuń z koszyka - umożliwia usunięcie wybranej pozycji z koszyka. </li>
  <li> Wyczyść zawartość - usunięcie wszystkich pozycji w koszyku oraz wyzerowanie kosztu zamówienia. </li>
	</ul>
  </p>
   </details>
   
   <details>
       <summary>Ekran Panelu Admina</summary>

![alt text](https://github.com/Iwanczyk/KsiegarniaInternetowa/blob/main/img/adminPanel1.PNG "Admin Panel")

           Screen przedstawiający panel administratora.
	   
   <p>Aplikacja posiada także panel administratora, aby się do niego dostać należy przy logowaniu wpisać w polu login: <b>admin</b>, a w polu hasło: <b>admin</b> (w planach jest weryfikacja danych admina poprzez dane pochodzące z bazy danych oraz rozszerzenie odpowiednich kont o role - Administracja, Moderacja, Użytkownik).</p>
  <p>Panel administratora umożliwia zarządzanie asortymentem sklepu (wyświetlenie asortymentu, dodawanie pozycji oraz edycja i usuwanie książek zarówno w asortymencie, jak i bazie danych).</p>
  
  ![alt text](https://github.com/Iwanczyk/KsiegarniaInternetowa/blob/main/img/adminPanel2.PNG "Admin Panel")
	   
   <p>Analogicznie, administrator może zarządzać użytkownikami, którzy posiadają konto. Admin może dodawać, usuwać i edytować konta użytkowników. (W planach jest poprawa bezpieczeństwa aplikacji oraz wrażliwych danych użytkowników).</p>
   </details>
   
   
### Baza danych
####	Diagram ERD
![alt text](https://github.com/Iwanczyk/KsiegarniaInternetowa/blob/main/img/ERD.PNG "Diagram ERD")

####	W plikach znajduje się kopia bazy danych (database.sql)
