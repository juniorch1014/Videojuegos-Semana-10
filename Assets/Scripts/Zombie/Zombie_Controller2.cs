using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Zombie_Controller2 : MonoBehaviour
{   

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
        rb = GetComponent<Rigidbody2D>();
        sr = GetComponent<SpriteRenderer>();
        rb.velocity = new Vector2(-1,rb.velocity.y);
        sr.flipX = true;
    }
}
