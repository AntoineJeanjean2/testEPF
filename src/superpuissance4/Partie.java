/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpuissance4;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Antoine Jeanjean
 */
public class Partie {

    Joueur[] listeJoueurs = new Joueur[2];
    Grille grilleJeu = new Grille();
    Joueur joueurCourant;

    public void attribuerCouleursAuxJoueurs() {
        Random r = new Random();
        boolean couleur;
        couleur = r.nextBoolean();
        if (couleur) {
            listeJoueurs[0].couleur = "Rouge";
            listeJoueurs[1].couleur = "Jaune";
        } else {
            listeJoueurs[0].couleur = "Jaune";
            listeJoueurs[1].couleur = "Rouge";

        }
    }

    public void initialiserPartie() {
        grilleJeu.viderGrille();

        Scanner sc = new Scanner(System.in);
        System.out.println("Le pseudo du joueur 1 est :");
        Joueur J1 = new Joueur(sc.nextLine());
        System.out.println("Le pseudo du joueur 2 est :");
        Joueur J2 = new Joueur(sc.nextLine());
        listeJoueurs[0] = J1;
        listeJoueurs[1] = J2;

        attribuerCouleursAuxJoueurs();

        System.out.println(J1.nom + " est de couleur " + J1.couleur);
        System.out.println(J2.nom + " est de couleur " + J2.couleur);

        for (int i = 0; i < 21; i++) {
            J1.ajouter_jeton(new Jeton(J1.couleur));
            J2.ajouter_jeton(new Jeton(J2.couleur));
        }
        
        J1.nombreDesintegrateurs=0;
        J2.nombreDesintegrateurs=0;

        Random r = new Random();
        boolean premierJoueur = r.nextBoolean();
        if (premierJoueur) {
            joueurCourant = listeJoueurs[0];
        } else {
            joueurCourant = listeJoueurs[1];
        }

        Random r2 = new Random();
        int ligne;
        int colonne;
        for (int i = 0; i < 6; i++) {
            ligne = r2.nextInt(6);
            colonne = r2.nextInt(7);
            if (grilleJeu.Cellules[ligne][colonne].desintegrateur == false) {
                grilleJeu.placerDesintegrateur(ligne, colonne);
            }
        }
    }

    public int choix_joueur() {
        System.out.println(joueurCourant.nom+"Choisissez une action à effectuer");
        System.out.println("1) Poser un jeton");
        System.out.println("2) Récupérer un jeton");
        System.out.println("3) Utiliser un désintégrateur");

        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        while (choix != 1 && choix != 2 && choix != 3) {
            System.out.println("Saisissez un choix valide (inclus entre 1 et 3)");
            choix = sc.nextInt();
        }
        return choix;
    }

    public Joueur prochainJoueur(Joueur un_joueur) {
        if (listeJoueurs[0] == joueurCourant) {
            return listeJoueurs[1];
        }
        return listeJoueurs[0];
    }
    

