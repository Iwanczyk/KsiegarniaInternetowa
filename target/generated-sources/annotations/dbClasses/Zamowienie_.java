package dbClasses;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Zamowienie.class)
public abstract class Zamowienie_ {

	public static volatile SingularAttribute<Zamowienie, String> stanRealizacji;
	public static volatile SingularAttribute<Zamowienie, Integer> idZamowienia;
	public static volatile SingularAttribute<Zamowienie, Integer> idKoszyka;
	public static volatile SingularAttribute<Zamowienie, String> adres;
	public static volatile SingularAttribute<Zamowienie, Date> dataZamowienia;

}

