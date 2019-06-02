/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iching;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Olivier
 */
public class IChing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random r = new Random();

        String hexa = "";
        Boolean random = false;
        Scanner input = new Scanner(System.in);
        int lines[] = new int[6];

        System.out.println("Enter r for random selection or n for select numbers:");
        String entry = input.next();

        while (!(entry.equals("r") || entry.equals("R") || entry.equals("n") || entry.equals("N"))) {
            System.out.println("Wrong entry. Enter r or n:");
            entry = input.next();
        }

        if (entry.equals("r") || entry.equals("R")) {
            random = true;
        }

        for (int i = 1; i < 7; i++) {

            //Step 1a
            int left = 0;
            while (left < 1 | left >= 49) {
                if (random) {
                    left = r.nextInt(49) + 1;
                } else {
                    System.out.println("Enter a number from 1 to 48");
                    while (!input.hasNextInt()) {
                        System.out.println("Wrong entry. Enter a number from 1 to 48");
                    }
                    left = input.nextInt();
                }

            }
            int right = 49 - left;
            System.out.println("Step 0 : " + left + " and " + right);

            //Step 1b
            int lt = left;
            while (lt > 4) {
                lt = lt - 4;
            }

            //Step 1c
            int rt = right - 1;
            while (rt > 4) {
                rt = rt - 4;
            }

            //Step 1d
            int n1 = lt + rt + 1;

            System.out.println("Step 1 : " + "1 + " + lt + " + " + rt + " = " + n1);

            /**
             * **                             ***
             */
            //Step 2a
            if (random) {
                left = r.nextInt(49 - n1) + 1;
            } else {
                System.out.println("Enter a number from 1 to " + (49 - n1));
                while (!input.hasNextInt()) {
                    System.out.println("Wrong entry. Enter a number from 1 to " + (49 - n1));
                }
                left = input.nextInt();
            }

            right = 49 - n1 - left;

            //Step 2b
            lt = left;
            while (lt > 4) {
                lt = lt - 4;
            }

            //Step 2c
            rt = right - 1;
            while (rt > 4) {
                rt = rt - 4;
            }

            //Step 2d
            int n2 = lt + rt + 1;
            System.out.println("Step 2 : " + "1 + " + lt + " + " + rt + " = " + n2);

            /**
             * **                             ***
             */
            //Step 3a
            if (random) {
                left = r.nextInt(49 - n1) + 1;
            } else {
                System.out.println("Enter a number from 1 to " + (49 - n1));
                while (!input.hasNextInt()) {
                    System.out.println("Wrong entry. Enter a number from 1 to " + (49 - n1));
                }
                left = input.nextInt();
            }
            right = 49 - n1 - left;

            //Step 3b
            lt = left;
            while (lt > 4) {
                lt = lt - 4;
            }

            //Step 3c
            rt = right - 1;
            while (rt > 4) {
                rt = rt - 4;
            }

            //Step 3d
            int n3 = lt + rt + 1;
            System.out.println("Step 3 : " + "1 + " + lt + " + " + rt + " = " + n3);

            /**
             * **                             ***
             */
            //Step 4
            int v1 = 0;
            int v2 = 0;
            int v3 = 0;

            if (n1 == 9) {
                v1 = 2;
            } else if (n1 == 5) {
                v1 = 3;
            } else {
                System.out.println("Error 1 :" + n1);
                System.exit(0);
            }

            if (n2 == 8) {
                v2 = 2;
            } else if (n2 == 4) {
                v2 = 3;
            } else {
                System.out.println("Error 2 :" + n2);
                System.exit(0);
            }

            if (n3 == 8) {
                v3 = 2;
            } else if (n3 == 4) {
                v3 = 3;
            } else {
                System.out.println("Error 3 :" + n3);
                System.exit(0);
            }
            int l = v1 + v2 + v3;
            System.out.println("Line " + i + " : " + v1 + " + " + v2 + " + " + v3 + " = " + l);
            hexa += l + "";
            lines[i - 1] = l;
        }

