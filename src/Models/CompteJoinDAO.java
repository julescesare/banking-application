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
import classes.CompteJoin;

public class CompteJoinDAO extends AbstractConnectionDAO implements IDAO<CompteJoin>{

	Set<Date> datesBase = new TreeSet<>();
	
	public void add(CompteJoin object) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="insert into  CompteJoin(typeCompte,valeur,date,numeroComptePremiere,numeroCompteSecond) values (?,?,?,?,?) ";
		
		try {
			
			pst = connection.prepareStatement(sql);
			
			
			pst.setString(1,object.getTypeCompte());
			pst.setDouble(2,object.getValeur());
			pst.setDate(3, Date.valueOf(object.getDate()));
			pst.setString(4, object.getNumeroDuCompte());
			pst.setString(5, object.getNumeroCompte2());
			
			System.out.println("Requete insert succes");
			
			 pst.executeUpdate();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		

		
	}
	public CompteJoin getOneByNum(String num) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="select * from CompteJoin where numeroComptePremiere =? OR numeroCompteSecond =? ";
		
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, num);
			pst.setString(2,num);
			System.out.println("Requete find succes");
			rs = pst.executeQuery();
			

			if(rs.next()) {
				CompteJoin cptg= new CompteJoin(rs.getInt("id"),rs.getString("numeroComptePremiere"),
						rs.getString("numeroCompteSecond")
						,rs.getString("typeCompte"),
						rs.getDouble("valeur"));
				cptg.setDate(rs.getDate("date").toLocalDate());
				
				return cptg;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void delete(int id) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="delete from CompteJoin where id =?";
		
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
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="UPDATE CompteJoin SET valeur = ? where id =?";
		
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
	public CompteJoin getOne(int id) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="select * from CompteJoin where id =?";
		
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("Requete id succes");
			rs = pst.executeQuery();
			

			if(rs.next()) {
				CompteJoin cptg= new CompteJoin(rs.getInt("id"),rs.getString("numeroComptePremiere"),
						rs.getString("numeroCompteSecond")
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
	public List<CompteJoin> getAll() {
		
		List<CompteJoin> compteEpgne = new ArrayList<CompteJoin>();
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="SELECT * FROM CompteJoin";
		
		try {
			pst = connection.prepareStatement(sql);
			System.out.println("Requete succes");
			rs = pst.executeQuery();
			

			while(rs.next()) {
				CompteJoin cptg= new CompteJoin(rs.getInt("id"),rs.getString("numeroComptePremiere"),
						rs.getString("numeroCompteSecond")
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
	public int nombreCompte() {
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    int count = 0;

	    try {
	        String sql = "SELECT COUNT(*) FROM CompteJoin";
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
            String sql = "SELECT COUNT(*) FROM CompteJoin WHERE date = ?";
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
        List<CompteJoin> comptesJoin = this.getAll();

        for (CompteJoin cj : comptesJoin) {
            datesBase.add(Date.valueOf(cj.getDate()));
        }
    }

    public Set<Date> getDatesBase() {
        return datesBase;
    }

    public void setDatesBase(Set<Date> datesBase) {
        this.datesBase = datesBase;
    }

    public Double montantTotalParDate(Date date) {
    	
   	 PreparedStatement pst = null;
        ResultSet rs = null;
        Double prixTotal = 0.0;
        recupDate();
        Set<Date> dates = getDatesBase();

        try {
            String sql = "SELECT sum(valeur) FROM CompteJoin WHERE date = ?";
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
		CompteJoinDAO cd = new CompteJoinDAO();
		//CompteJoin cp = new CompteJoin(0,"123-000-000","Joint",1000000.0,12);
		
		 //CompteJoin	c = cd.getOneByNum("123-888-666");
		
		  // System.out.print(c.getNumeroCompte2()+ c.getId());
		cd.recupDate();
        Set<Date> dates = cd.getDatesBase();

        for (Date date : dates) {
            int count = cd.nombreCompteParDate(date);
            System.out.println("Date: " + date + ", Nombre de comptes: " + count);
        }
	}*/

}
