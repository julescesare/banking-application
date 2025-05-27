package Models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


import classes.User;

public class UserDAO extends AbstractConnectionDAO implements IDAO<User>{

	Set<Date> datesBase = new TreeSet<>();
	
	public void add(User object) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="insert into  utilisateur(nom,motDePasse) values (?,?) ";
		
		try {
			
			pst = connection.prepareStatement(sql);
			
			
			pst.setString(1,object.getNom());
			pst.setString(5, object.getMotDePasse());
			
			System.out.println("Requete insert succes");
			
			 pst.executeUpdate();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		

		
	}
	public User getOneByNum(String nom) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="select * from utilisateur where nom =?";
		
		try {
			
			pst = connection.prepareStatement(sql);
			pst.setString(1, nom);
			System.out.println("Requete verife succes");
			rs = pst.executeQuery();
			

			if(rs.next()) {
				User cptg= new User(rs.getInt("id"),rs.getString("nom"),
						rs.getString("motDePasse"));
				return cptg;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(int id) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="delete from utilisateur where id =?";
		
		try {
			
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("Requete delete succes");
			 pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void update(int id,Double valeur) {
	
		
	}
	@Override
	public User getOne(int id) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="select * from utilisateur where id =?";
		
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("Requete id succes");
			rs = pst.executeQuery();
			

			if(rs.next()) {
				User cptg= new User(rs.getInt("id"),rs.getString("nom"),
						rs.getString("motDePasse"));
				return cptg;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> getAll() {
		
		List<User> compteEpgne = new ArrayList<User>();
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="SELECT * FROM utilisateur";
		
		try {
			pst = connection.prepareStatement(sql);
			System.out.println("Requete succes");
			rs = pst.executeQuery();
			

			while(rs.next()) {
				User cptg= new User(rs.getInt("id"),rs.getString("nom"),
						rs.getString("motDePasse"));
				
				
				
				compteEpgne.add(cptg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return compteEpgne;
		
	
	}
	public int nombreCompte() {
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    int count = 0;

	    try {
	        String sql = "SELECT COUNT(*) FROM utilisateur";
	        pst = connection.prepareStatement(sql);
	        
	        System.out.println("Requete count succes");

	        rs = pst.executeQuery();

	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (pst != null) {
	                pst.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return count;
	}
	
	
	public int nombreCompteParDate(Date date) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        recupDate();
        Set<Date> dates = getDatesBase();

        try {
            String sql = "SELECT COUNT(*) FROM utilisateur WHERE date = ?";
            pst = connection.prepareStatement(sql);
            pst.setDate(1, date);
            rs = pst.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    public void recupDate() {
       
    }

    public Set<Date> getDatesBase() {
        return datesBase;
    }

    public void setDatesBase(Set<Date> datesBase) {
        this.datesBase = datesBase;
    }

   

	/*public static void main(String[] args){
		UserDAO cd = new UserDAO();
		//User cp = new User(0,"123-000-000","Joint",1000000.0,12);
		
		 //User	c = cd.getOneByNum("123-888-666");
		
		  // System.out.print(c.getNumeroCompte2()+ c.getId());
		/*cd.recupDate();
        Set<Date> dates = cd.getDatesBase();

        for (Date date : dates) {
            int count = cd.nombreCompteParDate(date);
            System.out.println("Date: " + date + ", Nombre de comptes: " + count);
        }
        
        System.out.println(cd.getOneByNum("Jules").getMotDePasse());
	}*/

}
