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
public class Joueur {
    String nom;
    String couleur;
    Jeton[] listeJetons =new Jeton[21];
    int nombreDesintegrateurs;
    int nombreJetonsRestants=listeJetons.length;
   
    public Joueur(String unJoueur){
        nom=unJoueur;
    }
    public void affecter_couleur(String uneCouleur){
        couleur=uneCouleur;
    }
    
    public void ajouter_jeton(Jeton jeton){
         for (int i=0;i<listeJetons.length;i++)   
            if (listeJetons[i]==null){
                listeJetons[i]=jeton;
                break;
            }
            }    
}

    

                 
    