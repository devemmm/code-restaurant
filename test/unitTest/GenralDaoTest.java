/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitTest;

import config.AbstractTest;
import config.GeneralDataInialiation;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.src.dao.GeneralDao;
import com.src.db.Connect;
import com.src.domain.Account;
import com.src.domain.Reservation;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author emmanuel
 */
public class GenralDaoTest extends AbstractTest{
    
    private String email = "primaryemmy1@gmail.com";
    
    GeneralDao<Account> acctDao = new GeneralDao<Account>(Account.class);
    GeneralDao<Reservation> reservationDao = new GeneralDao<Reservation>(Reservation.class);
    Account account = new Account(email, "Emmanuel", "NTIVUGURUZWA", "0788596281", "Hello");
    Reservation table = new Reservation();
    
    @Test
    public void Testcreate(){
        Account retAcct = acctDao.create(account);
        Assert.assertEquals(retAcct.getFname(), "Emmanuel");
    }
    
    @Test
    public void TestcreateWithExistingAccount(){
        Account newAccount = new Account(email, "Emmanuel", "NTIVUGURUZWA", "0788596281", "Hello");
        Account retAcct = acctDao.create(newAccount);
        Assert.assertEquals(retAcct.equals(null), false);
    }
    
    @Test
    public void Test_findByINT_PK(){
        table = reservationDao.findByINT_PK(1);
        Assert.assertEquals(table.getTid(), 1);
    }
    
    @Test
    public void findBySTRING_PK(){
        Account retAcct = acctDao.findBySTRING_PK("default@gmail.com");
        Assert.assertEquals(retAcct.getEmail(), "default@gmail.com");
    }
    
    @Test(expectedExceptions = {NullPointerException.class})
    public void findBySTRING_PKWithWrongEmail_PK(){
        Account retAcct = acctDao.findBySTRING_PK("emmanuel@gmail.com");
        Assert.assertEquals(retAcct.getEmail(), "default@gmail.com");
    }
    
    
    @Test 
    public void TestUpdateTable(){
        table = new Reservation(1, "Emmanuel", "default@gmail.com", "0788596281", LocalDate.of(1998, Month.AUGUST, 01), "12h30", 6, "Pizza + Bugger");
        Reservation retTable =reservationDao.update(table);
        Assert.assertEquals(retTable.isReserved(), false);
    }
    
    @Test(expectedExceptions = {NullPointerException.class})
    public void testUpdateWrongTable(){
        table = new Reservation(100, "Emmanuel", "default@gmail.com", "0788596281", LocalDate.of(1998, Month.AUGUST, 01), "12h30", 6, "Pizza + Bugger");
        Reservation retTable =reservationDao.update(table);
        Assert.assertEquals(retTable.isReserved(), false);
    }
    
    @BeforeMethod
    public void initialiseDefaultData() {
        excute(GeneralDataInialiation.INSERT_ACCOUNTs);
        excute(GeneralDataInialiation.PREPARE_RESERVATIONs);
    }
    
    @AfterMethod
    public void resetDefaultData() {
        excute(GeneralDataInialiation.DELETE_ALL_ORDERs);
        excute(GeneralDataInialiation.DELETE_ALL_RESERVATIONs);
        excute(GeneralDataInialiation.DELETE_ALL_ACCOUNTs);
        
    }
    
    @BeforeTest
    public void initTables(){
        Connect.getSessionFactory();
        excute(GeneralDataInialiation.DELETE_ALL_ORDERs);
        excute(GeneralDataInialiation.DELETE_ALL_ACCOUNTs);
        excute(GeneralDataInialiation.DELETE_ALL_RESERVATIONs);
        
    }
}
