package pl.solvd.concerthall;

import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Authors;
import pl.solvd.concerthall.services.AuthorsService;
import pl.solvd.concerthall.services.impl.AuthorsServiceImpl;

import java.sql.Connection;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        AuthorsService authorsService = new AuthorsServiceImpl();
        authorsService.saveEntity(new Authors(26L,"Alexandr", "Pushkin"));





    }
}