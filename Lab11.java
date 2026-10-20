package lab11;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Random;

class Citizen {

    private long id;
    private String name;
    private String gender;
    private int age;
    private double income;

    public Citizen(long id, String name, String gender, int age, double income) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.income = income;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getIncome() {
        return income;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + gender + ", " + age + ", " + (long) income;
    }
}

class BST {

    private DefaultMutableTreeNode root;
    private int comparisons;

    private DefaultMutableTreeNode placeholder() {
        return new DefaultMutableTreeNode(new Citizen(0, "", "", 0, 0));
    }

    private boolean isPlaceholder(DefaultMutableTreeNode n) {
        return ((Citizen) n.getUserObject()).getId() == 0;
    }

    public void addNode(DefaultMutableTreeNode newNode) {
        if (newNode == null || newNode.getUserObject() == null) {
            return;
        }
        Citizen nc = (Citizen) newNode.getUserObject();

        if (root == null) {
            root = newNode;
            return;
        }

        DefaultMutableTreeNode p = root;
        while (true) {
            Citizen cur = (Citizen) p.getUserObject();

            if (p.getChildCount() == 0) {
                if (nc.getId() < cur.getId()) {
                    p.add(newNode);
                    p.add(placeholder());
                } else {
                    p.add(placeholder());
                    p.add(newNode);
                }
                break;
            }

            int idx = (nc.getId() < cur.getId()) ? 0 : 1;
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) p.getChildAt(idx);
            if (isPlaceholder(child)) {
                child.setUserObject(nc);
                break;
            }
            p = child;
        }
    }

    public int getHeight() {
        return height(root);
    }

    private int height(DefaultMutableTreeNode n) {
        if (n == null || isPlaceholder(n)) {
            return -1;
        }
        int l = (n.getChildCount() > 0) ? height((DefaultMutableTreeNode) n.getChildAt(0)) : -1;
        int r = (n.getChildCount() > 1) ? height((DefaultMutableTreeNode) n.getChildAt(1)) : -1;
        return 1 + Math.max(l, r);
    }

    public DefaultMutableTreeNode binarySearch(long key) {
        comparisons = 0;
        DefaultMutableTreeNode p = root;
        while (p != null) {
            long cur = ((Citizen) p.getUserObject()).getId();
            if (cur == 0) {
                return null;
            }

            comparisons++;
            if (key == cur) {
                return p;
            }

            comparisons++;
            if (key < cur) {
                p = (p.getChildCount() > 0) ? (DefaultMutableTreeNode) p.getChildAt(0) : null;
            } else {
                p = (p.getChildCount() > 1) ? (DefaultMutableTreeNode) p.getChildAt(1) : null;
            }
        }
        return null;
    }

    public int getComparisons() {
        return comparisons;
    }

    public DefaultMutableTreeNode getRoot() {
        return root;
    }
}

public class Lab11 extends javax.swing.JFrame {

    private static final int N = 100_000;
    private Citizen[] citizens;
    private BST bst;

    public Lab11() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGenCitizen = new javax.swing.JButton();
        btnGenBST = new javax.swing.JButton();
        btnSeq = new javax.swing.JButton();
        btnBin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textfield1 = new javax.swing.JTextField();
        textfield2 = new javax.swing.JTextField();
        textfield3 = new javax.swing.JTextField();
        textfield4 = new javax.swing.JTextField();
        textfield5 = new javax.swing.JTextField();
        textfield6 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("670710214 Searching in BST");

