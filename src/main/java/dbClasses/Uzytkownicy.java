
package dbClasses;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "uzytkownicy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uzytkownicy.findAll", query = "SELECT u FROM Uzytkownicy u")})
public class Uzytkownicy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_uzytkownika")
    private Integer idUzytkownika;
    @Basic(optional = false)
    @Column(name = "imie")
    private String imie;
    @Basic(optional = false)
    @Column(name = "nazwisko")
    private String nazwisko;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    @Column(name = "haslo")
    private String haslo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUzytkownika")
    private List<Koszyk> koszykList;

    public Uzytkownicy() {
    }

    public Uzytkownicy(Integer idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public Uzytkownicy(Integer idUzytkownika, String imie, String nazwisko, String email, String login) {
        this.idUzytkownika = idUzytkownika;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.login = login;
    }

    public Integer getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(Integer idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @XmlTransient
    public List<Koszyk> getKoszykList() {
        return koszykList;
    }

    public void setKoszykList(List<Koszyk> koszykList) {
        this.koszykList = koszykList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUzytkownika != null ? idUzytkownika.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uzytkownicy)) {
            return false;
        }
        Uzytkownicy other = (Uzytkownicy) object;
        if ((this.idUzytkownika == null && other.idUzytkownika != null) || (this.idUzytkownika != null && !this.idUzytkownika.equals(other.idUzytkownika))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbClasses.Uzytkownicy[ idUzytkownika=" + idUzytkownika + " ]";
    }

}
