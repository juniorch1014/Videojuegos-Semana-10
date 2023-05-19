using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using UnityEngine.SceneManagement;

public class CambioPersonaje : MonoBehaviour
{   

    CP_GameManagerController cpgameManager;
    public GameManagerC gm;
    public SpriteRenderer srCambio;
    public Sprite[] sprites;
    public int personajeC = 0;
    
    
    private void Start() {
       Debug.Log("Ejecutando Start");
       cpgameManager = FindObjectOfType<CP_GameManagerController>();
       gm = FindObjectOfType<GameManagerC>();    
    }

    public void CambiarPersonajeAdelante(){
        personajeC = 1;
        srCambio.sprite = sprites[personajeC]; 
        
    }
     public void CambiarPersonajeAtras(){
        personajeC = 0; 
        srCambio.sprite = sprites[personajeC];
    }
    public void Iniciar1 () {
        
        SceneManager.LoadScene(3);
    }
    public void Iniciar2 () {
        
        if(gm.Zomb()>=5) {
            SceneManager.LoadScene(3);
        }
      
    }
    public void Iniciar3 () {
        
        if(gm.Zomb()>=10) {
            SceneManager.LoadScene(3);
        }
    }

}
