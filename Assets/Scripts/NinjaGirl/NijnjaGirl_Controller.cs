using System.Collections;
using System.Collections.Generic;
using UnityEngine;

using UnityEngine.SceneManagement;

public class NijnjaGirl_Controller : MonoBehaviour
{   
    GameManagerC gameManager;
    public NGirlFootController footController;
    public NGirl_IzqDerController IzquController;
    public NGirl_IzqDerController DereController;
    public Coin_Controller coinSaltoController ;

    Animator animator;
    Rigidbody2D rb;
    SpriteRenderer sr;

    public GameObject kunai;
    public float jumpForce;
    public int vCorrer;
    const int Anima_Run   =  1;
    const int Anima_Dead  = 2;
    const int Anima_Attack = 3;
    const int Anima_Trow = 4;
    const int Anima_Slide = 5;
    const int Anima_Jump = 6;

    bool llave = false;
    public bool puntos = false;
    bool puerta = false;

    public AudioClip SaltarClip;
    public AudioClip deadClip;
    public AudioClip coinsClip;
    AudioSource audioSource;

    private int jump_Force = 200;

    bool band = false;
    int i=0;
    int aux = 0;
    // Start is called before the first frame update
    void Start()
    {
        Debug.Log("Ejecutando Start");
        gameManager = FindObjectOfType<GameManagerC>();
        rb = GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        sr = GetComponent<SpriteRenderer>();
        audioSource =GetComponent<AudioSource>();
    
        
    }

    // Update is called once per frame
    void Update()
    {   
        if(band == false){
        Debug.Log(rb);
     

       // Debug.Log("x: " + rb.velocity.x);
       // Debug.Log("y: " + rb.velocity.y);
        //RightArrow
        if(Input.GetKey(KeyCode.RightArrow)){
            derecha();
            //Attack
            //if (Input.GetKey(KeyCode.C)){
            //    ChangeAnimation(Anima_RunShoot);
            //}
           

        }
        if(Input.GetKeyUp(KeyCode.RightArrow)){
             detener();
        }
         //RightArrow + X
        if(Input.GetKey(KeyCode.RightArrow) && Input.GetKey(KeyCode.X)){
            rb.velocity = new Vector2(10,rb.velocity.y);
            ChangeAnimation(Anima_Run);
            sr.flipX = false;
            //Attack
            //  if (Input.GetKey(KeyCode.C)){
            //      ChangeAnimation(Anima_RunShoot);
            //  }
        }
        //LeftArrow
        if (Input.GetKey(KeyCode.LeftArrow)){

          izquierda();
            //Attack
            //  if (Input.GetKey(KeyCode.C)){
            //      ChangeAnimation(Anima_RunShoot);
            //  }
        }
        if(Input.GetKeyUp(KeyCode.LeftArrow)){
             detener(); 
        }

        //LeftArrow + X//////////////////////////////////
        if(Input.GetKey(KeyCode.LeftArrow) && Input.GetKey(KeyCode.X)){
            rb.velocity = new Vector2(-10,rb.velocity.y);
            ChangeAnimation(Anima_Run);
            sr.flipX = true;
            //Attack
          //  if (Input.GetKey(KeyCode.C)){
          //      ChangeAnimation(Anima_RunShoot);
          //  }
        }
        /////////////////////////////////////////////////
        
        //Space//////////////////////////////////////////
        // if (Input.GetKeyDown(KeyCode.Space)){
           
        //     audioSource.PlayOneShot(SaltarClip);
        //     if(footController.CanJump()){
        //       rb.AddForce(transform.up * jumpForce);
        //       //rb.velocity = new Vector2(rb.velocity.x, 5);
        //       //rb.AddForce(new Vector2(0,1),ForceMode2D.Impulse);
        //       footController.Jumps();
             
        //     } 
        //     if(IzquController.CanJumpPared()){
        //       rb.AddForce(new Vector2(0,5),ForceMode2D.Impulse);
        //       IzquController.JumpsPared();
        //     }
        //     if(DereController.CanJumpPared()){
        //       rb.AddForce(new Vector2(0,5),ForceMode2D.Impulse);
        //       DereController.JumpsPared();
        //     }
            

        
        // }
        // /////////////////////////////////////////////////////////////////
        // if(Input.GetKeyUp(KeyCode.Space) ){
              
        //       if(Input.GetKeyDown(KeyCode.M) && coinSaltoController.saltoMoneda==true){
        //       Debug.Log("AAAAA");
        //       audioSource.PlayOneShot(SaltarClip);
        //       rb.AddForce(transform.up * 200);
        //       }
        // }
        // if(footController.aux == 2){
        //   ChangeAnimation(Anima_Jump);
        // }
        /////////////////////////////////////////////////
        /////////////////SPACIOOOOOOOO 22222////////////////
        //Space
        if (Input.GetKeyDown(KeyCode.Space) && aux<2){
           // rb.AddForce(new Vector2(0,jump_Force),ForceMode2D.Impulse);
            saltar();


            // if(IzquController.CanJumpPared()){
            //  // rb.AddForce(new Vector2(0,jump_Force),ForceMode2D.Impulse);
            //   rb.AddForce(transform.up * jump_Force);
            //   IzquController.JumpsPared();
            // }
            // if(DereController.CanJumpPared()){
            // //  rb.AddForce(new Vector2(0,jump_Force),ForceMode2D.Impulse);
            //   rb.AddForce(transform.up * jump_Force);
            //   DereController.JumpsPared();
            // }
     
        }
        if(Input.GetKeyUp(KeyCode.Space)){
             detener(); 
        } 
        //Slide//////////////////////////////////////////////
        if (Input.GetKey(KeyCode.Q)){
            ChangeAnimation(Anima_Slide);
        }
        if(Input.GetKeyUp(KeyCode.Q)){
             detener(); 
        }
        /////////////////////////////////////////////////
         //DEAD//////////////////////////////////////////////
        if (Input.GetKey(KeyCode.D)){
           band = true;
        }
        if(Input.GetKeyUp(KeyCode.D)){
             detener(); 
        }
        /////////////////////////////////////////////////
        //ATACK//////////////////////////////////////////////
        if (Input.GetKey(KeyCode.W)){
           ChangeAnimation(Anima_Attack);
        }
        if(Input.GetKeyUp(KeyCode.W)){
             detener(); 
        }
        /////////////////////////////////////////////////
        if (Input.GetKeyDown(KeyCode.A)){
            atacar();
            
        }
        if(Input.GetKeyUp(KeyCode.A)){
             detener(); 
        }
        /////////////////////////////////////////////////
        }else{
            if(i!=1){
            rb.velocity = new Vector2(0,rb.velocity.y);
            ChangeAnimation(Anima_Dead);
            //Destroy(gameObject,1);
            i++;
            }
        }
            
        
        
    }   

