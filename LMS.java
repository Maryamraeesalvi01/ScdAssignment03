import java.util.*;
import java.io.*;

interface configuration {
    void displayinfo();
}

class item implements configuration {
    public String title;
    public boolean isborrowed;
    public int popularitycount;
    public static int id;
    public int cost;

    public item(String title, int popularitycount, int cost) {
        this.title = title;
        this.popularitycount = popularitycount;
        this.cost = cost;
    }

    public String gettitle() {
        return title;
    }

    public int getcost() {
        return cost;
    }

    // public boolean isborrowed()
    // {
    // return true;
    // }
    public int getid() {
        return id;
    }

    public int getpoularitycount() {
        return popularitycount;
    }

    public void settitle(String title) {
        this.title = title;
    }

    // public void setid(int id)
    // {
    // this.id=id;
    // }
    public void setcost(int cost) {
        this.cost = cost;
    }

    public void setpopularitycount(int popularitycount) {
        this.popularitycount = popularitycount;
    }

    public void displayinfo() {
        System.out.println("Title is : " + title + " , cost is : " + cost + " , id = " + id
                + " and popularity count is : " + popularitycount);
    }
    
}

class Book extends item {

    private String aurthor;
    private int year;
    private int bID;

    public Book(String title, int popularitycount, int cost, String aurthor, int year) {
        super(title, popularitycount, cost);
        this.aurthor = aurthor;
        this.year = year;
        bID = bID + (id + 1);

    }
    public int getId()
    {
        return bID;
    }
    public String getTitle()
    {
        return title;
    }
    public void setaurthor(String aurthor) {
        this.aurthor = aurthor;
    }

    public void setyear(int year) {
        this.year = year;
    }

    public String getaurthor() {
        return aurthor;
    }

    public int getyear() {
        return year;
    }
    public int getpopularitycount()
    {
        return popularitycount;
    }
    public int getCost()
    {
        return cost;
    }
    public void displayinfo() {
        super.displayinfo();
        System.out.println(" by : " + aurthor + "(" + year + ")");
    }

    public void displaysingleitem(item item) {
        item.displayinfo();
    }
}

class magzine extends item {
    private String publisher;
    private List<String> aurthors = new ArrayList<>();

    public magzine(int id, int popularitycount, String title, int cost, String publisher, List<String> aurthors) {
        super(title, popularitycount, cost);
        this.aurthors = aurthors;
        this.publisher = publisher;
    }

    public void setaurthors(List<String> aurthors) {
        this.aurthors = aurthors;
    }
 public int getId()
    {
        return id;
    }
    public String getTitle()
    {
        return title;
    }
    public int getpopularitycount()
    {
        return popularitycount;
    }
    public int getCost()
    {
        return cost;
    }
    public List<String> getaurthors() {
        return aurthors;
    }

    public String getpublisher() {
        return publisher;
    }

    public void setpublisher(String publisher) {
        this.publisher = publisher;
    }

    public void displayinfo() {
        super.displayinfo();
        for (int i = 0; i < aurthors.size(); i++) {
            String aurthor = aurthors.get(i);
            System.out.println(" the aurthors are : " + (i + 1) + ":" + aurthor);
        }
        System.out.println(" publisher : " + publisher);
    }
}

class newspaper extends item {
    private String publisher;
    private String date;

    public newspaper(String title, int popularitycount, int id, int cost, String publisher, String date) {
        super(title, popularitycount, cost);
        this.publisher = publisher;
        this.date = date;
    }
public int getId()
    {
        return id;
    }
    public String getTitle()
    {
        return title;
    }
    public int getpopularitycount()
    {
        return popularitycount;
    }
    public int getCost()
    {
        return cost;
    }
    public void setpublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getpublisher() {
        return publisher;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getdate() {
        return date;
    }

    public void displayinfo() {
        super.displayinfo();
        System.out.println(" publisher :  " + publisher + " ( " + date + ")");
    }
}
 class Borrower {
    private String BorrowerName;
    private List<item> Borrows;

    public Borrower() {
        Borrows = new ArrayList<>();
    }

