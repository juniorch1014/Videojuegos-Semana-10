using System.Collections;
using System.Collections.Generic;
using UnityEngine;

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
        rb.velocity = new Vector2(0,rb.velocity.y);
        ChangeAnimation(0);

       // Debug.Log("x: " + rb.velocity.x);
       // Debug.Log("y: " + rb.velocity.y);
        //RightArrow
        if(Input.GetKey(KeyCode.RightArrow)){
            rb.velocity = new Vector2(vCorrer,rb.velocity.y);
            ChangeAnimation(Anima_Run);
            sr.flipX = false;
            //Attack
          //  if (Input.GetKey(KeyCode.C)){
            //    ChangeAnimation(Anima_RunShoot);
           // }

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
            rb.velocity = new Vector2(-vCorrer,rb.velocity.y);
            ChangeAnimation(Anima_Run);
            sr.flipX = true;
            //Attack
          //  if (Input.GetKey(KeyCode.C)){
          //      ChangeAnimation(Anima_RunShoot);
          //  }
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
     
        }
        if(aux==2){
            ChangeAnimation(Anima_Jump);
            
        } 
        //Slide//////////////////////////////////////////////
        if (Input.GetKey(KeyCode.Q)){
            ChangeAnimation(Anima_Slide);
        }
        /////////////////////////////////////////////////
         //DEAD//////////////////////////////////////////////
        if (Input.GetKey(KeyCode.D)){
           band = true;
        }
        /////////////////////////////////////////////////
        //ATACK//////////////////////////////////////////////
        if (Input.GetKey(KeyCode.W)){
           ChangeAnimation(Anima_Attack);
        }
        /////////////////////////////////////////////////
        //ATACK//////////////////////////////////////////////
        if (Input.GetKey(KeyCode.E)){
           ChangeAnimation(Anima_Trow);
        }
        if (Input.GetKeyDown(KeyCode.A)){
          if(gameManager.Balas() > 0) {
            ChangeAnimation(Anima_Attack);
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
            gameManager.DisminBalas(1);
          }
            
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

    void OnCollisionEnter2D(Collision2D other) {
        aux=0;
    }
    void OnTriggerEnter2D(Collider2D other) {
      if(other.gameObject.tag == "Coin") {
        audioSource.PlayOneShot(coinsClip);
      }
      
    } 
    public void ChangeAnimation(int animation){     
        animator.SetInteger("Estado",animation);
    }
}