    //botones
    public void derecha(){
            rb.velocity = new Vector2(vCorrer, rb.velocity.y);
            sr.flipX = false;
            ChangeAnimation(Anima_Run);
    }
    public void izquierda(){
            rb.velocity = new Vector2(-vCorrer, rb.velocity.y);
            sr.flipX = true;
            ChangeAnimation(Anima_Run);
    }
    public void detener(){
        rb.velocity = new Vector2(0, rb.velocity.y);
        ChangeAnimation(0);
    }
    public void saltar () {
      //Space
           // rb.AddForce(new Vector2(0,jump_Force),ForceMode2D.Impulse);
            rb.AddForce(transform.up * jump_Force);
            audioSource.PlayOneShot(SaltarClip);
            aux++;


            // if(IzquController.CanJumpPared()){
            //  // rb.AddForce(new Vector2(0,jump_Force),ForceMode2D.Impulse);
            //   rb.AddForce(transform.up * jump_Force);
            //   IzquController.JumpsPared();
            // }
            // if(DereController.CanJumpPared()){
            // //  rb.AddForce(new Vector2(0,jump_Force),ForceMode2D.Impulse);
            //   rb.AddForce(transform.up * jump_Force);
            //   DereController.JumpsPared();
            // }
        if(aux==2){
          ChangeAnimation(Anima_Jump);
            
        }
    }
    public void atacar () {
     // if(gameManager.Balas() > 0) {
            ChangeAnimation(Anima_Trow);
            if(sr.flipX == false){
              var KunaiPosition = transform.position + new Vector3(1,0,0);
              var gb = Instantiate(kunai, 
                                   KunaiPosition,
                                   Quaternion.identity)
                                   as GameObject;
              var controller = gb.GetComponent<Kunai_Controller>();
              controller.SetRightDirection();
            }
            if(sr.flipX == true){
              var KunaiPosition = transform.position + new Vector3(-1,0,0);
              var gb = Instantiate(kunai, 
                                   KunaiPosition,
                                   Quaternion.identity)
                                   as GameObject;
              var controller = gb.GetComponent<Kunai_Controller>();
              controller.SetLeftDirection();
            }
          ///  gameManager.DisminBalas(1);
       //   }
          
    }


    void OnCollisionEnter2D(Collision2D other) {
        aux=0;
    }
    void OnTriggerEnter2D(Collider2D other) {
      if(other.gameObject.tag == "Coin") {
        audioSource.PlayOneShot(coinsClip);
      }
      if(other.gameObject.name == "Casa") {
        SceneManager.LoadScene(2);
      }
      if(other.gameObject.name == "Regresar") {
        SceneManager.LoadScene(1);
      }
      if(other.gameObject.tag == "llave") {
       
        llave = true;
        gameManager.AumetarBalas (1);
        Debug.Log("Llave: " + llave);

      }
    } 
    private void OnTriggerStay2D(Collider2D other) {
      if(other.gameObject.tag == "puerta") {
        puerta=true;
        Debug.Log("puerta: " + puerta);

      }
    }
      
    
    public void ChangeAnimation(int animation){     
        animator.SetInteger("Estado",animation);
    }

    public void ingresar () {
        if(puntos == true && llave==true && puerta==true) {
          SceneManager.LoadScene(2);
        }
    }
}
