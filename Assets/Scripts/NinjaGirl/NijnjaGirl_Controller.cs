using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NijnjaGirl_Controller : MonoBehaviour
{   
    public NGirlFootController footController;
    public NGirl_IzqDerController IzquController;
    public NGirl_IzqDerController DereController;
    public Coin_Controller coinSaltoController ;

    Animator animator;
    Rigidbody2D rb;
    SpriteRenderer sr;

    public float jumpForce =1000f;
    public int vCorrer = 10;
    const int Anima_Run   =  1;
    const int Anima_Dead  = 2;
    const int Anima_Attack = 3;
    const int Anima_Trow = 4;
    const int Anima_Slide = 5;
    const int Anima_Jump = 6;

 
    bool band = false;
    int i=0;
    // Start is called before the first frame update
    void Start()
    {
        Debug.Log("Ejecutando Start");
        rb = GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        sr = GetComponent<SpriteRenderer>();
        
    
        
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
        if (Input.GetKeyDown(KeyCode.Space)){
           
           //rb.velocity = new Vector2(rb.velocity.x, 5);
            if(footController.CanJump()){
              rb.AddForce(transform.up * 400);
              footController.Jumps();
             
            } 
            if(IzquController.CanJumpPared()){
              rb.AddForce(new Vector2(0,5),ForceMode2D.Impulse);
              IzquController.JumpsPared();
            }
            if(DereController.CanJumpPared()){
              rb.AddForce(new Vector2(0,5),ForceMode2D.Impulse);
              DereController.JumpsPared();
            }
            

        
        }
        if(Input.GetKeyUp(KeyCode.Space) ){

              if(Input.GetKeyDown(KeyCode.M) && coinSaltoController.saltoMoneda==true){
              Debug.Log("AAAAA");
              rb.AddForce(transform.up * 200);
              }
        }
        if(footController.aux == 2){
          ChangeAnimation(Anima_Jump);
        }
        /////////////////////////////////////////////////

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

    private void OnCollisionEnter2D(Collision2D other) {
      
    }
    public void ChangeAnimation(int animation){     
        animator.SetInteger("Estado",animation);
    }
}
