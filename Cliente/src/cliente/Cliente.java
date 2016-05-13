/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Frances
 */
public class Cliente {
 Socket requestSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    Cliente(){}
    void run()
    {
        try{
            //1. creating a socket to connect to the s)erver
            requestSocket = new Socket("localhost",2004);
                    
            System.out.println("Connected to localhost in port 2004");
            //2. get Input and Output streams
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
            //3: Communicating with the server
            Scanner lea = new Scanner(System.in);
            do{
                try{
                    message = (String)in.readObject();
                    System.out.println("Connected to localhost in port 2004");
                    System.out.println("Ingrese uno de los comandos siguientes");
                    System.out.println("1: Nombre");
                    System.out.println("2: Hobbie");
                    System.out.println("3: Tu fortuna");
                    System.out.println("4: Color");
                    System.out.println("5: Deporte");
                    System.out.println("server>" + message);
                    sendMessage("Hi my server");
                    message=lea.next();
                    sendMessage(message);
                }
                catch(ClassNotFoundException classNot){
                    System.err.println("data received in unknown format");
                }
            }while(!message.equals("bye"));
        }
        catch(UnknownHostException unknownHost){
            System.err.println("You are trying to connect to an unknown host!");
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                requestSocket.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }
    void sendMessage(String msg)
    {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println("client>" + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String args[])
    {
        Cliente client = new Cliente();
        client.run();
    }
    
    
}
