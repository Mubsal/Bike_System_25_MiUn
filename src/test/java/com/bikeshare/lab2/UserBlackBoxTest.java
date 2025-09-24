package com.bikeshare.lab2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bikeshare.model.User;

/**
 * Lab 2 Template: Black Box Testing for User class
 * 
 
 * 
 * TODO for students:
 * - Challenge 2.1: Add Equivalence Partitioning tests for email validation, name, telephone number (With GenAI help), and fund addition
 * - Challenge 2.2: Add Boundary Value Analysis tests for fund addition
 * - Challenge 2.3: Add Decision Table tests for phone number validation
 * - Optional Challenge 2.4: Add error scenario tests
 */

// This test is just an example to get you started. You will need to add more tests as per the challenges.
@DisplayName("Verify name handling in User class")
class UserBlackBoxTest {
    
    // Test constants
    private static final String VALID_PERSONNUMMER = "901101-1237"; // Valid Swedish personnummer
    
    // Test to verify name handling

    
    @Test
    @DisplayName("Should reject null first name")
    void shouldRejectNullFirstName() {
        // Arrange - Set up test data
        String validEmail = "john.doe@example.com";
        String nullFirstName = null;
        String lastName = "Doe";        
        // Act & Assert - Expect IllegalArgumentException when creating User with null first name
        assertThrows(IllegalArgumentException.class, () -> {
            new User(VALID_PERSONNUMMER, validEmail, nullFirstName, lastName);
        }, "Creating User with null first name should throw IllegalArgumentException");
    }


    @Test
    @DisplayName("Should reject null last name")
    void shouldRejectNullLastName() {
        // Arrange - Set up test data
        String validEmail = "john.doe@example.com";
        String firstName = "John";
        String nullLastName = null;
        // Act & Assert - Expect IllegalArgumentException when creating User with null last name
        assertThrows(IllegalArgumentException.class, () -> {
            new User(VALID_PERSONNUMMER, validEmail, firstName, nullLastName);
        }, "Creating User with null last name should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Should reject empty string first name")
    void shouldRejectEmptyFirstName() {
        // Arrange - Set up test data
        String validEmail = "john.doe@example.com"; 
        String emptyFirstName = "   "; // String with only spaces
        String lastName = "Doe";
        // Act & Assert - Expect IllegalArgumentException when creating User with empty first name
        assertThrows(IllegalArgumentException.class, () -> {
            new User(VALID_PERSONNUMMER, validEmail, emptyFirstName, lastName);
        }, "Creating User with empty first name should throw IllegalArgumentException");
    }       
    
    @Test
    @DisplayName("Should reject empty string last name")
    void shouldRejectEmptyLastName() {
        // Arrange - Set up test data
        String validEmail = "john.doe@example.com";
        String firstName = "John";
        String emptyLastName = "   "; // String with only spaces
        // Act & Assert - Expect IllegalArgumentException when creating User with empty last name
        assertThrows(IllegalArgumentException.class, () -> {
            new User(VALID_PERSONNUMMER, validEmail, firstName, emptyLastName);
        }, "Creating User with empty last name should throw IllegalArgumentException");
    }
   

    @Test
    @DisplayName("Should store and retrieve user names correctly")
    void shouldStoreAndRetrieveUserNamesCorrectly() {
        // Arrange - Set up test data
        String expectedFirstName = "John";
        String expectedLastName = "Doe";
        String validEmail = "john.doe@example.com";
        
        // Act - Execute the method under test
        User user = new User(VALID_PERSONNUMMER, validEmail, expectedFirstName, expectedLastName);
        String actualFirstName = user.getFirstName();
        String actualLastName = user.getLastName();
        String actualFullName = user.getFullName();
        
        // Assert - Verify the expected outcome
        assertNotNull(user, "User should be created successfully");
        assertEquals(expectedFirstName, actualFirstName, "First name should match");
        assertEquals(expectedLastName, actualLastName, "Last name should match");
        assertEquals("John Doe", actualFullName, "Full name should be formatted correctly");
    }
    
    // TODO: Challenge 2.1 - Add Equivalence Partitioning tests for email validation
    // Hint: Test valid emails (user@domain.com) and invalid emails (missing @, empty, etc.)

    // Valid email test

    @Test
    @DisplayName("Valid email: letters, digits, +, _, ., - in local part")
    void shouldAcceptValidEmailWithAllowedChars() {
    // Arrange - Set up test data
    String email = "user.name+alias-123@example.co.uk";
    String firstName = "Jenni";
    String lastName = "Pancake";

    // Act - Execute the method under test
    User user = new User(VALID_PERSONNUMMER, email, firstName, lastName);

    // Assert - Verify the expected outcome
    assertNotNull(user, "User should be created successfully with valid email");
    assertEquals(email.toLowerCase(), user.getEmail(), "Email should match input");
    }

    @Test
    @DisplayName("Should accept valid email with minimum domain length")
    void shouldAcceptValidEmailWithMinDomain() {
    // Arrange
    String email = "user@ex.co";
    String firstName = "Jenni";
    String lastName = "Pancake";

    // Act
    User user = new User(VALID_PERSONNUMMER, email, firstName, lastName);

    // Assert
    assertNotNull(user, "User should be created successfully with valid email");
    assertEquals(email.toLowerCase(), user.getEmail(), "Email should match input");
    }

    @Test
    @DisplayName("Should reject email missing @")
    void shouldRejectEmailMissingAt() {
    // Arrange
    String invalidEmail = "username.example.com";

    // Act & Assert
    assertThrows(IllegalArgumentException.class,
            () -> new User(VALID_PERSONNUMMER, invalidEmail, "Jenni", "Pancake"));
    }
    
    @Test
    @DisplayName("Should reject email with multiple @")
    void shouldRejectEmailMultipleAt() {
    // Arrange
    String invalidEmail = "user@@example.com";

    // Act & Assert
    assertThrows(IllegalArgumentException.class,
            () -> new User(VALID_PERSONNUMMER, invalidEmail, "Jenni", "Pancake"));
    }

    @Test
    @DisplayName("Should reject email with empty local part")
    void shouldRejectEmailEmptyLocal() {
    // Arrange
    String invalidEmail = "@example.com";

    // Act & Assert
    assertThrows(IllegalArgumentException.class,
            () -> new User(VALID_PERSONNUMMER, invalidEmail, "Jenni", "Pancake"));
    }

    @Test
    @DisplayName("Should reject null email")
    void shouldRejectNullEmail() {
    // Arrange
    String email = null;
    String firstName = "Anna";
    String lastName = "Who";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
                    new User(VALID_PERSONNUMMER, email, firstName, lastName),
            "Null email should throw exception");
    }

