package Models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import classes.CompteCourant;
import classes.CompteEpargne;

public class CompteCourantDAO extends AbstractConnectionDAO implements IDAO<CompteCourant>{

	Set<Date> datesBase = new TreeSet<>();	
	public void add(CompteCourant object) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="insert into  CompteCourant(numeroDucompte,typeCompte,valeur,date) values (?,?,?,?) ";
		
		try {
			
			pst = connection.prepareStatement(sql);
			
			pst.setString(1, object.getNumeroDuCompte());
			pst.setString(2,object.getTypeCompte());
			pst.setDouble(3,object.getValeur());
			pst.setDate(4, Date.valueOf(object.getDate()));
		
			
			System.out.println("Requete insert succes");
			
			 pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

	public void update(int id,Double valeur) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="UPDATE CompteCourant SET valeur = ? where id =?";
		
		try {
			
			pst = connection.prepareStatement(sql);
			pst.setDouble(1, valeur);
			pst.setDouble(2, id);
			System.out.println("Requete modif succes");
			 pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void delete(int id) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="delete from CompteCourant where id =?";
		
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

	@Override
	public CompteCourant getOne(int id) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="select * from CompteCourant where id =?";
		
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("Requete id succes");
			rs = pst.executeQuery();
			

			if(rs.next()) {
				CompteCourant cptg= new CompteCourant(rs.getInt("id"),rs.getString("numeroDuCompte")
						,rs.getString("typeCompte"),
						rs.getDouble("valeur"));
				
				cptg.setDate(rs.getDate("date").toLocalDate());
				
				return cptg;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	public CompteCourant getOneByNum(String num) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="select * from CompteCourant where numeroDuCompte =?";
		
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, num);
			System.out.println("Requete find succes");
			rs = pst.executeQuery();
			

			if(rs.next()) {
				CompteCourant cptg= new CompteCourant(rs.getInt("id"),rs.getString("numeroDuCompte")
						,rs.getString("typeCompte"),
						rs.getDouble("valeur"));
				
				cptg.setDate(rs.getDate("date").toLocalDate());
				
				return cptg;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public List<CompteCourant> getAll() {
		
		List<CompteCourant> compteEpgne = new ArrayList<CompteCourant>();
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="SELECT * FROM CompteCourant";
		
		try {
			pst = connection.prepareStatement(sql);
			System.out.println("Requete succes");
			rs = pst.executeQuery();
			

			while(rs.next()) {
				CompteCourant cptg= new CompteCourant(rs.getInt("id"),rs.getString("numeroDuCompte")
						,rs.getString("typeCompte"),
						rs.getDouble("valeur"));
				
				cptg.setDate(rs.getDate("date").toLocalDate());
				
				compteEpgne.add(cptg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return compteEpgne;
		
	
	}

	@Override
	public int nombreCompte() {
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    int count = 0;

	    try {
	        String sql = "SELECT COUNT(*) FROM CompteCourant";
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
            String sql = "SELECT COUNT(*) FROM CompteCourant WHERE date = ?";
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
        List<CompteCourant> comptesJoin = this.getAll();

        for (CompteCourant cj : comptesJoin) {
            datesBase.add(Date.valueOf(cj.getDate()));
        }
    }
    
    public Double montantTotalParDate(Date date) {
    	
    	 PreparedStatement pst = null;
         ResultSet rs = null;
         Double prixTotal = 0.0;
         recupDate();
         Set<Date> dates = getDatesBase();

         try {
             String sql = "SELECT sum(valeur) FROM CompteCourant WHERE date = ?";
             pst = connection.prepareStatement(sql);
             pst.setDate(1, date);
             rs = pst.executeQuery();

             if (rs.next()) {
                 prixTotal = rs.getDouble(1);
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
         return prixTotal;
    }
	/*public static void main(String[] args){
			CompteCourantDAO cd = new CompteCourantDAO();
			/*CompteCourant cp = new CompteCourant(0,"123-455-345","Joint",1000000.0);
			
			cd.add(cp);
			Date dat = Date.valueOf("2024-05-30");
			System.out.println(cd.montantTotalParDate(dat));
		}*/

	public Set<Date> getDatesBase() {
		return datesBase;
	}

	public void setDatesBase(Set<Date> datesBase) {
		this.datesBase = datesBase;
	}

	

	
}
