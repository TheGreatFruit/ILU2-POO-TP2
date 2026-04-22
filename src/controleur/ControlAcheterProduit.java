package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
    private Village village;
    private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
    private ControlVerifierIdentite controlVerifierIdentite;

    public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
            ControlTrouverEtalVendeur controlTrouverEtalVendeur,
            Village village) {
        this.village = village;
        this.controlVerifierIdentite = controlVerifierIdentite;
        this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
    }

    public boolean verifIdentite(String nomAcheteur) {
        return controlVerifierIdentite.verifierIdentite(nomAcheteur);
    }

    public Gaulois[] trouverEtalsProduit(String produit) {
        return village.rechercherVendeursProduit(produit);
    }

    // NOUVELLE MÉTHODE : Permet de réaliser l'achat effectif sur l'étal
    public int acheterProduit(String nomVendeur, int quantite) {
        Etal etalVendeur = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
        if (etalVendeur != null) {
            return etalVendeur.acheterProduit(quantite);
        }
        return 0; // Sécurité si l'étal n'est pas trouvé
    }
}