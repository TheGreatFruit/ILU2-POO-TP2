package frontiere;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;

public class BoundaryAcheterProduit {
    private ControlAcheterProduit controlAcheterProduit;

    public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
        this.controlAcheterProduit = controlAcheterProduit;
    }

    public void acheterProduit(String nomAcheteur) {
        // Vérification de l'identité
        if (!controlAcheterProduit.verifIdentite(nomAcheteur)) {
            System.out.println("Je suis désolée " + nomAcheteur
                    + " mais il faut être un habitant de notre village pour commercer ici.");
            return;
        }

        // Demande du produit
        String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?\n");
        Gaulois[] listeVendeursProduit = controlAcheterProduit.trouverEtalsProduit(produit);

        // Cas où personne ne vend le produit
        if (listeVendeursProduit == null || listeVendeursProduit.length == 0) {
            System.out.println("Désolé, personne ne vend ce produit au marché.");
            return;
        }

        // Construction de la liste des vendeurs
        StringBuilder chaine = new StringBuilder();
        chaine.append("Chez quel commerçant voulez-vous acheter des ").append(produit).append(" ?\n");
        for (int i = 0; i < listeVendeursProduit.length; i++) {
            chaine.append(i + 1).append(" - ").append(listeVendeursProduit[i].getNom()).append("\n");
        }

        // Sélection du vendeur
        int numVendeur = Clavier.entrerEntier(chaine.toString()) - 1;
        String nomVendeur = listeVendeursProduit[numVendeur].getNom();

        System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + nomVendeur);
        System.out.println("Bonjour " + nomAcheteur);

        // Demande de la quantité et exécution de l'achat
        int quantiteDemandee = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?\n");
        int quantiteAchetee = controlAcheterProduit.acheterProduit(nomVendeur, quantiteDemandee);

        // Affichage des résultats selon la disponibilité du stock
        if (quantiteAchetee == 0) {
            System.out.println(nomAcheteur + " veut acheter " + quantiteDemandee + " " + produit
                    + ", malheureusement il n'y en a plus !");
        } else if (quantiteAchetee < quantiteDemandee) {
            System.out.println(nomAcheteur + " veut acheter " + quantiteDemandee + " " + produit
                    + ", malheureusement " + nomVendeur + " n'en a plus que " + quantiteAchetee + ".");
            System.out.println(nomAcheteur + " achète tout le stock de " + nomVendeur + ".");
        } else {
            System.out.println(nomAcheteur + " achète " + quantiteAchetee + " " + produit + " à " + nomVendeur + ".");
        }
    }
}