using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Zombie_Controller2 : MonoBehaviour
{   
    GameManagerC gameManager;
    Rigidbody2D rb;
    SpriteRenderer sr;
    NijnjaGirl_Controller ninja;
    public GameObject paredZ;
    public GameObject paredZ2;

    private int run_velZ = 2;

    public float Pos_Z;
    public float Pos_Z2;

    int aux = 2;

    // Start is called before the first frame update
    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        sr = GetComponent<SpriteRenderer>();
        gameManager = FindObjectOfType<GameManagerC>();
        ninja = FindObjectOfType<NijnjaGirl_Controller>();
        GenerarParedes();
    }

    // Update is called once per frame
    void Update()
    {
        rb.velocity = new Vector2(run_velZ,rb.velocity.y);
        
        
    }

    private void GenerarParedes(){
        
            var paredPosition = transform.position + new Vector3(Pos_Z,0,0);
            var gb = Instantiate(paredZ,
                    paredPosition,
                    Quaternion.identity) as GameObject;
            var paredPosition2 = transform.position + new Vector3(Pos_Z2,0,0);
            var gb2 = Instantiate(paredZ2,
                    paredPosition2,
                    Quaternion.identity) as GameObject;

    }
    void OnCollisionEnter2D(Collision2D other) {
        //////////////////////////////////////////////////////////
        if(aux==0){
            if(other.gameObject.tag == "kunai"){
             Destroy(this.gameObject);
             gameManager.GanarZPunt(1);
             if(gameManager.Zomb()==3) {
                ninja.puntos = true;
                Debug.Log("Llave: " + ninja.puntos);
             }
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
    void OnTriggerEnter2D(Collider2D other){
        Debug.Log("Trigger");
        if(other.gameObject.tag == "ParedZ"){
        sr.flipX = false;
        run_velZ = 2;
        }
        if(other.gameObject.tag == "ParedZ2"){
         sr.flipX = true;
         run_velZ = -2;
        }
    }
   
}
