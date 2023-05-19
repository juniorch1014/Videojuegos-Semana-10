using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Generar_Zombie : MonoBehaviour
{   
    public GameObject zombie;
    int aux;
    bool band = false;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        //Debug.Log("aux: " + aux);
      //  aux++;
      //  if(aux == 750){
       
       // }
        Debug.Log("Time" + Time.time);
        aux = (int)(Time.time);
        if((aux%2)==0){

            if(band==false) {
                Debug.Log("Tiempo de Generacion" + aux);
                var ZombiePosition = transform.position + new Vector3(-1,0,0);
                var gb = Instantiate(zombie,
                             ZombiePosition,
                             Quaternion.identity)
                             as GameObject;
                var controller = gb.GetComponent<GirlGenerarController>();
                controller.SetLeftDirection();
                band = true;
            }
        
        }else
        {
            band = false;
        }


    }
}
