
package dbClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "koszyk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Koszyk.findAll", query = "SELECT k FROM Koszyk k")})
public class Koszyk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_koszyka")
    private Integer idKoszyka;
    @Basic(optional = false)
    @Column(name = "wartosc")
    private double wartosc;
    @JoinColumn(name = "id_ksiazki", referencedColumnName = "id_ksiazki")
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Ksiazki idKsiazki;
    @JoinColumn(name = "id_uzytkownika", referencedColumnName = "id_uzytkownika")
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Uzytkownicy idUzytkownika;

    public Koszyk() {
    }

    public Koszyk(Integer idKoszyka) {
        this.idKoszyka = idKoszyka;
    }

    public Koszyk(Integer idKoszyka, double wartosc) {
        this.idKoszyka = idKoszyka;
        this.wartosc = wartosc;
    }

    public Integer getIdKoszyka() {
        return idKoszyka;
    }

    public void setIdKoszyka(Integer idKoszyka) {
        this.idKoszyka = idKoszyka;
    }

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    public Ksiazki getIdKsiazki() {
        return idKsiazki;
    }

    public void setIdKsiazki(Ksiazki idKsiazki) {
        this.idKsiazki = idKsiazki;
    }

    public Uzytkownicy getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(Uzytkownicy idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKoszyka != null ? idKoszyka.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Koszyk)) {
            return false;
        }
        Koszyk other = (Koszyk) object;
        if ((this.idKoszyka == null && other.idKoszyka != null) || (this.idKoszyka != null && !this.idKoszyka.equals(other.idKoszyka))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbClasses.Koszyk[ idKoszyka=" + idKoszyka + " ]";
    }

}
