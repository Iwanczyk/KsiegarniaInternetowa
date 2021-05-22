
package assistantClass;


public class createBookTableView {

    private int id;
    private double price;
    private String title,author,genre,avalibility;

    public createBookTableView(int id, String title, String author,double price, String genre, String avalibility) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.avalibility = avalibility;
    }

    public createBookTableView(String title, String author,double price, String genre, String avalibility) {
        this.price = price;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.avalibility = avalibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAvalibility() {
        return avalibility;
    }

    public void setAvalibility(String avalibility) {
        this.avalibility = avalibility;
    }


    public String toStringAdmin() {
        return "createBookTableView{" + "id=" + id + ", price=" + price + ", title=" + title + ", author=" + author + ", genre=" + genre + ", avalibility=" + avalibility + '}';
    }

    @Override
    public String toString() {
        return "createBookTableView{" + "price=" + price + ", title=" + title + ", author=" + author + ", genre=" + genre + ", avalibility=" + avalibility + '}';
    }
    
    
    
    
}
