/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superpuissance4;

/**
 *
 * @author antoi
 */
public class Cellule {
    Jeton jetonCourant;
    boolean trouNoir;
    boolean desintegrateur;
    
    public Cellule(){
        jetonCourant=null;        
    }
    
    public boolean affecterJeton(Jeton unJeton){
       if (jetonCourant!=null){
           System.out.println("Jeton déjà présent");
           return false;
       }
       else{
            jetonCourant=unJeton;
            return true;
            
       }            
}
    
    public Jeton recupererJeton(){
        Jeton unJeton=jetonCourant;
        jetonCourant=null;
        return unJeton;
    }
    
    public String lireCouleurDuJeton(){
        if (jetonCourant==null){
            return "vide";
        }       
        return jetonCourant.couleur;
}
}
