/**
 * @author Tarcisio da Rocha (Prof. DCOMP/UFS)
 */
package br.ufs.dcomp.ExemploTcpJava;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPServer{
    public static void main(String[] args){
        
        try {
            System.out.print("[ Iniciando Servidor TCP    .........................  ");
            ServerSocket ss = new ServerSocket(3300, 5, InetAddress.getByName("127.0.0.1"));
            System.out.println("[OK] ]");
            
            System.out.print("[ Aquardando pedidos de conexão    ..................  ");
            Socket sock = ss.accept(); // Operação bloqueante (aguardando pedido de conexão)
            System.out.println("[OK] ]");
            
            InputStream is = sock.getInputStream(); //Canal de entrada de dados
            OutputStream os = sock.getOutputStream(); //Canal de saída de dados
            
            Scanner scan = new Scanner(System.in);
            String msg = null;
            byte[] buf_is = null; // buffer de recepção
            byte[] buf_os = null;
            
            while (true) {
                buf_is = new byte[20];
                is.read(buf_is); // Operação bloqueante (aguardando chegada de dados)
                msg = new String(buf_is); // Mapeando vetor de bytes recebido para String
                System.out.println("  Mensagem recebida: "+ msg);
                
                buf_os = new byte[20];
                msg = scan.nextLine();
                buf_os = msg.getBytes(); // Obtendo a respresntação em bytes da mensagem
                os.write(buf_os);
            }
        }catch(Exception e){System.out.println(e);}    
        System.out.println("[ FIM ]");
    }
}