package service;

import entity.Supplier;
import repository.CategoryRepository;
import repository.CurrencyRepository;
import repository.MeasurementRepository;
import repository.SupplierRepository;

import java.sql.SQLException;
import java.util.Currency;
import java.util.Scanner;

public class CRUD {
    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    public static void run() throws SQLException {
        System.out.println("1.Category CRUD");
        System.out.println("2.Client CRUD");
        System.out.println("3.Currency CRUD");
        System.out.println("4.Measurement CRUD");
        System.out.println("5.Supplier CRUD");
        System.out.println("0.Exit");
        System.out.print("Select :");
        int operation = SCANNER_NUM.nextInt();
        switch (operation) {


            case 1:
                categoryCrudMenu();
                break;
            case 2://client_crud
                break;
            case 3:currencyCrudMenu();
                break;
            case 4:measurementCrudMenu();
                break;
            case 5:supplierCrudMenu();
                break;
            case 0:
                System.out.println("Thank you for your attention🤩🤩🤩🤩🤩");
                break;
            default:
                System.out.println("Wrong selection!😣");
                System.out.println("Select again");
                run();
        }
    }

    private static void supplierCrudMenu() throws SQLException {
        System.out.println("1.Supplier Show");
        System.out.println("2.Supplier Add");
        System.out.println("3.Supplier Update");
        System.out.println("4.Supplier Delete");
        System.out.println("0.Back");
        System.out.print("Select :");
        int operation = SCANNER_NUM.nextInt();

        switch (operation) {
            case 1:
                SupplierRepository.supplierShow();
                break;
            case 2:
                SupplierRepository.supplierAdd();
                break;
            case 3:
                SupplierRepository.supplierUpdate();
                break;
            case 4:
                SupplierRepository.supplierDelete();
                break;
            case 0:
                run();
                break;
            default:
                System.out.println("Wrong selection!😣");
                System.out.println("Select again");
                supplierCrudMenu();

        }
        supplierCrudMenu();
    }

    private static void measurementCrudMenu() throws SQLException {
        System.out.println("1.Measurement Show");
        System.out.println("2.Measurement Add");
        System.out.println("3.Measurement Update");
        System.out.println("4.Measurement Delete");
        System.out.println("0.Back");
        System.out.print("Select :");
        int operation = SCANNER_NUM.nextInt();

        switch (operation) {
            case 1:
                MeasurementRepository.measurementShow();
                break;
            case 2:
                MeasurementRepository.measurementAdd();
                break;
            case 3:
                MeasurementRepository.measurementUpdate();
                break;
            case 4:
                MeasurementRepository.measurementDelete();
                break;
            case 0:
                run();
                break;
            default:
                System.out.println("Wrong selection!😣");
                System.out.println("Select again");
                measurementCrudMenu();

        }
        measurementCrudMenu();
    }

    private static void currencyCrudMenu() throws SQLException {
        System.out.println("1.Currency Show");
        System.out.println("2.Currency Add");
        System.out.println("3.Currency Update");
        System.out.println("4.Currency Delete");
        System.out.println("0.Back");
        System.out.print("Select :");
        int operation = SCANNER_NUM.nextInt();

        switch (operation) {
            case 1:
                CurrencyRepository.currencyShow();
                break;
            case 2:
                CurrencyRepository.currencyAdd();
                break;
            case 3:
                CurrencyRepository.currencyUpdate();
                break;
            case 4:
                CurrencyRepository.currencyDelete();
                break;
            case 0:
                run();
                break;
            default:
                System.out.println("Wrong selection!😣");
                System.out.println("Select again");
                currencyCrudMenu();

        }
        currencyCrudMenu();
    }

    private static void categoryCrudMenu() throws SQLException {
        System.out.println("1.Category Show");
        System.out.println("2.Category Add");
        System.out.println("3.Category Update");
        System.out.println("4.Category Delete");
        System.out.println("0.Back");
        System.out.print("Select :");
        int operation = SCANNER_NUM.nextInt();

        switch (operation) {
            case 1:
                CategoryRepository.categoryShow();
                break;
            case 2:
                CategoryRepository.categoryAdd();
                break;
            case 3:
                CategoryRepository.categoryUpdate();
                break;
            case 4:
                CategoryRepository.categoryDelete();
                break;
            case 0:
                run();
                break;
            default:
                System.out.println("Wrong selection!😣");
                System.out.println("Select again");
                categoryCrudMenu();

        }
        categoryCrudMenu();
    }


}
