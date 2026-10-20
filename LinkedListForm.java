
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author User
 */
public class LinkedListForm extends javax.swing.JFrame {
    // ===== Data structures =====
private final StringLinkedList sList = new StringLinkedList();
private final SinglyLinkedListPerson singly = new SinglyLinkedListPerson();
private final DoublyLinkedListPerson doubly = new DoublyLinkedListPerson();

// ===== UI components =====

// Tab 1: String list

// Tab 2: Person list

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LinkedListForm.class.getName());

    /**
     * Creates new form LinkedListForm
     */
    public LinkedListForm() {
        super("Linked List Exercises - JFrame Form");
    initUI();
    attachEvents();
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(820, 600);
    setLocationRelativeTo(null);
        initComponents();
    }

    private void initUI() {
    tabs = new JTabbedPane();
    tabs.addTab("String List", buildStringTab());
    tabs.addTab("Person List", buildPersonTab());
    setContentPane(tabs);
}

private JPanel buildStringTab() {
    JPanel panel = new JPanel(new BorderLayout(8, 8));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
    spGenCount = new JSpinner(new SpinnerNumberModel(5, 1, 1000, 1));
    btnGenerate = new JButton("Generate (n)");
    btnAddFirstRandom = new JButton("Add First (Random)");
    btnShowStr = new JButton("Show");
    btnClearStr = new JButton("Clear");

    top.add(new JLabel("n:"));
    top.add(spGenCount);
    top.add(btnGenerate);
    top.add(btnAddFirstRandom);
    top.add(btnShowStr);
    top.add(btnClearStr);

    taString = new JTextArea(12, 60);
    taString.setEditable(false);
    JScrollPane scroll = new JScrollPane(taString);

    panel.add(top, BorderLayout.NORTH);
    panel.add(scroll, BorderLayout.CENTER);
    return panel;
}