//                           System.out.println("hexa : " + hexa);
//                           System.out.println("conv : " + convertHexa(hexa));
        for (int i = 0; i < 6; i++) {
            if (lines[5 - i] == 6) {
                System.out.println((6 - i) + " : " + "---  --- o");
            }
            if (lines[5 - i] == 7) {
                System.out.println((6 - i) + " : " + "--------");
            }
            if (lines[5 - i] == 8) {
                System.out.println((6 - i) + " : " + "---  ---");
            }
            if (lines[5 - i] == 9) {
                System.out.println((6 - i) + " : " + "-------- o");
            }

        }

        callWebPage(hexa);

    }

    public static String convertHexa(String number) {
        String hexa = "";
        String strConverted = "";

        for (int i = 0; i < number.length(); i++) {
            strConverted += findType(number.charAt(i));
        }

        hexa = strConverted;

        if (number.length() < 6) {
            System.out.println("Error 1 " + number.length());
        }

        if (hexa.length() < 6) {
            System.out.println("Error 2 " + hexa.length());
        }

        /*                          if (strConverted.equals("000000"))
                                             hexa = 1;*/
        return hexa;
    }

    public static int findHexa(String number) {
        int hexa = 0;

        switch (number) {
            case "111111":
                hexa = 1;
                break;

            case "000000":
                hexa = 2;
                break;

            case "100010":
                hexa = 3;
                break;

            case "010001":
                hexa = 4;
                break;

            case "111010":
                hexa = 5;
                break;

            case "010111":
                hexa = 6;
                break;

            case "010000":
                hexa = 7;
                break;

            case "000010":
                hexa = 8;
                break;

            case "111011":
                hexa = 9;
                break;

            case "110111":
                hexa = 10;
                break;

            case "111000":
                hexa = 11;
                break;

            case "000111":
                hexa = 12;
                break;

            case "101111":
                hexa = 13;
                break;

            case "111101":
                hexa = 14;
                break;

            case "001000":
                hexa = 15;
                break;

            case "000100":
                hexa = 16;
                break;

            case "100110":
                hexa = 17;
                break;

            case "011001":
                hexa = 18;
                break;

            case "110000":
                hexa = 19;
                break;

            case "000011":
                hexa = 20;
                break;

            case "100101":
                hexa = 21;
                break;

            case "101001":
                hexa = 22;
                break;

            case "000001":
                hexa = 23;
                break;

            case "100000":
                hexa = 24;
                break;

            case "100111":
                hexa = 25;
                break;

            case "111001":
                hexa = 26;
                break;

            case "100001":
                hexa = 27;
                break;

            case "011110":
                hexa = 28;
                break;

            case "010010":
                hexa = 29;
                break;

            case "101101":
                hexa = 30;
                break;

            case "001110":
                hexa = 31;
                break;

            case "011100":
                hexa = 32;
                break;

            case "001111":
                hexa = 33;
                break;

            case "111100":
                hexa = 34;
                break;

            case "000101":
                hexa = 35;
                break;

            case "101000":
                hexa = 36;
                break;

            case "101011":
                hexa = 37;
                break;

            case "110101":
                hexa = 38;
                break;

            case "001010":
                hexa = 39;
                break;

            case "010100":
                hexa = 40;
                break;

            case "110001":
                hexa = 41;
                break;

            case "100011":
                hexa = 42;
                break;

            case "111110":
                hexa = 43;
                break;

            case "011111":
                hexa = 44;
                break;

            case "000110":
                hexa = 45;
                break;

            case "011000":
                hexa = 46;
                break;

            case "010110":
                hexa = 47;
                break;

            case "011010":
                hexa = 48;
                break;

            case "101110":
                hexa = 49;
                break;

            case "011101":
                hexa = 50;
                break;

            case "100100":
                hexa = 51;
                break;

            case "001001":
                hexa = 52;
                break;

            case "001011":
                hexa = 53;
                break;

            case "110100":
                hexa = 54;
                break;

            case "101100":
                hexa = 55;
                break;

            case "001101":
                hexa = 56;
                break;

            case "011011":
                hexa = 57;
                break;

            case "110110":
                hexa = 58;
                break;

            case "010011":
                hexa = 59;
                break;

            case "110010":
                hexa = 60;
                break;

            case "110011":
                hexa = 61;
                break;

            case "001100":
                hexa = 62;
                break;

            case "101010":
                hexa = 63;
                break;

            case "010101":
                hexa = 64;
                break;

        }

        return hexa;
    }

    public static String findType(char c) {
        String type = "";
        switch (c) {
            case '6':
                type = "0";
                break;
            case '7':
                type = "1";
                break;
            case '8':
                type = "0";
                break;
            case '9':
                type = "1";
                break;
        }
        return type;
    }

    public static void callWebPage(String number) {
        Desktop d = Desktop.getDesktop();
        try {
            d.browse(new URI("http://wengu.tartarie.com/wg/wengu.php?l=Yijing&tire=" + number + "&no=" + findHexa(convertHexa(number)) + "&lang=fr"));
        } catch (IOException | URISyntaxException e2) {
            e2.printStackTrace();
        }
    }

}
