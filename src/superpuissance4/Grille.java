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
public class Grille {
    Cellule[][] Cellules=new Cellule[6][7];
    
    public Grille(){
        for (int i=0;i<6;i++){
            for (int j=0;j<7;j++){
                Cellules[i][j]=new Cellule();
            }
        }
    }
    
    public boolean ajouterJetonDansColonne(Joueur joueurCourant,Jeton jeton,int colonne){
        for (int i=1;i<7;i++){   
            if (Cellules[6-i][colonne].jetonCourant==null){
                if (Cellules[6-i][colonne].presenceDesintegrateur()) {
                    Cellules[6-i][colonne].recupererDesintegrateur();
                    joueurCourant.nombreDesintegrateurs++;
                }
                Cellules[6-i][colonne].jetonCourant=jeton;
                return true;
            }   
            if (i==6){
                return false;
            }
        } return false;
        }

    
    public boolean etreRemplie(){
        for (int i=1; i<6;i++){
            for (int j=1;j<7;j++){
                if (Cellules[i][j].jetonCourant==null || Cellules[i][j].desintegrateur==true){
                    return false;
                }
                    
                }
        }        
        return true;            
    }    
    
    public void viderGrille(){
        for (int i=1; i<6;i++){
            for (int j=1;j<7;j++){
                Cellules[i][j].jetonCourant=null;
            }
    }
}
    public void afficherGrilleSurConsole(){
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
    
    public boolean celluleOccupee(int i, int j){
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

    public boolean etreGagnantePourJoueur(Joueur unJoueur){
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
    
    public void tasserGrille(int ligne, int colonne){
        for (int i = ligne; i >=0 ; i--) {
            if (i == 0) {
                Cellules[i][colonne].jetonCourant = null;
            } else {
                Cellules[i][colonne].jetonCourant = Cellules[i - 1][colonne].jetonCourant;
            }

        }
    }
    
    public boolean placerDesintegrateur(int ligne,int colonne){
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