    public void tourDeJeu() {
        while (grilleJeu.etreGagnantePourJoueur(joueurCourant) != true && grilleJeu.etreRemplie() != true) {

            int choix = choix_joueur();

            if (choix == 1) {

                System.out.println(joueurCourant.nom + " Choisissez dans quelle colonne vous souhaitez placer un jeton");
                Scanner sc = new Scanner(System.in);
                int saisie = sc.nextInt();

                while (saisie < 0 || saisie > 7) {
                    System.out.println("Mauvaise  saisie, saisissez un numéro de colonne valide");
                    sc = new Scanner(System.in);
                    saisie = sc.nextInt()-1;
                }

                if (joueurCourant.nombreJetonsRestants > 0) {
                    boolean jetonAposer;
                    jetonAposer = grilleJeu.ajouterJetonDansColonne(joueurCourant,joueurCourant.listeJetons[joueurCourant.nombreJetonsRestants - 1], saisie - 1);

                    if (jetonAposer == true) {
                        joueurCourant.nombreJetonsRestants--;
                    } else {
                        System.out.println("Cette colonne est remplie, veuillez saisir un autre numéro de colonne");
                        tourDeJeu();
                    }
                }
            }

            if (choix == 2) {
                System.out.println("Saisissez le numéro de ligne du jeton que vous souhaitez récupérer");
                Scanner saisieLigne = new Scanner(System.in);
                int ligne = saisieLigne.nextInt()-1;

                while (ligne < 0 || ligne > 5) {
                    System.out.println("Mauvaise  saisie, saisissez un numéro de ligne valide");
                    saisieLigne = new Scanner(System.in);
                    ligne = saisieLigne.nextInt()-1;
                }

                System.out.println("Saisissez le numéro de colonne du jeton que vous souhaitez récupérer");
                Scanner saisieColonne = new Scanner(System.in);
                int colonne = saisieColonne.nextInt()-1;

                while (colonne < 0 || colonne > 6) {
                    System.out.println("Mauvaise  saisie, saisissez un numéro de colonne valide");
                    saisieColonne = new Scanner(System.in);
                    colonne = saisieColonne.nextInt()-1;
                }
                
                if (grilleJeu.Cellules[ligne][colonne]!=null && grilleJeu.Cellules[ligne][colonne].lireCouleurDuJeton().equals(joueurCourant.couleur)){
                    grilleJeu.recupererJeton(ligne, colonne);
                    grilleJeu.tasserGrille(ligne, colonne);
                }    
                else{
                    System.out.println("Vous ne pouvez pas récupérer un jeton adverse ou un jeton sur une cellule vide");
                    tourDeJeu();       
                            }    
            }

            if (choix == 3) {
                
                if(joueurCourant.nombreDesintegrateurs==0){
                    System.out.println("Vous ne possédez pas de désintégrateurs, veuillez effectuer une autre action");
                    tourDeJeu();
                }
                else{
                    System.out.println("Saisissez le numéro de ligne du jeton que vous souhaitez désintégrer");
                    Scanner saisieLigne = new Scanner(System.in);
                    int ligne = saisieLigne.nextInt()-1;

                    while (ligne < 0 || ligne > 5) {
                        System.out.println("Mauvaise  saisie, saisissez un numéro de ligne valide");
                        saisieLigne = new Scanner(System.in);
                        ligne = saisieLigne.nextInt()-1;
                    }
                    System.out.println("Saisissez le numéro de colonne du jeton que vous souhaitez désintégrer");
                    Scanner saisieColonne = new Scanner(System.in);
                    int colonne = saisieColonne.nextInt()-1;

                    while (colonne < 0 || colonne > 6) {
                        System.out.println("Mauvaise  saisie, saisissez un numéro de colonne valide");
                        saisieColonne = new Scanner(System.in);
                        colonne = saisieColonne.nextInt()-1;
                    } 
                    
                    if (grilleJeu.Cellules[ligne][colonne]!=null && !grilleJeu.Cellules[ligne][colonne].lireCouleurDuJeton().equals(joueurCourant.couleur)){
                        grilleJeu.Cellules[ligne][colonne].supprimerJeton();
                        grilleJeu.tasserGrille(ligne, colonne);
                        joueurCourant.utiliserDesintegrateur();
                    }      
                        
                } 
                }

        this.grilleJeu.afficherGrilleSurConsole();
        joueurCourant = prochainJoueur(joueurCourant);
        }
    }

    public void debuterPartie() {
        initialiserPartie();
        this.grilleJeu.afficherGrilleSurConsole();
        System.out.println("Effectuez une action");
        tourDeJeu();

        if (grilleJeu.etreGagnantePourJoueur(joueurCourant) == true) {
            System.out.println("Le joueur " + joueurCourant.nom + " a gagné");
        }

        if (grilleJeu.etreRemplie() == true && grilleJeu.etreGagnantePourJoueur(joueurCourant) != true) {
            System.out.println("La grille est pleine, personne n'a gagné");
        }

    }

}
