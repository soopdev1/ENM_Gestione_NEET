
import it.refill.db.Entity;
import it.refill.domain.Allievi;
import it.refill.domain.ModelliPrg;
import it.refill.domain.ProgettiFormativi;
import it.refill.util.Pdf_new;
import it.refill.util.Utility;
import java.io.File;
import java.util.stream.Collectors;
import org.joda.time.DateTime;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rcosco
 */
public class GeneraDoc {

    public static void main(String[] args) {
        
        String idpr = "300";
        String idall = "1279";
        String usernameSA = "CINZIA.TONIN";
        
        
        
        
        
        Entity e = new Entity();
        e.begin();
        ProgettiFormativi prg = e.getEm().find(ProgettiFormativi.class,
                    Long.parseLong(idpr));
//        Allievi al = e.getEm().find(Allievi.class,
//                    Long.parseLong(idall));
        
        ModelliPrg m3 = Utility.filterModello3(prg.getModelli());
        ModelliPrg m4 = Utility.filterModello4(prg.getModelli());
        
//        File f1 = Pdf_new.MODELLO1(e, "3", usernameSA, prg.getSoggetto(), al, new DateTime(), true, true);
////        System.out.println(f1.getPath());
        
        File f2 = Pdf_new.MODELLO2(e,
                            "1",
                            usernameSA, prg.getSoggetto(),
                            prg,
                            prg.getAllievi().stream().filter(a1-> a1.getStatopartecipazione().getId().equals("01")).collect(Collectors.toList()) , new DateTime(), true);
        
        System.out.println(f2.getPath());
        
        File f3 = Pdf_new.MODELLO3(e,
                            usernameSA,
                            prg.getSoggetto(),
                            prg,
                            prg.getAllievi().stream().filter(p1 -> p1.getStatopartecipazione().getId().equals("01")).collect(Collectors.toList()),
                            prg.getDocenti(), m3.getLezioni(), prg.getStaff_modelli().stream().filter(m -> m.getAttivo() == 1).collect(Collectors.toList()),
                            new DateTime(), true);
        System.out.println(f3.getPath());
        
//        File f4 = Pdf_new.MODELLO4(e, usernameSA, prg.getSoggetto(), prg, prg.getAllievi().stream().filter(p1 -> p1.getStatopartecipazione().getId().equals("01")).collect(Collectors.toList()),
//                prg.getDocenti(), m4.getLezioni(), prg.getStaff_modelli().stream().filter(m -> m.getAttivo() == 1).collect(Collectors.toList()), new DateTime(), true);
//        
//        System.out.println(f4.getPath());
        
        
        e.close();
//        
//        String o = Pdf_new.checkFirmaQRpdfA("MODELLO1", "", new File("C:\\Users\\Administrator\\Desktop\\da caricare\\INFO05_MOISE_CLAUDIASILVIA_041120211144476.M1_pdfA.pdf"), "", "20;0;60;60");
//        System.out.println(o);
    }
                    
}
