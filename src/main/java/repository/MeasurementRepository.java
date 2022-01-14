package repository;

import entity.Currency;
import entity.Measurement;
import entity.Response;
import utils.DbConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeasurementRepository {
    public static final Scanner SCANNER_STR=new Scanner(System.in);
    public static final Scanner SCANNER_NUM=new Scanner(System.in);

    public static List<Measurement> getMeasurementies() throws SQLException {
        Connection ulanish= DbConfig.ulanish();
        Statement statement = ulanish.createStatement();
        ResultSet resultSet= statement.executeQuery("select *from measurement");
        List<Measurement> measurementList=new ArrayList<>();
        while (resultSet.next()){
            Measurement measurement=new Measurement();
            measurement.setId(resultSet.getInt(1));
            measurement.setName(resultSet.getString(2));
            measurement.setActive(resultSet.getBoolean(3));
            measurementList.add(measurement);
        }
        return measurementList;
    }


    public static void measurementShow() throws SQLException {
        for (Measurement measurementy : getMeasurementies()) {
            System.out.println(measurementy);
        }
    }

    public static boolean measurementAdd() throws SQLException {
        System.out.print("Enter the id :");
        int id=SCANNER_NUM.nextInt();
        System.out.print("Enter the name :");
        String name=SCANNER_STR.nextLine();
        //oddiy parametrli querylar uchun
        Connection connection = DbConfig.ulanish();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into measurement values(?,?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setBoolean(3, true);
        boolean execute = preparedStatement.execute();
        System.out.println("Measurement added 😎😎😎😎");

        return execute;
    }

    public static Response measurementUpdate() throws SQLException {
        System.out.print("Enter the old name : ");
        String name=SCANNER_STR.nextLine();
        System.out.print("Enter the new name : ");
        String n_name=SCANNER_STR.nextLine();
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call measurement_update(?,?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setString(2, n_name);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);
        callableStatement.registerOutParameter(4, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(3));
        response.setMessage(callableStatement.getString(4));
        System.out.println("Measurement updated 😉😉😉");

        return response;
    }

    public static Response measurementDelete() throws SQLException {
        System.out.print("Enter the name : ");
        String name=SCANNER_STR.nextLine();
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call measurement_delete(?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.registerOutParameter(2, Types.BOOLEAN);
        callableStatement.registerOutParameter(3, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(2));
        response.setMessage(callableStatement.getString(3));

        System.out.println("Measurement deleted 🙄🙄🙄");

        return response;

    }
}
