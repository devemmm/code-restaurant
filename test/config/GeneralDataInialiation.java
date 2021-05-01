/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package config;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;

/**
 *
 * @author emmanuel
 */
public class GeneralDataInialiation {
    
    //        INITIALITIONS 
    

    public static Operation PREPARE_RESERVATIONs =
            Operations.insertInto("Reservation")
            .columns("tid", "names", "email", "phone", "amount", "reserved", "dates", "time", "peaple", "message")
            .values(1, null, null, null, 555000.0, false, null, null, 0, null)
            .values(2, null, null, null, 450000.0, false, null, null, 0, null)
            .values(3, null, null, null, 420400.0, false, null, null, 0, null)
            .values(4, null, null, null, 400000.0, false, null, null, 0, null)
            .values(5, null, null, null, 380000.0, false, null, null, 0, null)
            .values(6, null, null, null, 320000.0, false, null, null, 0, null)
            .values(7, null, null, null, 280000.0, false, null, null, 0, null)
            .values(8, null, null, null, 220000.0, false, null, null, 0, null)
            .values(9, null, null, null, 150000.0, false, null, null, 0, null)
            .values(10, null, null, null, 150000.0, true, null, null, 0, null)
            .build();
    
    public static Operation INSERT_ACCOUNTs = 
            Operations.insertInto("Account")
            .columns("email", "fname", "lname", "phone", "password")
            .values("default@gmail.com", "default fname", "default lname", "0788888888", "21B111CBFE6E8FCA2D181C43F53AD548B22E38ACA955B9824706A504B0A07A2D")
            .build();
    
    //       DELETE
    
    public static Operation DELETE_ALL_ACCOUNTs = 
            Operations.deleteAllFrom("Account");
    
    public static Operation DELETE_ALL_RESERVATIONs = 
            Operations.deleteAllFrom("Reservation");
    
    public static Operation DELETE_ALL_ORDERs = 
            Operations.deleteAllFrom("Orders");
    
}
