package com.funnydvd.dvdrental.cli.main;

import static com.funnydvd.dvdrental.cli.ui.AppUI.*;

public class AppMain {

    public static void main(String[] args) {

        while (true){

            startScreen();
            int selection = inputInteger(">>> ");
            FrontController.chooseSystem(selection);

        }

    }
}
