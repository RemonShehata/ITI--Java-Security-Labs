/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author remon
 */
public class Main extends SecurityManager{

    
    public static void main(String[] args) {
        // TODO code application logic here
        String line;
        int count = 0;
        AccessControlContext context = AccessController.getContext();
        File policyFile = new File("./customPolicy.policy");
        File inputFile = new File("Data.txt");
        System.setProperty("java.security.policy", "file:/" + policyFile.getAbsolutePath());
        
        MainApp ma = new MainApp();
        
        System.setSecurityManager(ma);
        
        ma.checkPermission(new FilePermission("Data.txt", "read"), context);
        
        System.out.println("allowed");

        try {
            FileInputStream fileInputStream = new FileInputStream(inputFile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            
            while((line = br.readLine() )!= null) {
                count ++;
            }
            System.out.println(count);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
