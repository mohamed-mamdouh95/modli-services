/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modli.servicesApp;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import JDBC.controller;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author Administrator
 */
public class home extends javax.swing.JFrame {

    /**
     * Creates new form home
     */
    DefaultTableModel model,agazaModel,ast3lamModel;
    private controller DB = new controller(); 
    public home() throws IOException {
        initComponents();
        DateFormat df = new SimpleDateFormat("EEEE" , new Locale("ar"));
    	java.util.Date today = Calendar.getInstance().getTime();        
    	String dayName = df.format(today);
        String everything = ""; 
    	if ((dayName.equals("الاثنين")))  
    	{
            try(BufferedReader br = new BufferedReader(new FileReader("done.txt"))) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                everything = sb.toString();
                everything = everything.replaceAll("\\s+","");
            }
            if(everything.equals("false"))
            {
                JTextField saryaField = new JTextField(5);
                JTextField mktbField = new JTextField(5);
                JPanel myPanel = new JPanel();
                myPanel.add(saryaField);
                myPanel.add(new JLabel("عدد أيام الخدمات لعساكرالسرية في الأسبوع"));

                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(mktbField);
                myPanel.add(new JLabel("عدد أيام الخدمات لعساكرالمكاتب في الأسبوع"));
                int result = JOptionPane.showConfirmDialog(null, myPanel, 
                             "توزيع عدد أيام الخدمات", JOptionPane.OK_OPTION);
                     if (result == JOptionPane.OK_OPTION) {
                        DB.setSayra(Integer.parseInt(saryaField.getText()));
                        DB.setMktb(Integer.parseInt(mktbField.getText()));
                     } 
                     else{
                         System.exit(0);

                     }
                }
            
    	}
        if((dayName.equals("الثلاثاء")))
        {
             Writer writer = null;
                try {
                   BufferedWriter out = new BufferedWriter(new FileWriter("done.txt",false)); 
                    out.write("false");
                    out.close();
                } catch (IOException ex) {
                  // report
                } finally {
                   try {writer.close();} catch (Exception ex) {/*ignore*/}
                } 
        }
      
