using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GirlGenerarController : MonoBehaviour
{
    GameManagerC gameManager;
    private float velocity = 2;
    float realVelocity;
    Rigidbody2D rb;
    SpriteRenderer sr;
    int aux = 2;
    // Start is called before the first frame update
    void Start()
    {
        gameManager = FindObjectOfType<GameManagerC>();
        rb = GetComponent<Rigidbody2D>();
        sr = GetComponent<SpriteRenderer>();
    }

    // Update is called once per frame
    void Update()
    {   
        sr.flipX = true;
        rb.velocity = new Vector2(realVelocity,0);
        //Destroy(this.gameObject,5);
    }
    public void SetRightDirection(){
        realVelocity = velocity;
        Debug.Log("GNZ");
      //  sr.flipX = false;
        
    }
    public void SetLeftDirection(){
        
        realVelocity = -velocity;
        Debug.Log("GNZ2");
       
        
    }
  
    void OnCollisionEnter2D(Collision2D other) {
        //////////////////////////////////////////////////////////
        if(aux==0){
            if(other.gameObject.tag == "kunai"){
             Destroy(this.gameObject);
             gameManager.GanarZPunt(1);
            }
        }else{
            aux--;
        }
        if(other.gameObject.name == "NinjaGirl"){
                Destroy(this.gameObject);
                if(gameManager.Vida() > 0){
                    gameManager.PerVida(1);
                }else{
                if(Time.timeScale == 1){    //si la velocidad es 1
			    Time.timeScale = 0; 	//que la velocidad del juego sea 0
		        }
            }
        }
        /////////////////////////////////////////////////////////
        
    }

}
