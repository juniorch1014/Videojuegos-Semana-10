using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Kunai2_Controller : MonoBehaviour
{
    float realVelocity2= 5;
    int direcion2=0;
    Rigidbody2D rb;
    SpriteRenderer sr;
    bool band = false;
    int i=0;
    // Start is called before the first frame update

  

    void Start()
    {   
       
        rb = GetComponent<Rigidbody2D>();
        sr = GetComponent<SpriteRenderer>();
    }

    // Update is called once per frame
    void Update()
    {

        sr.flipX = band;
        if(i==1) {
            rb.velocity = new Vector2(realVelocity2++,direcion2++);
        }
        if(i==2) {
            rb.velocity = new Vector2(realVelocity2++,direcion2--);
        }
        if(i==3) {
            rb.velocity = new Vector2(-realVelocity2++,direcion2++);
        }
        if(i==4) {
            rb.velocity = new Vector2(-realVelocity2++,direcion2--);
        }
        Destroy(this.gameObject,5);
    }

    public void SetRightDirectionArriba(){

        i=1;
        band = false;
        
    }
     public void SetRightDirectionAbajo(){
        i=2;
        band = false;
        
    }
    public void SetLeftDirectionArriba(){
        i=3;
        band = true;
        
    }
    public void SetLeftDirectionAbajo(){
        i=4;
        band = true;
        
    }
    private void OnCollisionEnter2D(Collision2D other) {
        if(other.gameObject.tag == "Zombie"){
            Destroy(this.gameObject);
        }
    }
}
