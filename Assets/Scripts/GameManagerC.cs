using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using TMPro;

public class GameManagerC : MonoBehaviour
{
    // Start is called before the first frame update
    public TMP_Text scorePuntos;
    public TMP_Text scoreLlave;
    public TMP_Text scoreVida;

    private int zomb;
    private int bala;
    private int vida;

    void Start()
    {
        zomb = 0;
        bala = 0;
        vida = 2;
        PrintZomb_InScreen();
        PrintBalas_InScreen1();
        PrintVida_InScreen2();
    }
///////////////////////////////////////////////////////////
    public int Zomb(){
        return zomb;
    }
    public int Balas(){
        return bala;
    }
    public int Vida(){
        return vida;
    }
///////////////////////////////////////////////////////////
    public void GanarZPunt (int zpuntos) {
        zomb += zpuntos;
        PrintZomb_InScreen();
    }
    public void AumetarBalas(int mone) {
        bala += mone;
        PrintBalas_InScreen1();
    }
    public void DisminBalas(int dismone){
        bala -= dismone;
        PrintBalas_InScreen1();
    }
    public void PerVida (int plife) {
        vida -= plife;
        PrintVida_InScreen2();
    }
///////////////////////////////////////////////////////////
    private void PrintZomb_InScreen() {
        scorePuntos.text = "Puntos: "+ zomb;
        Debug.Log("Gpunto: " + zomb);
    }
    private void PrintBalas_InScreen1() {
        scoreLlave.text = "Llave: "+ bala;
        Debug.Log("Gcoins: " + bala);
    }
    private void PrintVida_InScreen2() {
        scoreVida.text = "Vidas: "+ vida;
        Debug.Log("Gvidas: " + vida);
    }
///////////////////////////////////////////////////////////
    // Update is called once per frame
    // void Update()
    // {
        
    // }
}
