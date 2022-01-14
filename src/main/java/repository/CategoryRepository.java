package repository;

import entity.Category;
import entity.Response;
import utils.DbConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryRepository {
    public static final Scanner SCANNER_STR=new Scanner(System.in);
    public static final Scanner SCANNER_NUM=new Scanner(System.in);

    public static List<Category> getCategories() throws SQLException {
        Connection ulanish = DbConfig.ulanish();
        Statement statement;
        statement = ulanish.createStatement();
        ResultSet resultSet = statement.executeQuery("select *from category");
        List<Category> categoryList = new ArrayList<>();
        while (resultSet.next()) {
            Category category = new Category();
            category.setId(resultSet.getInt(1));
            category.setName(resultSet.getString(2));
            category.setActive(resultSet.getBoolean(3));
            categoryList.add(category);
        }
        return categoryList;
    }

    public static void categoryShow() throws SQLException {
        for (Category category : getCategories()) {
            System.out.println(category);
        }
    }

    public static boolean categoryAdd() throws SQLException {
        System.out.print("Enter the id :");
        int id=SCANNER_NUM.nextInt();
        System.out.print("Enter the name :");
        String name=SCANNER_STR.nextLine();
        //oddiy parametrli querylar uchun
        Connection connection = DbConfig.ulanish();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into category values(?,?,?)");
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setBoolean(3, true);
        boolean execute = preparedStatement.execute();
        System.out.println("Category added 😎😎😎😎");

        return execute;
    }

    public static Response categoryUpdate() throws SQLException {
        System.out.print("Enter the old name : ");
        String name=SCANNER_STR.nextLine();
        System.out.print("Enter the new name : ");
        String n_name=SCANNER_STR.nextLine();
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call category_update(?,?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.setString(2, n_name);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);
        callableStatement.registerOutParameter(4, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(3));
        response.setMessage(callableStatement.getString(4));
        System.out.println("Category updated 😉😉😉");

        return response;

    }


    public static Response categoryDelete() throws SQLException {
        System.out.print("Enter the name : ");
        String name=SCANNER_STR.nextLine();
        Response response = new Response();
        Connection connection = DbConfig.ulanish();
        CallableStatement callableStatement = connection.prepareCall("{call category_delete(?,?,?)}");
        callableStatement.setString(1, name);
        callableStatement.registerOutParameter(2, Types.BOOLEAN);
        callableStatement.registerOutParameter(3, Types.VARCHAR);

        callableStatement.execute();
        response.setSuccess(callableStatement.getBoolean(2));
        response.setMessage(callableStatement.getString(3));

        System.out.println("Category deleted 🙄🙄🙄");

        return response;

    }
}
