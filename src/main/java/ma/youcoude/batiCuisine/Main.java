package ma.youcoude.batiCuisine;

import ma.youcoude.batiCuisine.database.DbConnection;
import ma.youcoude.batiCuisine.ui.Menu.Menu;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.startMenu();
    }
}
