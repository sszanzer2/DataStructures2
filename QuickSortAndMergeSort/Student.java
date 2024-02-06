package SortingHW;

public class Student implements Comparable<Student>{
	private String name;
	private int grade;
	
	public Student(String name, int grade) {
		this.name=name;
		this.grade=grade;
	}
	
	public String getName() {
		return name;
	}
	
	public int getGrade() {
		return grade;
	}
	//implementing the compareTo method to compare the grades
	@Override
    public int compareTo(Student other) {
        return Integer.compare(this.grade, other.grade);
    }
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Student name: " + name + " Grade: " + grade);
		return sb.toString();
	}
}
