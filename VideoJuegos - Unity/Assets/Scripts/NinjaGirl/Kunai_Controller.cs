using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Kunai_Controller : MonoBehaviour
{
    
    private float velocity = 5;
    float realVelocity;
    public GameObject kunai2;
    Rigidbody2D rb;
    SpriteRenderer sr;
    bool band = false;
    // Start is called before the first frame update

  

    void Start()
    {   
       
        rb = GetComponent<Rigidbody2D>();
        sr = GetComponent<SpriteRenderer>();
    }

    // Update is called once per frame
    void Update()
    {
        
        rb.velocity = new Vector2(realVelocity,0);
        sr.flipX = band;
        Destroy(this.gameObject,5);
        if (Input.GetKeyDown(KeyCode.O)){
            if(sr.flipX == false){
              var Kunai2PositionAr = transform.position + new Vector3(1,1,0);
              var gb = Instantiate(kunai2, 
                                   Kunai2PositionAr,
                                   Quaternion.identity)
                                   as GameObject;
              var controller = gb.GetComponent<Kunai2_Controller>();
              controller.SetRightDirectionArriba();

              var Kunai2PositionAb = transform.position + new Vector3(1,-1,0);
                var gb2 = Instantiate(kunai2, 
                                   Kunai2PositionAb,
                                   Quaternion.identity)
                                   as GameObject;
              var controller2 = gb2.GetComponent<Kunai2_Controller>();
              controller2.SetRightDirectionAbajo();
            }
            if(sr.flipX == true){
                
                var Kunai2PositionAr = transform.position + new Vector3(-1,1,0);
                var gb = Instantiate(kunai2, 
                                   Kunai2PositionAr,
                                   Quaternion.identity)
                                   as GameObject;
                var controller = gb.GetComponent<Kunai2_Controller>();
                controller.SetLeftDirectionArriba();


                var Kunai2PositionAb = transform.position + new Vector3(-1,-1,0);
                var gb2 = Instantiate(kunai2, 
                                   Kunai2PositionAb,
                                   Quaternion.identity)
                                   as GameObject;
                var controller2 = gb2.GetComponent<Kunai2_Controller>();
                controller2.SetLeftDirectionAbajo();
            }
        }
    }

    public void SetRightDirection(){
        realVelocity = velocity;
        band = false;
        
    }
    public void SetLeftDirection(){
        realVelocity = -velocity;
        band = true;
        
    }
    private void OnCollisionEnter2D(Collision2D other) {
        if(other.gameObject.tag == "Zombie"){
            Destroy(this.gameObject);
        }
        if(other.gameObject.tag == "suelo"){
            Destroy(this.gameObject);
        }
       
    }
}
