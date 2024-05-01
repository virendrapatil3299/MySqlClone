package ServletClass;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.Iterator;
import Model.Table;

@WebServlet(urlPatterns = {"/createTable","/view","/table"})
public class ServletClass extends HttpServlet{
	
	 public static ArrayList<Table> tableList;
	 public static ArrayList<String> ShowTableList;
	 public static ArrayList<Table> displayTables;
	 Connection connection;
	 
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/test","root","Vishal123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path=req.getServletPath();
		switch(path) {
		case "/createTable":
			try {
				table(req,resp);
			}  catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "/view":
			try {
				viewTable(req,resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case "/table":
			Display(req,resp);
		}
	}

	private void Display(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		displayTables = new ArrayList<>();
		String tableName = req.getParameter("tname");
		System.out.println(tableName);
		Table t2 ;
		String displayTable="DESC "+tableName;
		try {
			Statement pstmt=connection.createStatement();
			ResultSet rs=pstmt.executeQuery(displayTable);
			while(rs.next()) {
				t2=new Table();
				t2.setColumnName(rs.getString(1));
				t2.setDefaults(rs.getString(5));
				displayTables.add(t2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd=req.getRequestDispatcher("DisplayTable.jsp");
		req.setAttribute("DISPLAYLIST", displayTables);
		rd.forward(req, resp);
		
	}
	private void viewTable(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
		String query = "show tables";
		ShowTableList = new ArrayList<>();
		Statement stmt;
		stmt=connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next())
		{
			ShowTableList.add(rs.getString(1));
		}
		
		
		RequestDispatcher rd=req.getRequestDispatcher("viewTable.jsp");
		req.setAttribute("LIST", ShowTableList);
		try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void table(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
		String tableName = req.getParameter("tableName");
		String[] name=req.getParameterValues("ColumnName");
		String[] type=req.getParameterValues("Options");
		String[] length=req.getParameterValues("Length");
		String[] defaultVal=req.getParameterValues("Default");
		String primaryKey = req.getParameter("pk");
		String[] notNull = req.getParameterValues("notNull");
		Table t;
		tableList=new ArrayList<>();
		
		for(int i=0;i<name.length;i++)
		{
			if(name[i].isEmpty()) {
				break;
			}
			else {
				String pk = "";
				String notnull = "";
				if(req.getParameterValues(notnull)!=null)
				{
					for(int j=0;j<notNull.length;j++)
					{
						if(Integer.parseInt(notNull[j])==j)
						{
							notnull=" NOT NULL ";
						}
					}
				}
				if(primaryKey != null && (Integer.parseInt(primaryKey)==i))
				{
					pk="PRIMARY KEY";
				}
				else
				{
					pk="null";
				}
				t=new Table(name[i],type[i],length[i],defaultVal[i],pk,notnull);
				tableList.add(t);
			}
		}
		String Query = "CREATE TABLE "+tableName+" (";
		for(Table t1:tableList)
		{
			if(tableList.indexOf(t1)!=0)
			{
				Query+=" , ";
			}
			Query+=t1.getColumnName()+" "+t1.getOptions()+" ";
			if((t1.getOptions().equals("varchar") || t1.getOptions().equals("char"))&& t1.getLength()!="")
			{
				Query+="("+t1.getLength()+") ";
			}
			if(t1.getDefaults()!="")
			{
				Query+=" DEFAULT "+t1.getDefaults()+" ";
			}
			if(t1.getPrimaryKey().equals("PRIMARY KEY"))
			{
				Query+=" PRIMARY KEY ";
			}
			if(t1.getNotNull()!="")
			{
				Query+=" NOT NULL ";
			}
			
		}
		Query +=" );";
		System.out.println(Query);
		Statement stmt = connection.createStatement();
		int count = stmt.executeUpdate(Query);
		
		
		PrintWriter pw=resp.getWriter();
		pw.print("<html>");
		pw.print("<head>"
				+ "<title></title>"
				+ "</head>"
				+ "<body >"
				+ "<div  style=' padding-top: 40px'>");
		pw.print("<h1 style='margin-left: 40%;'>Table Created </h1>");
		pw.print("<a href='view'><h2 style='margin-left: 40%;'>Display Table</h2>\"</a>");
		pw.print("</div>"
				+ "</body>"
				+ "</html>");
	}
}