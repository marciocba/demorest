package com.teslusko.demorest;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PessoaRepository {
	//List<Pessoa> pessoas;
	Connection con=null;
	public PessoaRepository() {
	
/*		this.pessoas = new ArrayList<>();
		Pessoa a1=new Pessoa();
		a1.setId(1);
		a1.setAname("Preto");
		a1.setPoints(10);
		
		Pessoa a2=new Pessoa();
		a2.setId(2);
		a2.setAname("Marcio COrrea");
		a2.setPoints(100);
		
		this.pessoas.add(a1);
		this.pessoas.add(a2);
*/	
		String driver="com.mysql.jdbc.Driver";//"com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/restdb";
		String userName="root";
		String password="123456";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e1);
		}
try {
			
			//System.out.println("antes...okay");
			con=DriverManager.getConnection(url, userName, password);
			//System.out.println("depois...okay");
			//return conn;//DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("errro!");
			//relancando a exception como runtime
			throw new RuntimeException(e);
			//e.printStackTrace();
		}
		//con=DriverManager.getConnection(url);
	
	}


	public List<Pessoa> getPessoas() {
		List<Pessoa> pessoas=new ArrayList<>();
		String sql="select * from pessoa";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				Pessoa p=new Pessoa();
				p.setId(rs.getInt("id"));
				p.setAname(rs.getString("name"));
				p.setPoints(rs.getInt("points"));
				pessoas.add(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		return pessoas;
	}
	/*
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	*/
	public Pessoa getPessoa(int id) {
		
		Pessoa p=null;
		String sql="select * from pessoa where id="+id;
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				p=new Pessoa();
				p.setId(rs.getInt("id"));
				p.setAname(rs.getString("name"));
				p.setPoints(rs.getInt("points"));
				return p;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

/*		for(Pessoa a:pessoas) {
			if (a.getId()==id) return a;
		}
*/		return p;
		
	}

	public void create(Pessoa a1) {
		// TODO Auto-generated method stub
		//pessoas.add(a1);
		String sql="insert into pessoa values (?,?,?)";
		try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setString(2, a1.getAname());
			st.setInt(3, a1.getPoints());
			st.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

	}

	public void updade(Pessoa a1) {
		// TODO Auto-generated method stub
		//pessoas.add(a1);
		String sql="update pessoa set name=?, points=? where id=?";
		try {
			PreparedStatement st=con.prepareStatement(sql);
			
			st.setString(1, a1.getAname());
			st.setInt(2, a1.getPoints());
			st.setInt(3, a1.getId());
			st.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

	}


	public void delete(int id) {
		String sql="delete from pessoa where id=?";
		try {
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}

}
