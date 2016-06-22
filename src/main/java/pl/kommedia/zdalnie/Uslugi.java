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
import pl.kompro.model.kartoteki.Firma;

public class Uslugi {
	
	public static void main( String[] args){
		Uslugi uslugi= new Uslugi();
	}
	
	public Uslugi(){
		Properties clientProp = new Properties();
		clientProp.put( "remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
		clientProp.put( "remote.connections", "default");
		clientProp.put( "remote.connection.default.port", "8080"); 
		clientProp.put( "remote.connection.default.host", "localhost"); 
//		clientProp.put("remote.connection.default.username", "ejbUser");
//		clientProp.put("remote.connection.default.password", "ejbPassword");
		clientProp.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false"); 

		EJBClientConfiguration cc = new PropertiesBasedEJBClientConfiguration( clientProp);
		ContextSelector<EJBClientContext> selector = new ConfigBasedEJBClientContextSelector( cc);
		EJBClientContext.setSelector( selector);

		Properties props = new Properties();
		//props.put("jboss.naming.client.ejb.context", true);
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		Context ctx;
		try{
			ctx = new InitialContext(props);
			//Dupa
			Konf konfiguracja= (Konf)ctx.lookup("ejb:kom-media-ear/kom-media-ejb//Konfiguracja!pl.kommedia.ejb.administracja.Konf");
			List<Firma> dupa= konfiguracja.getWykazFirm();
			System.err.println( "Konfig: "+ dupa);
			for( Firma firma: dupa){
				System.out.println( "Firma("+ firma.getKod()+") - "+ firma.getNazwa());
			}


		}catch( NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
