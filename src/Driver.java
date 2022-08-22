import java.util.*;
import java.io.*;
public class Driver {

	public static void main(String[] args) throws IOException {
		File teachers = new File("C:\\Labproject\\teachers.txt");
		File classes = new File("C:\\Labproject\\classes.txt");
		File subjects = new File("C:\\Labproject\\subjects.txt");
		List<Teacher> teacherList = new ArrayList<>();
		List<Subject> subList = new ArrayList<>();
		List<Class> classList = new ArrayList<>();
		
		//Initial Subjects 
		Subject s1 = new Subject("Maths" , 1);
		Subject s2 = new Subject("English" , 2);
		Subject s3 = new Subject("History" , 3) ; 
		Subject s4 = new Subject("Science" , 4) ;
		subList.add(s1);
		subList.add(s2);
		subList.add(s3);
		subList.add(s4);
		//adding subjects to files
		addSubjectToFile(subList , subjects);
		
		
		//Initial Teachers 
		Teacher t1 = new Teacher("Navneet" , s1 , 123 ) ;
		Teacher t2 = new Teacher("Satyam" , s2 , 456) ;
		Teacher t3 = new Teacher("Pandey" , s3 , 789);
		teacherList.add(t1);
		teacherList.add(t2);
		teacherList.add(t3);
		addTeacherToFile(teacherList , teachers);
		
		//Classes 
		classList.add(new Class(51 , s1 , t1));
		classList.add(new Class(60 , s2 , t2));
		classList.add(new Class(55 , s3 , t3));
		
		
			addClassToFile(classList , classes);
		
		String choice = "" ; 
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("1. Show Files");
			System.out.println("2. Add Teacher/Class/Subject");
			System.out.println("3. Delete Teacher/Class/Subject");
			System.out.println("4. Search Teacher");
			System.out.println("5. Close Application");
			int option = Integer.parseInt(sc.nextLine());
			switch(option) {
			case 1 : // Show Files
				List<String> s = showFiles("C:\\Labproject\\");
				s.stream().forEach(System.out::println);
				System.out.println("Back to Main Screen [Y/N] : ");
				choice = sc.nextLine();
				break;
			case 2 : // Add teacher/class/subject
				toAddList(option);
				int selection = Integer.parseInt(sc.nextLine());
				switch(selection) {
					case 1 : //Add Teacher
						System.out.println("Enter Teacher Name :");
						String name = sc.nextLine();
						System.out.println("Select Subject :");
						
						for(Subject x : subList) {
							System.out.println( x.getSubId() +" : "+ x.getSubName());
							
						}
						System.out.println("Enter Subject ID :");
						int selectSubID = Integer.parseInt(sc.nextLine());
						Subject snew = null ;
						for(Subject x : subList) {
							if( x.getSubId() == selectSubID) {
								 snew = x ;
							}
							
						}
						System.out.println("Enter Emp. Code :");
						int code = Integer.parseInt(sc.nextLine());
						boolean flag = false ;
						for(Teacher t : teacherList) {
							if(t.getEmpId() == code) {
								System.out.println("Employee already exists with same Emp. ID.");
								flag = true ;
								break ;
							}
						}
						if(!flag){
						Teacher tnew = new Teacher(name , snew ,code);
						teacherList.add(tnew);
						addTeacherToFile(teacherList, teachers);
						
						}
						
						System.out.println("Do You Want to Continue[Y/N] :");
						choice = sc.nextLine() ;
						break ;
					case 2 : // Add Class
						snew = null ;
						System.out.println("Select Class Subject Name :");
						for(Subject x : subList) {
							System.out.println( x.getSubId() +" : "+ x.getSubName());
							
						}
						int subID= Integer.parseInt(sc.nextLine());;
						for(Subject x : subList) {
							if(x.getSubId() == subID)snew = x ;
						}
						System.out.println("Enter No. Of Student :");
						 int numStudent = Integer.parseInt(sc.nextLine());
						System.out.println("Select Class Teacher : ");
						for(Teacher x : teacherList) {
							System.out.println("ID :" + x.getEmpId() + " Name : " + x.getName());
						}
						System.out.println("Enter Emp. Id :");
						 int id = Integer.parseInt(sc.nextLine());
						 boolean f = false ;
						 for(Teacher x : teacherList) {
							 if(x.getEmpId() == id) {
								 Class cNew = new Class(numStudent , snew , x);
								 classList.add(cNew);
								 addClassToFile(classList , classes );
								 f=true ;
								 break;
							 }
						 }
						 if(!f) {
							 System.out.println("Enter Valid Emp. Id.");
						 }
						System.out.println("Do You Want to Continue[Y/N] :");
						choice = sc.nextLine() ;
						break;
					case 3 : //Add Subject
						System.out.println("Enter Subject Name");
						String subName = sc.nextLine();
						System.out.println("Enter Subject ID : ");
						int subId = Integer.parseInt(sc.nextLine());
						flag = false ;
						flag = subIDChecker(subId , subList);
						while(!flag) {
							System.out.println("Subject ID already Exists.");
							System.out.println("Enter Subject ID Again :");
							subId = Integer.parseInt(sc.nextLine());
							flag = subIDChecker(subId, subList);
						}
						snew = new Subject(subName , subId);
						subList.add(snew);
						addSubjectToFile(subList , subjects);
						System.out.println("Subject Added. Continue[Y/N] :");
						choice = sc.nextLine();
						break ;
					default :
						System.out.println("Invalid Input, Try again[Y/N] :");
						choice = sc.nextLine();
						break;
				}
				break;
			case 3 : //Delete Teacher/Class/Subject 
				toAddList(option);
				selection = Integer.parseInt(sc.nextLine());
				switch(selection) {
				case 1 :
					System.out.println("Select Teacher ID to Delete Teacher : ");
					for(Teacher x : teacherList) {
						System.out.println("ID :" + x.getEmpId() + " ,Name : "+ x.getName() + ", Subject : " + x.getSubject().getSubName());
					}
					int delSelection = Integer.parseInt(sc.nextLine());
					delTeacher(teacherList , delSelection , teachers) ;
					System.out.println("Do You Want to Continue[Y/N]");
					choice = sc.nextLine();
					break ;
				case 2 :
					System.out.println("Select Class to Delete");
					for(Class x : classList) {
						System.out.println("Class Name: " + x.getStandard().getSubName());
					}
					String dSelection = sc.nextLine().trim();
					delClass(classList , dSelection , classes);
					System.out.println("Do You Want to Continue[Y/N]");
					choice = sc.nextLine();
					break ;
				case 3 :
					System.out.println("Select Subject to Delete :");
					for(Subject x : subList) {
						System.out.println("Subject Name :" + x.getSubName());
					}
					dSelection = sc.nextLine().trim();
					delSubject(subList , dSelection , subjects);
					System.out.println("Do You Want to Continue[Y/N]");
					choice = sc.nextLine();
					break ;
				case 4 : 
					choice = "y" ; 
					break ;
				default :
					System.out.println("Invalid Input, Try Again");
					choice = "y" ;
					break;
				}
			break;
			case 4 : //Search Teacher
				System.out.println("Enter Teacher Emp. Id");
				String select = sc.nextLine();
				searchTeacher(teachers , select );
				System.out.println("Do You Want to Continue[Y/N]");
				choice = sc.nextLine();
				break ;
			case 5:
				System.out.println("Thanks for Visting.");
				choice = "N";
				break ;
			default:
				System.out.println("Invalid Input.");
				choice = "y";
				break;
			}
		}while(choice.equalsIgnoreCase("y"));
		
		
	}
	
	//********************Delete Operations***************************** 
	public static void delClass(List<Class> c , String name , File path) {
		for(Class x : c ) {
			if(x.getStandard().getSubName().equalsIgnoreCase(name)) {
				c.remove(x);
				addClassToFile(c , path) ;
				System.out.println("Class Deleted from File.");
				break ;
			}
		}
	}
	public static void delTeacher(List<Teacher> s , int id , File path ) {
		try {
			
		
		for(Teacher x : s ) {
			if(x.getEmpId() == id) {

				s.remove(x);
			}try {
					addTeacherToFile(s , path );
					System.out.println("Teacher Deleted from File.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}catch(ConcurrentModificationException e) {
			
			System.out.println("Exception thrown.");
		}
	}
	
	
	public static void delSubject(List<Subject> s, String name , File path) {
		for(Subject x : s) {
			if(x.getSubName().equalsIgnoreCase(name)) {
				s.remove(x) ; 
				addSubjectToFile(s , path);
				System.out.println("Subject Deleted from File.");
				break ;
			}
		}
		
	}
	
	
	//***********************************************************************************
	
	public static boolean subIDChecker(int subId , List<Subject> subList) {
		for(Subject x : subList) {
			if(x.getSubId() == subId) {
				
				return false ;
			}
		}
		
		
		
		return true ;
	}
	public static void toAddList(int n) {
		if(n == 2) {
			System.out.println("1. Add Teacher");
			System.out.println("2. Add Class");
			System.out.println("3. Add Subject");
			System.out.println("4. Back to Main Menu");
			System.out.println("Select Option :");
		}else {
			System.out.println("1. Delete Teacher");
			System.out.println("2. Delete Class");
			System.out.println("3. Delete Subject");
			System.out.println("4. Back to Main Menu");
			System.out.println("Select Option :");
		}
		
		
		
	}
	//Adding Objects to Files
	public static void addTeacherToFile(List<Teacher> tList , File teachers ) throws IOException {
		
		if(tList.isEmpty()) {
			System.out.println("Teacher cannot be null");
			return ;
		}
		
		try {
			 BufferedWriter fw = new BufferedWriter(new FileWriter(teachers));
			 for(int i = 0 ; i < tList.size() ; i++) {
				 fw.write(" Name : " + tList.get(i).getName() +": Emp. ID = " + tList.get(i).getEmpId() + " : Subject = " + tList.get(i).getSubject().getSubName() +":" + "\n");
			 }
			 
			 fw.close();
			 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		
	}
	
	public static void addClassToFile(List<Class> c , File classFile) {
		if(c == null) {
			System.out.println("Class cannot be null.");
			return ; 
		}
		
		try {
			FileWriter fw = new FileWriter(classFile);
			for(int i = 0 ; i < c.size() ; i++) {
				fw.write(" Name :" + c.get(i).getStandard().getSubName() + " No. Of Students :" + c.get(i).getNoOfStudent() + "Class Teacher :" + c.get(i).getClassTeacher().getName() + "\n");
					
			}
			fw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("Error Occurred While adding class");
		}
		
	}
	
	public static void addSubjectToFile(List<Subject> s , File SubFile) {
		if(s == null) {
			System.out.println("Subject Cannot be Null");
			return ;
		}
		try {
			FileWriter fw = new FileWriter(SubFile) ; 
			for(int i = 0 ; i < s.size() ; i++) {
				fw.write("Name : " + s.get(i).getSubName() + ", ID : " +  s.get(i).getSubId() + "\n" );	
			}
			
			fw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//Show Files 
	public static List<String> showFiles(String path){
		List<String> lists = new ArrayList<>() ; 
		File filePath = new File(path);
		lists = Arrays.asList(filePath.list()) ;
		Collections.sort(lists);
		
		return lists;
	}
	
	
	
	public static void searchTeacher(File teachers , String id) throws FileNotFoundException {
		Scanner reader = new Scanner(teachers) ; 
		boolean flag = false ;
		int i = 0 ;
		int j = 0;
		String[] words ;

		while(reader.hasNext() && !flag) {
			String data = reader.nextLine();
			words = data.split(":");
			for(String x : words) {
				if(x.contains(id)) {
					System.out.println(data);
					flag = true ;
					break;
				}
				
			}
			
		}if(!flag) {
			System.out.println("Cannot Find Teacher with thie Emp. Code.");
		}
		reader.close();
	}

}
