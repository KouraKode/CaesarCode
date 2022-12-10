package TP5;

/**
 * @author kourakodeur
 * 
 */
public class CaesarCode {

    /**
     * verify if a character is a alphabetic
     * @param c
     * @return
     */
    public static boolean isAlphabetic(char c){
        return String.valueOf(c).matches("^[a-zA-Z]$");
    }
    
    /**
     * allows to encrypt a message according to a given key using Caesar's cipher
     * @param message
     * @param key
     * @return encrypted message
     */
    public static String crypt(String message, int key) {
        char[] messageCharacters = message.toCharArray();
        for (int i = 0; i < messageCharacters.length; i++) {
            if(isAlphabetic(messageCharacters[i])){
                int asciiCode = (int)messageCharacters[i];
                int overtaking = 0;
                key = key % 26;
                asciiCode += key;

                if (asciiCode > 'z') {
                    overtaking = asciiCode - 'z';
                    asciiCode = 'a' + overtaking - 1;
                }
                else if(asciiCode > 'Z' && asciiCode <= 'Z' + key){
                    overtaking = asciiCode - 'Z';
                    asciiCode = 'A' + overtaking - 1;
                }
                messageCharacters[i] = (char)asciiCode;
            }
        }
        return String.valueOf(messageCharacters);
    }

    /**
     * allows to decrypt a message according to a given key using Caesar's cipher
     * @param message
     * @param key
     * @return decrypted message
     */
    public static String decrypt(String message, int key) {
        char[] messageCharacters = message.toCharArray();
        for (int i = 0; i < messageCharacters.length; i++) {
            if(isAlphabetic(messageCharacters[i])){
                int asciiCode = (int)messageCharacters[i];
                int overtaking = 0;
                key = key % 26; 

                asciiCode -= key;

                if(asciiCode < 'a' && asciiCode >= 'a' - key){
                    overtaking = 'a' - asciiCode;
                    asciiCode = 'z' - overtaking + 1;
                }
                else if(asciiCode < 'A'){
                    overtaking = 'A' - asciiCode;
                    asciiCode = 'Z' - overtaking + 1;
                }
                messageCharacters[i] = (char)asciiCode;
            }
        }
        return String.valueOf(messageCharacters);
    }

    /**
     * Display the helping page
     */
    public static void help(){
        System.out.println("==============================================================================");
        System.out.println("CaesarCode: Permet de crypte ou decrypte un message selon le 'chiffrement de Cesar'");
        System.out.printf("Syntax:\t\t java CaesarCode -c|-d message [-k key]\n\n");
        System.out.println("--Options");
        System.out.println("-c: Obligatoire, permet de cryptager le message");
        System.out.println("-d: Obligatoire, permet de decryptager le message");
        System.out.println("-k: Optionnel, permet de préciser la clé de cryptage. Par defaut 13");
        System.out.println("==============================================================================");
    }
    


    public static void main(String[] args) {
        int numberArgs = args.length;
        String operation = "";
        String message = "";
        int key = 13;
        if(numberArgs == 4 || numberArgs == 2){
            message = args[1];
            operation = args[0];
            if(!operation.equalsIgnoreCase("-c") && !operation.equalsIgnoreCase("-d")){
                System.out.println("Le premier argument doit etre '-c': Cryptage ou '-d': Decryptage. Tapez 'java CaesarCode' ou 'java CaesarCode -h' ou 'java CaesarCode --help'");
                return;
            }

            if(numberArgs == 4){
                if(!args[2].equalsIgnoreCase("-k")){
                    System.out.println("Le troiseme argument doit être -k. Tapez 'java CaesarCode' ou 'java CaesarCode -h' ou 'java CaesarCode --help'");
                    return;
                }

                if(!args[3].matches("\\d+")){
                    System.out.println("La clé de cryptage/decryptage doit etre un nombre.  Tapez 'java CaesarCode' ou 'java CaesarCode -h' ou 'java CaesarCode --help'");
                    return;
                }
                else
                    key = Integer.parseInt(args[3]);
            }

            if(operation.equalsIgnoreCase("-c")){
                System.out.println("Message Original: "+ message);
                System.out.println("Message crypté: "+ crypt(message, key));
            }
            else{
                System.out.println("Message crypté: "+ message);
                System.out.println("Message decrypté(original): "+ decrypt(message, key));
            }
        }
        else if((
            numberArgs == 1 && 
            (args[0].equalsIgnoreCase("-h") || args[0].equalsIgnoreCase("--help"))
            ) || (numberArgs == 0)){ // Display help
            
            help();
        }
        else {
            System.out.println("Argument(s) incorrect. Tapez 'java CaesarCode' ou 'java CaesarCode -h' ou 'java CaesarCode --help'");
        }
    }
}
