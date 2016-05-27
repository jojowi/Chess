/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.assault2142.hololol.chess.client.menus;

import eu.assault2142.hololol.chess.client.game.LocalGame;
import eu.assault2142.hololol.chess.client.networking.ServerConnection;
import eu.assault2142.hololol.chess.client.util.Translator;
import eu.assault2142.hololol.chess.game.Settings;
import eu.assault2142.hololol.chess.networking.ServerMessages;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.AncestorEvent;
import javax.swing.event.ChangeEvent;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author hololol2
 */
public class MainMenu extends javax.swing.JFrame implements IMenu {

    public static MainMenu MAINMENU;
    private ServerConnection client;
    private final HashMap<String, Pair<Integer, JTextArea>> areas = new HashMap();
    private static Locale locale = Locale.ENGLISH;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        init();
    }

    @Override
    public void challengeDeclined(String username) {
        JOptionPane.showMessageDialog(MainMenu.MAINMENU, username + " declined your Challenge!", "Challenge Declined", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void enemyOffline() {
        JOptionPane.showMessageDialog(MainMenu.MAINMENU, Translator.getString("GAME_ENEMYOFF_TEXT"), Translator.getString("GAME_ENEMYOFF_HEAD"), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void friendRequest(String username) {
        int addfriend = JOptionPane.showConfirmDialog(MainMenu.MAINMENU, username + Translator.getString("FRIENDREQ_ADD_TEXT"), Translator.getString("FRIENDREQ_ADD_HEAD"), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (addfriend == JOptionPane.NO_OPTION) {
            client.write(ServerMessages.DeclineFriend, new Object[]{username});
        } else if (addfriend == JOptionPane.YES_OPTION) {
            client.write(ServerMessages.AcceptFriend, new Object[]{username});
        }
    }

    @Override
    public void gameChallenge(String username) {
        int selected = JOptionPane.showConfirmDialog(MainMenu.MAINMENU, java.text.MessageFormat.format(Translator.getString("GAME_START?_TEXT"), new Object[]{username}), Translator.getString("GAME_START?_HEAD"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (selected == JOptionPane.YES_OPTION) {
            client.write(ServerMessages.AcceptGame, new Object[]{username});
        } else {
            client.write(ServerMessages.DeclineGame, new Object[]{username});
        }
    }

    @Override
    public void infoMessage(String text) {
        JOptionPane.showMessageDialog(MainMenu.MAINMENU, text, Translator.getString("DIALOG_INFO_HEAD"), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void passwordChanged() {
        JOptionPane.showMessageDialog(MainMenu.MAINMENU, Translator.getString("PASSCHANGED_TEXT"), Translator.getString("PASSCHANGED_HEAD"), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void unknownUsername() {
        JOptionPane.showMessageDialog(MainMenu.MAINMENU, "There is no User with this name!", "Unknown Username", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void usernameChanged(String newname) {
        JOptionPane.showMessageDialog(MainMenu.MAINMENU, java.text.MessageFormat.format(Translator.getString("NAMECHANGED_TEXT"), new Object[]{newname}), Translator.getString("NAMECHANGED_HEAD"), JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void usernameTaken() {
        JOptionPane.showMessageDialog(MainMenu.MAINMENU, Translator.getString("NAMECHANGE_TAKEN_TEXT"), Translator.getString("NAMECHANGE_TAKEN_HEAD"), JOptionPane.WARNING_MESSAGE);
    }

    private void init() {
        MAINMENU = this;
        jTabbedPane1.remove(jPanel6);
        jTabbedPane1.remove(jPanel7);
        initFriendsMenu();

        //Add listener to components that can bring up popup menus.
        /*if (Translator.TRANSLATOR.getLanguage().equals(Translator.EN)) {
            jComboBox2.setSelectedIndex(0);
        } else {
            jComboBox2.setSelectedIndex(1);
        }*/
    }

    private void initFriendsMenu() {
        JPopupMenu popup;
        JMenuItem menuItem;
        popup = new JPopupMenu();
        menuItem = new JMenuItem(Translator.getString("MENU_CHALLENGE"));
        menuItem.addActionListener((ActionEvent e) -> {
            client.write(ServerMessages.FriendGame, new Object[]{jList1.getSelectedValue()});
        });
        popup.add(menuItem);
        menuItem = new JMenuItem(Translator.getString("MENU_MESSAGE"));
        menuItem.addActionListener((ActionEvent e) -> {
            String from = jList1.getSelectedValue();
            if (!areas.containsKey(from)) {
                JTextArea text = new JTextArea();
                text.setEditable(false);
                JScrollPane scroll = new JScrollPane(text);

                jTabbedPane2.add(scroll, jList1.getSelectedValue());
                jTabbedPane2.setSelectedComponent(scroll);

                areas.put(from, Pair.of(jTabbedPane2.getSelectedIndex(), text));
                jTabbedPane2.setTabComponentAt(jTabbedPane2.getSelectedIndex(), new ButtonTabComponent(jTabbedPane2, this, from));
            } else {
                jTabbedPane2.setSelectedIndex(areas.get(from).getLeft());
            }
        });
        popup.add(menuItem);
        menuItem = new JMenuItem(Translator.getString("MENU_REMOVE"));
        menuItem.addActionListener((ActionEvent e) -> {
            client.write(ServerMessages.RemoveFriend, new Object[]{jList1.getSelectedValue()});
        });
        popup.add(menuItem);

        MouseListener popupListener = new PopupListener(popup);
        jList1.addMouseListener(popupListener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField4 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("eu/assault2142/hololol/chess/client/menus/Bundle"); // NOI18N
        setTitle(bundle.getString("MainMenu.title")); // NOI18N
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setResizable(false);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTabbedPane1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jButton6.setText(bundle.getString("MainMenu.jButton6.text")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton1.setText(bundle.getString("MainMenu.jRadioButton1.text")); // NOI18N
        jRadioButton1.setEnabled(false);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton2.setText(bundle.getString("MainMenu.jRadioButton2.text")); // NOI18N
        jRadioButton2.setEnabled(false);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton3.setSelected(true);
        jRadioButton3.setText(bundle.getString("MainMenu.jRadioButton3.text")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(222, 222, 222)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jRadioButton2)
                    .add(jRadioButton3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jRadioButton1)
                    .add(jButton6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(260, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .add(78, 78, 78)
                .add(jRadioButton3)
                .add(34, 34, 34)
                .add(jRadioButton2)
                .add(38, 38, 38)
                .add(jRadioButton1)
                .add(36, 36, 36)
                .add(jButton6)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("MainMenu.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel2FocusGained(evt);
            }
        });

        jCheckBox2.setText(bundle.getString("MainMenu.jCheckBox2.text")); // NOI18N
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jButton1.setText(bundle.getString("MainMenu.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText(bundle.getString("MainMenu.jLabel1.text")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText(bundle.getString("MainMenu.jLabel2.text")); // NOI18N

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(191, 191, 191)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(jPasswordField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jCheckBox2))
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 160, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(280, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(65, 65, 65)
                .add(jLabel1)
                .add(18, 18, 18)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(27, 27, 27)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPasswordField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jCheckBox2)
                .add(18, 18, 18)
                .add(jButton1)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("MainMenu.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jList1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jButton7.setText(bundle.getString("MainMenu.jButton7.text")); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText(bundle.getString("MainMenu.jButton8.text")); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText(bundle.getString("MainMenu.jButton9.text")); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText(bundle.getString("MainMenu.jButton10.text")); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText(bundle.getString("MainMenu.jButton11.text")); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextArea1FocusGained(evt);
            }
        });
        jScrollPane2.setViewportView(jTextArea1);

        jTabbedPane2.addTab(bundle.getString("MainMenu.jScrollPane2.TabConstraints.tabTitle"), jScrollPane2); // NOI18N

        jTextField4.setEnabled(false);

        jButton12.setText(bundle.getString("MainMenu.jButton12.text")); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText(bundle.getString("MainMenu.jButton13.text")); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTabbedPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 412, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jButton7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jButton13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(jPanel6Layout.createSequentialGroup()
                        .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 325, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jButton12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(28, 28, 28)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 132, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 132, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                        .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jPanel6Layout.createSequentialGroup()
                                .add(jButton8)
                                .add(18, 18, 18)
                                .add(jButton10)
                                .add(18, 18, 18)
                                .add(jButton11))
                            .add(jPanel6Layout.createSequentialGroup()
                                .add(jButton7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jButton13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 45, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(36, 36, 36)
                        .add(jTabbedPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 270, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton12)
                    .add(jButton9))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("MainMenu.jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 628, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 370, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(bundle.getString("MainMenu.jPanel7.TabConstraints.tabTitle"), jPanel7); // NOI18N

        jButton2.setText(bundle.getString("MainMenu.jButton2.text")); // NOI18N

        jLabel3.setPreferredSize(new java.awt.Dimension(68, 14));

        jButton3.setText(bundle.getString("MainMenu.jButton3.text")); // NOI18N

        jButton4.setText(bundle.getString("MainMenu.jButton4.text")); // NOI18N

        jLabel4.setText(bundle.getString("MainMenu.jLabel4.text")); // NOI18N

        jLabel5.setText(bundle.getString("MainMenu.jLabel5.text")); // NOI18N

        jLabel6.setText(bundle.getString("MainMenu.jLabel6.text")); // NOI18N

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .add(34, 34, 34)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel5)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jButton3))
                .add(155, 155, 155)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 165, Short.MAX_VALUE)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel4)
                    .add(jButton4)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6))
                .add(44, 44, 44))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(37, 37, 37)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 90, Short.MAX_VALUE)
                        .add(jButton2)
                        .add(23, 23, 23))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jLabel4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)))
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(27, 27, 27)
                        .add(jButton4))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(29, 29, 29)
                        .add(jButton3)))
                .addContainerGap(120, Short.MAX_VALUE))
            .add(jSeparator2)
        );

        jTabbedPane1.addTab(bundle.getString("MainMenu.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        jLabel8.setText(bundle.getString("MainMenu.jLabel8.text")); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Deutsch" }));

        jButton5.setText(bundle.getString("MainMenu.jButton5.text")); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(41, 41, 41)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabel8)
                    .add(jComboBox2, 0, 180, Short.MAX_VALUE)
                    .add(jButton5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(410, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel8)
                .add(18, 18, 18)
                .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 256, Short.MAX_VALUE)
                .add(jButton5)
                .add(37, 37, 37))
        );

        jTabbedPane1.addTab(bundle.getString("MainMenu.jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 636, Short.MAX_VALUE)
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jTabbedPane1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 407, Short.MAX_VALUE)
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 636, Short.MAX_VALUE)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 407, Short.MAX_VALUE)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jButton6ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new LocalGame();
        setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        connectToServer();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        saveSettings();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTabbedPane1AncestorAdded(AncestorEvent evt) {//GEN-FIRST:event_jTabbedPane1AncestorAdded

    }//GEN-LAST:event_jTabbedPane1AncestorAdded

    private void jButton9ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        logout();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        String input = JOptionPane.showInputDialog(this, Translator.getString("ADDFRIENDDIALOG_TEXT"), Translator.getString("ADDFRIENDDIALOG_HEAD"), JOptionPane.PLAIN_MESSAGE);
        if (input != null) {
            client.write(ServerMessages.AddFriend, new Object[]{input});
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        client.write(ServerMessages.FriendGame, new Object[]{jList1.getSelectedValue()});
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextArea1FocusGained(FocusEvent evt) {//GEN-FIRST:event_jTextArea1FocusGained

    }//GEN-LAST:event_jTextArea1FocusGained

    private void jTabbedPane2StateChanged(ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        if (jTabbedPane2.getSelectedIndex() == 0) {
            jTextField4.setEnabled(false);
            jButton12.setEnabled(false);
        } else {
            jTextField4.setEnabled(true);
            jButton12.setEnabled(true);
        }
    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void jList1MouseClicked(MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked

    }//GEN-LAST:event_jList1MouseClicked

    private void jList1MousePressed(MouseEvent evt) {//GEN-FIRST:event_jList1MousePressed
        if (evt.getButton() == MouseEvent.BUTTON3) {
            jList1.setSelectedIndex(jList1.locationToIndex(evt.getPoint()));
        }
    }//GEN-LAST:event_jList1MousePressed

    private void jButton12ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        writeMessage();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        changePassword();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        String input = JOptionPane.showInputDialog(this, Translator.getString("CHANGENAME_TEXT"), Translator.getString("CHANGENAME_HEAD"), JOptionPane.PLAIN_MESSAGE);
        if (input != null) {
            client.write(ServerMessages.ChangeUsername, new Object[]{input});
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        client.write(ServerMessages.RandomGame, null);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jCheckBox2ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        jTextField1.requestFocus();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jPanel2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel2FocusGained

    }//GEN-LAST:event_jPanel2FocusGained

    public void loggedIn(ServerConnection c) {
        jTabbedPane1.remove(jPanel2);
        jTabbedPane1.add(jPanel6, 1);
        jTabbedPane1.setTitleAt(1, "Online" + c.getName());
        jTabbedPane1.setSelectedIndex(1);
        jTabbedPane2.removeAll();
        JTextArea text = new JTextArea();
        text.setEditable(false);
        JScrollPane scroll = new JScrollPane(text);
        areas.clear();
        jTabbedPane2.add(scroll, Translator.getString("TABBEDPANE_INFO"));
        jTabbedPane2.setSelectedComponent(scroll);
        client = c;

    }

    public void updateFriends(String[] friends) {
        jList1.removeAll();
        jList1.setListData(friends);
    }

    public void setPlayerName(String name) {
        jTabbedPane1.setTitleAt(1, "Online: " + name);
    }

    public String getTime() {
        GregorianCalendar gc = new GregorianCalendar();
        return gc.get(GregorianCalendar.HOUR_OF_DAY) + ":" + gc.get(GregorianCalendar.MINUTE);
    }

    public void newMessage(String from, String msg) {
        Pair<Integer, JTextArea> pair = areas.get(from);
        JTextArea area;
        if (pair == null) {
            area = new JTextArea();
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            jTabbedPane2.add(scroll, from);
            jTabbedPane2.setSelectedComponent(scroll);
            areas.put(from, Pair.of(jTabbedPane2.getSelectedIndex(), area));
            jTabbedPane2.setTabComponentAt(jTabbedPane2.getSelectedIndex(), new ButtonTabComponent(jTabbedPane2, this, from));
        } else {
            area = pair.getRight();
        }
        jTabbedPane2.setSelectedIndex(areas.get(from).getLeft());
        area.append("[" + getTime() + "] " + from + ": " + msg + System.lineSeparator());
    }

    public void removeTextArea(String name) {
        areas.remove(name);
    }

    public void enableLoginButton() {
        jButton1.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

    class PopupListener extends MouseAdapter {

        private JPopupMenu popup;

        public PopupListener(JPopupMenu pop) {
            popup = pop;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                        e.getX(), e.getY());

            }
        }

    }

    private void connectToServer() {
        String text = jTextField1.getText();
        Settings.SETTINGS.username = text;
        if (text.length() < 4) {
            JOptionPane.showMessageDialog(MainMenu.MAINMENU, Translator.getString("NAMETOOSHORT_TEXT"), Translator.getString("NAMETOOSHORT_HEAD"), JOptionPane.WARNING_MESSAGE);
        } else if (jPasswordField1.getPassword().length < 4) {
            JOptionPane.showMessageDialog(this, Translator.getString("PASSTOOSHORT_TEXT"), Translator.getString("PASSTOOSHORT_HEAD"), JOptionPane.WARNING_MESSAGE);
        } else {
            jButton1.setEnabled(false);
            ServerConnection.connect(jTextField1.getText(), new String(jPasswordField1.getPassword()), jCheckBox2.isSelected());
            jPasswordField1.setText("");
            jCheckBox2.setSelected(false);
        }
    }

    private void saveSettings() {
        if (jComboBox2.getSelectedIndex() == 1) {
            locale = new Locale("de", "DE");
        } else {
            locale = new Locale("en", "US");
        }
        Locale.setDefault(locale);
        Translator.setLanguage(locale);
        JOptionPane.setDefaultLocale(locale);
        ResourceBundle.clearCache();
        setVisible(false);
        MainMenu main = new MainMenu();
        main.setVisible(true);
        main.setLocation(this.getLocation());
        MAINMENU = main;
    }

    private void logout() {
        client.write(ServerMessages.Logout, new Object[0]);

        jTabbedPane1.remove(jPanel6);
        jTabbedPane1.add(jPanel2, 1);
        jTabbedPane1.setTitleAt(1, "Online");
        jTabbedPane1.setSelectedIndex(1);
        jButton1.setEnabled(true);
    }

    private void writeMessage() {
        String msg = jTextField4.getText();
        String name = jTabbedPane2.getTitleAt(jTabbedPane2.getSelectedIndex());
        if (msg.isEmpty()) {
            return;
        }
        areas.get(name).getRight().append("[" + getTime() + "] " + client.getName() + ": " + msg + System.lineSeparator());
        client.write(ServerMessages.Message, new Object[]{name, msg.replace(" ", "_")});
        jTextField4.setText("");
    }

    private void changePassword() {
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(
                this,
                passwordField,
                Translator.getString("CHANGEPASS_HEAD"),
                JOptionPane.OK_CANCEL_OPTION);
        String input = new String(passwordField.getPassword());
        if (option == JOptionPane.OK_OPTION) {
            if (input.length() > 3) {
                client.write(ServerMessages.ChangePassword, new Object[]{input});
            } else {
                JOptionPane.showMessageDialog(this, Translator.getString("PASSTOOSHORT_TEXT"), Translator.getString("PASSTOOSHORT_HEAD"), JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
