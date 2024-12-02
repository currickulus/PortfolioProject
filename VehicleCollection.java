import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Comparator;

public class VehicleCollection extends JFrame {
    private LinkedList<Vehicle> vehicleList = new LinkedList<>();
    private JTable vehicleTable;
    private TableRowSorter<VehicleTableModel> sorter;

    public VehicleCollection() {//makes the window
        setTitle("Vehicle Collection");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components in the window
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
//make panel
        JPanel makePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel makeLabel = new JLabel("Make:");
        JTextField makeField = new JTextField(15);
        makePanel.add(makeLabel);
        makePanel.add(makeField);
        inputPanel.add(makePanel);
//model panel
        JPanel modelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel modelLabel = new JLabel("Model:");
        JTextField modelField = new JTextField(15);
        modelPanel.add(modelLabel);
        modelPanel.add(modelField);
        inputPanel.add(modelPanel);
//mpg panel
        JPanel mpgPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel mpgLabel = new JLabel("Miles per Gallon:");
        JTextField mpgField = new JTextField(15);
        mpgPanel.add(mpgLabel);
        mpgPanel.add(mpgField);
        inputPanel.add(mpgPanel);
        //Button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Vehicle");
        JButton quitButton = new JButton("Quit");
        JButton sortByMakeButton = new JButton("Sort By Make");
        JButton sortByMPGButton = new JButton("Sort By MPG");
        buttonPanel.add(addButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(sortByMakeButton);
        buttonPanel.add(sortByMPGButton);
        inputPanel.add(buttonPanel);
        //table output
        VehicleTableModel tableModel = new VehicleTableModel();
        vehicleTable = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);

        // comparator for make
        sorter.setComparator(0, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        vehicleTable.setRowSorter(sorter);
        JScrollPane scrollPane = new JScrollPane(vehicleTable);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        //exception to only allow number in miles per gallon
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double mpg = Double.parseDouble(mpgField.getText());
                    vehicleList.add(new Vehicle(makeField.getText(), modelField.getText(), mpg));
                    tableModel.fireTableDataChanged(); // Notify model about the change
                    clearFields(makeField, modelField, mpgField);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(VehicleCollection.this,
                            "Invalid miles per gallon entry. Please enter a number.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //exit the program button action listener
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //sort by make action listeneer

        sortByMakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorter.setRowFilter(null); // Clear any existing filters
                sorter.setSortKeys(java.util.Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING))); // Sort by Make column
                sorter.sort();
            }
        });
        sortByMakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorter.setRowFilter(null); // Clear any existing filters
                sorter.setSortKeys(java.util.Arrays.asList(new RowSorter.SortKey(0, SortOrder.ASCENDING))); // Sort by Make column
                sorter.sort();
            }
        });
        //sort by MPG action lisener
        sortByMPGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorter.setRowFilter(null);
                sorter.setSortKeys(java.util.Arrays.asList(new RowSorter.SortKey(2, SortOrder.ASCENDING))); // Sort by MPG column
                sorter.sort();
            }
        });
    }

    //clear the frame after add car
    private void clearFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

        //table construction
    private class VehicleTableModel extends AbstractTableModel {
        private String[] columnNames = {"Make", "Model", "Miles per Gallon"};

        @Override
        public int getRowCount() {
            return vehicleList.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override//gets input from text fields and puts them in the table
        public Object getValueAt(int rowIndex, int columnIndex) {
            Vehicle vehicle = vehicleList.get(rowIndex);
            switch (columnIndex) {
                case 0: return vehicle.getMake();
                case 1: return vehicle.getModel();
                case 2: return vehicle.getMilesPerGallon();
                default: return null;
            }
        }

        @Override//names of columns in thee table
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override//turns the inputs into strings for the table
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 2: return Double.class;
                default: return String.class;
            }
        }
    }
        //main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VehicleCollection gui = new VehicleCollection();
            gui.setVisible(true);
        });
    }
}