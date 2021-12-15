package com.company;

import java.io.*;
import java.awt.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class StartProgramClass {
    static String InStr;
    static String Host;
    static String tcpMassage;

    public static void main(String[] args) throws Exception {
        BufferedReader InReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("   _____  _____ _    _ ");
        System.out.println("  / ____|/ ____| |  | |");
        System.out.println(" | (___ | |  __| |__| |");
        System.out.println("  \\___ \\| | |_ |  __  |");
        System.out.println("  ____) | |__| | |  | |");
        System.out.println(" |_____/ \\_____|_|  |_|");
        System.out.println("                       ");

        System.out.println("Запущен пульт управления комплексом SmartGreenHouse");
        System.out.println("Введите [y/n] для продолжения или отмены");

        boolean r = true;
        while(r ==true) {
            System.out.print(">>> ");
            InStr = InReader.readLine();

            if (InStr.equals("y") || InStr.equals("Y") || InStr.equals("д") || InStr.equals("Д")) {
                while(r == true){
                    System.out.println("Введите ip адрес теплицы");
                    System.out.print(">>> ");
                    String InStr = InReader.readLine();
                    if(InStr.equals("exit")){
                        return;
                    }
                    if(InStr.equals("")){
                        continue;
                    }
                    System.out.println("Подключение...");
                    Host = InStr;
                    try {
                        Socket clientSocket = new Socket (Host,80);
                        InputStream is = clientSocket.getInputStream();
                        BufferedReader TcpIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter TcpOut = new PrintWriter(clientSocket.getOutputStream(), true);
                        TcpOut.println("connect?");
                        tcpMassage = TcpIn.readLine();
                        System.out.println(tcpMassage);
                        if(tcpMassage.equals("connect!")){
                            while (r == true){
                                System.out.println("Введите пароль");
                                System.out.print(">>> ");
                                InStr = InReader.readLine();
                                if(InStr.equals("")){
                                    continue;
                                }
                                TcpOut.println("password:");
                                TcpOut.println(InStr);
                                tcpMassage = TcpIn.readLine();
                                if(InStr.equals("done!")){
                                    System.out.println("Соединение установлено");
                                    System.out.println("Для помощи введите [help]");
                                    while (r==true){
                                        System.out.print(">>> ");
                                        InStr = InReader.readLine();
                                        if(InStr.equals("")){
                                            continue;
                                        }
                                        if(InStr.equals("exit")){
                                            TcpOut.println("stop!");
                                            return;
                                        }
                                        if(InStr.equals("settings")){
                                            System.out.println("[1].Настройка расписания");
                                            System.out.println("[2].Смена пароля");
                                            System.out.print(">>> ");
                                            InStr = InReader.readLine();
                                            if(InStr.equals("1")){
                                                TcpOut.println("editTable:");
                                                TcpOut.println("table?");
                                                for(int i = 1 ; i<=4;i++){
                                                    System.out.println("\t"+"Часы"+"\t"+"Минуты"+"\t"+"Секунды"+"\t"+"Полив"+"\t"+"Статус");
                                                    TcpOut.println("line"+i+"?");
                                                    tcpMassage = TcpIn.readLine();
                                                    System.out.println(tcpMassage);
                                                    if(i == 4){
                                                        TcpOut.println("tableExit!");
                                                    }

                                                }
                                                System.out.println("Введите номер строчки, которую вы хотите изменить");
                                                String line = InReader.readLine();
                                                if((line.equals("1"))||(line.equals("2"))||(line.equals("3"))||(line.equals("4"))){
                                                    TcpOut.println("editLine:");
                                                    TcpOut.println(line);

                                                    while(r == true){
                                                        System.out.println("Вы выбрали "+line+"строчку");
                                                        System.out.println("[1].Часы");
                                                        System.out.println("[2].Минуты");
                                                        System.out.println("[3].Секунды");
                                                        System.out.println("[4].Полив");
                                                        System.out.println("[5].Статус(Вкл/Выкл");

                                                        System.out.print(">>> ");
                                                        InStr = InReader.readLine();

                                                        if(InStr.equals("exit")){
                                                            TcpOut.println("stop!");
                                                            return;
                                                        }
                                                        if(InStr.equals("")){

                                                        }

                                                        if(InStr.equals("1")){
                                                            System.out.println("Введите часы");
                                                            System.out.print(">>> ");
                                                            InStr = InReader.readLine();
                                                            TcpOut.println("editHour:");
                                                            TcpOut.println(InStr);
                                                        }
                                                        if(InStr.equals("2")){
                                                            System.out.println("Введите минуты");
                                                            System.out.print(">>> ");
                                                            InStr = InReader.readLine();
                                                            TcpOut.println("editMinute:");
                                                            TcpOut.println(InStr);
                                                        }
                                                        if(InStr.equals("3")){
                                                            System.out.println("Введите секунды");
                                                            System.out.print(">>> ");
                                                            InStr = InReader.readLine();
                                                            TcpOut.println("editSecond:");
                                                            TcpOut.println(InStr);
                                                        }
                                                        if(InStr.equals("4")){
                                                            System.out.println("Введите [1/0] если хотите включить полив или нет");
                                                            System.out.print(">>> ");
                                                            InStr = InReader.readLine();
                                                            TcpOut.println("editWater:");
                                                            TcpOut.println(InStr);
                                                        }
                                                        if(InStr.equals("5")){
                                                            System.out.println("Введите [1/0] если хотите включить эту строчку или нет");
                                                            System.out.print(">>> ");
                                                            InStr = InReader.readLine();
                                                            TcpOut.println("editAlarms:");
                                                            TcpOut.println(InStr);
                                                        }
                                                    }

                                                }

                                            }
                                            if(InStr.equals("2")){
                                                System.out.println("Введите старый пароль");
                                                System.out.print(">>> ");
                                                InStr = InReader.readLine();
                                                TcpOut.println("editPassword:");
                                                TcpOut.println(InStr);
                                                tcpMassage = TcpIn.readLine();
                                                if(tcpMassage.equals("done!")){
                                                    System.out.println("Введите новый пароль");
                                                    InStr = InReader.readLine();
                                                    TcpOut.println("password:");
                                                    TcpOut.println(InStr);
                                                }
                                                else {
                                                    System.out.println("Пароль неверный");
                                                }

                                            }
                                        }
                                        if(InStr.equals("table")){
                                            TcpOut.println("table?");
                                            System.out.println("\t"+"Часы"+"\t"+"Минуты"+"\t"+"Секунды"+"\t"+"Полив"+"\t"+"Статус");
                                            for(int i = 1 ; i<=4;i++){
                                                TcpOut.println("line"+i+"?");
                                                tcpMassage = TcpIn.readLine();
                                                System.out.println(tcpMassage);
                                                if(i == 4){
                                                    TcpOut.println("tableExit!");
                                                }
                                            }

                                        }
                                        if(InStr.equals("temp")){
                                            TcpOut.println("temp?");
                                            tcpMassage = TcpIn.readLine();
                                            System.out.println(tcpMassage+" °C");

                                        }
                                        if(InStr.equals("damp")){
                                            TcpOut.println("damp?");
                                            tcpMassage = TcpIn.readLine();
                                            System.out.println(tcpMassage+" %");

                                        }
                                        if(InStr.equals("time")){
                                            TcpOut.println("time?");
                                            tcpMassage = TcpIn.readLine();
                                            System.out.println(tcpMassage);
                                        }
                                        if(InStr.equals("offline")){
                                            TcpOut.println("stop!");
                                            is.close();
                                            break;
                                        }
                                    }

                                }else{
                                    System.out.println("Не верный пароль");
                                }
                            }
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Ошибка, устройство не найдено");
                        System.out.println("Проверте правильность ввода и подключение к интернету");
                        System.out.println("");
                    }
                }
            }
            if (InStr.equals("v") || InStr.equals("V") || InStr.equals("version") || InStr.equals("в")) {
                System.out.println("SmartGreenHouse version 0.1 beta by NX-Man");
            } if(InStr.equals("v")){
                continue;
            }
            else {
                return;
            }
        }
    }
}
