/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpuissance4;

import java.util.Arrays;

/**
 *
 * @author Antoine Jeanjean
 */
public class Grille { //On crée une grille de 6 x 7 cellules
    Cellule[][] Cellules=new Cellule[6][7];
    
    public Grille(){ //On crée les cellules dans la grille
        for (int i=0;i<6;i++){
            for (int j=0;j<7;j++){
                Cellules[i][j]=new Cellule();
            }
        }
    }
    
    public boolean ajouterJetonDansColonne(Joueur joueurCourant,Jeton jeton,int colonne){ //On part de la ligne du bas et on vérifie qu'elle n'est occupée par aucun jeton, si ce n'est pas le cas on remonte à la ligne au-dessus
        for (int i=1;i<7;i++){   
            if (Cellules[6-i][colonne].jetonCourant==null){
                if (Cellules[6-i][colonne].presenceDesintegrateur()) { //Si il y a un désintégrateur sur cette cellule, le joueur le récupère
                    Cellules[6-i][colonne].recupererDesintegrateur();
                    joueurCourant.obtenirDesintegrateur();
                }
                Cellules[6-i][colonne].jetonCourant=jeton;  //On place le jeton sur la cellule
                return true;
            }   
            if (i==6){  //Si on est arrivée à la première ligne et qu'on a pas pu placer de jeton, c'est que la colonne est pleine, on n'ajoute donc pas de jeton
                return false;
            }
        } return false;
        }

    
    public boolean etreRemplie(){ //Si au moins une cellule de la grille est vide ou contient un désintégrateur, la grille n'est pas pleine
        for (int i=1; i<6;i++){
            for (int j=1;j<7;j++){
                if (Cellules[i][j].jetonCourant==null || Cellules[i][j].desintegrateur==true){
                    return false;
                }
                    
                }
        }        
        return true;            
    }    
    
    public void viderGrille(){  //On vide la grille de ses jetons
        for (int i=1; i<6;i++){
            for (int j=1;j<7;j++){
                Cellules[i][j].jetonCourant=null;
            }
    }
}
    public void afficherGrilleSurConsole(){   //On affiche cellule par cellule la grille, en allant à la ligne à chaque fin de ligne grâce à System.out.println()
        for (int i=0; i <6; i++) { 
            for (int j =0;j < 7; j++){
                if (Cellules[i][j].jetonCourant != null) {                   
                    System.out.print(Cellules[i][j].jetonCourant);
                }
                else if(Cellules[i][j].desintegrateur==true){
                    System.out.print("\u001B[0m D ");
                }
                else{
                    System.out.print("\u001B[0m N ");
            }  
        }System.out.println();
    }
    }
    
    public boolean celluleOccupee(int i, int j){  //On vérifie que la cellule n'a pas de jeton courant
        if (Cellules[i][j].jetonCourant!=null){
            return true;          
    }                        
        else {
            return false;
    }
              
    }
    
    public String lireCouleurDuJeton(int i, int j){
        return Cellules[i][j].lireCouleurDuJeton();
    }

    public boolean etreGagnantePourJoueur(Joueur unJoueur){  //On parcourt la grille 4 fois, pour chercher si la grille est gagnante sur les lignes, sur les colonnes, sur les diagonales montantes, et sur les diagonales descendantes  
    //On vérifie que la cellule n'est pas vide puis on lit sa couleur et celle des 3 jetons suivants pour voir si elle est la même, si c'est le cas on retourne que la grille est gagnante   
    //Si après les 4 boucles on a pas retourné true, la grille n'est pas gagnante pour le joueur, on retourne donc false    
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (Cellules[i][j] != null && Cellules[i][j].lireCouleurDuJeton().equals(unJoueur.couleur)  
                       && Cellules[i][j + 1] != null && Cellules[i][j + 1].lireCouleurDuJeton().equals(unJoueur.couleur)
                        && Cellules[i][j + 2] != null && Cellules[i][j + 2].lireCouleurDuJeton().equals(unJoueur.couleur)
                        && Cellules[i][j + 3] != null && Cellules[i][j + 3].lireCouleurDuJeton().equals(unJoueur.couleur)) {
                    return true;
                }
            }
        }

        for (int i=0; i<3;i++){
            for (int j=0;j<7;j++){
                if (Cellules[i][j] != null && Cellules[i][j].lireCouleurDuJeton().equals(unJoueur.couleur)
                       && Cellules[i + 1][j]!= null && Cellules[i + 1][j].lireCouleurDuJeton().equals(unJoueur.couleur)
                        && Cellules[i + 2][j]!= null && Cellules[i + 2][j].lireCouleurDuJeton().equals(unJoueur.couleur)
                        && Cellules[i + 3][j]!= null && Cellules[i + 3][j].lireCouleurDuJeton().equals(unJoueur.couleur)) {
                    return true;
                }
            }
        }

        for (int i=0;i<3;i++) {
            for (int j=0;j<4;j++){
                if (Cellules[i][j]!= null && Cellules[i][j].lireCouleurDuJeton().equals(unJoueur.couleur)
                       && Cellules[i + 1][j + 1]!= null && Cellules[i + 1][j + 1].lireCouleurDuJeton().equals(unJoueur.couleur)
                        && Cellules[i + 2][j + 2]!= null && Cellules[i + 2][j + 2].lireCouleurDuJeton().equals(unJoueur.couleur)
                        && Cellules[i + 3][j + 3]!= null && Cellules[i + 3][j + 3].lireCouleurDuJeton().equals(unJoueur.couleur)) {
                    return true;
                }
            }
        }

        for (int i=3;i<6;i++){
            for (int j=0;j<4;j++){
                if (Cellules[i][j] != null && Cellules[i][j].lireCouleurDuJeton().equals(unJoueur.couleur)
                      && Cellules[i - 1][j + 1] != null && Cellules[i - 1][j + 1].lireCouleurDuJeton().equals(unJoueur.couleur)
                        && Cellules[i - 2][j + 2] != null && Cellules[i - 2][j + 2].lireCouleurDuJeton().equals(unJoueur.couleur)
                        && Cellules[i - 3][j + 3] != null && Cellules[i - 3][j + 3].lireCouleurDuJeton().equals(unJoueur.couleur)) {
                    return true;
                }
            }
        }return false;

    }
    


    public boolean colonneRemplie(int colonne){
        return Cellules[5][colonne].recupererJeton()!=null;
    }  
    
    public void tasserGrille(int ligne, int colonne){ //On part de la ligne concernée par le tassage, et on remonte jusqu'à la première ligne
        for (int i = ligne; i >=0 ; i--) {
            if (i == 0) {  //Si on est à la première ligne, alors la cellule devient juste vide
                Cellules[i][colonne].jetonCourant = null;
            } else { //Si on est à une autre ligne, de la ligne tassée jusqu'à la première ligne, la cellule d'une colonne donnée prend le contenu de la cellule au-dessus d'elle, donc de la i-1 ieme ligne 
                Cellules[i][colonne].jetonCourant = Cellules[i - 1][colonne].jetonCourant;
            }

        }
    }
    
    public boolean placerDesintegrateur(int ligne,int colonne){ //On place les désintégrateurs en vérifiant qu'il n'y a pas déjà un désintégrateur sur la cellule où l'on souhaite le placer
        if (Cellules[ligne][colonne].desintegrateur==true){
            return false;            
        }
        else{
            Cellules[ligne][colonne].desintegrateur=true;
            return true;
        }
}
    
    public Jeton recupererJeton(int ligne, int colonne){
        Jeton unJeton=Cellules[ligne][colonne].recupererJeton();
        return unJeton;
    }
}
