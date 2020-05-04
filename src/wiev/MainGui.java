package wiev;

import file.OpenAndSave;
import model.IWeight;
import store.ProductStore;
import store.WoodDirectory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGui {
    public JFrame frame;
    private JList list1;
    private JTextArea textArea1;
    private JPanel MainPanel;
    private JScrollPane scrollArea1;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("File");
    private JMenuItem menuItem_save = new JMenuItem("Save");
    private JMenuItem menuItem_open = new JMenuItem("Open");

    private WoodDirectory wd = new WoodDirectory();
    private ProductStore ps = new ProductStore();
    private DlgTimber dlgTimber = new DlgTimber();
    private DlgCilinder dlgCilinder = new DlgCilinder();
    private DlgWaste dlgWaste = new DlgWaste();
    private DlgWood dlgWood = new DlgWood();


    /**
     * Create the application
     */
    public MainGui() {
        initialize();
    }

    /**
     * Initilize the contens of the frame
     */
    private void initialize() {

        frame = new JFrame();
        frame.setBounds(200, 200, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Lab4 OOP");
        frame.setContentPane(MainPanel);
        //frame.setLocationRelativeTo(textArea1);
        //frame.setSize(600,300);
        menuBar.add(menu);
        menu.add(menuItem_open);
        menu.add(menuItem_save);
        frame.setJMenuBar(menuBar);
        //list1 = new JList<>();
        DefaultListModel<IWoodDialog> model = new DefaultListModel<>();
        model.addElement(dlgTimber);
        model.addElement(dlgCilinder);
        model.addElement(dlgWaste);
        model.addElement(dlgWood);
        list1.setModel(model);

        menuItem_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OpenAndSave.save(wd, ps);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "File save error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        menuItem_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Object[] obj = OpenAndSave.open();
                    if(obj != null){
                        wd = (WoodDirectory) obj[0];
                        ps = (ProductStore) obj[1];
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "File open error", "Error", JOptionPane.ERROR_MESSAGE);
                    wd = new WoodDirectory();
                    ps = new ProductStore();
                }
                textArea1.setText(ps.toString());
            }

        });

        list1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                IWoodDialog dlg = (IWoodDialog) list1.getSelectedValue();
                dlg.setWoodDirectory(wd);
                dlg.setVisible(true);
                Object obj = dlg.getObject();
                if (obj != null) {
                    ps.add((IWeight) obj);
                }
                textArea1.setText(ps.toString());
            }
        });
    }
}
