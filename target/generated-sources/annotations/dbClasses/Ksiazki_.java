package dbClasses;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Ksiazki.class)
public abstract class Ksiazki_ {

	public static volatile SingularAttribute<Ksiazki, Integer> idKsiazki;
	public static volatile SingularAttribute<Ksiazki, String> dostepnosc;
	public static volatile SingularAttribute<Ksiazki, String> gatunek;
	public static volatile SingularAttribute<Ksiazki, Double> cena;
	public static volatile ListAttribute<Ksiazki, Koszyk> koszykList;
	public static volatile SingularAttribute<Ksiazki, String> tytul;
	public static volatile SingularAttribute<Ksiazki, String> autor;

}

