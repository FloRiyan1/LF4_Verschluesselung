package verschlüsselung;

	import java.security.*;
	import javax.crypto.*;
	import javax.crypto.spec.*;
	import java.util.Base64;
	import java.util.Scanner;
	
public class Verschlüsselung{
	    public static void main(String[] args) throws Exception {
	        
	        // Create a key pair using elliptic curve cryptography with a 1024-bit key length
	        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
	        kpg.initialize(571);
	        KeyPair keyPair = kpg.generateKeyPair();
	        PrivateKey privateKey = keyPair.getPrivate();
	        PublicKey publicKey = keyPair.getPublic();

	        // Convert the public key to an encoded format for transport
	        byte[] publicKeyBytes = publicKey.getEncoded();

	        // Print the public key for testing purposes
	        System.out.println("Public key: " + Base64.getEncoder().encodeToString(publicKeyBytes));

	        // Create a Scanner object to read from the console
	        Scanner scanner = new Scanner(System.in);

	        // System info
	        System.out.println("Geben sie den zu verschlüsselnden Satz ein:");
	        
	        // Create a message to encrypt
	        String message = scanner.nextLine();;

	        // Encrypt the message using the public key
	        Cipher cipher = Cipher.getInstance("ECIES");
	        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	        byte[] encryptedMessage = cipher.doFinal(message.getBytes());

	        // Print the encrypted message for testing purposes
	        System.out.println("Encrypted message: " + Base64.getEncoder().encodeToString(encryptedMessage));

	        // Decrypt the message using the private key
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        byte[] decryptedMessage = cipher.doFinal(encryptedMessage);

	        // Print the decrypted message for testing purposes
	        System.out.println("Decrypted message: " + new String(decryptedMessage));
	    }
	}