    @Test
    @DisplayName("Should reject empty string email")
    void shouldRejectEmptyEmail() {
    // Arrange
    String email = "";
    String firstName = "Anna";
    String lastName = "Who";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
                    new User(VALID_PERSONNUMMER, email, firstName, lastName),
            "Empty string email should throw exception");
    }

    @Test
    @DisplayName("Should reject email with missing domain")
    void shouldRejectMissingDomain() {
    // Arrange
    String email = "user@";
    String firstName = "Anna";
    String lastName = "Who";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
                    new User(VALID_PERSONNUMMER, email, firstName, lastName),
            "Email missing domain should throw exception");
    }

    @Test
    @DisplayName("Should reject email with Top-Level Domain too short (<2 chars)")
    void shouldRejectShortTopLevelDomain() {
    // Arrange
    String email = "user@example.c";
    String firstName = "Anna";
    String lastName = "Who";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
                    new User(VALID_PERSONNUMMER, email, firstName, lastName),
            "Email with Top-Level Domain <2 chars should throw exception");
    }

    @Test
    @DisplayName("Should reject email with invalid character in domain")
    void shouldRejectInvalidCharInDomain() {
    // Arrange
    String email = "user@exam!ple.com";
    String firstName = "Anna";
    String lastName = "Who";

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () ->
                    new User(VALID_PERSONNUMMER, email, firstName, lastName),
            "Email with invalid char in domain should throw exception");
    }


    @Test
    @DisplayName("Should accept valid email addresses")
    void shouldAcceptValidEmailAddresses() {
        // Arrange
        String validEmail = "user@domain.com";
        String firstName = "Alice";
        String lastName = "Smith";
        // Act
        User user = new User(VALID_PERSONNUMMER, validEmail, firstName, lastName);
        // Assert
        assertNotNull(user, "User should be created with a valid email");
        assertEquals(validEmail, user.getEmail(), "Email should match the valid input");
    }

    // TODO: Challenge 2.2 - Add Boundary Value Analysis tests for fund addition
    // Hint: Test minimum (0.01), maximum (1000.00), and invalid amounts (0, negative, > 1000)

    @Test
    @DisplayName("Should add funds within valid boundaries")
    void shouldAddFundsWithinValidBoundaries() {
        // Arrange
        String validEmail = "user@domain.com";
        User user = new User(VALID_PERSONNUMMER, validEmail, "Bob", "Brown");

        // Act
        user.addFunds(100.00); // Add valid funds

        // Assert
        assertEquals(100.00, user.getAccountBalance(), "Balance should be 100.00 after adding funds");
    }

    // TODO: Challenge 2.3 - Add Decision Table tests for phone number validation
    // Hint: Test Swedish phone formats (+46701234567, 0701234567) and invalid formats

    @Test
    @DisplayName("Should accept valid international phone number format")
    void shouldAcceptValidInternationalPhoneNumber() {
        // Arrange
        String validEmail = "user@domain.com";
        String validPhoneNumber = "+46701234567"; // Valid Swedish phone number
        User user = new User(VALID_PERSONNUMMER, validEmail, "Charlie", "Davis");
        // Act
        user.setPhoneNumber(validPhoneNumber);

        // Assert
        assertEquals(validPhoneNumber, user.getPhoneNumber(), "Phone number should match the valid input");
    }

    @Test
    @DisplayName("Should accept valid Swedish phone number format")
    void shouldAcceptAnotherValidSwedishPhoneNumberFormat() {
        // Arrange
        String validEmail = "user@domain.com";
        String validPhoneNumber = "0701234567"; // Another valid Swedish phone number format
        User user = new User(VALID_PERSONNUMMER, validEmail, "Diana", "Evans");
        // Act
        user.setPhoneNumber(validPhoneNumber);      
        // Assert
        assertEquals(validPhoneNumber, user.getPhoneNumber(), "Phone number should match the valid input");
    }

    @Test
    @DisplayName("Should reject phone number with letters")
    void shouldRejectPhoneNumberWithLetters() {
        // Arrange
        String validEmail = "user@domain.com";
        User user = new User(VALID_PERSONNUMMER, validEmail, "Eve", "Foster");
        String invalidPhoneNumber = "07012ABCD"; // Invalid phone number with letters

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(invalidPhoneNumber),
                "Phone number with letters should throw exception");
    }



    @Test
    @DisplayName("Should reject phone number that is too short")
    void shouldRejectTooShortPhoneNumber() {
    // Arrange
    String phoneNumber = "070123";
    User user = new User(VALID_PERSONNUMMER, "test@example.com", "Anna", "Who");

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(phoneNumber),
            "Phone number too short should throw exception");
    }

    @Test
    @DisplayName("Phone number validation - invalid format missing digits")
    void shouldRejectPhoneNumberMissingDigits() {
    // Arrange
    String phoneNumber = "+4670 123 45"; // missing one digit
    User user = new User(VALID_PERSONNUMMER, "test@example.com", "Anna", "Who");

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> user.setPhoneNumber(phoneNumber),
            "Phone number with missing digits should throw exception");
    }
    
    // TODO: Challenge 2.4 - Add error scenario tests
    // Hint: Test insufficient balance, invalid inputs, state violations


    // Boundary Value Analysis tests for adding funds
    // Testing values just below, at, and just above the boundaries of 0.10 and 10,000.00 

    @Test 
    @DisplayName("Should reject negative fund amounts")
    void shouldRejectNegativeAmounts() {
        // Arrange
        String validEmail = "user@domain.com";
        User user = new User(VALID_PERSONNUMMER, validEmail, "Eve", "Johnson");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            user.addFunds(-0.01); // Test adding negative funds (should be rejected)
        });
        
        // Verify the exception and that balance wasn't changed
        assertEquals("Amount must be positive", exception.getMessage());
        assertEquals(0.00, user.getAccountBalance(), "Balance should remain 0.00 when invalid amount is rejected");          
    }

    @Test
    @DisplayName("Should reject zero fund amounts")
    void shouldRejectZeroAmounts() {
        // Arrange
        String validEmail = "user@domain.com";
        User user = new User(VALID_PERSONNUMMER, validEmail, "Zero", "Test");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            user.addFunds(0.00); // Test adding zero funds (should be rejected)
        });
        
        // Verify the exception and that balance wasn't changed
        assertEquals("Amount must be positive", exception.getMessage());
        assertEquals(0.00, user.getAccountBalance(), "Balance should remain 0.00 when invalid amount is rejected");          
    }

    @Test
    @DisplayName("Boundary Value Analysis for adding funds")
    void shouldAcceptMinimum() { 
        String validEmail = "user@domain.com";
        User user = new User(VALID_PERSONNUMMER, validEmail, "Eve", "Johnson");
        // Test adding minimum funds (0.10)
        user.addFunds(0.10);
        assertEquals(0.10, user.getAccountBalance(), "Balance should be 0.10 after adding minimum funds");          
    }


    @Test
    @DisplayName("Boundary Value Analysis for adding funds")
    void shouldAcceptJustBelowMaximum() { 
        String validEmail = "user@domain.com";
        User user = new User(VALID_PERSONNUMMER, validEmail, "Eve", "Johnson");
        // Test adding just below maximum funds (9999.99)
        user.addFunds(999.99);
        assertEquals(999.99, user.getAccountBalance(), "Balance should be 9999.99 after adding just below maximum funds");
    }

    @Test
    @DisplayName("Boundary Value Analysis for adding funds")
    void shouldAcceptMaximum() {
        String validEmail = "user@domain.com";
        User user = new User(VALID_PERSONNUMMER, validEmail, "Eve", "Johnson");
        // Test adding maximum funds (10000.00)
        user.addFunds(1000.00);
        assertEquals(1000.00, user.getAccountBalance(), "Balance should be 10000.00 after adding maximum funds");
    }

    @Test
    @DisplayName("Should reject fund addition above maximum")
    void shouldRejectAboveMaximum() { 
        // Arrange
        String validEmail = "user@domain.com";
        User user = new User(VALID_PERSONNUMMER, validEmail, "Eve", "Johnson");
        
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            user.addFunds(1000.01); // Test adding above maximum funds
        });
        
        // Verify the exception and that balance wasn't changed
        assertEquals("Cannot add more than $1000 at once", exception.getMessage());
        assertEquals(0.00, user.getAccountBalance(), "Balance should remain 0.00 when invalid amount is rejected");
    }



