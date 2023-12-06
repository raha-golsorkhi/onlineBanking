import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    public Bank(String name)
    {
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }
    public String getName()
    {
        return this.name;
    }
    public String getNewUserUUID()
    {
        String uuid;
        Random random = new Random();
        int len = 8;
        boolean nonUnique;

        /*
        generate new UUIDs and ensure it's unique.
         */
        do{
            uuid = "";
            for (int i = 0; i < len; i++)
            {

                uuid += ((Integer)random.nextInt(10)).toString(); //bound of 0-9
            }

            nonUnique = false; //nonUnique is flagged as False
            for (User u : this.users)
                /* This loop iterates through each User object(u) in the users arraylist,
             and within the loop, accesses and manipulates the properties or perform operations related to each user.
            */
            {
                if(uuid.compareTo(u.getUUID())== 0)
                //checks whether the generated UUID is equal to the UUID of the current user (u.getUUID())
                {
                    nonUnique = true;
                    break;
                }
            }

        }while(nonUnique);

        return uuid;

    }
    //generate unique IDs for each account
    public String getNewAccontUUID()
    {
        String uuid;
        Random random = new Random();
        int len = 10;
        boolean nonUnique;

        do{
            uuid = "";
            for (int c = 0; c<len; c++)
            {

                uuid+= ((Integer)random.nextInt(10)).toString();
            }

            nonUnique = false;
            for (Account a : this.accounts)
            /* This loop iterates through each Account object(a) in the accounts arraylist,
             and within the loop, accesses and manipulates the properties or perform operations related to each account.
            */
            {
                if(uuid.compareTo(a.getUUID())== 0)
                //checks whether the generated UUID is equal to the UUID of the existing user (u.getUUID())
                {
                    nonUnique = true;
                    break;
                }
            }

        }while(nonUnique);

        return uuid;
    }

    public void addAccount(Account onAcct)
    {
        this.accounts.add(onAcct);
    }

    public User addUser(String firstName, String lastName , String pin)
    {
        //Create a new user object and add it to our list

        User newUser = new User(firstName, lastName,pin, this);
        this.users.add(newUser);
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    public User userLogin(String userID, String pin)
    {
        for(User u : this.users)
        {
            //check user ID is correct
            if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin))
            {
                return u;
            }
        }
        //if we haven't found the pin
        return null;
    }
}
