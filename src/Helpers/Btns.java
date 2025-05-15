package Helpers;

import SourcePackages.Main;

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
        System.out.println("hi");
    }
    public static void back(){
        Main.frame.startup();
    }
    public static void openProg1(){
        Main.frame.setPanel(Main.pnlProg1);
    }
    public static void openProg2(){
        Main.frame.setPanel(Main.pnlProg2);
    }
    public static void openProg3(){
        Main.frame.setPanel(Main.pnlProg3);
    }
}
