
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
@Table(name = "ksiazki")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ksiazki.findAll", query = "SELECT k FROM Ksiazki k")})
public class Ksiazki implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ksiazki")
    private Integer idKsiazki;
    @Basic(optional = false)
    @Column(name = "tytul")
    private String tytul;
    @Basic(optional = false)
    @Column(name = "autor")
    private String autor;
    @Basic(optional = false)
    @Column(name = "cena")
    private double cena;
    @Column(name = "dostepnosc")
    private String dostepnosc;
    @Column(name = "gatunek")
    private String gatunek;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKsiazki")
    private List<Koszyk> koszykList;

    public Ksiazki() {
    }

    public Ksiazki(Integer idKsiazki) {
        this.idKsiazki = idKsiazki;
    }

    public Ksiazki(Integer idKsiazki, String tytul, String autor, double cena) {
        this.idKsiazki = idKsiazki;
        this.tytul = tytul;
        this.autor = autor;
        this.cena = cena;
    }

    public Integer getIdKsiazki() {
        return idKsiazki;
    }

    public void setIdKsiazki(Integer idKsiazki) {
        this.idKsiazki = idKsiazki;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getDostepnosc() {
        return dostepnosc;
    }

    public void setDostepnosc(String dostepnosc) {
        this.dostepnosc = dostepnosc;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
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
        hash += (idKsiazki != null ? idKsiazki.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ksiazki)) {
            return false;
        }
        Ksiazki other = (Ksiazki) object;
        if ((this.idKsiazki == null && other.idKsiazki != null) || (this.idKsiazki != null && !this.idKsiazki.equals(other.idKsiazki))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbClasses.Ksiazki[ idKsiazki=" + idKsiazki + " ]";
    }

}
