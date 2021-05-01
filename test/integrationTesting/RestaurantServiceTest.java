/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package integrationTesting;

import config.AbstractTest;
import config.GeneralDataInialiation;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.src.db.Connect;
import com.src.domain.Account;
import com.src.domain.Reservation;
import com.src.service.RestaurantService;
import com.src.service.RestaurantServiceImpl;
import java.time.LocalDate;

/**
 *
 * @author emmanuel
 */
public class RestaurantServiceTest extends AbstractTest{
    
    RestaurantService restaurantService = new RestaurantServiceImpl();
    Account account = new Account();
    Reservation reservation = new Reservation();
    
    public RestaurantServiceTest() {
    }

    @Test
    public void testIsValidAccount() {
        boolean result = restaurantService.isValidAccount("default@gmail.com");
        Assert.assertEquals(result, true);
    }
    
    
    // check for wrong account
    @Test
    public void testForWrongAccount(){
        boolean result = restaurantService.isValidAccount("emmanuel@g,ail.com");
        Assert.assertEquals(result, false);
    }

    @Test
    public void testSignin() {
        account = restaurantService.signin("default@gmail.com", "Default");
        Assert.assertEquals(account.getEmail(), "default@gmail.com");
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testSigninWithNonExstingAccount() {
        restaurantService.signin("mayala@gmail.com", "Default");
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void testSigninWithWrongPassword() {
        restaurantService.signin("default@gmail.com", "wrongPassword");
    }
    
    @Test
    public void testSignup() {
        account = new Account("primaryemmy@gmail.com", "Emmanuel", "Ntivuguruzwa", "0788596281", "mypassword");
        Account retAcct = restaurantService.signup(account);
        Assert.assertEquals(retAcct.getEmail(), "primaryemmy@gmail.com");
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void testSignupWithExistingAccount() {
        account = new Account("default@gmail.com", "Emmanuel", "Ntivuguruzwa", "0788596281", "mypassword");
        restaurantService.signup(account);
    }

    @Test
    public void findTableInformation(){
        reservation =  restaurantService.findTableInformation(1);
        Assert.assertEquals(reservation.isReserved(), false);
    }
    
    @Test
    public void findTableInformationWithWrongTable(){
        reservation =  restaurantService.findTableInformation(30);
        Assert.assertEquals(reservation, null);
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testReserveForWrongAccount(){
        reservation = new Reservation(100, "Emmanuel", "emmanuel@gmail.com", "0788596281", LocalDate.now(), "12h30", 2, "Pizza");
        reservation = restaurantService.reserveTable(reservation);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void testReserveForWrongTable(){
        reservation = new Reservation(100, "Emmanuel", "emmanuel@gmail.com", "0788596281", LocalDate.now(), "12h30", 2, "Pizza");
        reservation = restaurantService.reserveTable(reservation);
    }
    
    @Test(expectedExceptions = {RuntimeException.class})
    public void testReserveForReservedTable(){
        reservation = new Reservation(10, "Emmanuel", "emmanuel@gmail.com", "0788596281", LocalDate.now(), "12h30", 2, "Pizza");
        reservation = restaurantService.reserveTable(reservation);
    }
    
    
    
    @Test
    public void reserveTable(){
        Reservation reservation = new Reservation(1, "Emmanuel", "default@gmail.com", "0788596281", LocalDate.MIN, "18h30", 3, "King Bugger, want pizza");
        Reservation reservationConf = restaurantService.reserveTable(reservation);
        Assert.assertEquals(reservationConf.isReserved(), true);
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
        excute(GeneralDataInialiation.DELETE_ALL_RESERVATIONs);
        excute(GeneralDataInialiation.DELETE_ALL_ACCOUNTs);
    }
    
}
