package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Main extends Application {
 
	 static int canvasW = 1000;
	 static int canvasH = 600;
	

	@Override
	public void start(Stage primaryStage) {
							try {							
		primaryStage.setTitle("proj4");
		Canvas canvas = new Canvas(canvasW, canvasH);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		int d  = 500, centerX = canvasW/2, centerY = canvasH/2;
		MyPoint p = new MyPoint(centerX,centerY);
		
		String url = "jdbc:sqlserver://DESKTOP-TN55T76:1433;\" +\r\n"
				+ "				\"database=proj;integratedSecurity=true;\" +\r\n"
				+ "				\"encrypt=true;trustServerCertificate=true";
		
		Database con = new Database(url);
		con.getConnection();
		
		String sqlTable,sqlInsert, sqlQuery;
		
		sqlTable = "CREATE TABLE Schedule("
				+ " courseId CHAR(12) NOT NULL UNIQUE,"
				+ "sectionNumber VARCHAR(8) NOT NULL UNIQUE,"
				+ "title VARCHAR(64),"
				+ "year INT,"
				+ "semester CHAR(6),"
				+ "instructor VARCHAR(24),"
				+ "department CHAR(16),"
				+ "program VARCHAR(48),"
				+ "PRIMARY KEY(courseId, sectionNumber))";
		
		String filename = "D:\\Documents\\proj2\\ScheduleSpring2021.txt";
		String tablename = "Schedule";
		
		sqlInsert = "BULK INSERT "+ tablename + " FROM " + filename + " WITH (FIELDTERMINATOR = '\\t',\r\n"
				+ "		ROWTERMINATOR = '\\n',\r\n"
				+ "		FIRSTROW = 2)";
		Database.Schedule springSchedule = con.new Schedule(sqlTable,sqlInsert);
		
		
		sqlTable = "CREATE TABLE Courses(\r\n"
				+ "	courseId CHAR(12) PRIMARY KEY REFERENCES Schedule(courseId),\r\n"
				+ "	title VARCHAR(64),\r\n"
				+ "	department CHAR(16))";
		sqlInsert = "INSERT INTO Courses\r\n"
				+ "SELECT courseId, title, department\r\n"
				+ "FROM Schedule";
		Database.Courses course = con.new Courses(sqlTable,sqlInsert);
		
		sqlTable = "CREATE TABLE Student(\r\n"
				+ "	emplId INT PRIMARY KEY,\r\n"
				+ "	firtName VARCHAR(32) NOT NULL,\r\n"
				+ "	LastName VARCHAR(32) NOT NULL,\r\n"
				+ "	email VARCHAR(255) NOT NULL UNIQUE,\r\n"
				+ "	gender CHAR CHECK (gender = 'F' OR gender = 'M' OR gender = 'U'),\r\n"
				+ "	)";
	//	sqlInsert = "INSERT INTO Student VALUES (18, 'John' , 'Doe','jdoe@gmail.com' ,'M')";
		
		Database.Students stu= con.new Students(sqlTable,sqlInsert);
		stu.insert("INSERT INTO Student VALUES (19, 'Jon' , 'De','jdo@gmail.com' ,'M')");
		stu.insert("INSERT INTO Student VALUES (20, 'Jo' , 'D','jo@gmail.com' ,'M')");
		
		sqlTable = "CREATE TABLE Classes(\r\n"
				+ "	studentID INT REFERENCES Student(emplId),\r\n"
				+ "	courseId CHAR(12) REFERENCES Schedule(courseId),\r\n"
				+ "	sectionNumber VARCHAR(8) REFERENCES Schedule(sectionNumber),\r\n"
				+ "	year INT,\r\n"
				+ "	semester CHAR(6),\r\n"
				+ "	grade CHAR CHECK(grade = 'A' OR grade = 'B' OR grade = 'C' OR grade = 'D' OR grade = 'F' OR grade = 'W'),\r\n"
				+ "	PRIMARY KEY(studentID, courseID, sectionNumber))";
//		sqlInsert = "INSERT INTO Classes VALUES (19, '22100 F', '32131', 2021 , 'Spring', 'B')";
	
		
		Database.Classes clas = con.new Classes(sqlTable,sqlTable);
		//clas.insert("INSERT INTO Classes VALUES (19, '22100 F','32131', 2021 , 'Spring', 'D')");
//		clas.insert("INSERT INTO Classes VALUES (20, '22100 F','32131', 2021 , 'Spring', 'F')");
		
		/*clas.delete("Delete \r\n"
				+ "FROM Classes\r\n"
				+ "WHERE studentID = 20");
		*/
		sqlTable = "CREATE TABLE GradeAggregate(\r\n"
				+ "	grade CHAR,\r\n"
				+ "	numberStudents INT)";
		sqlInsert = "INSERT INTO GradeAggregate\r\n"
				+ "SELECT grade, count(grade)\r\n"
				+ "FROM Classes\r\n"
				+ "GROUP BY grade";
		Database.AggregateGrades spring2021 = con.new AggregateGrades(sqlTable,sqlInsert);
		
		sqlQuery = "SELECT *\r\n"
				+ "FROM GradeAggregate";
		
		
		
		HistogramAlphaBet mapAgregate = new HistogramAlphaBet(spring2021.getAgregateGrades(sqlQuery));
		
		HistogramAlphaBet.MyPieChart pie = mapAgregate.new MyPieChart(6, p,d/2,30);
		pie.draw(gc);

		StackPane root = new StackPane(canvas);
		Scene scene = new Scene(root,canvasW,canvasH);
		primaryStage.setScene(scene);
		primaryStage.show();
				} catch(Exception e) {
					e.printStackTrace();
				}
	}

	
	public static void main(String[] args) {

		launch(args);
		
	}
	}