        if(everything.equals("false"))
        {
            while(!DB.distributeServices())
            {
                JOptionPane.showMessageDialog(null, "عدد العساكر غير كافي... من فضلك أعد ادخال عدد الأيام");
                JTextField saryaField = new JTextField(5);
                JTextField mktbField = new JTextField(5);
                JPanel myPanel = new JPanel();
                myPanel.add(saryaField);
                myPanel.add(new JLabel("عدد أيام الخدمات لعساكرالسرية في الأسبوع"));

                myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                myPanel.add(mktbField);
                myPanel.add(new JLabel("عدد أيام الخدمات لعساكرالمكاتب في الأسبوع"));
                int result = JOptionPane.showConfirmDialog(null, myPanel, 
                             "توزيع عدد أيام الخدمات", JOptionPane.OK_OPTION);
                     if (result == JOptionPane.OK_OPTION) {
                        DB.setSayra(Integer.parseInt(saryaField.getText()));
                        DB.setMktb(Integer.parseInt(mktbField.getText()));
                     }  
                     else
                     {
                        System.exit(0);
                     }

            }
        }
        DB.dayOff();
        tableAgazaContainer.setVisible(false);
        formPanel.setVisible(false);
        menuPanel.setVisible(false);
        wrongLabel.setVisible(false);
        wrongLabel1.setVisible(false);
        wrongLabel2.setVisible(false);
        wrongLabel3.setVisible(false);
        egyptLogo.setVisible(false);
        ast3lamForm.setVisible(false);
        deleteForm.setVisible(false);
        copyrightsLabel.setVisible(false);
        modliLabel.setVisible(false);
        t7dethform.setVisible(false);
        back.setVisible(false);
        khdmatPanel.setVisible(false);
        ButtonGroup group = new ButtonGroup();
        group.add(sgn);
        group.add(teby);
        ButtonGroup group2 = new ButtonGroup();
        group.add(mktbBtn);
        group.add(sryaBtn);
        model = (DefaultTableModel)servTable.getModel();
        agazaModel = (DefaultTableModel)agazaTable.getModel();
        ast3lamModel = (DefaultTableModel)ast3lamTable.getModel();
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.RIGHT);
        servTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        servTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        servTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        servTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        servTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        servTable.getTableHeader().setFont(new Font("thoma", Font.BOLD, 14)); 
        agazaTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        agazaTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        agazaTable.getTableHeader().setFont(new Font("thoma", Font.BOLD, 14));
        ast3lamTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        ast3lamTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        ast3lamTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        ast3lamTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        ast3lamTable.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        TableCellRenderer rendererFromHeader = servTable.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        TableCellRenderer rendererFromHeader1 = agazaTable.getTableHeader().getDefaultRenderer();
        JLabel headerLabe2 = (JLabel) rendererFromHeader1;
        headerLabe2.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color (236, 240, 241));
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        servTable.getTableHeader().setPreferredSize(new Dimension(100, 40));
        agazaTable.getTableHeader().setPreferredSize(new Dimension(100, 40));
        ast3lamTable.getTableHeader().setPreferredSize(new Dimension(100, 40));
        ast3lamTableContainer.setVisible(false);
        TableCellRenderer rendererFromHeader3 = ast3lamTable.getTableHeader().getDefaultRenderer();
        JLabel headerLabe3 = (JLabel) rendererFromHeader3;
        headerLabe3.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < servTable.getModel().getColumnCount(); i++) 
        {
            servTable.getTableHeader().setDefaultRenderer(headerRenderer);
        }
        for (int i = 0; i < agazaTable.getModel().getColumnCount(); i++) 
        {
        agazaTable.getTableHeader().setDefaultRenderer(headerRenderer); 
        }
        for (int i = 0; i < ast3lamTable.getModel().getColumnCount(); i++) 
        {
        ast3lamTable.getTableHeader().setDefaultRenderer(headerRenderer); 
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        khdmat = new javax.swing.JButton();
        agazat = new javax.swing.JButton();
        tsgel = new javax.swing.JButton();
        ast3lamButton = new javax.swing.JButton();
        deleteMenuButton = new javax.swing.JButton();
        sgnTebyMenu = new javax.swing.JButton();
        tableAgazaContainer = new javax.swing.JScrollPane();
        agazaTable = new javax.swing.JTable();
        formPanel = new javax.swing.JPanel();
        alasmLabel = new javax.swing.JLabel();
        alasmText = new javax.swing.JTextField();
        rqmAskaryText = new javax.swing.JTextField();
        rqmQwmyText = new javax.swing.JTextField();
        tre5altgnedText = new javax.swing.JTextField();
        rqmaskaryLabel = new javax.swing.JLabel();
        rqmQwmyLabel = new javax.swing.JLabel();
        tre5TgnedLabel = new javax.swing.JLabel();
        tre5Tsre7Text = new javax.swing.JTextField();
        anwanText = new javax.swing.JTextField();
        tre5Tsre7Label = new javax.swing.JLabel();
        anwanLabel = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        vacText = new javax.swing.JTextField();
        vacLabel = new javax.swing.JLabel();
        mktbBtn = new javax.swing.JRadioButton();
        sryaBtn = new javax.swing.JRadioButton();
        a3fa = new javax.swing.JCheckBox();
        egyptLogo = new javax.swing.JLabel();
        loginForm = new javax.swing.JPanel();
        userName = new javax.swing.JLabel();
        userNameText = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        login = new javax.swing.JButton();
        wrongLabel = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        ast3lamForm = new javax.swing.JPanel();
        rqmALabel = new javax.swing.JLabel();
        rqmAText = new javax.swing.JTextField();
        ast3lam = new javax.swing.JButton();
        wrongLabel1 = new javax.swing.JLabel();
        deleteForm = new javax.swing.JPanel();
        deleteLabel = new javax.swing.JLabel();
        deleteText = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        wrongLabel2 = new javax.swing.JLabel();
        modliLabel = new javax.swing.JLabel();
        copyrightsLabel = new javax.swing.JLabel();
        t7dethform = new javax.swing.JPanel();
        t7dethLabel = new javax.swing.JLabel();
        t7dethText = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        wrongLabel3 = new javax.swing.JLabel();
        teby = new javax.swing.JRadioButton();
        sgn = new javax.swing.JRadioButton();
        undoDate = new javax.swing.JTextField();
        label = new javax.swing.JLabel();
        ast3lamTableContainer = new javax.swing.JScrollPane();
        ast3lamTable = new javax.swing.JTable();
        back = new javax.swing.JButton();
        khdmatPanel = new javax.swing.JPanel();
        tableContainer = new javax.swing.JScrollPane();
        servTable = new javax.swing.JTable();
        tableTitle = new javax.swing.JLabel();
        todaysDate = new javax.swing.JLabel();
        JL1 = new javax.swing.JLabel();
        JL2 = new javax.swing.JLabel();
        JL3 = new javax.swing.JLabel();
        btnprev = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        JL4 = new javax.swing.JLabel();
        JL5 = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("الصفحة الرئيسية");
        setExtendedState(MAXIMIZED_BOTH);

        mainPanel.setBackground(new Color (244,242,243));
        mainPanel.setAutoscrolls(true);
        mainPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mainPanel.setName(""); // NOI18N
        mainPanel.setLayout(null);

        menuPanel.setBackground(new Color (94,65,129));

        khdmat.setBackground(new java.awt.Color(0, 0, 0));
        khdmat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        khdmat.setForeground(new java.awt.Color(255, 255, 255));
        khdmat.setText("الخدمات");
        khdmat.setBorderPainted(false);
        khdmat.setFocusPainted(false);
        khdmat.setFocusable(false);
        khdmat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khdmatActionPerformed(evt);
            }
        });

        agazat.setBackground(new java.awt.Color(0, 0, 0));
        agazat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        agazat.setForeground(new java.awt.Color(255, 255, 255));
        agazat.setText("الأجازات");
        agazat.setBorderPainted(false);
        agazat.setFocusPainted(false);
        agazat.setFocusable(false);
        agazat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agazatActionPerformed(evt);
            }
        });

        tsgel.setBackground(new java.awt.Color(0, 0, 0));
        tsgel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tsgel.setForeground(new java.awt.Color(255, 255, 255));
        tsgel.setText("تسجيل بيانات");
        tsgel.setBorderPainted(false);
        tsgel.setFocusPainted(false);
        tsgel.setFocusable(false);
        tsgel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tsgelActionPerformed(evt);
            }
        });

        ast3lamButton.setBackground(new java.awt.Color(0, 0, 0));
        ast3lamButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ast3lamButton.setForeground(new java.awt.Color(255, 255, 255));
        ast3lamButton.setText("استعلام");
        ast3lamButton.setBorderPainted(false);
        ast3lamButton.setFocusPainted(false);
        ast3lamButton.setFocusable(false);
        ast3lamButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ast3lamButtonActionPerformed(evt);
            }
        });

        deleteMenuButton.setBackground(new java.awt.Color(0, 0, 0));
        deleteMenuButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deleteMenuButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteMenuButton.setText("مسح");
        deleteMenuButton.setBorderPainted(false);
        deleteMenuButton.setFocusPainted(false);
        deleteMenuButton.setFocusable(false);
        deleteMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMenuButtonActionPerformed(evt);
            }
        });

        sgnTebyMenu.setBackground(new java.awt.Color(0, 0, 0));
        sgnTebyMenu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sgnTebyMenu.setForeground(new java.awt.Color(255, 255, 255));
        sgnTebyMenu.setText("سجن / طبي");
        sgnTebyMenu.setBorderPainted(false);
        sgnTebyMenu.setFocusPainted(false);
        sgnTebyMenu.setFocusable(false);
        sgnTebyMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sgnTebyMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                .addGap(0, 525, Short.MAX_VALUE)
                .addComponent(sgnTebyMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ast3lamButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tsgel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(agazat, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(khdmat, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(agazat, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(khdmat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tsgel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ast3lamButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sgnTebyMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(deleteMenuButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        mainPanel.add(menuPanel);
        menuPanel.setBounds(0, 0, 1280, 40);

        tableAgazaContainer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableAgazaContainer.setPreferredSize(new java.awt.Dimension(452, 600));

        agazaTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agazaTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        agazaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الأسم", "الرقم العسكري"
            }
        ));
        agazaTable.setRowHeight(30);
        tableAgazaContainer.setViewportView(agazaTable);

        mainPanel.add(tableAgazaContainer);
        tableAgazaContainer.setBounds(280, 110, 950, 560);

        formPanel.setBackground(new Color(228,241,254));
        formPanel.setBorder(new javax.swing.border.MatteBorder(null));

        alasmLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        alasmLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alasmLabel.setLabelFor(alasmText);
        alasmLabel.setText("الأسم");

        alasmText.setPreferredSize(new java.awt.Dimension(10, 30));

        rqmAskaryText.setPreferredSize(new java.awt.Dimension(10, 30));

        rqmQwmyText.setMinimumSize(new java.awt.Dimension(10, 30));
        rqmQwmyText.setPreferredSize(new java.awt.Dimension(10, 30));

        tre5altgnedText.setMinimumSize(new java.awt.Dimension(10, 30));
        tre5altgnedText.setPreferredSize(new java.awt.Dimension(10, 30));

        rqmaskaryLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rqmaskaryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rqmaskaryLabel.setLabelFor(rqmAskaryText);
        rqmaskaryLabel.setText("الرقم العسكري");
        rqmaskaryLabel.setPreferredSize(new java.awt.Dimension(200, 17));

        rqmQwmyLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rqmQwmyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rqmQwmyLabel.setLabelFor(rqmQwmyText);
        rqmQwmyLabel.setText("الرقم القومي ");
        rqmQwmyLabel.setPreferredSize(new java.awt.Dimension(200, 17));

        tre5TgnedLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tre5TgnedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tre5TgnedLabel.setLabelFor(tre5altgnedText);
        tre5TgnedLabel.setText("تاريخ التجنيد");
        tre5TgnedLabel.setPreferredSize(new java.awt.Dimension(400, 17));

        tre5Tsre7Text.setPreferredSize(new java.awt.Dimension(210, 30));

        anwanText.setPreferredSize(new java.awt.Dimension(210, 30));

        tre5Tsre7Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tre5Tsre7Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tre5Tsre7Label.setText("تاريخ التسريح");

        anwanLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        anwanLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anwanLabel.setText("العنوان");

        submit.setBackground(new Color (94,65,129));
        submit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        submit.setForeground(new java.awt.Color(255, 255, 255));
        submit.setText("تسجيل");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        vacText.setPreferredSize(new java.awt.Dimension(210, 30));

        vacLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        vacLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vacLabel.setText("رقم دفعة الأجازة");

        mktbBtn.setText("مكتب");

        sryaBtn.setText("سرية");

        a3fa.setText("إعفاء من الخدمة");

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addComponent(submit)
                .addGap(165, 165, 165))
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(a3fa)
                    .addComponent(sryaBtn)
                    .addComponent(mktbBtn)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(vacText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(vacLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(formPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(formPanelLayout.createSequentialGroup()
                            .addComponent(alasmText, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(alasmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(formPanelLayout.createSequentialGroup()
                            .addComponent(rqmAskaryText, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(rqmaskaryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(formPanelLayout.createSequentialGroup()
                            .addComponent(rqmQwmyText, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(rqmQwmyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(formPanelLayout.createSequentialGroup()
                            .addComponent(tre5altgnedText, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(45, 45, 45)
                            .addComponent(tre5TgnedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(formPanelLayout.createSequentialGroup()
                            .addComponent(tre5Tsre7Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(55, 55, 55)
                            .addComponent(tre5Tsre7Label))
                        .addGroup(formPanelLayout.createSequentialGroup()
                            .addComponent(anwanText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(75, 75, 75)
                            .addComponent(anwanLabel)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                .addContainerGap(262, Short.MAX_VALUE)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vacText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vacLabel))
                .addGap(18, 18, 18)
                .addComponent(mktbBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sryaBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(a3fa)
                .addGap(61, 61, 61)
                .addComponent(submit)
                .addGap(27, 27, 27))
            .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(formPanelLayout.createSequentialGroup()
                    .addGap(0, 19, Short.MAX_VALUE)
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(alasmText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(alasmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rqmAskaryText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rqmaskaryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rqmQwmyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rqmQwmyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tre5altgnedText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tre5TgnedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tre5Tsre7Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tre5Tsre7Label))
                    .addGap(10, 10, 10)
                    .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(anwanText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(anwanLabel))
                    .addGap(0, 249, Short.MAX_VALUE)))
        );

        mainPanel.add(formPanel);
        formPanel.setBounds(500, 120, 420, 500);

        egyptLogo.setBackground(new Color (244,242,243));
        egyptLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/modli/servicesApp/egyptLogo.png"))); // NOI18N
        mainPanel.add(egyptLogo);
        egyptLogo.setBounds(0, 40, 170, 130);

        loginForm.setBackground(new Color(228,241,254));
        loginForm.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        userName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userName.setLabelFor(alasmText);
        userName.setText("اسم المستخدم");

        userNameText.setPreferredSize(new java.awt.Dimension(10, 30));

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setLabelFor(rqmAskaryText);
        passwordLabel.setText("كلمة السر");
        passwordLabel.setPreferredSize(new java.awt.Dimension(200, 17));

        login.setBackground(new Color (94,65,129));
        login.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("دخول");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        wrongLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        wrongLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wrongLabel.setText("عفواً أعد المحاولة ");

        javax.swing.GroupLayout loginFormLayout = new javax.swing.GroupLayout(loginForm);
        loginForm.setLayout(loginFormLayout);
        loginFormLayout.setHorizontalGroup(
            loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFormLayout.createSequentialGroup()
                .addContainerGap(188, Short.MAX_VALUE)
                .addComponent(login)
                .addGap(165, 165, 165))
            .addGroup(loginFormLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(loginFormLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginFormLayout.createSequentialGroup()
                            .addComponent(userNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(loginFormLayout.createSequentialGroup()
                            .addGap(250, 250, 250)
                            .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFormLayout.createSequentialGroup()
                    .addContainerGap(122, Short.MAX_VALUE)
                    .addComponent(wrongLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(138, Short.MAX_VALUE)))
        );
        loginFormLayout.setVerticalGroup(
            loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFormLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(login)
                .addGap(27, 27, 27))
            .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(loginFormLayout.createSequentialGroup()
                    .addGap(0, 34, Short.MAX_VALUE)
                    .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(userNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 145, Short.MAX_VALUE)))
            .addGroup(loginFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginFormLayout.createSequentialGroup()
                    .addContainerGap(111, Short.MAX_VALUE)
                    .addComponent(wrongLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(101, Short.MAX_VALUE)))
        );

        mainPanel.add(loginForm);
        loginForm.setBounds(450, 250, 420, 240);

        ast3lamForm.setBackground(new Color(228,241,254));
        ast3lamForm.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        rqmALabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rqmALabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rqmALabel.setLabelFor(alasmText);
        rqmALabel.setText("الرقم العسكري");

        rqmAText.setPreferredSize(new java.awt.Dimension(10, 30));

        ast3lam.setBackground(new Color (94,65,129));
        ast3lam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ast3lam.setForeground(new java.awt.Color(255, 255, 255));
        ast3lam.setText("استعلام");
        ast3lam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ast3lamActionPerformed(evt);
            }
        });

        wrongLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        wrongLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wrongLabel1.setText("عفواً أعد المحاولة ");

        javax.swing.GroupLayout ast3lamFormLayout = new javax.swing.GroupLayout(ast3lamForm);
        ast3lamForm.setLayout(ast3lamFormLayout);
        ast3lamFormLayout.setHorizontalGroup(
            ast3lamFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ast3lamFormLayout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addComponent(ast3lam)
                .addGap(165, 165, 165))
            .addGroup(ast3lamFormLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(rqmAText, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rqmALabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(ast3lamFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ast3lamFormLayout.createSequentialGroup()
                    .addContainerGap(122, Short.MAX_VALUE)
                    .addComponent(wrongLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(138, Short.MAX_VALUE)))
        );
        ast3lamFormLayout.setVerticalGroup(
            ast3lamFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ast3lamFormLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(ast3lamFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rqmAText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rqmALabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(ast3lam)
                .addGap(27, 27, 27))
            .addGroup(ast3lamFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ast3lamFormLayout.createSequentialGroup()
                    .addContainerGap(75, Short.MAX_VALUE)
                    .addComponent(wrongLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(67, Short.MAX_VALUE)))
        );

        mainPanel.add(ast3lamForm);
        ast3lamForm.setBounds(450, 250, 420, 170);

        deleteForm.setBackground(new Color(228,241,254));
        deleteForm.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        deleteLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        deleteLabel.setLabelFor(alasmText);
        deleteLabel.setText("الرقم العسكري");

        deleteText.setPreferredSize(new java.awt.Dimension(10, 30));

        deleteButton.setBackground(new Color (94,65,129));
        deleteButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("مسح");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        wrongLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        wrongLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wrongLabel2.setText("عفواً أعد المحاولة ");

        javax.swing.GroupLayout deleteFormLayout = new javax.swing.GroupLayout(deleteForm);
        deleteForm.setLayout(deleteFormLayout);
        deleteFormLayout.setHorizontalGroup(
            deleteFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteFormLayout.createSequentialGroup()
                .addContainerGap(186, Short.MAX_VALUE)
                .addComponent(deleteButton)
                .addGap(165, 165, 165))
            .addGroup(deleteFormLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(deleteText, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(deleteFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteFormLayout.createSequentialGroup()
                    .addContainerGap(122, Short.MAX_VALUE)
                    .addComponent(wrongLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(138, Short.MAX_VALUE)))
        );
        deleteFormLayout.setVerticalGroup(
            deleteFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteFormLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(deleteFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(deleteButton)
                .addGap(27, 27, 27))
            .addGroup(deleteFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deleteFormLayout.createSequentialGroup()
                    .addContainerGap(76, Short.MAX_VALUE)
                    .addComponent(wrongLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(66, Short.MAX_VALUE)))
        );

        mainPanel.add(deleteForm);
        deleteForm.setBounds(450, 250, 420, 170);

        modliLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/modli/servicesApp/oie_hearts.gif"))); // NOI18N
        mainPanel.add(modliLabel);
        modliLabel.setBounds(430, 110, 420, 480);

        copyrightsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        copyrightsLabel.setText("Copyrights © 2018 - MODLI - all copyrights reserved ");
        mainPanel.add(copyrightsLabel);
        copyrightsLabel.setBounds(494, 604, 290, 20);

        t7dethform.setBackground(new Color(228,241,254));
        t7dethform.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        t7dethLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        t7dethLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        t7dethLabel.setLabelFor(alasmText);
        t7dethLabel.setText("الرقم العسكري");

        t7dethText.setPreferredSize(new java.awt.Dimension(10, 30));
        t7dethText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t7dethTextActionPerformed(evt);
            }
        });

        updateButton.setBackground(new Color (94,65,129));
        updateButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("تحديث");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        wrongLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        wrongLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        wrongLabel3.setText("عفواً أعد المحاولة ");

        teby.setText("طبي");
        teby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tebyActionPerformed(evt);
            }
        });

        sgn.setText("سجن");
        sgn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sgnActionPerformed(evt);
            }
        });

        undoDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoDateActionPerformed(evt);
            }
        });

        label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label.setText("ميعاد الرجوع للخدمات");

        javax.swing.GroupLayout t7dethformLayout = new javax.swing.GroupLayout(t7dethform);
        t7dethform.setLayout(t7dethformLayout);
        t7dethformLayout.setHorizontalGroup(
            t7dethformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(t7dethformLayout.createSequentialGroup()
                .addGroup(t7dethformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(t7dethformLayout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(updateButton))
                    .addGroup(t7dethformLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(wrongLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(t7dethformLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(t7dethformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(t7dethformLayout.createSequentialGroup()
                        .addGroup(t7dethformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(teby)
                            .addComponent(sgn))
                        .addContainerGap(342, Short.MAX_VALUE))
                    .addGroup(t7dethformLayout.createSequentialGroup()
                        .addGroup(t7dethformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(undoDate, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t7dethText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(t7dethformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t7dethLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))))
        );
        t7dethformLayout.setVerticalGroup(
            t7dethformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, t7dethformLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(t7dethformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t7dethText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t7dethLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(teby)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sgn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(t7dethformLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(t7dethformLayout.createSequentialGroup()
                        .addComponent(undoDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(wrongLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateButton)
                        .addGap(21, 21, 21))
                    .addGroup(t7dethformLayout.createSequentialGroup()
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        mainPanel.add(t7dethform);
        t7dethform.setBounds(450, 250, 420, 280);

        ast3lamTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ast3lamTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "مكتب / سرية", "تاريخ التسريح", "رقم دفعة الأجازة", "الرقم العسكري", "الأسم"
            }
        ));
        ast3lamTable.setRowHeight(30);
        ast3lamTableContainer.setViewportView(ast3lamTable);

        mainPanel.add(ast3lamTableContainer);
        ast3lamTableContainer.setBounds(22, 300, 1240, 90);

        back.setBackground(new Color (94,65,129));
        back.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("رجوع");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        mainPanel.add(back);
        back.setBounds(620, 450, 100, 30);

        khdmatPanel.setBackground(new Color (244,242,243));

        tableContainer.setBackground(new java.awt.Color(0, 0, 0));
        tableContainer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        servTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        servTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        servTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الغفرة الثالثة", "الغفرة الثانية", "الغفرة الأولي", "الحكمدار", "الخدمة"
            }
        ));
        servTable.setRowHeight(20);
        tableContainer.setViewportView(servTable);

        tableTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tableTitle.setText("توزيع الخدمات ليوم");

        todaysDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        todaysDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        JL1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JL1.setText("هيئة تدريب القوات المسلحة");

        JL2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JL2.setText("معهد اللغات للقوات المسلحة");

        JL3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JL3.setText("فرع السكرتارية");

        btnprev.setBackground(new Color (94,65,129));
        btnprev.setForeground(new java.awt.Color(255, 255, 255));
        btnprev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/modli/servicesApp/if_arrow-right-thick_216092.png"))); // NOI18N
        btnprev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprevActionPerformed(evt);
            }
        });

        btnnext.setBackground(new Color (94,65,129));
        btnnext.setForeground(new java.awt.Color(255, 255, 255));
        btnnext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/modli/servicesApp/if_arrow-left-thick_216092.png"))); // NOI18N
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });

        JL4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JL4.setText("ممنوع تغيير اي فرد من أفراد الخدمة من طاقم النوبتجية");

        JL5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JL5.setText("علي من يتظلم التوجه باكر الي فرع السكرتارية");

        btn_print.setBackground(new Color (94,65,129));
        btn_print.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_print.setForeground(new java.awt.Color(255, 255, 255));
        btn_print.setText("طباعة");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout khdmatPanelLayout = new javax.swing.GroupLayout(khdmatPanel);
        khdmatPanel.setLayout(khdmatPanelLayout);
        khdmatPanelLayout.setHorizontalGroup(
            khdmatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, khdmatPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JL3)
                .addGap(69, 69, 69))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, khdmatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(khdmatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(khdmatPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(khdmatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tableContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(khdmatPanelLayout.createSequentialGroup()
                                .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(todaysDate, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tableTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(khdmatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(khdmatPanelLayout.createSequentialGroup()
                                        .addGap(131, 131, 131)
                                        .addGroup(khdmatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(JL1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(JL2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(khdmatPanelLayout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(btnprev, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(khdmatPanelLayout.createSequentialGroup()
                        .addComponent(btn_print)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(khdmatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JL4)
                            .addComponent(JL5))))
                .addGap(37, 37, 37))
        );
        khdmatPanelLayout.setVerticalGroup(
            khdmatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, khdmatPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(JL1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JL2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JL3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(khdmatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(khdmatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnprev, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tableTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(todaysDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(khdmatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(khdmatPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JL4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JL5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(khdmatPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btn_print)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mainPanel.add(khdmatPanel);
        khdmatPanel.setBounds(280, 50, 960, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        setSize(new java.awt.Dimension(1296, 675));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void khdmatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khdmatActionPerformed
        // TODO add your handling code here:\
        tableAgazaContainer.setVisible(false);
        formPanel.setVisible(false);
        ast3lamForm.setVisible(false);
        deleteForm.setVisible(false);
        copyrightsLabel.setVisible(false);
        modliLabel.setVisible(false);
        t7dethform.setVisible(false);
        back.setVisible(false);
        ast3lamTableContainer.setVisible(false);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        ResultSet r = DB.selectServicesByDate(reportDate);
        model.setRowCount(0);
        DateFormat df1 = new SimpleDateFormat(" EEEE , dd/MM/yyyy" , new Locale("ar"));
        String reportDate1 = df1.format(today);
        todaysDate.setText(reportDate1);      
        khdmatPanel.setVisible(true);
        try {
            
            String[] tblData = new String[5];
            while (r.next())
            {
                tblData[4] = r.getString(2);
                tblData[3] = r.getString(3);
                tblData[2] = r.getString(4);
                r.next();
                tblData[1] = r.getString(4);
                r.next();
                tblData[0] = r.getString(4);
                model.insertRow(model.getRowCount(),tblData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_khdmatActionPerformed

    private void agazatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agazatActionPerformed
        // TODO add your handling code here:
        ResultSet inVac = DB.vacationToday();
        khdmatPanel.setVisible(false);
        formPanel.setVisible(false);
        ast3lamForm.setVisible(false);
        deleteForm.setVisible(false);
        copyrightsLabel.setVisible(false);
        modliLabel.setVisible(false);
        back.setVisible(false);
        agazaModel.setRowCount(0);
        tableAgazaContainer.revalidate();
        tableAgazaContainer.repaint();
        t7dethform.setVisible(false);
        formPanel.setVisible(false);
        ast3lamTableContainer.setVisible(false);
        tableAgazaContainer.setVisible(true);

        try {

            String[] tblData = new String[2];
            while (inVac.next())
            {
              
                tblData[0] = inVac.getString(2);
                tblData[1] = inVac.getString(8);
                agazaModel.insertRow(agazaModel.getRowCount(),tblData);
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_agazatActionPerformed

    private void tsgelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tsgelActionPerformed
        // TODO add your handling code here:
        khdmatPanel.setVisible(false);
        tableAgazaContainer.setVisible(false);
        ast3lamForm.setVisible(false);
        deleteForm.setVisible(false);
        copyrightsLabel.setVisible(false);
        modliLabel.setVisible(false);
        t7dethform.setVisible(false);
        ast3lamTableContainer.setVisible(false);
        back.setVisible(false);
        formPanel.setVisible(true); 
    }//GEN-LAST:event_tsgelActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        String userName = userNameText.getText();
        char[] passw = password.getPassword();
        String pass = new String (passw);
        if (userName.equals("admin") & pass.equals("admin"))
        {
            loginForm.setVisible(false);
            menuPanel.setVisible(true);
            egyptLogo.setVisible(true);
            copyrightsLabel.setVisible(true);
            modliLabel.setVisible(true);
        }
        else
        {
            wrongLabel.setVisible(true);
        }
    }//GEN-LAST:event_loginActionPerformed

    private void ast3lamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ast3lamActionPerformed
        // TODO add your handling code here:
        ast3lamModel.setRowCount(0);
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT * FROM soldiers WHERE milNum = ?";
        try {
            preparedStatement = DB.getConnection().prepareStatement(selectSQL);
            preparedStatement.setLong(1, Long.valueOf(rqmAText.getText()));
            ResultSet r = preparedStatement.executeQuery();
            if(r.next())
            {
                
                String[] tblData = new String[5];
                tblData[4] = r.getString(2);
                tblData[3] = r.getString(8); 
                tblData[2] = r.getString(3); 
                tblData[1] = r.getString(5);
                if(r.getInt(4) == 0 )
                {
                   tblData[0] = "سرية";

                }
                else{
                   tblData[0] = "مكتب";

                }
                ast3lamModel.insertRow(ast3lamModel.getRowCount(),tblData);
                ast3lamForm.setVisible(false);
                ast3lamTableContainer.setVisible(true);
                back.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "الرقم العسكري غير موجود");
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage()+"'\n'"+"If you can't pass by, Please contact adiministrator");
        }
        catch(Exception ee)
        {
            JOptionPane.showMessageDialog(null,"Error ! "+ ee.getMessage()+"'\n'"+"If you can't pass by, Please contact adiministrator");
        }
       
    }//GEN-LAST:event_ast3lamActionPerformed

    private void ast3lamButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ast3lamButtonActionPerformed
        // TODO add your handling code here:
        khdmatPanel.setVisible(false);
        tableAgazaContainer.setVisible(false);
        formPanel.setVisible(false);
        deleteForm.setVisible(false);
        copyrightsLabel.setVisible(false);
        modliLabel.setVisible(false);
        t7dethform.setVisible(false);  
        ast3lamTableContainer.setVisible(false);
        back.setVisible(false);
        ast3lamForm.setVisible(true);
    }//GEN-LAST:event_ast3lamButtonActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
         String insertTableSQL = "INSERT INTO soldiers"
		+ "(name, vacGroup, office, layOff, available, exceptions, milNum ) VALUES"
		+ "(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = DB.getConnection().prepareStatement(insertTableSQL);
            preparedStatement.setString(1,alasmText.getText());
            preparedStatement.setInt(2,Integer.parseInt(vacText.getText()));
            if(mktbBtn.isSelected()  ){
                preparedStatement.setInt(3,1);
            }
            if(sryaBtn.isSelected()  ){
                preparedStatement.setInt(3,0);
            }
            preparedStatement.setString(4,tre5Tsre7Text.getText());
            preparedStatement.setInt(5,1);
            if(a3fa.isSelected())
            {
               preparedStatement.setInt(6,1);

            }
            else
            {
               preparedStatement.setInt(6,0);

            }
            preparedStatement.setLong(7,Long.valueOf(rqmAskaryText.getText()));
            boolean state = preparedStatement.execute();
           
                alasmText.setText("");
                vacText.setText("");
                tre5Tsre7Text.setText("");
                tre5altgnedText.setText("");
                rqmAskaryText.setText("");
                rqmQwmyText.setText("");
                anwanText.setText("");
                mktbBtn.setSelected(false);
                sryaBtn.setSelected(false);
                a3fa.setSelected(false);
                JOptionPane.showMessageDialog(null, "Database changed successfully"); 
       
          
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage()+"'\n'"+"If you can't pass by, Please contact adiministrator");
        }
        catch(Exception ee)
        {
            JOptionPane.showMessageDialog(null,"Error ! "+ ee.getMessage()+"'\n'"+"If you can't pass by, Please contact adiministrator");
        }
        
    }//GEN-LAST:event_submitActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        String deleteSQL = "DELETE FROM soldiers WHERE milNum = ?";
        PreparedStatement preparedStatement = null;
        try {
            int state = 0  ; 
            int error = 1;
            Statement stmt = DB.getConnection().createStatement();
            String selectSQL = "SELECT name FROM soldiers WHERE milNum = ?";
            preparedStatement = DB.getConnection().prepareStatement(selectSQL);
            preparedStatement.setLong(1, Long.valueOf(deleteText.getText()));
            String nameToDelete = "";
            ResultSet r = preparedStatement.executeQuery();
            if (r.next())
            {    
                 nameToDelete = r.getString(1);
            
            }
            if (nameToDelete.equals(""))
            {
               JOptionPane.showMessageDialog(null, "الرقم العسكري غير موجود");

            }
            else
            {
                int dialogResult = JOptionPane.showConfirmDialog (null, "You are about to delete "+nameToDelete,"Warning",JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                stmt.execute("SET FOREIGN_KEY_CHECKS=0");
                preparedStatement = DB.getConnection().prepareStatement(deleteSQL);
                preparedStatement.setLong(1, Long.valueOf(deleteText.getText()));
                // execute delete SQL stetement
                state = preparedStatement.executeUpdate();
            }
                else{
                      JOptionPane.showMessageDialog(null, "تم الإلغاء" );
                      error = 0 ;
                }
           
            }
            
            if (state == 0 & error == 1 )
            {
                JOptionPane.showMessageDialog(null, "Error!, Please check your inputs");
            }
            if(state == 1)
            {
                 JOptionPane.showMessageDialog(null, "Database changed successfully");
                 deleteText.setText("");
                 stmt.execute("SET FOREIGN_KEY_CHECKS=1");
    
            }
        } catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error ! "+ ex.getMessage()+"'\n'"+"If you can't pass by, Please contact adiministrator");

        }
        
        
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void deleteMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuButtonActionPerformed
        // TODO add your handling code here:
        tableAgazaContainer.setVisible(false);
        khdmatPanel.setVisible(false);
        formPanel.setVisible(false);
        ast3lamForm.setVisible(false);
        copyrightsLabel.setVisible(false);
        modliLabel.setVisible(false);
        t7dethform.setVisible(false);
        ast3lamTableContainer.setVisible(false);
        back.setVisible(false);
        deleteForm.setVisible(true);

    }//GEN-LAST:event_deleteMenuButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        int state = 0  ; 
        int error = 1;
        PreparedStatement preparedStatement;
        try{
        Statement stmt = DB.getConnection().createStatement();
        String selectSQL = "SELECT name FROM soldiers WHERE milNum = ?";
        preparedStatement = DB.getConnection().prepareStatement(selectSQL);
        preparedStatement.setLong(1, Long.valueOf(t7dethText.getText()));
        String nameToupdate = "";
        Boolean TebyVar = true;
        ResultSet r = preparedStatement.executeQuery();
        if(sgn.isSelected())
        {
          
            TebyVar = false;
        }
        if (r.next())
        {    
             nameToupdate = r.getString(1);

        }
        if (nameToupdate.equals(""))
        {
           JOptionPane.showMessageDialog(null, "الرقم العسكري غير موجود");

        }
        else
         {
            int dialogResult = JOptionPane.showConfirmDialog (null, "الأسم : " +nameToupdate,"Warning",JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
            String updateTableSQL = "UPDATE soldiers SET exceptions = ? WHERE milNum = ?";
            preparedStatement = DB.getConnection().prepareStatement(updateTableSQL);
            preparedStatement.setInt(1, 1);
            preparedStatement.setLong(2,Long.parseLong(t7dethText.getText()));
            // execute insert SQL stetement
            preparedStatement .executeUpdate();
            String insertTableSQL = "INSERT INTO sgn_mardi"
		+ "(milNum, startDate, endDate, sick) VALUES"
		+ "(?,?,?,?)";
            preparedStatement = DB.getConnection().prepareStatement(insertTableSQL);
            
            preparedStatement.setString(3,undoDate.getText());
            preparedStatement.setLong(1,Long.parseLong(t7dethText.getText()));
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat ("YYYY/MM/dd");  
            java.util.Date dateToReterieve = cal.getTime();
            String datetoday = sdf.format(dateToReterieve);
            preparedStatement.setString(2,datetoday);
            preparedStatement.setBoolean(4,TebyVar);
                // execute insert SQL stetement
            boolean execute = preparedStatement .execute();
            
            
            }
            
            else{
                          JOptionPane.showMessageDialog(null, "تم الإلغاء" );
                          error = 0 ;
                    }

                }

           
        }
        catch (SQLException ex) {
            Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Error ! "+ ex.getMessage()+"'\n'"+"If you can't pass by, Please contact adiministrator");

        }
        
    }//GEN-LAST:event_updateButtonActionPerformed

    private void sgnTebyMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sgnTebyMenuActionPerformed
        // TODO add your handling code here:
        khdmatPanel.setVisible(false);
        tableAgazaContainer.setVisible(false);
        formPanel.setVisible(false);
        ast3lamForm.setVisible(false);
        copyrightsLabel.setVisible(false);
        modliLabel.setVisible(false);
        deleteForm.setVisible(false);
        ast3lamTableContainer.setVisible(false);
        back.setVisible(false);
        t7dethform.setVisible(true);
    }//GEN-LAST:event_sgnTebyMenuActionPerformed

    private void sgnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sgnActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_sgnActionPerformed

    private void tebyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tebyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tebyActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        ast3lamTableContainer.setVisible(false);
        ast3lamForm.setVisible(true);
        back.setVisible(false);
    }//GEN-LAST:event_backActionPerformed

    private void btnprevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprevActionPerformed
        // TODO add your handling code here:
        
        String day = todaysDate.getText();
        btnnext.setEnabled(true);
        int comma = day.indexOf(',');
    	if(day.substring(0, comma).equals(" الاثنين "))
        {
            btnprev.setEnabled(false);
        }
        else 
        {
        
        String someDate = todaysDate.getText().substring(comma+2, todaysDate.getText().length());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat(" EEEE , dd/MM/yyyy" , new Locale("ar"));
            try {
                java.util.Date date = sdf.parse(someDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DATE, -1);
                java.util.Date dateToReterieve = cal.getTime();
                String dateLabel = sdf1.format(dateToReterieve);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                todaysDate.setText(dateLabel);
                try{
                    ResultSet r = DB.selectServicesByDate(df.format(dateToReterieve));
                    model.setRowCount(0);
                    String[] tblData = new String[4];
                    while (r.next())
                    {
                        tblData[3] = r.getString(2);
                        tblData[2] = r.getString(3);
                        r.next();
                        tblData[1] = r.getString(3);
                        r.next();
                        tblData[0] = r.getString(3);
                        model.insertRow(model.getRowCount(),tblData);
                    }
                }
                    catch (SQLException ex) {
                        Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            } catch (ParseException ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        }
    }//GEN-LAST:event_btnprevActionPerformed

    private void btnnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextActionPerformed
        // TODO add your handling code here:
            String day = todaysDate.getText();
        int comma = day.indexOf(',');
        btnprev.setEnabled(true);
    	if(day.substring(0, comma).equals(" الأحد "))
        {
            btnnext.setEnabled(false);
        }
        else 
        {
        String someDate = todaysDate.getText().substring(comma+2, todaysDate.getText().length());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat(" EEEE , dd/MM/yyyy" , new Locale("ar"));
            try {
                java.util.Date date = sdf.parse(someDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DATE, 1);
                java.util.Date dateToReterieve = cal.getTime();
                String dateLabel = sdf1.format(dateToReterieve);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                todaysDate.setText(dateLabel);
                try{
                    ResultSet r = DB.selectServicesByDate(df.format(dateToReterieve));
                    model.setRowCount(0);
                    String[] tblData = new String[4];
                    while (r.next())
                    {
                        tblData[3] = r.getString(2);
                        tblData[2] = r.getString(3);
                        r.next();
                        tblData[1] = r.getString(3);
                        r.next();
                        tblData[0] = r.getString(3);
                        model.insertRow(model.getRowCount(),tblData);
                    }
                }
                    catch (SQLException ex) {
                        Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            } catch (ParseException ex) {
                Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        }
    }//GEN-LAST:event_btnnextActionPerformed
    
    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
        Toolkit tkp = khdmatPanel.getToolkit();
        PrintJob pjp = tkp.getPrintJob(this, null, null);
        Graphics g = pjp.getGraphics();
        khdmatPanel.print(g);
        g.dispose();
        pjp.end();
        
    }//GEN-LAST:event_btn_printActionPerformed

    private void t7dethTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t7dethTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t7dethTextActionPerformed

    private void undoDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_undoDateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new home().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(home.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JL1;
    private javax.swing.JLabel JL2;
    private javax.swing.JLabel JL3;
    private javax.swing.JLabel JL4;
    private javax.swing.JLabel JL5;
    private javax.swing.JCheckBox a3fa;
    private javax.swing.JTable agazaTable;
    private javax.swing.JButton agazat;
    private javax.swing.JLabel alasmLabel;
    private javax.swing.JTextField alasmText;
    private javax.swing.JLabel anwanLabel;
    private javax.swing.JTextField anwanText;
    private javax.swing.JButton ast3lam;
    private javax.swing.JButton ast3lamButton;
    private javax.swing.JPanel ast3lamForm;
    private javax.swing.JTable ast3lamTable;
    private javax.swing.JScrollPane ast3lamTableContainer;
    private javax.swing.JButton back;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnprev;
    private javax.swing.JLabel copyrightsLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel deleteForm;
    private javax.swing.JLabel deleteLabel;
    private javax.swing.JButton deleteMenuButton;
    private javax.swing.JTextField deleteText;
    private javax.swing.JLabel egyptLogo;
    private javax.swing.JPanel formPanel;
    private javax.swing.JButton khdmat;
    private javax.swing.JPanel khdmatPanel;
    private javax.swing.JLabel label;
    private javax.swing.JButton login;
    private javax.swing.JPanel loginForm;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JRadioButton mktbBtn;
    private javax.swing.JLabel modliLabel;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel rqmALabel;
    private javax.swing.JTextField rqmAText;
    private javax.swing.JTextField rqmAskaryText;
    private javax.swing.JLabel rqmQwmyLabel;
    private javax.swing.JTextField rqmQwmyText;
    private javax.swing.JLabel rqmaskaryLabel;
    private javax.swing.JTable servTable;
    private javax.swing.JRadioButton sgn;
    private javax.swing.JButton sgnTebyMenu;
    private javax.swing.JRadioButton sryaBtn;
    private javax.swing.JButton submit;
    private javax.swing.JLabel t7dethLabel;
    private javax.swing.JTextField t7dethText;
    private javax.swing.JPanel t7dethform;
    private javax.swing.JScrollPane tableAgazaContainer;
    private javax.swing.JScrollPane tableContainer;
    private javax.swing.JLabel tableTitle;
    private javax.swing.JRadioButton teby;
    private javax.swing.JLabel todaysDate;
    private javax.swing.JLabel tre5TgnedLabel;
    private javax.swing.JLabel tre5Tsre7Label;
    private javax.swing.JTextField tre5Tsre7Text;
    private javax.swing.JTextField tre5altgnedText;
    private javax.swing.JButton tsgel;
    private javax.swing.JTextField undoDate;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel userName;
    private javax.swing.JTextField userNameText;
    private javax.swing.JLabel vacLabel;
    private javax.swing.JTextField vacText;
    private javax.swing.JLabel wrongLabel;
    private javax.swing.JLabel wrongLabel1;
    private javax.swing.JLabel wrongLabel2;
    private javax.swing.JLabel wrongLabel3;
    // End of variables declaration//GEN-END:variables
   
}
