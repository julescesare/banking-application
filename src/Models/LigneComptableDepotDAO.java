package Models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import classes.LigneComptableDepot;

public class LigneComptableDepotDAO extends AbstractConnectionDAO implements IDAO<LigneComptableDepot> {

	Set<Date> datesBase = new TreeSet<>();
	
	public Set<Date> getDatesBase() {
		return datesBase;
	}

	public void setDatesBase(Set<Date> datesBase) {
		this.datesBase = datesBase;
	}

	@Override
	public void add(LigneComptableDepot object) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="insert into LigneComptableDepotRetrait(theme,moyenPayement,motif,valeur,date,numeroCompte) values (?,?,?,?,?,?) ";
		
		try {
			
			pst = connection.prepareStatement(sql);
			
			pst.setString(6, object.getNumeroCompte());
			pst.setString(1,object.getTheme());
			pst.setString(2, object.getMoyenPayement());
			pst.setString(3, object.getMotif());
			pst.setDouble(4,object.getValeur());
			pst.setDate(5, Date.valueOf(object.getDate()));
			
			
			System.out.println("Requete insert succes ");
			
			
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
		String sql="delete from LigneComptableDepotRetrait where id =?";
		
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
	public LigneComptableDepot getOne(int id) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="select * from LigneComptableDepotRetrait where id =?";
		
		try {
			pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			System.out.println("Requete id succes");
			rs = pst.executeQuery();
			

			if(rs.next()) {
				LigneComptableDepot cptg= new LigneComptableDepot(rs.getInt("id"),rs.getString("theme")
						,rs.getString("moyenPayement"),rs.getDouble("valeur"),
						rs.getString("motif"),rs.getString("numeroCompte"));
				
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
	public List<LigneComptableDepot> getAll() {
		
		List<LigneComptableDepot> ligneComptableDepot = new ArrayList<LigneComptableDepot>();
		PreparedStatement pst = null;
		ResultSet rs;
		String sql="SELECT * FROM LigneComptableDepotRetrait";
		
		try {
			pst = connection.prepareStatement(sql);
			System.out.println("Requete succes");
			rs = pst.executeQuery();
			

			while(rs.next()) {
				LigneComptableDepot ligne=  new LigneComptableDepot(rs.getInt("id"),rs.getString("theme")
						,rs.getString("moyenPayement"),rs.getDouble("valeur"),
						rs.getString("motif"),rs.getString("numeroCompte"));
				
				ligne.setDate(rs.getDate("date").toLocalDate());
				
				ligneComptableDepot .add(ligne);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ligneComptableDepot;
		
	
	}

	public int nombreCompte() {
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    int count = 0;

	    try {
	        String sql = "SELECT COUNT(*) FROM LigneComptableDepotRetrait";
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
            String sql = "SELECT COUNT(*) FROM LigneComptableDepotRetrait WHERE date = ?";
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
        List<LigneComptableDepot> comptesJoin = this.getAll();

        for (LigneComptableDepot cj : comptesJoin) {
            datesBase.add(Date.valueOf(cj.getDate()));
        }
    }
    
    
    public int nombreTransactionsDepot(Date date) {
    	PreparedStatement pst = null;
	    ResultSet rs = null;
	    int count = 0;

	    try {
	        String sql = "SELECT COUNT(*) FROM LigneComptableDepotRetrait where theme = ? AND date = ?";
	        pst = connection.prepareStatement(sql);
	        pst.setString(1,"Retrait");
	        pst.setDate(2, date);
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
    
    public int nombreTransactionsRetrait(Date date) {
    	PreparedStatement pst = null;
	    ResultSet rs = null;
	    int count = 0;

	    try {
	        String sql = "SELECT COUNT(*) FROM LigneComptableDepotRetrait where theme = ? AND date = ?";
	        pst = connection.prepareStatement(sql);
	        pst.setString(1,"Retrait");
	        pst.setDate(2, date);
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
}
