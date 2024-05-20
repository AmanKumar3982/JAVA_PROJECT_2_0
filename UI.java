import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UI extends JFrame {

    public UI() {
        setTitle("Railway Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JButton bookButton = new JButton("Book Ticket");
        JButton updateButton = new JButton("Update Ticket");
        JButton removeButton = new JButton("Remove Ticket");
        JButton viewButton = new JButton("View Ticket");

        bookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new BookTicketFrame().setVisible(true);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UpdateTicketFrame().setVisible(true);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RemoveTicketFrame().setVisible(true);
            }
        });

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ViewTicketFrame().setVisible(true);
            }
        });

        add(bookButton);
        add(updateButton);
        add(removeButton);
        add(viewButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    static class BookTicketFrame extends JFrame {
        public BookTicketFrame() {
            setTitle("Book Ticket");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new GridLayout(7, 2));

            JLabel pnrLabel = new JLabel("PNR:");
            pnrLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
            JTextField pnrField = new JTextField();

            JLabel nameLabel = new JLabel("Name:");
            nameLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
            JTextField nameField = new JTextField();

            JLabel ageLabel = new JLabel("Age:");
            ageLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
            JTextField ageField = new JTextField();

            JLabel genderLabel = new JLabel("Gender:");
            genderLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
            JTextField genderField = new JTextField();

            JLabel sourceLabel = new JLabel("Source:");
            sourceLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
            JTextField sourceField = new JTextField();

            JLabel destinationLabel = new JLabel("Destination:");
            destinationLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
            JTextField destinationField = new JTextField();

            JButton bookButton = new JButton("Book");

            bookButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    long pnr = Long.parseLong(pnrField.getText());
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String gender = genderField.getText();
                    String source = sourceField.getText();
                    String destination = destinationField.getText();

                    if (BackendRelated.bookTicket(pnr, name, age, gender, source, destination)) {
                        JOptionPane.showMessageDialog(null, "Ticket booked successfully! Your PNR is: " + pnr);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error occurred while booking ticket!");
                    }
                }
            });

            add(pnrLabel);
            add(pnrField);
            add(nameLabel);
            add(nameField);
            add(ageLabel);
            add(ageField);
            add(genderLabel);
            add(genderField);
            add(sourceLabel);
            add(sourceField);
            add(destinationLabel);
            add(destinationField);
            add(new JLabel());
            add(bookButton);
        }
    }

    static class UpdateTicketFrame extends JFrame {
        public UpdateTicketFrame() {
            setTitle("Update Ticket");
            setSize(400, 200);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new GridLayout(4, 2));

            JLabel pnrLabel = new JLabel("Enter PNR number:");
            pnrLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
            JTextField pnrField = new JTextField();

            JLabel choiceLabel = new JLabel("Choose field to update:");
            choiceLabel.setFont(new Font("Arial Black", Font.BOLD, 18));

            JComboBox<String> fieldComboBox = new JComboBox<>(
                    new String[]{"Name", "Age", "Gender", "Source", "Destination"});

            JLabel newValueLabel = new JLabel("Enter new value:");
            newValueLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
            JTextField newValueField = new JTextField();

            JButton updateButton = new JButton("Update");

            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    long pnr = Long.parseLong(pnrField.getText());
                    String field = (String) fieldComboBox.getSelectedItem();
                    String newValue = newValueField.getText();

                    if (BackendRelated.updateTicket(pnr, field, newValue)) {
                        JOptionPane.showMessageDialog(null, "Ticket updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error occurred while updating ticket!");
                    }
                }
            });

            add(pnrLabel);
            add(pnrField);
            add(choiceLabel);
            add(fieldComboBox);
            add(newValueLabel);
            add(newValueField);
            add(new JLabel());
            add(updateButton);
        }
    }

    static class RemoveTicketFrame extends JFrame {
        public RemoveTicketFrame() {
            setTitle("Remove Ticket");
            setSize(400, 100);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new GridLayout(2, 2));

            JLabel pnrLabel = new JLabel("Enter PNR number:");
            pnrLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
            JTextField pnrField = new JTextField();

            JButton removeButton = new JButton("Remove");

            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    long pnr = Long.parseLong(pnrField.getText());

                    if (BackendRelated.removeTicket(pnr)) {
                        JOptionPane.showMessageDialog(null, "Ticket removed successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error occurred while removing ticket!");
                    }
                }
            });

            add(pnrLabel);
            add(pnrField);
            add(new JLabel());
            add(removeButton);
        }
    }

    static class ViewTicketFrame extends JFrame {
        public ViewTicketFrame() {
            setTitle("View Ticket");
            setSize(400, 100);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new GridLayout(2, 2));

            JLabel pnrLabel = new JLabel("Enter PNR number:");
            pnrLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
            JTextField pnrField = new JTextField();

            JButton viewButton = new JButton("View");

            viewButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    long pnr = Long.parseLong(pnrField.getText());

                    ResultSet rs = BackendRelated.viewTicket(pnr);

                    try {
                        if (rs != null && rs.next()) {
                            JOptionPane.showMessageDialog(null, "PNR: " + rs.getLong("pnr") +
                                    "\nName: " + rs.getString("name") +
                                    "\nAge: " + rs.getInt("age") +
                                    "\nGender: " + rs.getString("gender") +
                                    "\nSource: " + rs.getString("source") +
                                    "\nDestination: " + rs.getString("destination"));
                        } else {
                            JOptionPane.showMessageDialog(null, "Ticket not found!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error occurred while viewing ticket!");
                    }
                }
            });

            add(pnrLabel);
            add(pnrField);
            add(new JLabel());
            add(viewButton);
        }
    }
}
