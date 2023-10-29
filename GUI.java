import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;


class AddItemDialog extends JDialog {
    private library library;
    private JTextField titleField;
    private JComboBox<String> itemTypeComboBox;
    private JTextField authorField;
    private JTextField yearField;
    private JTextField publisherField;
    private JTextField dateField;
    private JTextField popularityField;
    private JTextField costField;
    private JTextField idField;
     
    public AddItemDialog(JFrame parent, library library) {
        super(parent, "Add Item", true);
        this.library = library;

        // Initialize the dialog components
        titleField = new JTextField(20);
        itemTypeComboBox = new JComboBox<>(new String[]{"Book", "Magazine", "Newspaper"});
        authorField = new JTextField(20);
        yearField = new JTextField(20);
        publisherField = new JTextField(20);
        dateField = new JTextField(20);
        popularityField = new JTextField(20);
        costField = new JTextField(20);
        idField=new JTextField(20);

        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        // Create a panel for dialog components
        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Item Type:"));
        panel.add(itemTypeComboBox);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Cost:"));
        panel.add(yearField);
        panel.add(new JLabel("Publisher:"));
        panel.add(publisherField);
        panel.add(new JLabel("Date:"));
        panel.add(dateField);
        panel.add(new JLabel("Year:"));
        panel.add(popularityField);
        panel.add(new JLabel("Popularity:"));
        panel.add(costField);
        panel.add(new JLabel("Id:"));
        panel.add(idField);

        // Add components to the dialog
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(addButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    private void addItem() {
        String title = titleField.getText();
        String itemType = (String) itemTypeComboBox.getSelectedItem();
        String author = authorField.getText();
        String yearText = yearField.getText();
        String publisher = publisherField.getText();
        String date = dateField.getText();
        String popularityText = popularityField.getText();
        String costText = costField.getText();
        String id=idField.getText();

        try {
            int year = Integer.parseInt(yearText);
            int popularity = Integer.parseInt(popularityText);
            int cost = Integer.parseInt(costText);
            int ID = Integer.parseInt(id);

            switch (itemType) {
                case "Book":
                    library.items.add(new Book(title, cost, year, author, popularity));
                    break;
                // case "Magazine":
                //     List<String> authors = List.of(author.split(","));
                //     library.items.add(new magzine(ID,popularity, title, cost, publisher,authors));
                //     break;
                // case "Newspaper":
                //     library.additem(new newspaper(title,popularity,ID, cost, publisher, date));
                //     break;
            }

            JOptionPane.showMessageDialog(this, "Item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            this.setVisible(false);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for year, popularity count, and cost.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void clearFields() {
        titleField.setText("");
        itemTypeComboBox.setSelectedIndex(0);
        authorField.setText("");
        yearField.setText("");
        publisherField.setText("");
        dateField.setText("");
        popularityField.setText("");
        costField.setText("");
    }
}
class PopularityDialog extends JDialog 
{
    private library library;
    public PopularityDialog(JFrame parent, library library) {
        super(parent, "Popularity", true);
        this.library = library;

        setSize(400, 400);
        setLocationRelativeTo(parent);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        List<Integer> popularityCounts = new ArrayList<>();
        for (item item : library.getItems()) 
        {
            popularityCounts.add(item.getpoularitycount());
        }

        // Calculate the total popularity count
        int totalPopularityCount = popularityCounts.stream().mapToInt(Integer::intValue).sum();

        // Calculate the angles for the pie chart
        List<Double> angles = new ArrayList<>();
        for (int popularityCount : popularityCounts) {
            double angle = (double) popularityCount / totalPopularityCount * 360;
            angles.add(angle);
        }

        // Draw the pie chart
        int x = 100;
        int y = 100;
        int diameter = 200;
        int startAngle = 0;
        int i = 0;
        for (double angle : angles) {
            g.setColor(getRandomColor());
            g.fillArc(x, y, diameter, diameter, startAngle, (int) angle);
            startAngle += angle;
            i++;
        }

        // Draw the legend
        int legendX = 320;
        int legendY = 120;
        int legendWidth = 60;
        int legendHeight = 20;
        int legendSpacing = 25;
        i = 0;
        for (item item : library.getItems()) {
            g.setColor(getRandomColor());
            g.fillRect(legendX, legendY + i * legendSpacing, legendWidth, legendHeight);
            g.setColor(Color.BLACK);
            g.drawString(item.gettitle(), legendX + legendWidth + 10, legendY + i * legendSpacing + legendHeight - 5);
            i++;
        }
    }

    private Color getRandomColor() {
        Random random = new Random();
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
class EditItemDialog extends JDialog {
    private library library;
    private JTextField idField;
    private JTextField titleField;
    private JTextField costField;
    private JTextField popularityField;

    public EditItemDialog(JFrame parent, library library) {
        super(parent, "Edit Item", true);
        this.library = library;

        // Initialize the dialog components
        idField = new JTextField(20);
        titleField = new JTextField(20);
        costField = new JTextField(20);
        popularityField = new JTextField(20);

        JButton editButton = new JButton("Edit");

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editItem();
            }
        });

        // Create a panel for dialog components
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Item ID:"));
        panel.add(idField);
        panel.add(new JLabel("New Title:"));
        panel.add(titleField);
        panel.add(new JLabel("New Cost:"));
        panel.add(costField);
        panel.add(new JLabel("New Popularity Count:"));
        panel.add(popularityField);

        // Add components to the dialog
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(editButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    private void editItem() {
        String idText = idField.getText();
        String title = titleField.getText();
        String costText = costField.getText();
        String popularityText = popularityField.getText();

        try {
            int id = Integer.parseInt(idText);
            int cost = Integer.parseInt(costText);
            int popularity = Integer.parseInt(popularityText);

            item itemToEdit = library.getitembyid(id);

            if (itemToEdit != null) {
                itemToEdit.settitle(title);
                itemToEdit.setcost(cost);
                itemToEdit.setpopularitycount(popularity);

                JOptionPane.showMessageDialog(this, "Item edited successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Item with the specified ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for ID, cost, and popularity count.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
class ReadItemDialog extends JDialog {
    private JTextArea textArea;
    private JButton closeButton;
    private boolean isReading;

    public ReadItemDialog(JFrame parent, String title, String content) {
        super(parent, title, true);

        textArea = new JTextArea(content);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeDialog();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

      setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        pack();
        setLocationRelativeTo(parent);
        isReading = true;
    }

    private void closeDialog() {
        isReading = false;
        setVisible(false);
    }

    public boolean isReading() {
        return isReading;
    }
}
class DeleteItemDialog extends JDialog {
    private library library;
    private JTextField idField;

    public DeleteItemDialog(JFrame parent, library library) {
        super(parent, "Delete Item", true);
        this.library = library;

        // Initialize the dialog components
        idField = new JTextField(20);

        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteItem();
            }
        });

        // Create a panel for dialog components
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Item ID to Delete:"));
        panel.add(idField);

        // Add components to the dialog
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(deleteButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    private void deleteItem() {
        String idText = idField.getText();

        try {
            int id = Integer.parseInt(idText);

            item itemToDelete = library.getitembyid(id);

            if (itemToDelete != null) {
                library.deleteitem(id);
                JOptionPane.showMessageDialog(this, "Item deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Item with the specified ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
public class GUI implements MouseListener {
    private JFrame frame;
    private library library;
    String[] columnNames = {"ID", "Title", "Type", "Author/Publisher", "Cost", "Year", "Popularity"};

    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    private JTable itemTable=new JTable(tableModel);

    public GUI(library library) {
        this.library = library;
        itemTable.addMouseListener(this);
        updateTable(); 
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create the JTable for displaying items
        String[] columnNames = {"ID", "Title", "Type", "Author/Publisher", "Cost", "Year", "Popularity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        itemTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(itemTable);

        frame.add(tableScrollPane, BorderLayout.CENTER);

        // Create buttons for Add, Delete, and Edit
        JButton addButton = new JButton("Add Item");
        JButton deleteButton = new JButton("Delete Item");
        JButton editButton = new JButton("Edit Item");
        JButton viewPopularityButton = new JButton("View Popularity");
       
        viewPopularityButton.addActionListener(new ActionListener() 
        {
        @Override
        public void actionPerformed(ActionEvent e) {
        openPopularityDialog();
    }
});


        // Add action listeners to the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the Add Item operation here
                openAddDialog();
                updateTable();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the Delete Item operation here
             openDeleteDialog();
                updateTable();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Handle the Edit Item operation here
                openEditDialog();
                updateTable();
            }
        });

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(viewPopularityButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        updateTable(); // Initialize the table with available items

        frame.setVisible(true);
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        int row = itemTable.rowAtPoint(e.getPoint());
        if (row >= 0) {
            itemTable.setRowSelectionInterval(row, row);
        }
    }
    @Override
    public void mouseExited(MouseEvent e) {
        itemTable.clearSelection();
    }
    private void openReadDialog(String title, String content) {
        ReadItemDialog readItemDialog = new ReadItemDialog(frame, title, content);
        readItemDialog.setVisible(true);
    }
    private void openAddDialog() {
        AddItemDialog addItemDialog = new AddItemDialog(frame, library);
        addItemDialog.setVisible(true);
    }

   private void openDeleteDialog() {
      DeleteItemDialog deleteItemDialog = new DeleteItemDialog(frame, library);
       deleteItemDialog.setVisible(true);
    }

    private void openEditDialog() {
       EditItemDialog editItemDialog = new EditItemDialog(frame, library);
       editItemDialog.setVisible(true);
 }
 private void openPopularityDialog() {
    PopularityDialog popularityDialog = new PopularityDialog(frame, library);
    popularityDialog.setVisible(true);
}
public void mouseClicked(MouseEvent e) {
    if (e.getClickCount() == 2) {
        int row = itemTable.getSelectedRow();
        int column = itemTable.getSelectedColumn();

        if (row >= 0 && column == 1) {
            String title = (String) itemTable.getValueAt(row, column);
            String content = "Sample content for " + title; // Replace with actual content retrieval logic

            openReadDialog(title, content);
        }
    }
}

    private void updateTable() {
        // Clear the existing rows in the table
        tableModel.setRowCount(0);

        // Add items to the table
        for (item item : library.getItems()) {
            if (item instanceof Book) {
                Book book = (Book) item;
                tableModel.addRow(new Object[]{book.getId(), book.getTitle(), "Book", book.getaurthor(), book.getyear(), book.getpopularitycount(), book.getCost()});
            } else if (item instanceof magzine) {
                magzine magazineItem = (magzine) item;
                String authors = String.join(", ", magazineItem.getaurthors());
                tableModel.addRow(new Object[]{magazineItem.getId(), magazineItem.getTitle(), "Magazine", magazineItem.getpublisher(), "", magazineItem.getpopularitycount(), magazineItem.getCost()});
            } else if (item instanceof newspaper) {
                newspaper newspaperItem = (newspaper) item;
                tableModel.addRow(new Object[]{newspaperItem.getId(), newspaperItem.getTitle(), "Newspaper", newspaperItem.getpublisher(), newspaperItem.getdate(), newspaperItem.getpopularitycount(), newspaperItem.getCost()});
            }
        }
        itemTable.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = itemTable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    itemTable.setRowSelectionInterval(row, row);
                }
            }
        });
        
        itemTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                itemTable.clearSelection();
            }
        });
        
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            library library = new library("Demo.txt");
            GUI gui = new GUI(library);
            gui.updateTable();
        });
    }
}
