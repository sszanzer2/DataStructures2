package LambdasAndStreams;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class SchulMember implements Comparable<SchulMember>{

	private String lastNameOfMember;
	private String firstNameOfMember;
	private LocalDate birthDateOfMember;
	private String spouseFirstName;
	private String spouseLastName;
	private String[] childrenNames; 
	private int yearsOfMembership;

	public SchulMember(String lastNameOfMember,String firstNameOfMember, LocalDate birthDateOfMember,String spouseFirstName,
						String spouseLastName,String[] childrenNames,int yearsOfMembership) {
		
		this.lastNameOfMember=lastNameOfMember;
		this.firstNameOfMember = firstNameOfMember;
		this.birthDateOfMember = birthDateOfMember;
		this.spouseFirstName = spouseFirstName;
		this.spouseLastName= spouseLastName;
		this.childrenNames = childrenNames;
		this.yearsOfMembership = yearsOfMembership;
	}

	public String getLastNameOfMember() {
		return lastNameOfMember;
	}
	
	public String getFirstNameOfMember() {
		return firstNameOfMember;
	}
	
	public LocalDate getBirthDateOfMember() {
		return birthDateOfMember;
	}
	
	public String getSpouseFirstName() {
		return spouseFirstName;
	}
	
	public String getSpouselastName() {
		return spouseLastName;
	}
	
	public String[] getChildrenNames() {
		return childrenNames;
	}
	
	public int getYearsOfMembership() {
		return yearsOfMembership;
	}
	
	public void setLastNameOfMember(String lastNameOfMember) {
		this.lastNameOfMember = lastNameOfMember;
	}
	
	public void setFirstNameOfMember(String firstNameOfMember) {
		this.firstNameOfMember = firstNameOfMember;
	}
	
	public void setBirthDateOfMember(LocalDate birthDateOfMember) {
		this.birthDateOfMember = birthDateOfMember;
	}
	
	public void setSpouseFirstName(String spouseFirstName) {
		this.spouseFirstName = spouseFirstName;
	}
	
	public void setSpouseLastName(String spouseLastName) {
		this.spouseLastName = spouseLastName;
	}
	
	public void setChildrenNames(String[] childrenNames) {
		this.childrenNames = childrenNames;
	}
	
	public void setYearsOfMembership(int yearsOfMembership) throws Exception {
		if(yearsOfMembership < 0) {
			throw new Exception("Please enter a non-negative number.");
		}
		this.yearsOfMembership = yearsOfMembership;
	}
	@Override
	public int compareTo(SchulMember o) {
		return this.birthDateOfMember.compareTo(o.birthDateOfMember);
	}
	
}
