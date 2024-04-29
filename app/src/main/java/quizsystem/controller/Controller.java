package quizsystem.controller;

import java.util.Scanner;

public abstract class Controller {
    protected Scanner scanner;

    public Controller() {
        this.scanner = new Scanner(System.in);
    }

}