private JPanel buildPersonTab() {
    JPanel panel = new JPanel(new BorderLayout(8, 8));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel top = new JPanel(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();
    gc.insets = new Insets(4, 4, 4, 4);
    gc.anchor = GridBagConstraints.WEST;

    rdoSingly = new JRadioButton("Singly", true);
    rdoDoubly = new JRadioButton("Doubly");
    grpType = new ButtonGroup();
    grpType.add(rdoSingly);
    grpType.add(rdoDoubly);

    txtName = new JTextField(12);
    spAge = new JSpinner(new SpinnerNumberModel(20, 0, 150, 1));
    spIndex = new JSpinner(new SpinnerNumberModel(0, 0, 1_000_000, 1));

    btnAddFirstP = new JButton("Add First");
    btnAddLastP = new JButton("Add Last");
    btnInsertP = new JButton("Insert @index (Doubly)");
    btnRemoveAtP = new JButton("Remove @index (Doubly)");
    btnPrintForwardP = new JButton("Print Forward");
    btnPrintReverseP = new JButton("Print Reverse (Doubly)");
    btnAverageP = new JButton("Average Age");
    btnClearPerson = new JButton("Clear (Both)");

    int col = 0, row = 0;
    gc.gridx = col++; gc.gridy = row; top.add(new JLabel("List Type:"), gc);
    gc.gridx = col++; top.add(rdoSingly, gc);
    gc.gridx = col++; top.add(rdoDoubly, gc);

    col = 0; row++;
    gc.gridx = col++; gc.gridy = row; top.add(new JLabel("Name:"), gc);
    gc.gridx = col++; top.add(txtName, gc);
    gc.gridx = col++; top.add(new JLabel("Age:"), gc);
    gc.gridx = col++; top.add(spAge, gc);

    col = 0; row++;
    gc.gridx = col++; gc.gridy = row; top.add(new JLabel("Index:"), gc);
    gc.gridx = col++; top.add(spIndex, gc);

    col = 0; row++;
    gc.gridx = col++; gc.gridy = row; top.add(btnAddFirstP, gc);
    gc.gridx = col++; top.add(btnAddLastP, gc);
    gc.gridx = col++; top.add(btnInsertP, gc);
    gc.gridx = col++; top.add(btnRemoveAtP, gc);

    col = 0; row++;
    gc.gridx = col++; gc.gridy = row; top.add(btnPrintForwardP, gc);
    gc.gridx = col++; top.add(btnPrintReverseP, gc);
    gc.gridx = col++; top.add(btnAverageP, gc);
    gc.gridx = col++; top.add(btnClearPerson, gc);

    taPerson = new JTextArea(12, 60);
    taPerson.setEditable(false);
    JScrollPane scroll = new JScrollPane(taPerson);

    lblStatus = new JLabel("Ready.");

    panel.add(top, BorderLayout.NORTH);
    panel.add(scroll, BorderLayout.CENTER);
    panel.add(lblStatus, BorderLayout.SOUTH);

    updateButtonsByType();
    return panel;
}

private void attachEvents() {
    // Tab1: String
    btnGenerate.addActionListener(e -> {
        int n = (Integer) spGenCount.getValue();
        sList.generate(n);
        taString.setText(sList.toText());
    });
    btnAddFirstRandom.addActionListener(e -> {
        sList.addFirstRandom();
        taString.setText(sList.toText());
    });
    btnShowStr.addActionListener(e -> taString.setText(sList.toText()));
    btnClearStr.addActionListener(e -> {
        sList.clear();
        taString.setText("");
    });

    // Tab2: Person
    ActionListener typeListener = e -> updateButtonsByType();
    rdoSingly.addActionListener(typeListener);
    rdoDoubly.addActionListener(typeListener);

    btnAddFirstP.addActionListener(e -> {
        Person p = readPerson();
        if (p == null) return;
        if (isSingly()) singly.addFirst(p); else doubly.addFirst(p);
        printForwardToArea();
    });
    btnAddLastP.addActionListener(e -> {
        Person p = readPerson();
        if (p == null) return;
        if (isSingly()) singly.addLast(p); else doubly.addLast(p);
        printForwardToArea();
    });
    btnInsertP.addActionListener(e -> {
        if (isSingly()) {
            info("Insert/Remove ทำใน Doubly เท่านั้น");
            return;
        }
        int idx = (Integer) spIndex.getValue();
        Person p = readPerson();
        if (p == null) return;
        doubly.insert(idx, p);
        printForwardToArea();
    });
    btnRemoveAtP.addActionListener(e -> {
        if (isSingly()) {
            info("Insert/Remove ทำใน Doubly เท่านั้น");
            return;
        }
        int idx = (Integer) spIndex.getValue();
        boolean ok = doubly.removeAt(idx);
        info(ok ? "Removed index " + idx : "Index ไม่ถูกต้อง");
        printForwardToArea();
    });
    btnPrintForwardP.addActionListener(e -> printForwardToArea());
    btnPrintReverseP.addActionListener(e -> {
        if (isSingly()) {
            info("Reverse ทำใน Doubly เท่านั้น");
        } else {
            taPerson.setText(doubly.toTextReverse());
        }
    });
    btnAverageP.addActionListener(e -> {
        double avg = isSingly() ? singly.getAverageAge() : doubly.getAverageAge();
        info(String.format("Average age = %.2f", avg));
    });
    btnClearPerson.addActionListener(e -> {
        singly.clear();
        doubly.clear();
        taPerson.setText("");
        info("Cleared both lists.");
    });
}

private boolean isSingly() {
    return rdoSingly.isSelected();
}

private void updateButtonsByType() {
    boolean s = isSingly();
    btnInsertP.setEnabled(!s);
    btnRemoveAtP.setEnabled(!s);
    btnPrintReverseP.setEnabled(!s);
}

private Person readPerson() {
    String name = txtName.getText().trim();
    int age = (Integer) spAge.getValue();
    if (name.isEmpty()) {
        info("กรุณากรอก Name");
        return null;
    }
    if (age < 0) {
        info("Age ต้องไม่ติดลบ");
        return null;
    }
    return new Person(name, age);
}

private void printForwardToArea() {
    taPerson.setText(isSingly() ? singly.toText() : doubly.toText());
}

private void info(String msg) {
    lblStatus.setText(msg);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpType = new javax.swing.ButtonGroup();
        spGenCount = new javax.swing.JSpinner();
        btnGenerate = new javax.swing.JButton();
        btnAddFirstRandom = new javax.swing.JButton();
        btnClearStr = new javax.swing.JButton();
        btnShowStr = new javax.swing.JButton();
        JScrollPane1 = new javax.swing.JScrollPane();
        taString = new javax.swing.JTextArea();
        JScrollPane2 = new javax.swing.JScrollPane();
        taPerson = new javax.swing.JTextArea();
        rdoSingly = new javax.swing.JRadioButton();
        rdoDoubly = new javax.swing.JRadioButton();
        txtName = new javax.swing.JTextField();
        spAge = new javax.swing.JSpinner();
        spIndex = new javax.swing.JSpinner();
        lblStatus = new javax.swing.JLabel();
        btnAddFirstP = new javax.swing.JButton();
        btnAddLastP = new javax.swing.JButton();
        btnInsertP = new javax.swing.JButton();
        btnRemoveAtP = new javax.swing.JButton();
        btnPrintForwardP = new javax.swing.JButton();
        btnPrintReverseP = new javax.swing.JButton();
        btnAverageP = new javax.swing.JButton();
        btnClearPerson = new javax.swing.JButton();
        tabs = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGenerate.setText("btnGenerate");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        btnAddFirstRandom.setText("btnAddFirstRandom");

        btnClearStr.setText("btnClearStr");

        btnShowStr.setText("btnShowStr");

        taString.setColumns(20);
        taString.setRows(5);
        JScrollPane1.setViewportView(taString);

        taPerson.setColumns(20);
        taPerson.setRows(5);
        JScrollPane2.setViewportView(taPerson);

        rdoSingly.setText("rdoSingly");

        rdoDoubly.setText("rdoDoubly");

        txtName.setText("txtName");

        lblStatus.setText("lblStatus");

        btnAddFirstP.setText("btnAddFirstP");

        btnAddLastP.setText("btnAddLastP");

        btnInsertP.setText("btnInsertP");

        btnRemoveAtP.setText("btnRemoveAtP");

        btnPrintForwardP.setText("btnPrintForwardP");

        btnPrintReverseP.setText("btnPrintReverseP");

        btnAverageP.setText("btnAverageP");

        btnClearPerson.setText("btnClearPerson");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spGenCount))
                    .addComponent(JScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClearStr)
                            .addComponent(btnGenerate)
                            .addComponent(btnShowStr)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnAddFirstRandom)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(JScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoDoubly)
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnPrintForwardP)
                                    .addComponent(btnRemoveAtP)
                                    .addComponent(btnInsertP)
                                    .addComponent(btnAddLastP)
                                    .addComponent(btnPrintReverseP)
                                    .addComponent(btnAverageP)
                                    .addComponent(btnAddFirstP)
                                    .addComponent(btnClearPerson))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdoSingly)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spGenCount, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabs, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoSingly)
                            .addComponent(btnAddFirstP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoDoubly)
                            .addComponent(btnAddLastP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInsertP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemoveAtP))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrintForwardP))
                        .addGap(18, 18, 18)
                        .addComponent(btnPrintReverseP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAverageP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClearPerson)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGenerate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddFirstRandom)
                        .addGap(18, 18, 18)
                        .addComponent(btnShowStr)
                        .addGap(18, 18, 18)
                        .addComponent(btnClearStr)
                        .addGap(131, 131, 131))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JScrollPane1)
                        .addContainerGap())
                    .addComponent(JScrollPane2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenerateActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new LinkedListForm().setVisible(true));
    }

    static class Person {
    private final String name;
    private final int age;
    public Person(String name, int age) { this.name = name; this.age = age; }
    public String getName() { return name; }
    public int getAge() { return age; }
    @Override public String toString() { return name + "-" + age; }
}