// Decision Table tests for membership type and discount application 
    
    @Test
    @DisplayName("Should apply BASIC membership discount (0%)")
    void shouldApplyBasicMembershipDiscount() {
        // Arrange
        String validEmail = "user@domain.com";
        User user = new User(VALID_PERSONNUMMER, validEmail, "Frank", "Miller");
        // User starts with BASIC membership by default
        
        // Act
        double actualDiscount = user.calculateDiscount();
        
        // Assert
        assertEquals(0.0, actualDiscount, "BASIC members should receive 0% discount");
    }

    @Test
    @DisplayName("Should apply PREMIUM membership discount (15%)")
    void shouldApplyPremiumMembershipDiscount() {
        // Arrange
        String validEmail = "user@domain.com";
        User user = new User(VALID_PERSONNUMMER, validEmail, "Alice", "Premium");
        
        // Activate user to allow membership changes
        user.verifyEmail();
        user.activate();
        user.updateMembership(User.MembershipType.PREMIUM);
        
        // Act
        double actualDiscount = user.calculateDiscount();
        
        // Assert
        assertEquals(0.15, actualDiscount, "PREMIUM members should receive 15% discount");
    }

    @Test
    @DisplayName("Should apply STUDENT membership discount (20%)")
    void shouldApplyStudentMembershipDiscount() {
        // Arrange
        String validEmail = "user@domain.com";
        User user = new User(VALID_PERSONNUMMER, validEmail, "Charlie", "Student");
        
        // Activate user to allow membership changes
        user.verifyEmail();
        user.activate();
        user.updateMembership(User.MembershipType.STUDENT);
        
        // Act
        double actualDiscount = user.calculateDiscount();
        
        // Assert
        assertEquals(0.20, actualDiscount, "STUDENT members should receive 20% discount");
    }

    @Test
    @DisplayName("Should apply CORPORATE membership discount (10%)")
    void shouldApplyCorporateMembershipDiscount() {
        // Arrange
        String validEmail = "user@domain.com";
        User user = new User(VALID_PERSONNUMMER, validEmail, "Diana", "Corporate");
        
        // Activate user to allow membership changes
        user.verifyEmail();
        user.activate();
        user.updateMembership(User.MembershipType.CORPORATE);
        
        // Act
        double actualDiscount = user.calculateDiscount();
        
        // Assert
        assertEquals(0.10, actualDiscount, "CORPORATE members should receive 10% discount");
    }



}
    

