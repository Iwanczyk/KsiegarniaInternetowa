package dbClasses;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Uzytkownicy.class)
public abstract class Uzytkownicy_ {

	public static volatile SingularAttribute<Uzytkownicy, String> imie;
	public static volatile SingularAttribute<Uzytkownicy, String> nazwisko;
	public static volatile SingularAttribute<Uzytkownicy, String> haslo;
	public static volatile ListAttribute<Uzytkownicy, Koszyk> koszykList;
	public static volatile SingularAttribute<Uzytkownicy, String> login;
	public static volatile SingularAttribute<Uzytkownicy, Integer> idUzytkownika;
	public static volatile SingularAttribute<Uzytkownicy, String> email;

}