static class StringLinkedList {
    private static class Node { String data; Node next; Node(String d){data=d;} }
    private Node head;
    private int count;
    private static final java.util.Random RND = new java.util.Random();
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

    public void addFirst(String s) {
        Node n = new Node(s);
        n.next = head;
        head = n;
        count++;
    }
    public void addLast(String s) {
        Node n = new Node(s);
        if (head == null) head = n;
        else { Node p = head; while (p.next != null) p = p.next; p.next = n; }
        count++;
    }
    public void generate(int n) {
        for (int i=0;i<n;i++) addLast(randomWord(3,7));
    }
    public void addFirstRandom() { addFirst(randomWord(3,7)); }
    public void clear() { head = null; count = 0; }
    public String toText() {
        StringBuilder sb = new StringBuilder();
        Node p = head;
        while (p != null) {
            sb.append(p.data);
            if (p.next != null) sb.append(", ");
            p = p.next;
        }
        return sb.toString();
    }
    private static String randomWord(int minLen, int maxLen) {
        int len = minLen + RND.nextInt(maxLen - minLen + 1);
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<len;i++){
            char c = ALPHA.charAt(RND.nextInt(ALPHA.length()));
            sb.append(i==0 ? Character.toUpperCase(c) : c);
        }
        return sb.toString();
    }
}

