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
    
    public Jeton recupererJeton(){ //On supprime le jeton courant de la cellule pour pouvoir le récupérer
        Jeton unJeton=jetonCourant;
        jetonCourant=null;
        return unJeton;
    }
    
    public String lireCouleurDuJeton(){ //On lit la couleur du jeton et on renvoie "vide" si il n'y a pas de jeton sur la cellule
        if (jetonCourant==null){
            return "vide";
        }       
        return jetonCourant.couleur;
}
    
    public boolean supprimerJeton(){   //Si il n'y a pas de jeton courant on ne peut pas le supprimer, si il y a un jeton on le supprime
        if(jetonCourant == null){
            return false;
        }
        else{
            jetonCourant = null;
            return true;
        }
    }
    
    public boolean placerDesintegrateur(){  //On place un désintégrateur si la cellule n'en contient pas déjà un
        if (this.desintegrateur==true){
            return false;
        }
        else{
            this.desintegrateur=true;
            return true;
        }
    }
    
    public boolean presenceDesintegrateur(){  //On vérifie si un désintégrateur est déjà présent ou non
        return this.desintegrateur;
}
    
    public boolean recupererDesintegrateur(){  //Si un désintégrateur est présent on peut le récupérer
        if(presenceDesintegrateur()==true){
            this.desintegrateur=false;
            return true;            
        }
        else{
            return false;
        }
    }   
}
