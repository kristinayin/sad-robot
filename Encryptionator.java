
/**
 * Encryptionator
 * Kristina Yin
 * Goal: A text translator that transforms normal phrases/text/words/sentences to ciphers (Caesar Cipher, Pig Latin, and Baconian).
 * 
 * 12/19/17
 */

import java.util.*;

public class Encryptionator
{
    // integer that correspond with encryption type 
    static int option = 0;
    
    // string that will be the ciphertext output
    static String ciphertext = " ";
    
    // string that will be the plaintext input
    static String plaintext = " ";
    
    // determines whether or not the do loop will repeat / user can translate another plaintext input (see line 305) 
    static boolean repeat = true;
    
    // method to display menu / list items
    public static void menu()
    {
        // for the rest of this program: 1 = Caesar Cipher, 2 = Pig Latin, 3 = Baconian Cipher
        System.out.println("What encryption type would you like?");
        System.out.println("Please enter the item number of the options below.");
        System.out.println("1. Caesar Cipher");
        System.out.println("2. Pig Latin");
        System.out.println("3. Baceconian Cipher");
    }
    
    // method that gets user input for which encryption type they would want
    public static int getUserChoice() {
        Scanner userinput = new Scanner(System.in);
        int userchoice = 0;
        
        // used keep repeating loop if the input is incorrect
        boolean incorrectInput = true;
        
        do { // if the userinput is not an integer
            if (!userinput.hasNextInt()) {
                System.out.println("Please enter an integer."); 
                userinput.next();
            } else { // if the userinput is an integer
                userchoice = userinput.nextInt();
                if (userchoice < 1 || userchoice > 3) { // if the userinput is not between or equal to 1 and 3
                     System.out.println("Not within bounds. Please select a menu item."); 
                     userinput.nextLine();
                } else { // if the userinput is correct, then the loop is finished
                    incorrectInput = false;
                }
            }
        } while (incorrectInput == true); // repeat loop if the input is still not an int equal to or within 1 to 3
        return userchoice;
    } 
    
    // method for Caesar Cipher
    // BUG REPORT: method does not work if shift > 31 for some reason (still gives an output but includes punction and symbols)
    // Ascii table reference:  https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
    public static String Caesar(String plaintext, int shift) {
        for(int i = 0; i < plaintext.length(); i++){
           // original letter
           char p = (char)(plaintext.charAt(i));
           
           // new letter (shifted to replace p), convenient that the ascii table is built in
           char c = (char)(plaintext.charAt(i) + shift);
           
           // if the char is a space
           if (p == ' ') {
               ciphertext += p;
           }else{
               // lowercase
               if (p < '{' && p > '`') { 
                   if (c > 'z') { // if the shifted character is past z, then count it from a
                       ciphertext += (char)(plaintext.charAt(i) - (26-shift));
                   }else{
                      if (c < 'a') { // if the shifted character is before a, count it (backwards) from z   
                          ciphertext += (char)(plaintext.charAt(i) + (26-shift));
                      } else { // if the character does not go over the letter boundaries
                          ciphertext += c;
                      }
                   } 
               } else { // uppercase
                   if (c > 'Z') {  // if the shifted character is past Z, then count it from A
                       ciphertext += (char)(plaintext.charAt(i) - (26-shift));
                   }else{
                        if (c < 'A') { // if the shifted character is before A, count it (backwards) from Z
                          ciphertext += (char)(plaintext.charAt(i) + (26-shift));
                      } else {
                          ciphertext += c;
                      }
                   } 
               }
           }
        }
        return ciphertext;
    }
    
    // method for Pig Latin
    public static String PigLatin(String plaintext)
    {
        // String of vowels
        String vowels = "aeiouAEIOU";
    
        // turning userinput into String array of words
        String[] words = plaintext.split(" ");
        
        // loop for each word in string array
        for (int i = 0; i < words.length; i++) {
            // string with all the consonants before a vowel
            String beforevowel = "";
            
            // int that represents location in word where the beforevowel ends
            int cut = 0;
            
            // for convenience, save words[i] under a variable
            String currentword = words[i];
            
            // if the letter of the word is a constant, save the letters of the string before a vowel in beforeVowel
            while (cut < currentword.length() && !vowels.contains("" + currentword.charAt(cut))) 
            { 
                beforevowel += currentword.charAt(cut);
                cut++; 
            }    
            
            // if the first letter of the word is a constant, add the leftover word before the cut is made, add -, add the constants that were in the beginning, and an ay
            if (!vowels.contains("" + currentword.charAt(0))) {
                String cipherword = currentword.substring(cut) + "-" + beforevowel + "ay";
                ciphertext += cipherword;
            } else { // if the first letter of the word is not a constant, just add a -way at the end 
                String cipherword = currentword + "-way";
                ciphertext += cipherword;
            }

            ciphertext += " ";
        }
        return ciphertext;
    }
    
    
    // create a hashmap with Baconian code
    static Map <Character, String> code = new HashMap<>();
    
    // getter for the HashMap
    public static String getMap(char c) {
        return code.get(c);
    }
    
