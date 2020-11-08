/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpuissance4;

/**
 *
 * @author Antoine Jeanjean
 */
public class SuperPuissance4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    //On crée la partie et on la débute, on est sur la version classique, à laquelle on a ajouté les désintégrateurs mais pas les trous noirs
        System.out.println("Bienvenue sur le jeu Puissance 4 !");
        Partie premierePartie=new Partie();
        premierePartie.debuterPartie();
    }
    
}

        