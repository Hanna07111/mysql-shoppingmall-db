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
			//jdbc ����
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
	
			
			//�޴� ���� ȭ��
			Scanner sc = new Scanner(System.in);
			int flag=1;
			int menu;
			while(flag==1){
				System.out.println("\n=====================");
				System.out.println("        MENU");
				System.out.println("=====================");
				System.out.println("1. Insert - ȸ�� ����");
				System.out.println("2. Select1 - ���� ��ǰ ��� ��ȸ");
				System.out.println("3. Select2 - ��ǰ�� ��� ����� ��ȸ");
				System.out.println("4. Select3 - ��ǰ�� �Ǹž� ��ȸ");
				System.out.println("5. Update - ���� ���� ����");
				System.out.println("6. Delete - ȸ�� Ż��");
				System.out.println("0. Exit");
				System.out.println("=====================");
				System.out.print("���Ͻô� �޴��� ���� �ϼ���");
				menu = sc.nextInt();
				switch(menu) {
				case 0:
					flag=0;
					break;
					
				case 1:
					//ResultSet
					ResultSet rs1=null;
					
					//ȸ������ �ޱ�
					System.out.println("�̸��� �Է��ϼ���: ");
					String CustomerName = sc.next();
					System.out.println("�ּҸ� �Է��ϼ���: ");
					String Address = sc.next();
					System.out.println("��� ���ø� �Է��ϼ���: ");
					String City = sc.next();
					System.out.println("���� ��ȣ�� �Է��ϼ���: ");
					int PostalCode = sc.nextInt();
					System.out.println("��� ���� �Է��ϼ���: ");
					String Country = sc.next();
					
					pStmt1.setString(1, CustomerName);
					pStmt1.setString(2, Address);
					pStmt1.setString(3, City);
					pStmt1.setInt(4, PostalCode);
					pStmt1.setString(5, Country);
					pStmt1.executeUpdate();
					
					//�Ϸ� ǥ�� + ���Ե� ȸ�� ���� �����ֱ�
					System.out.println("ȸ�� ���� �Ϸ�!");
					rs1 = stmt.executeQuery("select * from customers where CustomerID >= all(select CustomerID from customers)");
					displayResultSetWithColumnNames(rs1);
					
					rs1.close();
					break;
					
				case 2: 
					//ResultSet
					ResultSet rs2=null;
					
					//CustomerID �ޱ�
					System.out.println("CustomerID�� �Է��ϼ���: ");
					int CustomerID = sc.nextInt();
					pStmt2.setInt(1, CustomerID);
					
					//SQL ����
					rs2 = pStmt2.executeQuery();
					
					//�о����
					displayResultSetWithColumnNames(rs2);
					
					rs2.close();
					break;
					
				case 3:
					//ResultSet
					ResultSet rs3=null;
					
					//ProductID �ޱ�
					System.out.println("ProductID�� �Է��ϼ���: ");
					int ProductID = sc.nextInt();
					pStmt3.setInt(1, ProductID);
					
					//SQL ����
					rs3 = pStmt3.executeQuery();
					
					//�о����
					displayResultSetWithColumnNames(rs3);

					rs3.close();
					break;
					
				case 4:
					//ResultSet
					ResultSet rs4=null;
					
					//SQL ����
					rs4 = stmt.executeQuery("select p.ProductID as ProductID, round(sum(o.Quantity*p.Price),2) as total_sales from orderdetails o join product p on o.ProductID=p.ProductID group by ProductID");
					
					//�о����
					displayResultSetWithColumnNames(rs4);
					
					rs4.close();
					break;
					
				case 5:
					//ResultSEt
					ResultSet rs5=null;
					
					//������ CustomerID �ޱ�
					System.out.println("CustomerID�� �Է��ϼ���: ");
					CustomerID = sc.nextInt();
					
					//������ ���� �Է� �ޱ�
					System.out.println("������ �̸��� �Է��ϼ���: ");
					CustomerName = sc.next();
					System.out.println("������ �ּҸ� �Է��ϼ���: ");
					Address = sc.next();
					System.out.println("������ ��� ���ø� �Է��ϼ���: ");
					City = sc.next();
					System.out.println("������ ���� ��ȣ�� �Է��ϼ���: ");
					PostalCode = sc.nextInt();
					System.out.println("������ ��� ���� �Է��ϼ���: ");
					Country = sc.next();
					
					pStmt5.setString(1, CustomerName);
					pStmt5.setString(2, Address);
					pStmt5.setString(3, City);
					pStmt5.setInt(4, PostalCode);
					pStmt5.setString(5, Country);
					pStmt5.setInt(6, CustomerID);
					pStmt5.executeUpdate();
					//�Ϸ� ǥ�� + ������ ȸ�� ���� �����ֱ�
					System.out.println("ȸ�� ���� ���� �Ϸ�!!");
					pStmt5_1.setInt(1, CustomerID);
					rs5 = pStmt5_1.executeQuery();
					displayResultSetWithColumnNames(rs5);
					
					rs5.close();
					break;
					
				case 6:
					//CustomerID �ޱ�
					System.out.println("CustomerID�� �Է��ϼ���: ");
					CustomerID = sc.nextInt();
					pStmt6.setInt(1, CustomerID);
					
					//SQL ����
					int res = pStmt6.executeUpdate();
					
					//Ż�� ���� Ȯ��
					if(res>0) {
						System.out.println("Ż�� �Ϸ�!");
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
		//�� �߰�
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