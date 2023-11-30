import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class Task {
    private String name;
    private String dueDate;
    private int priority;
    long timeLeftInMillis;

    public Task(String name, String dueDate, int priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.timeLeftInMillis = calculateTimeLeft();
    }

    public String getName() {
        return name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public long getTimeLeftInMillis() {
        return timeLeftInMillis;
    }

    public long calculateTimeLeft() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date taskDate = dateFormat.parse(this.dueDate);
            return taskDate.getTime() - System.currentTimeMillis();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

public class TaskScheduler extends JFrame {
    private List<Task> tasks;
    private JTextField nameField;
    private JTextField dateField;
    private JTextField priorityField;
    private JTextArea taskArea;

    public TaskScheduler() {
        tasks = new ArrayList<>();

        setTitle("Task Scheduler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 450);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Task Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Due Date (dd/mm/yyyy):"));
        dateField = new JTextField();
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Priority (1-5):"));
        priorityField = new JTextField();
        inputPanel.add(priorityField);
        JButton addButton = new JButton("Add Task");
        inputPanel.add(addButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        taskArea = new JTextArea();
        taskArea.setEditable(false);
        panel.add(new JScrollPane(taskArea), BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String date = dateField.getText();
                int priority = Integer.parseInt(priorityField.getText());

                Task newTask = new Task(name, date, priority);
                addTask(newTask);
                displayTasks();
                clearFields();
            }
        });

        getContentPane().add(panel);
        setVisible(true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                checkNotifications();
            }
        }, 0, 1000);
    }

    public void addTask(Task task) {
        tasks.add(task);
        tasks.sort(Comparator.comparing(Task::getDueDate));
    }

    public void displayTasks() {
        taskArea.append("Tasks:\n");
        Task lastTask = tasks.get(tasks.size() - 1);
        taskArea.append("Task: " + lastTask.getName() + ", Due Date: " + lastTask.getDueDate() + ", Priority: " + lastTask.getPriority() + "\n");
    }

    public void clearFields() {
        nameField.setText("");
        dateField.setText("");
        priorityField.setText("");
    }

    public void checkNotifications() {
        for (Task task : tasks) {
            if (task.getTimeLeftInMillis() <= 0) {
                showNotification(task.getName());
                tasks.remove(task);
                break;
            }
        }
    }

    public void showNotification(String taskName) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, "Task: " + taskName + " is due now!", "Notification", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
    }

    public static void main(String[] args) {
        new TaskScheduler();
    }
}