    // method that takes the plaintext and changes it to the ciphertext
    public static String Baconian(String plaintext) {
        // make a new Stringbuilder (useful because you can just add stuff to it easily)
        StringBuilder b = new StringBuilder();
        
        // for the length of the string, add the corresponding code (see the legend in main method)
        for (int i=0; i<plaintext.length(); i++) 
        {
            b.append(getMap(plaintext.charAt(i)));
        }
        
        // change b to a String, then declare ciphertext equal to it
        ciphertext = b.toString();
        return ciphertext; 
    }
    
    public static void main(String[] args) {
        // BACONIAN MAP: account for a space
        code.put(' ', " ");
        
        // BACONIAN MAP: uppercase
        code.put('A', "AAAAA");
        code.put('B', "AAAAB");
        code.put('C', "AAABA");
        code.put('D', "AAABB");
        code.put('E', "AABAA");
        code.put('F', "AABAB");
        code.put('G', "AABBA");
        code.put('H', "AABBB");
        code.put('I', "ABAAA");
        code.put('J', "ABAAB");
        code.put('K', "ABABA");
        code.put('L', "ABABB");
        code.put('M', "ABBAA");
        code.put('N', "ABBAB");
        code.put('O', "ABBBA");
        code.put('P', "ABBBB");
        code.put('Q', "BAAAA");
        code.put('R', "BAAAB");
        code.put('S', "BAABA");
        code.put('T', "BAABB");
        code.put('U', "BABAA");
        code.put('V', "BABAB");
        code.put('W', "BABBA");
        code.put('Y', "BBAAA");
        code.put('Z', "BBAAB");
    
        // BACONIAN MAP: lowercase
        code.put('a', "aaaaa");
        code.put('b', "aaaab");
        code.put('c', "aaaba");
        code.put('d', "aaabb");
        code.put('e', "aabaa");
        code.put('f', "aabab");
        code.put('g', "aabba");
        code.put('h', "aabbb");
        code.put('i', "abaaa");
        code.put('j', "abaab");
        code.put('k', "ababa");
        code.put('l', "ababb");
        code.put('m', "abbaa");
        code.put('n', "abbab");
        code.put('o', "abbba");
        code.put('p', "abbbb");
        code.put('q', "baaaa");
        code.put('r', "baaab");
        code.put('s', "baaba");
        code.put('t', "baabb");
        code.put('u', "babaa");
        code.put('v', "babab");
        code.put('w', "babba");
        code.put('y', "bbaaa");
        code.put('z', "bbaab");
        
        // code that starts interaction with user starts here
        do {
            // call menu
            menu();
            
            // call getUserChoice, save it, confirm it with user
            option = getUserChoice();
            if (option == 1) {
                System.out.println("You picked Caesar Cipher.");
            } else {
                if (option == 2) {
                    System.out.println("You picked Pig Latin.");
                } else {
                    System.out.println("You picked Baconian.");
                }
            }
        
            // get user to provide the plaintext
            System.out.println("Now please enter the sentence, phrase, or word you would like translated. Do not include puntuation, numbers, or symbols.");
            Scanner givemestring = new Scanner(System.in);
            
            // string of punctionations and symbols on keyboard
            // BUG REPORT: missing some symbols beacuse of "illegal escape character"
            String punctuation = "`1234567890-=!@#$%^&*())_+[]\'?;";
            
            // declare plaintext as input
            plaintext = givemestring.nextLine();
            
            // if plaintext contains symbols, ask user to provide another input again (repeat until input does not contain symbols)
            boolean containssymbols = true;
            do {
                // BUG REPORT: if the input contains multiple symbols, it will repeat the if statement as many times as the number of symbols
                for (int i =0; i<plaintext.length(); i++) {
                    if (punctuation.contains("" + plaintext.charAt(i))) { 
                        System.out.println("Error! Do not include puntuation, numbers, or symbols. Please try again.");
                        givemestring.next();
                    } else {
                        containssymbols = false;
                    }
                }
            } while (containssymbols == true);
        
            // actually do the encryption and print out result
            if (option == 1) {
                // ask user for shift number
                System.out.println("Please provide a shift number: ");
                Scanner shiftnumber = new Scanner(System.in);
                
                // if the input is not an integer
                while (!shiftnumber.hasNextInt()) {
                    System.out.println("Please provide an integer.");
                    shiftnumber.nextLine();
                }
            
                // declare shift to input
                int shift = shiftnumber.nextInt();
            
                // do the ceasar cipher encryption
                System.out.println(Caesar(plaintext, shift));
            } else {
                if (option == 2) {
                    // do the pig latin encryption
                    System.out.println(PigLatin(plaintext));
                } else {
                    // do the baconian encyption
                    System.out.println(Baconian(plaintext));
                }
            }
            
            // ask if user wants to repeat
            System.out.println("Would you like to translate something else?");
            Scanner repeatorno = new Scanner(System.in);
            String reply = repeatorno.nextLine();
            
            // if input is any variation of no, do not repeat do loop
            if (reply.contains("n") || reply.contains("N")){
                repeat = false;
            }
        } while (repeat == true);
    }
}