        btnGenCitizen.setText("Gen Citizen");
        btnGenCitizen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenCitizenActionPerformed(evt);
            }
        });

        btnGenBST.setText("Gen BST");
        btnGenBST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenBSTActionPerformed(evt);
            }
        });

        btnSeq.setText("SequentialSearch");
        btnSeq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeqActionPerformed(evt);
            }
        });

        btnBin.setText("BinarySearch");
        btnBin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBinActionPerformed(evt);
            }
        });

        jLabel1.setText("Textfield1 (Last Citizen / ID to search):");

        jLabel2.setText("Textfield2 (BST Height):");

        jLabel3.setText("Textfield3 (Sequential result):");

        jLabel4.setText("Textfield4 (Sequential comparisons):");

        jLabel5.setText("Textfield5 (Binary result):");

        jLabel6.setText("Textfield6 (Binary comparisons):");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGenCitizen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGenBST)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSeq)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBin))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textfield1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                            .addComponent(textfield2, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                            .addComponent(textfield3, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                            .addComponent(textfield4, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                            .addComponent(textfield5, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                            .addComponent(textfield6, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenCitizen)
                    .addComponent(btnGenBST)
                    .addComponent(btnSeq)
                    .addComponent(btnBin))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textfield1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textfield2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textfield3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textfield4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textfield5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textfield6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("670710214 Searching in BST");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenCitizenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenCitizenActionPerformed
        Random rand = new Random();
        java.util.HashSet<Long> used = new java.util.HashSet<>();

        String[] names = {"Somchai", "Suda", "Anan", "Mali", "Arthit", "Preecha", "Malee", "Niran"};
        String[] genders = {"Male", "Female"};

        citizens = new Citizen[N];
        for (int i = 0; i < N; i++) {
            long id;
            do {
                id = 1_000_000_000_000L + (long) (rand.nextDouble() * 9_000_000_000_000L);
            } while (!used.add(id));

            String name = names[rand.nextInt(names.length)];
            String gender = genders[rand.nextInt(genders.length)];
            int age = 18 + rand.nextInt(63);
            double income = (1 + rand.nextInt(100)) * 10000;
            citizens[i] = new Citizen(id, name, gender, age, income);
        }

        bst = null;
        textfield1.setText(citizens[N - 1].toString());
        textfield2.setText("");
        textfield3.setText("");
        textfield4.setText("");
        textfield5.setText("");
        textfield6.setText("");
    }//GEN-LAST:event_btnGenCitizenActionPerformed

    private void btnGenBSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenBSTActionPerformed
        if (citizens == null) {
            JOptionPane.showMessageDialog(this, "Please click Gen Citizen first.");
            return;
        }
        bst = new BST();
        for (int i = 0; i < N; i++) {
            bst.addNode(new DefaultMutableTreeNode(citizens[i]));
        }
        textfield2.setText(String.valueOf(bst.getHeight()));
    }//GEN-LAST:event_btnGenBSTActionPerformed

    private void btnSeqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeqActionPerformed
        if (citizens == null) {
        JOptionPane.showMessageDialog(this, "Please click Gen Citizen first.");
        return;
    }
    long key = citizens[N - 1].getId();

    int count = 0;
    Citizen found = null;
    for (int i = 0; i < citizens.length; i++) {
        count++;
        if (citizens[i].getId() == key) {
            found = citizens[i];
            break;
        }
    }
    textfield3.setText(found != null ? found.toString() : "Not found");
    textfield4.setText(String.valueOf(count));
    }//GEN-LAST:event_btnSeqActionPerformed

    private void btnBinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBinActionPerformed
         if (bst == null) {
        JOptionPane.showMessageDialog(this, "Please click Gen BST first.");
        return;
    }
    long key = citizens[N - 1].getId();

    DefaultMutableTreeNode node = bst.binarySearch(key);
    String result = (node != null) ? node.getUserObject().toString() : "Not found";
    textfield5.setText(result);
    System.out.println("Binary Search result: " + result);
    textfield6.setText(String.valueOf(bst.getComparisons()));
    }//GEN-LAST:event_btnBinActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lab11().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBin;
    private javax.swing.JButton btnGenBST;
    private javax.swing.JButton btnGenCitizen;
    private javax.swing.JButton btnSeq;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField textfield1;
    private javax.swing.JTextField textfield2;
    private javax.swing.JTextField textfield3;
    private javax.swing.JTextField textfield4;
    private javax.swing.JTextField textfield5;
    private javax.swing.JTextField textfield6;
    // End of variables declaration//GEN-END:variables
}
