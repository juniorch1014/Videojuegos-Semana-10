using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Coin_Controller : MonoBehaviour
{
    GameManagerC gameManager;
    public bool saltoMoneda = false;
    // Start is called before the first frame update
    void Start()
    {
        gameManager = FindObjectOfType<GameManagerC>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
    private void OnTriggerEnter2D(Collider2D other) {
       if(other.gameObject.name == "NinjaGirl"){
            Destroy(this.gameObject);
            saltoMoneda =true;
            Debug.Log("Pipipiiiiiiiiii");
            gameManager.AumetarBalas(5);
        }
    
    }
    private void OnCollisionEnter2D(Collision2D other) {
     
       
    }
}
