package pl.kommedia.zdalnie;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

import pl.kommedia.ejb.administracja.Konf;
import pl.kommedia.ejb.rozrachunki.RozrachunkiBil;
import pl.kompro.model.handel.Faktura;
import pl.kompro.model.handel.Faktury;
import pl.kompro.model.handel.Faktury.KryteriaFaktury;
import pl.kompro.model.kartoteki.Firma;
import pl.kompro.model.rozrachunki.Rozrachunki;

public class Uslugi {
	
	private static Properties props= new Properties();
	static{
		props.put( Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	}

	public static KryteriaFaktury szukajKryteriaFaktury() throws NamingException{
		final Context ctx= new InitialContext( props);
		return (KryteriaFaktury) ctx.lookup( "ejb:kom-media-ear/kom-media-ejb/"
				+ "/KryteriaFakturySprzedazy!pl.kompro.model.handel.Faktury$KryteriaFaktury?stateful");
	}
	
	public static Rozrachunki szukajRozrachunki() throws NamingException{
		final Context ctx= new InitialContext( props);
		return (Rozrachunki) ctx.lookup( "ejb:kom-media-ear/kom-media-ejb/"
				+ "/RozrachunkiBil!pl.kompro.model.rozrachunki.Rozrachunki?stateful");
	}
}
