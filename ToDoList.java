import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoList extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskInput;
    private JButton addButton;
    private JButton deleteButton;

    public ToDoList() {
        // Set up the frame
        setTitle("To-Do List");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Create the task list model and JList
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add scroll pane for the task list
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Tasks"));

        // Create input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        taskInput = new JTextField();
        taskInput.setFont(new Font("Arial", Font.PLAIN, 14));

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 5, 5));

        addButton = new JButton("Add Task");
        deleteButton = new JButton("Delete Task");

        addButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        // Allow pressing Enter to add task
        taskInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskInput.setText("");
            taskInput.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please enter a task!",
                    "Empty Task",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select a task to delete!",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ToDoList app = new ToDoList();
                app.setVisible(true);
            }
        });
    }
}