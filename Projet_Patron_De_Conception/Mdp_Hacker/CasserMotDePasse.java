import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class CasserMotDePasse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez le type d'attaque : ");
        System.out.println("1. Attaque par force brute");
        System.out.println("2. Attaque par dictionnaire");
        System.out.println("Votre choix : ");
        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                attaqueParForceBrute();
                break;
            case 2:
                attaqueParDictionnaire();
                break;
            default:
                System.out.println("Choix invalide.");
        }

        scanner.close();
    }

    public static void attaqueParForceBrute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le mot de passe : ");
        String motDePasse = scanner.next();
        String hash = hashPassword(motDePasse);
        System.out.println("Le hash du mot de passe est : " + hash);
        scanner.close();
    }

    public static void attaqueParDictionnaire() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le mot de passe : ");
        String motDePasse = scanner.next();
        String hash = hashPassword(motDePasse);

        File dictionnaire = new File("C:\\Users\\pc\\Desktop\\Mdp_Hacker\\dictionnaire.txt");
        try {
            Scanner lecteur = new Scanner(dictionnaire);
            while (lecteur.hasNextLine()) {
                String mot = lecteur.nextLine();
                if (hash.equals(hashPassword(mot))) {
                    System.out.println("Mot de passe est dans le dictionnaire : " + hashPassword(mot));
                    lecteur.close();
                    scanner.close();
                    return;
                }
            }
            System.out.println("le Mot de passe n'est pas dans le dictionnaire.");
            lecteur.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier dictionnaire introuvable.");
        }
        scanner.close();
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Erreur lors du hachage du mot de passe : " + e.getMessage());
            return null;
        }
    }
}
