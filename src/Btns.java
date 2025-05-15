/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Pololoers
 */
public class Btns{
    Btns(){
        
    }
    static void back(){
        Main.frame.startup();
    }
    static void btnOpenProg1(){
        Main.frame.setPanel(Main.pnlProg1);
    }
    static void btnOpenProg2(){
        Main.frame.setPanel(Main.pnlProg2);
    }
    static void btnOpenProg3(){
        Main.frame.setPanel(Main.pnlProg3);
    }
}
