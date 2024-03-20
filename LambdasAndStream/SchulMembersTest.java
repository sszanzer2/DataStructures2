package LambdasAndStreams;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class SchulMembersTest {

	@Test
    public void TestGetters(){
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        String[] childrenNames = {"Alice", "Bob"};
        SchulMember member = new SchulMember("Doe", "John", birthDate, "Jane", "Doe", childrenNames, 5);
        
        assertEquals("Doe", member.getLastNameOfMember());
        assertEquals("John", member.getFirstNameOfMember());
        assertEquals(birthDate, member.getBirthDateOfMember());
        assertEquals("Jane", member.getSpouseFirstName());
        assertEquals("Doe", member.getSpouselastName());
        assertEquals(childrenNames, member.getChildrenNames());
        assertEquals(5, member.getYearsOfMembership());
        
       
    }
	
	@Test
	public void TestSetters() throws Exception {
		LocalDate birthDate = LocalDate.of(1990, 5, 15);
        String[] childrenNames = {"Alice", "Bob"};
        SchulMember member = new SchulMember("Doe", "John", birthDate, "Jane", "Doe", childrenNames, 5);
	 	member.setLastNameOfMember("Smith");
        member.setFirstNameOfMember("Alice");
        LocalDate newBirthDate = LocalDate.of(1985, 3, 20);
        member.setBirthDateOfMember(newBirthDate);
        member.setSpouseFirstName("Bob");
        member.setSpouseLastName("Smith");
        String[] newChildrenNames = {"Charlie"};
        member.setChildrenNames(newChildrenNames);
        member.setYearsOfMembership(10);
        
        assertEquals("Smith", member.getLastNameOfMember());
        assertEquals("Alice", member.getFirstNameOfMember());
        assertEquals(newBirthDate, member.getBirthDateOfMember());
        assertEquals("Bob", member.getSpouseFirstName());
        assertEquals("Smith", member.getSpouselastName());
        assertEquals(newChildrenNames, member.getChildrenNames());
        assertEquals(10, member.getYearsOfMembership());
	}
    
    @Test
    public void TestCompareTo() {
        LocalDate birthDate1 = LocalDate.of(1990, 5, 15);
        LocalDate birthDate2 = LocalDate.of(1985, 3, 20);
        SchulMember member1 = new SchulMember("Doe", "John", birthDate1, "Jane", "Doe", null, 5);
        SchulMember member2 = new SchulMember("Smith", "Alice", birthDate2, "Bob", "Smith", null, 10);
        
        assertTrue(member1.compareTo(member2) > 0);
        assertTrue(member2.compareTo(member1) < 0);
        assertEquals(0, member1.compareTo(member1));
    }
    
    @Test
    public void testCompareToSameBirthDate() {
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        SchulMember member1 = new SchulMember("Doe", "John", birthDate, "Jane", "Doe", null, 5);
        SchulMember member2 = new SchulMember("Smith", "Alice", birthDate, "Bob", "Smith", null, 10);
        
        assertEquals(0, member1.compareTo(member2));
        assertEquals(0, member2.compareTo(member1));
    }
    
    @Test
    public void testNullValuesInConstructor() {
        // Test providing null values in the constructor
        SchulMember member = new SchulMember(null, null, null, null, null, null, 0);
        
        assertNull(member.getLastNameOfMember());
        assertNull(member.getFirstNameOfMember());
        assertNull(member.getBirthDateOfMember());
        assertNull(member.getSpouseFirstName());
        assertNull(member.getSpouselastName());
        assertNull(member.getChildrenNames());
        assertEquals(0, member.getYearsOfMembership());
    }

    @Test
    public void testEmptyArrayInConstructor() {
        // Test providing an empty array for children names in the constructor
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        SchulMember member = new SchulMember("Doe", "John", birthDate, "Jane", "Doe", new String[]{}, 5);
        
        assertArrayEquals(new String[]{}, member.getChildrenNames());
    }

    @Test
    public void testNullValuesInSetters() {
        // Test setting properties to null using setters
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        SchulMember member = new SchulMember("Doe", "John", birthDate, "Jane", "Doe", new String[]{"Alice"}, 5);
        
        member.setLastNameOfMember(null);
        member.setFirstNameOfMember(null);
        member.setBirthDateOfMember(null);
        member.setSpouseFirstName(null);
        member.setSpouseLastName(null);
        member.setChildrenNames(null);
        
        assertNull(member.getLastNameOfMember());
        assertNull(member.getFirstNameOfMember());
        assertNull(member.getBirthDateOfMember());
        assertNull(member.getSpouseFirstName());
        assertNull(member.getSpouselastName());
        assertNull(member.getChildrenNames());
    }

    @Test
    public void testEmptyArrayInSetter() {
        // Test setting an empty array for children names using a setter
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        SchulMember member = new SchulMember("Doe", "John", birthDate, "Jane", "Doe", new String[]{"Alice"}, 5);
        
        member.setChildrenNames(new String[]{});
        
        assertArrayEquals(new String[]{}, member.getChildrenNames());
    }
    
    @Test
    public void testSetNegativeYearsOfMembership() {
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        SchulMember member = new SchulMember("Doe", "John", birthDate, "Jane", "Doe", new String[]{"Alice"}, 5);
        
        // Attempt to set negative years of membership using assertThrows
        assertThrows(Exception.class, () -> member.setYearsOfMembership(-1));
    }

}
