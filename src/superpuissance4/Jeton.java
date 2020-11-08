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
public class Jeton {
    String couleur;
    
    public Jeton(String uneCouleur){  //On attribue une couleur au jeton
        couleur=uneCouleur;
    }
    
    public String lire_Couleur(String couleur){            
        return couleur;
}
    
    @Override
    public String toString(){  //On attribue un unicode au jeton en fonction de sa couleur pour améliorer l'affichage graphique
        if("Rouge".equals(couleur))
            return "\u001B[31m O ";
        return "\u001B[33m O ";
    }
}