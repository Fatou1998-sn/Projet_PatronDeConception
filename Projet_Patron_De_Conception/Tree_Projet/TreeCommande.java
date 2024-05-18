import java.io.File;
import java.util.Scanner;

public class TreeCommande {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le chemin du repertoire : ");
        String cheminRepertoire = scanner.nextLine();
        File repertoire = new File(cheminRepertoire);
        
        if (repertoire.exists() && repertoire.isDirectory()) {
            afficherArborescence(repertoire, "");
        } else {
            System.out.println("Le chemin specifie n'existe pas ou n'est pas un repertoire.");
        }
        
        scanner.close();
    }

    public static void afficherArborescence(File repertoire, String espace) {
       
        System.out.println(espace + "+-- " + repertoire.getName());
        File[] fichiers = repertoire.listFiles();
        
        if (fichiers != null) {
            for (File fichier : fichiers) {
                if (fichier.isDirectory()) {
                    afficherArborescence(fichier, espace + "|   "); 
                } else {
                    System.out.println(espace + "|   " + fichier.getName()); 
                }
            }
        }
    }
}
