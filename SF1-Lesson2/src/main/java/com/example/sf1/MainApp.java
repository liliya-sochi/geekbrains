package com.example.sf1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean("productService", ProductService.class);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("ВВЕДИТЕ КОМАНДУ:");
            String command = scanner.nextLine();
            if (command.equals("#exit")) break;
            if (command.equals("#show")) productService.showBox();
            if (command.equals("#average")) System.out.println("Средняя стоимость товаров в коробке = " + productService.calculateAverageCost());
            if (command.equals("#total")) System.out.println("Общее количество товаров в коробке = " + productService.calculateTotalProduct());
            if (command.equals("#add")) {
                System.out.println("Введите название товара для добавления в коробку:");
                productService.addProduct(new Product(scanner.nextLine()));
            }
            if (command.equals("#update")) {
                System.out.println("Введите id товара для изменения цены:");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.println("Введите новую цену для товара:");
                int cost = Integer.parseInt(scanner.nextLine());
                productService.updateCost(id, cost);
            }
            if (command.equals("#find")) {
                System.out.println("Введите id товара для поиска в коробке:");
                productService.findProduct(Integer.parseInt(scanner.nextLine()));
            }
            if (command.equals("#delete")) {
                System.out.println("Введите id товара для удаления из коробки:");
                productService.delProduct(Integer.parseInt(scanner.nextLine()));
            }
        }

        context.close();
    }
}
