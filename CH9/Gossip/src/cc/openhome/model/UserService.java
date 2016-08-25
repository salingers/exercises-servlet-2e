package cc.openhome.model;

import java.util.*;
import java.io.*;

public class UserService {
    private LinkedList<Blah> newest = new LinkedList<Blah>();
    private AccountDAO accountDAO;
    private BlahDAO blahDAO;
    
    public UserService(String USERS, 
            AccountDAO userDAO, BlahDAO blahDAO) {
        this(userDAO, blahDAO);
    }
    
    public UserService(AccountDAO userDAO, BlahDAO blahDAO) {
        this.accountDAO = userDAO;
        this.blahDAO = blahDAO;
    }

    public boolean isUserExisted(Account account) {
        return accountDAO.isUserExisted(account);
    }
    
    public void add(Account account) {
        accountDAO.addAccount(account);
    }

    public boolean checkLogin(Account account) {
        if (account.getName() != null && account.getPassword() != null) {
            Account storedAcct = accountDAO.getAccount(account); 
            return storedAcct != null && 
                   storedAcct.getPassword().equals(account.getPassword()); 
        }
        return false;
    }
    
    private class DateComparator implements Comparator<Blah> {
        @Override
        public int compare(Blah b1, Blah b2) {
            return -b1.getDate().compareTo(b2.getDate());
        }
    }
    
    private DateComparator comparator = new DateComparator();
    
    public List<Blah> getBlahs(Blah blah) {
        List<Blah> blahs = blahDAO.getBlahs(blah);
        Collections.sort(blahs, comparator);
        return blahs;
    }
    
    public void addBlah(Blah blah) {
        blahDAO.addBlah(blah);
        newest.addFirst(blah);
        if(newest.size() > 20) {
            newest.removeLast();
        }
    }
   
    public void deleteBlah(Blah blah) {
        blahDAO.deleteBlah(blah);
        newest.remove(blah);
    }

    public List<Blah> getNewest() {
        return newest;
    }   
}