    public void Borrow_Item(item Item) {
        Scanner in = new Scanner(System.in);
        if (!Item.isborrowed) {
            System.out.println("Enter Borrower's name :");
            String N = in.nextLine();
            this.BorrowerName = N;
            Borrows.add(Item);
            Item.isborrowed = true;
            Item.setpopularitycount(Item.popularitycount + 1);
            System.out.println("Item Borrowed Successfully!");
            return;
        } else {
            System.out.println("Item has already been borrowed!");
            return;
        }
    }

    public String ReturnName() {
        return BorrowerName;
    }
}
class library {
    public  List<item> items;
    public  List<String> BorrowersName;

    public library() {
        items = new ArrayList<>();
    }
public library(String fname)
{
     items = new ArrayList<>();
    BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fname));
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                String Part1 = parts[0].trim();

                if (Part1.equals("1")) {
                    String Part2 = parts[1].trim();
                    String Part3 = parts[2].trim();
                    String Part4 = parts[3].trim();
                    String Part5 = parts[4].trim();
                    String Part6 = parts[5].trim();
                    int part4 = Integer.parseInt(Part4);
                    int part5 = Integer.parseInt(Part5);
                    int part6 = Integer.parseInt(Part6);
                    Book newBook = new Book(Part2, part4,part5, Part3, part6);
                    items.add(newBook);
                }
                // } else if (Part1.equals("3")) {
                //     String Part2 = parts[1].trim();
                //     String Part3 = parts[2].trim();
                //     String Part4 = parts[3].trim();
                //     String Part5 = parts[4].trim();
                //     int part4 = Integer.parseInt(Part4);
                //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                //     LocalDate date = LocalDate.parse(Part5, formatter);
                //     Newspaper newPaper = new Newspaper(Part2, Part3, date, part4);
                //     this.AddItem(newPaper);
                // } else if (Part1.equals("2")) {
                //     String Part2 = parts[1];
                //     List<String> Authorlist = new ArrayList<>();
                //     int start = 2;
                //     for (int i = 0; i < parts.length; i++) {
                //         if (parts[i].endsWith(".")) {
                //             Authorlist.add(parts[i].substring(0, parts[i].length() - 1));
                //             start = i + 1;
                //             break;
                //         }
                //         Authorlist.add(parts[i]);
                //     }
                //     String Part3 = parts[start];
                //     int Part4 = Integer.parseInt(parts[start + 1]);
                //     int Part5 = Integer.parseInt(parts[start + 2]);
                //     Magazine newMag = new Magazine(Part2, Authorlist, Part3, Part4, Part5);
                //     this.AddItem(newMag);
                // }
                line = reader.readLine();
                }
            reader.close();
        }
    
      catch (IOException err) {
            err.printStackTrace();
        }
}
    public  void additem(item item) {
        items.add(item);
    }
