using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NGirlFootController : MonoBehaviour
{   
    public NijnjaGirl_Controller NijnjaGirl_Controller;
    public const int MAX_JUMPS = 1;
    private bool onGround = false;
    public int aux = 0;
   

    public bool CanJump (){
        return onGround || (!onGround && aux < MAX_JUMPS);
    }

    public void Jumps(){
       aux++;
       onGround = false;
    }

    void OnCollisionEnter2D(Collision2D other){
        onGround = true;
        aux = 0;
    }
    void OnTriggerEnter2D(Collider2D other){
        if(other.gameObject.name =="cabeza"){
          NijnjaGirl_Controller.vCorrer = NijnjaGirl_Controller.vCorrer + 5;
            
       }
    }
}
