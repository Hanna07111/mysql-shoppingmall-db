package sql_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class jdbc_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//jdbc 연결
			String url = "jdbc:mysql://localhost/project_db";
			Connection conn = DriverManager.getConnection(url,"root","0711");
			Statement stmt = conn.createStatement();
			
			//Prepared Statement
			PreparedStatement pStmt1 = conn.prepareStatement("insert into customers(CustomerName, Address, City, PostalCode, Country) values(?,?,?,?,?)");
			PreparedStatement pStmt2 = conn.prepareStatement("select OrderDate, ProductName, Price, Quantity, DeliveryPeriod from product natural join v where CustomerID=?");
			PreparedStatement pStmt3 = conn.prepareStatement("select avg(deliveryPeriod) as avg_period from v join product p on v.ProductID=p.ProductID where p.ProductID=?");
			PreparedStatement pStmt5 = conn.prepareStatement("update customers set CustomerName=?, Address=?, City=?, PostalCode=?, Country=? where CustomerID=?");
			PreparedStatement pStmt5_1 = conn.prepareStatement("select * from customers where CustomerID=?");
			PreparedStatement pStmt6 = conn.prepareStatement("delete from customers where CustomerID=?");
	
			
			//메뉴 선택 화면
			Scanner sc = new Scanner(System.in);
			int flag=1;
			int menu;
			while(flag==1){
				System.out.println("\n=====================");
				System.out.println("        MENU");
				System.out.println("=====================");
				System.out.println("1. Insert - 회원 가입");
				System.out.println("2. Select1 - 구매 상품 목록 조회");
				System.out.println("3. Select2 - 제품의 평균 배송일 조회");
				System.out.println("4. Select3 - 제품의 판매액 조회");
				System.out.println("5. Update - 개인 정보 수정");
				System.out.println("6. Delete - 회원 탈퇴");
				System.out.println("0. Exit");
				System.out.println("=====================");
				System.out.print("원하시는 메뉴를 선택 하세요");
				menu = sc.nextInt();
				switch(menu) {
				case 0:
					flag=0;
					break;
					
				case 1:
					//ResultSet
					ResultSet rs1=null;
					
					//회원정보 받기
					System.out.println("이름을 입력하세요: ");
					String CustomerName = sc.next();
					System.out.println("주소를 입력하세요: ");
					String Address = sc.next();
					System.out.println("사는 도시를 입력하세요: ");
					String City = sc.next();
					System.out.println("우편 번호를 입력하세요: ");
					int PostalCode = sc.nextInt();
					System.out.println("사는 나라를 입력하세요: ");
					String Country = sc.next();
					
					pStmt1.setString(1, CustomerName);
					pStmt1.setString(2, Address);
					pStmt1.setString(3, City);
					pStmt1.setInt(4, PostalCode);
					pStmt1.setString(5, Country);
					pStmt1.executeUpdate();
					
					//완료 표시 + 가입된 회원 정보 보여주기
					System.out.println("회원 가입 완료!");
					rs1 = stmt.executeQuery("select * from customers where CustomerID >= all(select CustomerID from customers)");
					displayResultSetWithColumnNames(rs1);
					
					rs1.close();
					break;
					
				case 2: 
					//ResultSet
					ResultSet rs2=null;
					
					//CustomerID 받기
					System.out.println("CustomerID를 입력하세요: ");
					int CustomerID = sc.nextInt();
					pStmt2.setInt(1, CustomerID);
					
					//SQL 실행
					rs2 = pStmt2.executeQuery();
					
					//읽어오기
					displayResultSetWithColumnNames(rs2);
					
					rs2.close();
					break;
					
				case 3:
					//ResultSet
					ResultSet rs3=null;
					
					//ProductID 받기
					System.out.println("ProductID를 입력하세요: ");
					int ProductID = sc.nextInt();
					pStmt3.setInt(1, ProductID);
					
					//SQL 실행
					rs3 = pStmt3.executeQuery();
					
					//읽어오기
					displayResultSetWithColumnNames(rs3);

					rs3.close();
					break;
					
				case 4:
					//ResultSet
					ResultSet rs4=null;
					
					//SQL 실행
					rs4 = stmt.executeQuery("select p.ProductID as ProductID, round(sum(o.Quantity*p.Price),2) as total_sales from orderdetails o join product p on o.ProductID=p.ProductID group by ProductID");
					
					//읽어오기
					displayResultSetWithColumnNames(rs4);
					
					rs4.close();
					break;
					
				case 5:
					//ResultSEt
					ResultSet rs5=null;
					
					//수정할 CustomerID 받기
					System.out.println("CustomerID를 입력하세요: ");
					CustomerID = sc.nextInt();
					
					//수정할 정보 입력 받기
					System.out.println("변경할 이름을 입력하세요: ");
					CustomerName = sc.next();
					System.out.println("변경할 주소를 입력하세요: ");
					Address = sc.next();
					System.out.println("변경할 사는 도시를 입력하세요: ");
					City = sc.next();
					System.out.println("변경할 우편 번호를 입력하세요: ");
					PostalCode = sc.nextInt();
					System.out.println("변경할 사는 나라를 입력하세요: ");
					Country = sc.next();
					
					pStmt5.setString(1, CustomerName);
					pStmt5.setString(2, Address);
					pStmt5.setString(3, City);
					pStmt5.setInt(4, PostalCode);
					pStmt5.setString(5, Country);
					pStmt5.setInt(6, CustomerID);
					pStmt5.executeUpdate();
					//완료 표시 + 수정된 회원 정보 보여주기
					System.out.println("회원 정보 수정 완료!!");
					pStmt5_1.setInt(1, CustomerID);
					rs5 = pStmt5_1.executeQuery();
					displayResultSetWithColumnNames(rs5);
					
					rs5.close();
					break;
					
				case 6:
					//CustomerID 받기
					System.out.println("CustomerID를 입력하세요: ");
					CustomerID = sc.nextInt();
					pStmt6.setInt(1, CustomerID);
					
					//SQL 실행
					int res = pStmt6.executeUpdate();
					
					//탈퇴 여부 확인
					if(res>0) {
						System.out.println("탈퇴 완료!");
					}
					break;
		
					
			}
			}
			stmt.close();
			pStmt1.close();
			pStmt2.close();
			pStmt3.close();
			pStmt5.close();
			pStmt5_1.close();
			pStmt6.close();
			conn.close();
			sc.close();
		}
		catch(SQLException e) {
			System.out.println("SQLException: "+ e);
		}
	}
	private static void displayResultSetWithColumnNames(ResultSet resultSet) throws SQLException {
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();

        // Print column names
		for (int i = 1; i <= columnCount; i++) {
			System.out.print(metaData.getColumnName(i) + "\t");
       		 }
		System.out.println();
		//선 추가
		System.out.println("\n-------------------------------------------------------------------------------");
        // Print data
		while (resultSet.next()) {
		for (int i = 1; i <= columnCount; i++) {
			System.out.print(resultSet.getString(i) + "\t");
		}
		System.out.println();
        }
    }


}