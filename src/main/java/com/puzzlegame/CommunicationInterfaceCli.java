package com.puzzlegame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommunicationInterfaceCli implements CommunicationInterface {

    @Override
    public Integer getNextNumber() {
        System.out.println();
        System.out.print("Move number: ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String number = reader.readLine();
            return Integer.valueOf(number);
        } catch (IOException e) {
            System.out.println("Unable ro read a number. Please, type again");
        } catch (NumberFormatException ex) {
            System.out.println("Not a number. Type again");
        }

        return null;
    }
}
