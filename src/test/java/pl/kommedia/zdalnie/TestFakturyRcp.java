package pl.kommedia.zdalnie;

import java.util.List;

import javax.naming.NamingException;

import pl.kompro.model.handel.Faktura;
import pl.kompro.model.handel.Faktury.KryteriaFaktury;
import pl.kompro.model.rozrachunki.Rozrachunki;

public class TestFakturyRcp {
	public static void main( String[] args){
		(new TestFakturyRcp()).start();
	}

	private void start() {
		try{
			Rozrachunki rozrachunki= Uslugi.szukajRozrachunki();
			KryteriaFaktury kryteria= Uslugi.szukajKryteriaFaktury();
			
			List<Faktura> faktury = kryteria.odbFaktury();
			for( Faktura faktura: faktury){
				System.out.println( "Faktura: "+ faktura.getNumer());
				System.out.println( "Rozrachunek: "+ rozrachunki.utwRozrachunekDlaFaktury( faktura.getId()));
			}
		}catch( NamingException e){
			e.printStackTrace();
		}
	}
}
