/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package config;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 *
 * @author emmanuel
 */
public class AbstractTest {
    
    private String url = "jdbc:mysql://localhost:3306/Quiz";
    private String usr = "root";
    private String passwd= "";
    
    DbSetup dbSetup;
    public void  excute(Operation operation){
        try {
            DbSetup dbSetup = new DbSetup(new DriverManagerDestination(url,usr,passwd), operation);
            dbSetup.launch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