public List<item> getItems() {
    List<item> allItems = new ArrayList<>();
    for (int i = 0; i < items.size(); i++) {
        item item = items.get(i);
        allItems.add(item);
    }
    return allItems;
}

    public item getitembyid(int id) {
        for (int i = 0; i < items.size(); i++) {
            item item = items.get(i);
            if (item.getid() == id) {
                return item;
            }
        }
        return null;
    }

    public  void edititem(String title, int year, int id, int cost, int popularitycount) {
        for (int i = 0; i < items.size(); i++) {
            item item = items.get(i);
            if (item.getid() == id) {
                item.settitle(title);
                item.setcost(cost);
                item.setpopularitycount(popularitycount);
                return;
            }
        }
        System.out.println("Item not available with the id : " + id);
    }

    public void deleteitem(int id) {
        for (int i = 0; i < items.size(); i++) {
            item item = items.get(i);
            if (item.getid() == id) {
                item = getitembyid(id);
                items.remove(item);
                return;
            }
        }
        System.out.println("Book not available with the id : " + id);
    }

    public void viewallitems() {
        for (int i = 0; i < items.size(); i++) {
            item item = items.get(i);
            // System.out.println(item);
            item.displayinfo();
        }
    }

    public void Split_String(String Filename) {
        String mainStr = Read_File(Filename);
        String[] splitter = mainStr.split(",");
        String title = splitter[0];
        String aurthor = splitter[1];
        String cost = splitter[2];
        String year = splitter[3];
        String pCount = splitter[4];
        int IntYear = Integer.parseInt(year);
        int intPcount = Integer.parseInt(pCount);
        int intCost = Integer.parseInt(cost);
        Book fileBook = new Book(title, intPcount, intCost, aurthor, IntYear);
        items.add(fileBook);
    }

    public String Read_File(String Filename) {
        try {
            File file = new File("Demo.txt"); // creates a new file instance
            FileReader filereader = new FileReader(file); // reads the file
            BufferedReader br = new BufferedReader(filereader); // creates a buffering character input stream
            StringBuffer sb = new StringBuffer(); // constructs a string buffer with no characters
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line); // appends line to string buffer
                sb.append("\n"); // line feed
            }
            filereader.close(); // closes the stream and release the resources
            System.out.println("Contents of File: ");
            return sb.toString(); // returns a string that textually represents the object
        } catch (IOException e) {
            e.printStackTrace();
            return "IOexception";
        }
    }
    public  void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter("Demo.txt")) {
            for (item item : items) {
                writer.println(itemToDataString(item));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String itemToDataString(item item) {
        // Convert an item to a string format for saving
        if (item instanceof Book) {
            Book book = (Book) item;
            return String.format("Book,%s,%d,%d,%s,%d", book.gettitle(), book.getpoularitycount(),
                    book.getcost(), book.getaurthor(), book.getyear());
        } else if (item instanceof magzine) {
            magzine magzineItem = (magzine) item;
            String aurthors = String.join(",", magzineItem.getaurthors());
            return String.format("Magazine,%s,%d,%d,%s,%s,%s", magzineItem.gettitle(), magzineItem.getpoularitycount(),
                    magzineItem.getcost(), magzineItem.getpublisher(), aurthors);
        } else if (item instanceof newspaper) {
            newspaper newspaperItem = (newspaper) item;
            return String.format("Newspaper,%s,%d,%d,%s,%s,%s", newspaperItem.gettitle(), newspaperItem.getpoularitycount(),
                    newspaperItem.getcost(), newspaperItem.getpublisher(), newspaperItem.getdate());
        } else {
            return ""; // Handle other item types if needed
        }
    }
    private void loadItemsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6) {
                    String itemType = data[0];
                    String title = data[1];
                    int popularitycount = Integer.parseInt(data[2]);
                    int cost = Integer.parseInt(data[3]);
                    int id=Integer.parseInt(data[6]);
                    if ("Book".equals(itemType) && data.length >= 6) {
                        String aurthor = data[4];
                        int year = Integer.parseInt(data[5]);
                        items.add(new Book(title, popularitycount, cost, aurthor, year));
                    } else if ("Magazine".equals(itemType) && data.length >= 7) {
                        String publisher = data[4];
                        List<String> aurthors = Arrays.asList(data[5].split(","));
                        int year = Integer.parseInt(data[6]);
                        items.add(new magzine(id,popularitycount, title, cost, publisher, aurthors));
                    } else if ("Newspaper".equals(itemType) && data.length >= 6) {
                        String publisher = data[4];
                        String date = data[5];
                        items.add(new newspaper(title, popularitycount,id, cost, publisher, date));
                    }
                    // Handle other item types if needed
                }
            }
        } catch (IOException e) 
        {
            e.printStackTrace();
        }

    }

    public void AddItem() {
        Scanner scanner = new Scanner(System.in);
        // System.out.println("Enter Id : ");
        // int id = scanner.nextInt();
        System.out.println("Enter Title : ");
        String title = scanner.nextLine();
        System.out.println("Enter Item Type :");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. Newspaper");
        int itemtype = scanner.nextInt();
        scanner.nextLine();
        switch (itemtype) {
            case 1:
                System.out.println("Enter Aurthor Name : ");
                String aurthor = scanner.nextLine();
                System.out.println("Enter Year : ");
                int year = scanner.nextInt();
                System.out.println("Enter popularity count : ");
                int bookpopularitycount = scanner.nextInt();
                System.out.println("Enter book cost : ");
                int bookcost = scanner.nextInt();
                items.add(new Book(title, bookpopularitycount, bookcost, aurthor, year));
                break;
            case 2:
                List<String> aurthors = new ArrayList<>();
                while (true) {
                    System.out.println("Enter list of aurthors of magzine and 'exit' to end entering in the list : ");
                    String aurthorname = scanner.nextLine();
                    if (aurthorname.equalsIgnoreCase("exit")) {
                        break;
                    }
                    aurthors.add(aurthorname);
                }
                System.out.println("Enter Publisher : ");
                String publisher = scanner.nextLine();
                System.out.println("Enter popularity count : ");
                int magzinepopularitycount = scanner.nextInt();
                System.out.println("Enter magzine cost : ");
                int magzinecost = scanner.nextInt();
                System.out.println("Enter Year : ");
                int yearr = scanner.nextInt();
        items.add(new magzine(magzinepopularitycount, yearr, title, magzinecost, publisher, aurthors));
                break;
            case 3:
                System.out.println("Enter publisher : ");
                String newspaperpublisher = scanner.nextLine();
                System.out.println("Enter date : ");
                String date = scanner.nextLine();
                System.out.println("Enter popularity count : ");
                int newspaperpopularitycount = scanner.nextInt();
                System.out.println("Enter newspaper cost: ");
                int cost = scanner.nextInt();
                System.out.println("Enter Year : ");
                int yearrr = scanner.nextInt();
                items.add(new newspaper(title, newspaperpopularitycount, cost, yearrr, newspaperpublisher, date));
                break;
            default:
                System.out.println("Invalid item type.");
        }
    }

    public void EditItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Item ID to edit: ");
        int editId = scanner.nextInt();
        scanner.nextLine();
        item itemtoedit = getitembyid(editId);
        if (itemtoedit != null) {
            System.out.println("Enetr new title : ");
            String newtitle = scanner.nextLine();
            System.out.println("Enter new cost : ");
            int newcost = scanner.nextInt();
            System.out.println("Enter new popularity count: ");
            int newpopularitycount = scanner.nextInt();
            System.out.println("Enter new year : ");
            int newyear = scanner.nextInt();
            edititem(newtitle, newyear, editId, newcost, newpopularitycount);
        } else {
            System.out.println("Item not available : ");
        }

    }

    public void View_Bor_List() {
        if (!BorrowersName.isEmpty()) {
            for (String names : BorrowersName) {
                System.out.println(names);
            }
        } else {
            System.out.println("Borrowers list is empty");
        }
    }

    public class LMS {
        public static void main(String[] args) {
            library library = new library();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("***Library Management System Menu****");
                System.out.println("1. Add Item");
                System.out.println("2. Edit Item");
                System.out.println("3. Delete Item");
                System.out.println("4. View All Items");
                System.out.println("5. View Item by ID");
                System.out.println("6. Borrow Item");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        library.AddItem();
                        break;
                    case 2:
                        library.EditItem();
                        break;

                    case 3:
                        System.out.println("Enter item id to delete : ");
                        int itemtodelete = scanner.nextInt();
                        library.deleteitem(itemtodelete);
                        break;
                    case 4:
                        library.viewallitems();
                        break;
                    case 5:
                        System.out.println("Enter id to view item : ");
                        int idview = scanner.nextInt();
                        item itemtoview = library.getitembyid(idview);
                        if (itemtoview != null) {
                            // System.out.println(itemtoview);
                            itemtoview.displayinfo();
                        } else {
                            System.out.println("Item not available");
                        }
                        break;
                    case 6:
                        System.out.println("Enter id to Borrow Item  : ");
                        int idview2 = scanner.nextInt();
                        item itemtoview2 = library.getitembyid(idview2);
                        Borrower newBorrower = new Borrower();
                        newBorrower.Borrow_Item(itemtoview2);
                        library.BorrowersName.add(newBorrower.ReturnName());
                        break;
                    case 7:
                        library.View_Bor_List();
                        break;
                    case 8:
                        System.out.println("Exiting ....!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice....!");
                }

            }
        }
    }
}

