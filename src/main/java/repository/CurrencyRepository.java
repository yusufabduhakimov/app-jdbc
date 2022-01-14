package repository;

import entity.Currency;
import entity.Response;

import utils.DbConfig;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyRepository {
    public static final Scanner SCANNER_STR=new Scanner(System.in);
    public static final Scanner SCANNER_NUM=new Scanner(System.in);

    public static List<Currency> getCurrencies() throws SQLException {
        Connection ulanish= DbConfig.ulanish();
        Statement statement = ulanish.createStatement();
        ResultSet resultSet= statement.executeQuery("select *from currency");
        List<Currency> currencyList=new ArrayList<>();
        while (resultSet.next()){
            Currency currency=new Currency();
            currency.setId(resultSet.getInt(1));
            currency.setName(resultSet.getString(2));
            currency.setActive(resultSet.getBoolean(3));
            currencyList.add(currency);
        }
        return currencyList;
    }

    public static void currencyShow() throws SQLException {

        for (Currency currency : getCurrencies()) {
            System.out.println(currency);
        }
    }

    public static boolean currencyAdd() throws SQLException {
        System.out.print("Enter the id :");
        int id=SCANNER_NUM.nextInt();
        System.out.print("Enter the name :");
        String name=SCANNER_STR.nextLine();
        //oddiy parametrli querylar uchun
        Connection connection = DbConfig.ulanish();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into currency values(?,?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setBoolean(3, true);
        boolean execute = preparedStatement.execute();
        System.out.println("Currency added 😎😎😎😎");

        return execute;
    }

    public static Response currencyUpdate() throws SQLException {
        System.out.print("Enter the old name : ");
        String name=SCANNER_STR.nextLine();
        System.out.print("Enter the new name : ");
        String n_name=SCANNER_STR.nextLine();
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call currency_update(?,?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setString(2, n_name);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);
        callableStatement.registerOutParameter(4, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(3));
        response.setMessage(callableStatement.getString(4));
        System.out.println("Currency updated 😉😉😉");

        return response;
    }

    public static Response currencyDelete() throws SQLException {
        System.out.print("Enter the name : ");
        String name=SCANNER_STR.nextLine();
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call currency_delete(?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.registerOutParameter(2, Types.BOOLEAN);
        callableStatement.registerOutParameter(3, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(2));
        response.setMessage(callableStatement.getString(3));

        System.out.println("Currency deleted 🙄🙄🙄");

        return response;
    }
}
