using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class SwordManController : MonoBehaviour
{
    Animator animator;
    Rigidbody2D rb;
    SpriteRenderer sr;
    
    const int Anima_Run   = 1   ;
    const int Anima_Jump  = 2;
    const int Anima_jumpAttacK = 3;

    int aux = 0;
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
        Debug.Log(rb);
        rb.velocity = new Vector2(0,rb.velocity.y);
        ChangeAnimation(0);

        Debug.Log("x: " + rb.velocity.x);
        Debug.Log("y: " + rb.velocity.y);
        //RightArrow
        if(Input.GetKey(KeyCode.RightArrow)){
            rb.velocity = new Vector2(5,rb.velocity.y);
            ChangeAnimation(Anima_Run);
            sr.flipX = false;
        }
         //RightArrow + X
        if(Input.GetKey(KeyCode.RightArrow) && Input.GetKey(KeyCode.X)){
            rb.velocity = new Vector2(10,rb.velocity.y);
            ChangeAnimation(Anima_Run);
            sr.flipX = false;
        }
        //LeftArrow
        if (Input.GetKey(KeyCode.LeftArrow)){
            rb.velocity = new Vector2(-5,rb.velocity.y);
            ChangeAnimation(Anima_Run);
            sr.flipX = true;
        }
        //LeftArrow + X//////////////////////////////////
        if(Input.GetKey(KeyCode.LeftArrow) && Input.GetKey(KeyCode.X)){
            rb.velocity = new Vector2(-20,rb.velocity.y);
            ChangeAnimation(Anima_Run);
            sr.flipX = true;
        }
        /////////////////////////////////////////////////

        //Space//////////////////////////////////////////
        if ( Input.GetKeyDown(KeyCode.Space) && aux<2 ){
           
           rb.velocity = new Vector2(rb.velocity.x, 5);
            //rb.AddForce(new Vector2(0,5),ForceMode2D.Impulse);
            aux++;
           
                 
        }
        if(aux==2){
            ChangeAnimation(Anima_Jump);
         
        }
        /////////////////////////////////////////////////

        //Q//////////////////////////////////////////////
        if (Input.GetKey(KeyCode.Q)){
            ChangeAnimation(Anima_jumpAttacK);
        }
        /////////////////////////////////////////////////
            
        
        
        
        
    }   
    void OnCollisionEnter2D(Collision2D other){
        ChangeAnimation(Anima_Jump);
        aux=0;
    }
    private void ChangeAnimation(int animation){     
        animator.SetInteger("a",animation);
    }

}
