using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class NGirl_IzqDerController : MonoBehaviour
{
    GameManagerC gameManager;
    public const int MAX_JUMPS = 1;
    private bool onGround = false;
    public int aux = 0;
   

    void Start() {
        gameManager = FindObjectOfType<GameManagerC>();
    }
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
    }
    void OnTriggerEnter2D (Collider2D other){
       
      
    }
}

