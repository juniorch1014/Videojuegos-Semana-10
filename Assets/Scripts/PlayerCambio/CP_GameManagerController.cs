using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using TMPro;

public class CP_GameManagerController : MonoBehaviour
{
    // Start is called before the first frame update
    // public TMP_Text scorePuntos;
    // public TMP_Text scoreLlave;
    // public TMP_Text scoreVida;

    public GameObject ninjaGirl;
    public GameObject swordMan;

    private int personaje;
    // private int zomb;
    // private int bala;
    // private int vida;

    void Start()
    {
        // zomb = 0;
        // bala = 0;
        // vida = 2;
        // PrintZomb_InScreen();
        // PrintBalas_InScreen1();
        // PrintVida_InScreen2();
        ElegirPersonaje();
    }
///////////////////////////////////////////////////////////
    // public int Zomb(){
    //     return zomb;
    // }
    // public int Balas(){
    //     return bala;
    // }
    // public int Vida(){
    //     return vida;
    // }
    public int Personaje(){
        return personaje;
    }
///////////////////////////////////////////////////////////
    // public void GanarZPunt (int zpuntos) {
    //     zomb += zpuntos;
    //     PrintZomb_InScreen();
    // }
    // public void AumetarBalas(int mone) {
    //     bala += mone;
    //     PrintBalas_InScreen1();
    // }
    // public void DisminBalas(int dismone){
    //     bala -= dismone;
    //     PrintBalas_InScreen1();
    // }
    // public void PerVida (int plife) {
    //     vida -= plife;
    //     PrintVida_InScreen2();
    // }
    public void NroPersonaje (int persona) {
        personaje = persona;
    }
///////////////////////////////////////////////////////////
    // private void PrintZomb_InScreen() {
    //     scorePuntos.text = "Puntos: "+ zomb;
    //     Debug.Log("Gpunto: " + zomb);
    // }
    // private void PrintBalas_InScreen1() {
    //     scoreLlave.text = "Llave: "+ bala;
    //     Debug.Log("Gcoins: " + bala);
    // }
    // private void PrintVida_InScreen2() {
    //     scoreVida.text = "Vidas: "+ vida;
    //     Debug.Log("Gvidas: " + vida);
    // }
    private void ElegirPersonaje() {
        if(Personaje() == 0){
            Debug.Log("Ninja: " + personaje);
            var ninjaPosition = new Vector3(-8.265254f,-0.62f,0);
            var gb = Instantiate(ninjaGirl,
                                 ninjaPosition,
                                 Quaternion.identity) 
                                 as GameObject;
        }
        if(Personaje() == 1){
            Debug.Log("Sword: " + personaje);
            var swordPosition = new Vector3(-8.265254f,-0.62f,0);
            var gb = Instantiate(swordMan,
                                 swordPosition,
                                 Quaternion.identity)
                                 as GameObject;
        }
    }
///////////////////////////////////////////////////////////
    // Update is called once per frame
    // void Update()
    // {
        
    // }
}
