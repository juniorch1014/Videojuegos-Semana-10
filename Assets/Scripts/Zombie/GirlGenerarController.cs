using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class GirlGenerarController : MonoBehaviour
{
    private float velocity = 5;
    float realVelocity;
    Rigidbody2D rb;
    SpriteRenderer sr;
    // Start is called before the first frame update
    void Start()
    {
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
    private void OnCollisionEnter2D(Collision2D other) {
        if(other.gameObject.tag == "kunai"){
            Destroy(this.gameObject);
        }
    }

}
