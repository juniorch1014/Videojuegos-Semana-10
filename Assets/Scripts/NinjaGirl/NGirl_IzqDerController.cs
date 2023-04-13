using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NGirl_IzqDerController : MonoBehaviour
{
    public const int MAX_JUMPS = 1;
    private bool onGround = false;
    public int aux = 0;
   

    public bool CanJumpPared(){
        return onGround || (!onGround && aux < MAX_JUMPS);
    }

    public void JumpsPared(){
       aux++;
       onGround = false;
    }

    void OnCollisionEnter2D(Collision2D other){
        onGround = true;
        aux = 0;
         if(other.gameObject.tag =="Zombie"){
          if(Time.timeScale == 1){    //si la velocidad es 1
			      Time.timeScale = 0; 	//que la velocidad del juego sea 0
		    }
         }
    }
    void OnTriggerEnter2D (Collider2D other){
       
      
    }
}

