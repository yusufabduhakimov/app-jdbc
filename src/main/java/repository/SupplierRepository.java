package repository;

import entity.Currency;
import entity.Response;
import entity.Supplier;
import utils.DbConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupplierRepository {
    public static final Scanner SCANNER_STR=new Scanner(System.in);
    public static final Scanner SCANNER_NUM=new Scanner(System.in);

    public static List<Supplier> getSupplieries() throws SQLException {
        Connection ulanish= DbConfig.ulanish();
        Statement statement = ulanish.createStatement();
        ResultSet resultSet= statement.executeQuery("select *from supplier");
        List<Supplier> supplierList=new ArrayList<>();
        while (resultSet.next()){
            Supplier supplier=new Supplier();
            supplier.setId(resultSet.getInt(1));
            supplier.setName(resultSet.getString(2));
            supplier.setActive(resultSet.getBoolean(3));
            supplierList.add(supplier);
        }
        return supplierList;
    }

    public static void supplierShow() throws SQLException {
        for (Supplier suppliery : getSupplieries()) {
            System.out.println(suppliery);
        }

    }

    public static boolean supplierAdd() throws SQLException {
        System.out.print("Enter the id :");
        int id=SCANNER_NUM.nextInt();
        System.out.print("Enter the name :");
        String name=SCANNER_STR.nextLine();
        //oddiy parametrli querylar uchun
        Connection connection = DbConfig.ulanish();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into supplier values(?,?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setBoolean(3, true);
        boolean execute = preparedStatement.execute();
        System.out.println("Supplier added 😎😎😎😎");

        return execute;
    }

    public static Response supplierUpdate() throws SQLException {
        System.out.print("Enter the old name : ");
        String name=SCANNER_STR.nextLine();
        System.out.print("Enter the new name : ");
        String n_name=SCANNER_STR.nextLine();
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call supplier_update(?,?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setString(2, n_name);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);
        callableStatement.registerOutParameter(4, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(3));
        response.setMessage(callableStatement.getString(4));
        System.out.println("Supplier updated 😉😉😉");

        return response;
    }

    public static Response supplierDelete() throws SQLException {

        System.out.print("Enter the name : ");
        String name=SCANNER_STR.nextLine();
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call supplier_delete(?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.registerOutParameter(2, Types.BOOLEAN);
        callableStatement.registerOutParameter(3, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(2));
        response.setMessage(callableStatement.getString(3));

        System.out.println("Supplier deleted 🙄🙄🙄");

        return response;
    }
}
