package LambdasAndStreams;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Main {
	public static void main(String[] args) {
		LocalDate bday1 = LocalDate.of(2000,1,1);
		LocalDate bday2 = LocalDate.of(1975,6,12);
		LocalDate bday3 = LocalDate.of(1990,4,5);
		LocalDate bday4 = LocalDate.of(2002,2,18);
		LocalDate bday5 = LocalDate.of(1970,11,8);
		LocalDate bday6 = LocalDate.of(1995,3,17);
		LocalDate bday7 = LocalDate.of(1985,8,3);
		List<SchulMember> members = Stream.of(
				new SchulMember("Schwartz", "Yehuda", bday1, "Chaya", "Schwartz", new String[]{"Avigail", "Mordechai", "Yaakov"}, 5),
				new SchulMember("Schron", "Moshe", bday2, "Meira", "Schron", new String[]{"Esti", "Miri", "Nosson"}, 10),
				new SchulMember("Goldstien", "Daniel", bday3, "Sara", "Goldstien", new String[]{"Tehila", "Shira"}, 3),
				new SchulMember("Gordon", "Chaim", bday4, "Kayla", "Gordon", new String[]{"Rochel", "Shua", "Tzvi", "Baila"}, 7),
				new SchulMember("Weiss", "Shaya", bday5, "Leah", "Weiss", new String[]{"Tzippy", "Zevi", "Avi", "Michal"}, 12),
				new SchulMember("Krieger", "Yonatan", bday6, "Hindy", "Krieger", new String[]{"Benyamin", "Ahuva"}, 4),
				new SchulMember("Millstien", "Dovid", bday7, "Rivka", "Millstien", new String[]{"Layla", "Chana", "Tzvi", "Dovi", "Rena"}, 9)
				).collect(Collectors.toList());
		
		//Print how many families belong to your shul
		long numberOfFamilies = members.stream().count();
		System.out.println("Number of families in the shul: " + numberOfFamilies);
		System.out.println();
		
		//Print in sorted order how long each family has been a member of your schul 
	    members.stream().sorted(Comparator.comparingInt(SchulMember::getYearsOfMembership))
	           .forEach(entry -> System.out.println("Family: " + entry.getLastNameOfMember() + ", Years of Membership: " + entry.getYearsOfMembership()));
		System.out.println();
		
	    //Print out from oldest to youngest the ages of your members (not spouses) 
		members.stream().sorted(Comparator.comparing(SchulMember:: getBirthDateOfMember))
					.forEach(entry ->System.out.println("Family: " + entry.getLastNameOfMember() + ", Age: " + entry.getBirthDateOfMember())); 
		System.out.println();
		
		//Sort the names of the spouses of all members 
	    members.stream().sorted(Comparator.comparing(SchulMember:: getSpouseFirstName))
	    .forEach(entry -> System.out.println("Spouses of schul members: " + entry.getSpouseFirstName()));
	    System.out.println();
	    
	    //print out all families who have more than 3 children 
	    members.stream().filter(member -> member.getChildrenNames().length > 3)
	    .forEach(entry -> System.out.println("Families with more than 3 kids: " + entry.getLastNameOfMember()));
	    
	    // print out the names of all children whose name is larger (alphabetically) than the letter “c” (and what family they belong to).    
        members.stream() .filter(member -> Arrays.stream(member.getChildrenNames())
        				.anyMatch(name -> name.compareToIgnoreCase("c") > 0))
				        .forEach(member -> {System.out.println("\nFamily: " + member.getLastNameOfMember() + "\nChildren: ");
			            Arrays.stream(member.getChildrenNames())
		                .filter(name -> name.compareToIgnoreCase("c") > 0)
		                .forEach(name -> System.out.print(name + " "));
        });
	}

}
