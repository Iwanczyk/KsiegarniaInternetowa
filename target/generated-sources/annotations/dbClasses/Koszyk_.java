package dbClasses;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Koszyk.class)
public abstract class Koszyk_ {

	public static volatile SingularAttribute<Koszyk, Ksiazki> idKsiazki;
	public static volatile SingularAttribute<Koszyk, Integer> idKoszyka;
	public static volatile SingularAttribute<Koszyk, Double> wartosc;
	public static volatile SingularAttribute<Koszyk, Uzytkownicy> idUzytkownika;

}