static class SinglyLinkedListPerson {
    private static class Node { Person data; Node next; Node(Person d){data=d;} }
    private Node head, tail;
    private int count;

    public void addFirst(Person p) {
        Node n = new Node(p);
        if (head == null) head = tail = n;
        else { n.next = head; head = n; }
        count++;
    }
    public void addLast(Person p) {
        Node n = new Node(p);
        if (head == null) head = tail = n;
        else { tail.next = n; tail = n; }
        count++;
    }
    public double getAverageAge() {
        if (count == 0) return 0.0;
        int sum = 0;
        for (Node cur = head; cur != null; cur = cur.next) sum += cur.data.getAge();
        return sum / (double) count;
    }
    public void clear() { head = tail = null; count = 0; }
    public String toText() {
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data);
            if (cur.next != null) sb.append(", ");
        }
        return sb.toString();
    }
}

static class DoublyLinkedListPerson {
    private static class Node { Person data; Node next, prev; Node(Person d){data=d;} }
    private Node head, tail;
    private int count;

    public void addFirst(Person p) {
        Node n = new Node(p);
        if (head == null) head = tail = n;
        else { n.next = head; head.prev = n; head = n; }
        count++;
    }
    public void addLast(Person p) {
        Node n = new Node(p);
        if (tail == null) head = tail = n;
        else { tail.next = n; n.prev = tail; tail = n; }
        count++;
    }
    public void insert(int index, Person p) {
        if (index <= 0 || head == null) { addFirst(p); return; }
        if (index >= count) { addLast(p); return; }
        Node cur = head;
        for (int i=0;i<index;i++) cur = cur.next;
        Node prev = cur.prev;
        Node n = new Node(p);
        prev.next = n; n.prev = prev;
        n.next = cur; cur.prev = n;
        count++;
    }
    public boolean removeAt(int index) {
        if (index < 0 || index >= count || head == null) return false;
        if (index == 0) {
            Node del = head; head = head.next;
            if (head != null) head.prev = null; else tail = null;
            del.next = null; count--; return true;
        }
        if (index == count - 1) {
            Node del = tail; tail = tail.prev;
            if (tail != null) tail.next = null; else head = null;
            del.prev = null; count--; return true;
        }
        Node cur = head; for (int i=0;i<index;i++) cur = cur.next;
        Node prev = cur.prev, next = cur.next;
        prev.next = next; next.prev = prev;
        cur.next = cur.prev = null; count--; return true;
    }
    public double getAverageAge() {
        if (count == 0) return 0.0;
        int sum = 0;
        for (Node cur = head; cur != null; cur = cur.next) sum += cur.data.getAge();
        return sum / (double) count;
    }
    public void clear() { head = tail = null; count = 0; }
    public String toText() {
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data);
            if (cur.next != null) sb.append(", ");
        }
        return sb.toString();
    }
    public String toTextReverse() {
        StringBuilder sb = new StringBuilder();
        for (Node cur = tail; cur != null; cur = cur.prev) {
            sb.append(cur.data);
            if (cur.prev != null) sb.append(", ");
        }
        return sb.toString();
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane1;
    private javax.swing.JScrollPane JScrollPane2;
    private javax.swing.JButton btnAddFirstP;
    private javax.swing.JButton btnAddFirstRandom;
    private javax.swing.JButton btnAddLastP;
    private javax.swing.JButton btnAverageP;
    private javax.swing.JButton btnClearPerson;
    private javax.swing.JButton btnClearStr;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnInsertP;
    private javax.swing.JButton btnPrintForwardP;
    private javax.swing.JButton btnPrintReverseP;
    private javax.swing.JButton btnRemoveAtP;
    private javax.swing.JButton btnShowStr;
    private javax.swing.ButtonGroup grpType;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JRadioButton rdoDoubly;
    private javax.swing.JRadioButton rdoSingly;
    private javax.swing.JSpinner spAge;
    private javax.swing.JSpinner spGenCount;
    private javax.swing.JSpinner spIndex;
    private javax.swing.JTextArea taPerson;
    private javax.swing.JTextArea taString;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
