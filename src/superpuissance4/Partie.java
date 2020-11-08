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

    public void attribuerCouleursAuxJoueurs() {  //Attribution des couleurs au hasard
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

        Scanner sc = new Scanner(System.in);    // Création des joueurs
        System.out.println("Le pseudo du joueur 1 est :");
        Joueur J1 = new Joueur(sc.nextLine());
        System.out.println("Le pseudo du joueur 2 est :");
        Joueur J2 = new Joueur(sc.nextLine());
        listeJoueurs[0] = J1;
        listeJoueurs[1] = J2;

        attribuerCouleursAuxJoueurs();

        System.out.println(J1.nom + " est de couleur " + J1.couleur);
        System.out.println(J2.nom + " est de couleur " + J2.couleur);

        for (int i = 0; i < 21; i++) {    //Distribution des jetons aux joueurs
            J1.ajouter_jeton(new Jeton(J1.couleur));
            J2.ajouter_jeton(new Jeton(J2.couleur));
        }
        
        J1.nombreDesintegrateurs=0; 
        J2.nombreDesintegrateurs=0;

        Random r = new Random();    //On définit au hasard le premier joueur
        boolean premierJoueur = r.nextBoolean();
        if (premierJoueur) {
            joueurCourant = listeJoueurs[0];
        } else {
            joueurCourant = listeJoueurs[1];
        }

        Random r2 = new Random();   //On place au hasard 5 désintégrateurs
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

    public int choix_joueur() {    //Le joueur choisit l'action qu'il désire effectuer
        System.out.println(joueurCourant.nom+"Choisissez une action à effectuer");
        System.out.println("1) Poser un jeton");
        System.out.println("2) Récupérer un jeton");
        System.out.println("3) Utiliser un désintégrateur");

        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        while (choix != 1 && choix != 2 && choix != 3) {   //On vérifie que le choix de l'utilisateur est valide
            System.out.println("Saisissez un choix valide (inclus entre 1 et 3)");
            choix = sc.nextInt();
        }
        return choix;
    }

    public Joueur prochainJoueur(Joueur un_joueur) {  // Méthode permettant le passage d'un joueur à l'autre
        if (listeJoueurs[0] == joueurCourant) {
            return listeJoueurs[1];
        }
        return listeJoueurs[0];
    }
    

    public void tourDeJeu() {  //Coeur du jeu
        while (grilleJeu.etreGagnantePourJoueur(joueurCourant) != true && grilleJeu.etreRemplie() != true) {  //La partie dure tant que personne n'a gagné et que la grille n'est pas pleine

            int choix = choix_joueur();    

            if (choix == 1) {  

                System.out.println(joueurCourant.nom + " Choisissez dans quelle colonne vous souhaitez placer un jeton"); //On demande une saisie de la colonne ou placer le jeton
                Scanner sc = new Scanner(System.in);
                int saisie = sc.nextInt();

                while (saisie < 0 || saisie > 7) {   // On vérifie que le numéro de colonne saisi est valide
                    System.out.println("Mauvaise  saisie, saisissez un numéro de colonne valide");
                    sc = new Scanner(System.in);
                    saisie = sc.nextInt()-1;
                }

                if (joueurCourant.nombreJetonsRestants > 0) {  //On vérifie que le joueur a encore des jetons à placer
                    boolean jetonAposer;
                    jetonAposer = grilleJeu.ajouterJetonDansColonne(joueurCourant,joueurCourant.listeJetons[joueurCourant.nombreJetonsRestants - 1], saisie - 1);  //On place le jeton dans la colonne

                    if (jetonAposer == true) {  //On vérifie si le placement du jeton s'est bien passé
                        joueurCourant.nombreJetonsRestants--;
                    } else {    //Si il s'est mal passé, la colonne est remplie, le joueur doit effectuer une autre action, on relance donc tourDeJeu()
                        System.out.println("Cette colonne est remplie, veuillez saisir un autre numéro de colonne");
                        tourDeJeu();
                    }
                }
            }

            if (choix == 2) {
                System.out.println("Saisissez le numéro de ligne du jeton que vous souhaitez récupérer");
                Scanner saisieLigne = new Scanner(System.in);
                int ligne = saisieLigne.nextInt()-1;

                while (ligne < 0 || ligne > 5) {   //On vérifie que la saisie de ligne est valide
                    System.out.println("Mauvaise  saisie, saisissez un numéro de ligne valide");
                    saisieLigne = new Scanner(System.in);
                    ligne = saisieLigne.nextInt()-1;
                }

                System.out.println("Saisissez le numéro de colonne du jeton que vous souhaitez récupérer");
                Scanner saisieColonne = new Scanner(System.in);
                int colonne = saisieColonne.nextInt()-1;

                while (colonne < 0 || colonne > 6) {   //On vérifie que la saisie de colonne est valide
                    System.out.println("Mauvaise  saisie, saisissez un numéro de colonne valide");
                    saisieColonne = new Scanner(System.in);
                    colonne = saisieColonne.nextInt()-1;
                }
                //On vérifie que le jeton récupéré appartient bien au joueur, et non à l'adversaire, ou que la cellule ne contienne pas de jeton, si la saisie est correcte on récupère le jeton et on tasse la grille
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
                
                if(joueurCourant.nombreDesintegrateurs==0){   //On vérifie que le joueur possède un désintégrateur
                    System.out.println("Vous ne possédez pas de désintégrateurs, veuillez effectuer une autre action");
                    tourDeJeu();
                }
                else{
                    System.out.println("Saisissez le numéro de ligne du jeton que vous souhaitez désintégrer");
                    Scanner saisieLigne = new Scanner(System.in);
                    int ligne = saisieLigne.nextInt()-1;

                    while (ligne < 0 || ligne > 5) {  //On vérifie si la saisie de ligne est valide
                        System.out.println("Mauvaise  saisie, saisissez un numéro de ligne valide");
                        saisieLigne = new Scanner(System.in);
                        ligne = saisieLigne.nextInt()-1;
                    }
                    System.out.println("Saisissez le numéro de colonne du jeton que vous souhaitez désintégrer");
                    Scanner saisieColonne = new Scanner(System.in);
                    int colonne = saisieColonne.nextInt()-1;

                    while (colonne < 0 || colonne > 6) {  //On vérifie si la saisie de colonne est valide
                        System.out.println("Mauvaise  saisie, saisissez un numéro de colonne valide");
                        saisieColonne = new Scanner(System.in);
                        colonne = saisieColonne.nextInt()-1;
                    } 
                    //On vérifie que la cellule sélectionnée contienne bien un jeton adverse, dans le cas contraire on relance le tour de jeu
                    if (grilleJeu.Cellules[ligne][colonne]!=null && !grilleJeu.Cellules[ligne][colonne].lireCouleurDuJeton().equals(joueurCourant.couleur)){
                        grilleJeu.Cellules[ligne][colonne].supprimerJeton();
                        grilleJeu.tasserGrille(ligne, colonne);
                        joueurCourant.utiliserDesintegrateur();
                    }      
                    else{
                        System.out.println("Vous ne pouvez pas désintégrer un de vos jetons, ou une cellule ne contenant aucun jeton");
                        tourDeJeu();
                    }    
                } 
                }

        this.grilleJeu.afficherGrilleSurConsole();   //On affiche la grille après chaque tour et on passe au joueur suivant
        joueurCourant = prochainJoueur(joueurCourant);
        }
    }

    public void debuterPartie() {
        initialiserPartie();  //On initialise la partie et on affiche la grille de départ
        this.grilleJeu.afficherGrilleSurConsole();
        System.out.println("Effectuez une action");
        tourDeJeu(); //On lance les tours de jeu

        if (grilleJeu.etreGagnantePourJoueur(joueurCourant) == true) {  //Si la grille est gagnante on affiche le nom du gagnant
            System.out.println("Le joueur " + joueurCourant.nom + " a gagné");
        }

        if (grilleJeu.etreRemplie() == true && grilleJeu.etreGagnantePourJoueur(joueurCourant) != true) {  //Si la grille est remplie et qu'il n'y a pas de gagnant, personne ne gagne
            System.out.println("La grille est pleine, personne n'a gagné");
        }

    }

}
